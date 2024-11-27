package org.unesp;

import org.unesp.dataStructure.CircularList;
import org.unesp.entities.Delivery;
import org.unesp.entities.Redistributor;
import org.unesp.entities.Vehicle;
import org.unesp.services.VehicleService;
import org.unesp.util.ApplicationContext;
import org.unesp.util.ParameterValidator;
import org.unesp.util.RandomGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        var parameterValidator = new ParameterValidator(args);
        var vehicleService = new VehicleService();

        List<Vehicle> vehicleList = new ArrayList<>();
        List<Delivery> deliveryList = new ArrayList<>();
        List<Redistributor> redistributorList = new ArrayList<>();

        Map<Character, Integer> argsMap = parameterValidator.getParametersMap();

        final int VEHICLE_LOAD_SPACE = argsMap.get('A');

        // Atribuindo a lista circular a classe ApplicationContext para que ela possa ser acessada facilmente
        ApplicationContext.setCircularList(new CircularList());
        ApplicationContext.setRemainingDelivery(argsMap.get('P'));

        ExecutorService executorService = Executors.newFixedThreadPool(argsMap.get('S') + argsMap.get('C') + argsMap.get('P'));

        // Gerando pontos de redistribuição e gerando lista circular
        for (int i = 0; i < argsMap.get('S'); i++) {
            Redistributor redistributor = new Redistributor(i);
            ApplicationContext.getCircularList().add(redistributor);
            redistributorList.add(redistributor);
        }

        // Gerando pacotes
        for (int i = 0; i < argsMap.get('P'); i++) {
            Redistributor origin = RandomGenerator.getRandomRedistributor();
            Redistributor destination = RandomGenerator.getRandomRedistributor();
            if (destination.equals(origin)) {
                destination = ApplicationContext.getCircularList().getNextRedistributor(origin);
            }
            Delivery delivery = new Delivery(i, origin, destination, LocalDateTime.now());
            origin.getListOfDeliveries().add(delivery);
            deliveryList.add(delivery);
        }

        // Gerando veículos
        for (int i = 0; i < argsMap.get('C'); i++) {
            Vehicle vehicle = new Vehicle(i, VEHICLE_LOAD_SPACE);
            vehicleList.add(vehicle);
        }

        // Inicializando threads de Redistributor
        for (Redistributor redistributor : redistributorList) {
            executorService.submit(redistributor);
        }

        // Inicializando threads de Delivery
        for (Delivery delivery : deliveryList) {
            executorService.submit(delivery);
        }

        // Inicializando threads de Vehicle
        for (Vehicle vehicle : vehicleList) {
            executorService.submit(vehicle);
        }

    }
}