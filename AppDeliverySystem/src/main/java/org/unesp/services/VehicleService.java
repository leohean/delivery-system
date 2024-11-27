package org.unesp.services;

import org.unesp.entities.Redistributor;
import org.unesp.entities.Vehicle;
import org.unesp.util.ApplicationContext;
import org.unesp.util.RandomGenerator;

import java.util.concurrent.Semaphore;

public class VehicleService {
    public void initializeVehicleToRandomRedistributor(Vehicle vehicle) {
        Redistributor randomRedistributor = RandomGenerator.getRandomRedistributor();
        if (randomRedistributor.getCurrentVehicle() == null) {
            associateVehicleToRedistributor(vehicle, randomRedistributor);
        }
    }

    public void associateVehicleToRedistributor(Vehicle vehicle, Redistributor redistributor) {
        try {
            redistributor.getRedistributorsSemaphore().acquire();
            while (ApplicationContext.getRemainingDelivery() > 0) { // Substituir pelo número de pacotes que faltam ser entregues
                if (redistributor.getCurrentVehicle() == null && (vehicleHasDeliveriesToUnload(vehicle) || !redistributor.getListOfDeliveries().isEmpty())) {
                    vehicle.setCurrentRedistributor(redistributor);
                    redistributor.setCurrentVehicle(vehicle);
                    System.out.printf("Veículo #%s começou a carregar no ponto de redistribuição #%s.\n", vehicle.getId(), redistributor.getId());
                } else {
                    System.out.printf("Veículo #%s não possui encomendas para descarregar ou ponto #%s não possui encomendas. " +
                            "Se dirigindo para o próximo ponto.\n", vehicle.getId(), redistributor.getId());
                    goToNextRedistributor(vehicle);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            redistributor.getRedistributorsSemaphore().release();
        }
    }

    public void goToNextRedistributor(Vehicle vehicle) throws InterruptedException {
        Redistributor nextRedistributor = ApplicationContext.getCircularList().getNextRedistributor(vehicle.getCurrentRedistributor());
        vehicle.getCurrentRedistributor().setCurrentVehicle(null);
        vehicle.setCurrentRedistributor(null);
        vehicle.getCurrentRedistributor().getRedistributorsSemaphore().release();

        simulateTravel(vehicle);

        associateVehicleToRedistributor(vehicle, nextRedistributor);
    }

    private boolean vehicleHasDeliveriesToUnload(Vehicle vehicle) {
        if (vehicle.getCurrentRedistributor() == null) {
            return false;
        }

        Redistributor currentRedistributorPoint = vehicle.getCurrentRedistributor();
        return vehicle.getListOfDeliveries().stream().anyMatch(d ->
                d.getRedistributorDestination().equals(currentRedistributorPoint));
    }

    private void simulateTravel(Vehicle vehicle) throws InterruptedException {
        int travelTime = RandomGenerator.generateRandomTravelTime();
        Thread.sleep(travelTime);
        System.out.printf("Veículo #%s chegou ao próximo ponto de redistribuição. (Tempo de viagem: %.2f segundos) - Aguardando Liberação\n",
                vehicle.getId(), (double) travelTime / 1000);
    }

    private void loadDeliveries(Vehicle vehicle) {
        System.out.printf("-> #%s carregando encomendas no ponto de redistribuição #%s", vehicle.getId(), vehicle.getCurrentRedistributor().getId());
    }

    private void unloadDeliveries(Vehicle vehicle) {
        System.out.printf("-> #%s descarregando encomendas no ponto de redistribuição #%s", vehicle.getId(), vehicle.getCurrentRedistributor().getId());
    }
}
