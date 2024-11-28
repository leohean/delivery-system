package org.unesp.services;

import org.unesp.entities.Delivery;
import org.unesp.entities.Redistributor;
import org.unesp.entities.Vehicle;
import org.unesp.monitors.MessageMonitor;
import org.unesp.util.ApplicationContext;
import org.unesp.util.RandomGenerator;

import java.util.List;
import java.util.concurrent.Semaphore;

public class VehicleService {
    private final RedistributorService redistributorService = new RedistributorService();
    private final MessageMonitor messageMonitor = new MessageMonitor();

    public void initializeVehicleToRandomRedistributor(Vehicle vehicle) {
        Redistributor randomRedistributor = RandomGenerator.getRandomRedistributor();
        if (randomRedistributor.getCurrentVehicle() == null) {
            associateVehicleToRedistributor(vehicle, randomRedistributor);
        }
    }

    public void associateVehicleToRedistributor(Vehicle vehicle, Redistributor redistributor) {
        while (ApplicationContext.getRemainingDelivery() > 0) {
            if (redistributor.getCurrentVehicle() == null && (vehicleHasDeliveriesToUnload(vehicle) || !redistributor.getListOfDeliveries().isEmpty())) {
                vehicle.setCurrentRedistributor(redistributor);
                redistributor.setCurrentVehicle(vehicle);
                System.out.printf("Veículo #%s começou a carregar no ponto de redistribuição #%s.\n", vehicle.getId(), redistributor.getId());
                loadDeliveries(vehicle);
            } else {
                System.out.printf("Veículo #%s não possui encomendas para descarregar ou ponto #%s não possui encomendas. " +
                        "Se dirigindo para o próximo ponto.\n", vehicle.getId(), redistributor.getId());
                goToNextRedistributor(vehicle);
            }
        }
    }

    public void goToNextRedistributor(Vehicle vehicle) {
        Redistributor nextRedistributor = ApplicationContext.getCircularList().getNextRedistributor(vehicle.getCurrentRedistributor());
        vehicle.getCurrentRedistributor().setCurrentVehicle(null);
        vehicle.setCurrentRedistributor(null);

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

    private void simulateTravel(Vehicle vehicle) {
        int travelTime = RandomGenerator.generateRandomTravelTime();
        try {
            Thread.sleep(travelTime);
            System.out.printf("Veículo #%s chegou ao próximo ponto de redistribuição. (Tempo de viagem: %.2f segundos) - Aguardando Liberação\n",
                    vehicle.getId(), (double) travelTime / 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadDeliveries(Vehicle vehicle) {
        if (vehicle.getUsedSpace() < vehicle.getMaxSpace()
                && redistributorService.getRemainingDeliveriesNumber(vehicle.getCurrentRedistributor()) != 0) {
            System.out.printf("-> #%s carregando encomendas no ponto de redistribuição #%s\n",
                    vehicle.getId(), vehicle.getCurrentRedistributor().getId());
            Redistributor currentRedistributor = vehicle.getCurrentRedistributor();

            try {
                currentRedistributor.getRedistributorsSemaphore().acquire();
                for (Delivery delivery : currentRedistributor.getListOfDeliveries()) {
                    if (!currentRedistributor.getListOfDeliveries().isEmpty()) {
                        delivery.getDeliverySemaphore().acquire();
                        vehicle.getListOfDeliveries().add(delivery);
                        currentRedistributor.getListOfDeliveries().remove(delivery);
                        vehicle.increaseUsedSpaceByOne();
                        int loadTime = RandomGenerator.generateRandomLoadTime();
                        Thread.sleep(loadTime);
                        messageMonitor.showInformation("=> Encomenda " + delivery.getId()
                                + " foi carregada no veículo " + vehicle.getId()
                                + " (Tempo para carregar: " + String.format("%.2f", (double) loadTime / 1000)
                                + " segundos.\n", delivery);
                        delivery.getDeliverySemaphore().release();
                    }
                    goToNextRedistributor(vehicle);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                currentRedistributor.getRedistributorsSemaphore().release();
            }
        }
    }

    private void unloadDeliveries(Vehicle vehicle) {
        System.out.printf("-> #%s descarregando encomendas no ponto de redistribuição #%s\n", vehicle.getId(), vehicle.getCurrentRedistributor().getId());
        var currentRedistributor = vehicle.getCurrentRedistributor();
        try {
            if (vehicleHasDeliveriesToUnload(vehicle)) {
                currentRedistributor.getRedistributorsSemaphore().acquire();
                for (Delivery delivery : filterDeliveriesToLoad(vehicle)) {
                    delivery.getDeliverySemaphore().acquire();
                    vehicle.getListOfDeliveries().remove(delivery);
                    vehicle.decreaseUsedSpaceByOne();
                    ApplicationContext.decreaseRemainingDelivery();

                    int unloadTime = RandomGenerator.generateRandomLoadTime();
                    Thread.sleep(unloadTime);
                    delivery.getDeliverySemaphore().release();
                    messageMonitor.showInformation("=> Encomenda " + delivery.getId()
                            + " foi descarregada pelo veículo " + vehicle.getId()
                            + " no centro de redistribuição " + currentRedistributor.getId()
                            + " (Tempo para descarregar: " + String.format("%.2f", (double) unloadTime / 1000)
                            + " segundos.\n", delivery);
                    messageMonitor.showInformation("==> Entrega de #"
                            + delivery.getId() + " finalizada com sucesso.\n", delivery);
                }
            }
            loadDeliveries(vehicle);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            currentRedistributor.getRedistributorsSemaphore().release();
        }
    }

    private List<Delivery> filterDeliveriesToLoad(Vehicle vehicle) {
        var currentRedistributor = vehicle.getCurrentRedistributor();
        return vehicle.getListOfDeliveries().stream()
                .filter(d -> d.getRedistributorDestination().equals(currentRedistributor)).toList();
    }
}
