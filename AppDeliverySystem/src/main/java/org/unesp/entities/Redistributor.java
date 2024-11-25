package org.unesp.entities;

import java.util.List;

public class Redistributor implements Runnable{
    private List<Delivery> listOfDeliveries;
    private Vehicle currentVehicle;

    public Redistributor(List<Delivery> listOfDeliveries, Vehicle currentVehicle) {
        this.listOfDeliveries = listOfDeliveries;
        this.currentVehicle = currentVehicle;
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
    public void run() {
    }
}
