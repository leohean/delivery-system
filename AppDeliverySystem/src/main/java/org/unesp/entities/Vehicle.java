package org.unesp.entities;

import org.unesp.services.VehicleService;

import java.util.List;

public class Vehicle implements Runnable {
    private String id;
    private Integer maxSpace;
    private List<Delivery> listOfDeliveries;
    private Redistributor currentRedistributor;
    private Redistributor nextRedistributor;

    public Vehicle(int id, int maxSpace) {
        this.maxSpace = maxSpace;
        setId(id);
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

    public Redistributor getCurrentRedistributor() {
        return currentRedistributor;
    }

    public void setCurrentRedistributor(Redistributor currentRedistributor) {
        this.currentRedistributor = currentRedistributor;
    }

    public Redistributor getNextRedistributor() {
        return nextRedistributor;
    }

    public void setNextRedistributor(Redistributor nextRedistributor) {
        this.nextRedistributor = nextRedistributor;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", maxSpace=" + maxSpace +
                ", currentRedistributor=" + currentRedistributor +
                '}';
    }

    @Override
    public void run(){
        VehicleService vehicleService = new VehicleService();
        vehicleService.initializeVehicleToRandomRedistributor(this);
    }
}
