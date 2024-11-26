package org.unesp.entities;

import java.util.List;

public class Vehicle implements Runnable {
    private static int counter = 0;
    private String id;
    private Integer maxSpace;
    private List<Delivery> listOfDeliveries;

    public Vehicle(Integer maxSpace, List<Delivery> listOfDeliveries) {
        this.maxSpace = maxSpace;
        this.listOfDeliveries = listOfDeliveries;
        setId(counter);
        counter++;
    }

    public String getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = "V" + id;
    }

    public Integer getMaxSpace() {
        return maxSpace;
    }

    public void setMaxSpace(Integer maxSpace) {
        this.maxSpace = maxSpace;
    }

    public List<Delivery> getListOfDeliveries() {
        return listOfDeliveries;
    }

    public void setListOfDeliveries(List<Delivery> listOfDeliveries) {
        this.listOfDeliveries = listOfDeliveries;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", maxSpace=" + maxSpace +
                '}';
    }

    @Override
    public void run(){
        System.out.printf("=> Veículo #%s Indo fazer entregas.\n", this.id);
    }
}
