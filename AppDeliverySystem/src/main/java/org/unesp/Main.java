package org.unesp;

import org.unesp.dataStructure.CircularList;
import org.unesp.entities.Redistributor;
import org.unesp.entities.Vehicle;
import org.unesp.util.ParameterValidator;
import org.unesp.util.RandomGenerator;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        var parameterValidator = new ParameterValidator(args);
        Map<Character, Integer> argsMap = parameterValidator.getParametersMap();

        final int VEHICLE_LOAD_SPACE = argsMap.get('A');

        CircularList circularList = new CircularList();

        ExecutorService executorService = Executors.newFixedThreadPool(argsMap.get('S') + argsMap.get('C') + argsMap.get('P'));

        // Gerando pontos de redistribuição e gerando lista circular
        for (int i = 0; i < argsMap.get('S'); i++) {
            Redistributor redistributor = new Redistributor(i);
            circularList.add(redistributor);
            executorService.submit(redistributor);
        }

        circularList.display();

        // Gerando veículos
        for (int i = 0; i < argsMap.get('C'); i++) {
            Vehicle vehicle = new Vehicle(i, VEHICLE_LOAD_SPACE);
            vehicle.setCurrentRedistributor(RandomGenerator.getRandomRedistributor(circularList));
            executorService.submit(vehicle);
        }






        /*
        VehicleBuilder vehicleBuilder = new VehicleBuilder(5);

        CircularList cl = new CircularList();

        Vehicle v1 = new Vehicle(10, null);
        Vehicle v2 = new Vehicle(10, null);
        Vehicle v3 = new Vehicle(10, null);
        //Adds data to the list
        Redistributor r1 = new Redistributor(null, v1);
        Redistributor r2 = new Redistributor(null, v2);
        Redistributor r3 = new Redistributor(null, v3);

        cl.add(r1);
        cl.add(r2);
        cl.add(r3);
        //Displays all the nodes present in the list
        cl.display();

        System.out.println(cl.getNextRedistributor(r3));
        */
    }
}