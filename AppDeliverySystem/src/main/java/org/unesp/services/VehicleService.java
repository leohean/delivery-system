package org.unesp.services;

import org.unesp.dataStructure.CircularList;
import org.unesp.entities.Redistributor;
import org.unesp.entities.Vehicle;
import org.unesp.util.ApplicationContext;
import org.unesp.util.RandomGenerator;

public class VehicleService {

    public void initializeVehicleToRandomRedistributor(Vehicle vehicle) {
        Redistributor randomRedistributor = RandomGenerator.getRandomRedistributor(ApplicationContext.getCircularList());

        if (randomRedistributor != null && randomRedistributor.getCurrentVehicle() == null) {
            associateVehicleToRedistributor(vehicle, randomRedistributor);
        }
    }

    public void associateVehicleToRedistributor(Vehicle vehicle, Redistributor redistributor) {
        while (true) {
            if (redistributor.getCurrentVehicle() == null) {
                vehicle.setCurrentRedistributor(redistributor);
                redistributor.setCurrentVehicle(vehicle);
                vehicle.setNextRedistributor(ApplicationContext.getCircularList().getNextRedistributor(redistributor));
                System.out.printf("Veículo #%s começou a carregar no ponto de redistribuição #%s.\n", vehicle.getId(), redistributor.getId());
            } else {
                goToNextRedistributor(vehicle, redistributor);
            }
        }
    }

    public void goToNextRedistributor(Vehicle vehicle, Redistributor redistributor) {
        System.out.printf("Ponto de redistribuição #%s está ocupado. Veículo #%s está se dirigindo para o próximo ponto.\n", redistributor.getId(), vehicle.getId());
        vehicle.setCurrentRedistributor(null);
        vehicle.setNextRedistributor(ApplicationContext.getCircularList().getNextRedistributor(redistributor));

        int travelTime = RandomGenerator.generateRandomTravelTime();

        try {
            Thread.sleep(travelTime);
            System.out.printf("Veículo #%s chegou ao ponto de redistribuição #%s. (Tempo de viagem: %.2f segundos)\n", vehicle.getId(), redistributor.getId(), (double) travelTime / 1000);
            associateVehicleToRedistributor(vehicle, redistributor);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
