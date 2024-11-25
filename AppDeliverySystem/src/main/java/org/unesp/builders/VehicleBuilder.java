package org.unesp.builders;

import org.unesp.entities.Vehicle;

import java.util.ArrayList;

public class VehicleBuilder{

    public VehicleBuilder(){
        Runnable vehicle = new Vehicle(1,1, new ArrayList<>());
        Thread thread = new Thread(vehicle);

        thread.start();
    }
}
