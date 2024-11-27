package org.unesp;

import org.unesp.dataStructure.CircularList;
import org.unesp.entities.Redistributor;
import org.unesp.entities.Vehicle;
import org.unesp.services.VehicleService;
import org.unesp.util.ApplicationContext;
import org.unesp.util.ParameterValidator;
import org.unesp.util.RandomGenerator;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        var parameterValidator = new ParameterValidator(args);
        var vehicleService = new VehicleService();

        Map<Character, Integer> argsMap = parameterValidator.getParametersMap();

        final int VEHICLE_LOAD_SPACE = argsMap.get('A');

        // Atribuindo a lista circular a classe ApplicationContext para que ela possa ser acessada facilmente
        ApplicationContext.setCircularList(new CircularList());

        ExecutorService executorService = Executors.newFixedThreadPool(argsMap.get('S') + argsMap.get('C') + argsMap.get('P'));

        // Gerando pontos de redistribuição e gerando lista circular
        for (int i = 0; i < argsMap.get('S'); i++) {
            Redistributor redistributor = new Redistributor(i);
            ApplicationContext.getCircularList().add(redistributor);
            executorService.submit(redistributor);
        }

        // Gerando veículos
        for (int i = 0; i < argsMap.get('C'); i++) {
            Vehicle vehicle = new Vehicle(i, VEHICLE_LOAD_SPACE);
            executorService.submit(vehicle);
        }

    }
}