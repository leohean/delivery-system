package org.unesp.builders;

import org.unesp.entities.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class VehicleBuilder{

    public VehicleBuilder(int numVehicles){

        List<Runnable> listVehicles = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threadsVehicle = new ArrayList<>();

        for(int i = 0; i < numVehicles; i++){
            int maxSpace = ThreadLocalRandom.current().nextInt(10, 51);

            Runnable vehicle = new Vehicle(i,i, new ArrayList<>());
            listVehicles.add(vehicle);

            threadsVehicle.add(new Thread(listVehicles.get(i)));
            threadsVehicle.get(i).start();
        }

    }
}
