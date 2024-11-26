package org.unesp.entities;

import java.util.List;

public class Redistributor implements Runnable{
    private static int counter = 0;
    private String id;
    private List<Delivery> listOfDeliveries;
    private Vehicle currentVehicle;

    public Redistributor(List<Delivery> listOfDeliveries, Vehicle currentVehicle) {
        this.listOfDeliveries = listOfDeliveries;
        this.currentVehicle = currentVehicle;
        setId(counter);
        counter++;
    }

    public String getId() {
        return id;
    }

    public void setId(int id) {
        this.id = "R" + id;
    }

    public List<Delivery> getListOfDeliveries() {
        return listOfDeliveries;
    }

    public void setListOfDeliveries(List<Delivery> listOfDeliveries) {
        this.listOfDeliveries = listOfDeliveries;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentVehicle(Vehicle currentVehicle) {
        this.currentVehicle = currentVehicle;
    }

    @Override
    public String toString() {
        return "Redistributor{" +
                "id=" + id +
                '}';
    }

    @Override
    public void run() {
    }
}
