package org.unesp;

import org.unesp.builders.VehicleBuilder;
import org.unesp.dataStructure.CircularList;
import org.unesp.entities.Delivery;
import org.unesp.entities.Redistributor;
import org.unesp.entities.Vehicle;

public class Main {
    public static void main(String[] args) {
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
    }
}