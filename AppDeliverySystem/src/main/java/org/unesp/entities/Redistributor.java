package org.unesp.entities;

import java.util.List;

public class Redistributor implements Runnable{
    private String id;
    private List<Delivery> listOfDeliveries;
    private Vehicle currentVehicle;

    public Redistributor(int id) {
        setId(id);
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
                "id='" + id + '\'' +
                ", currentVehicle=" + currentVehicle +
                '}';
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.printf("Ponto de redistribuição %s inicializado.\n", this.id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
