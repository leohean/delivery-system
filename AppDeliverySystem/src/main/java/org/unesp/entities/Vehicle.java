package org.unesp.entities;

import org.unesp.services.VehicleService;
import org.unesp.util.ApplicationContext;

import java.util.List;

public class Vehicle implements Runnable {
    private String id;
    private Integer maxSpace;
    private Integer usedSpace;
    private List<Delivery> listOfDeliveries;
    private Redistributor currentRedistributor;

    public Vehicle(int id, int maxSpace) {
        this.maxSpace = maxSpace;
        this.usedSpace = 0;
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

    public Integer getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(Integer usedSpace) {
        this.usedSpace = usedSpace;
    }

    public void increaseUsedSpaceByOne() {
        this.usedSpace += 1;
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
    public void run() {
        System.out.printf(" + Thread Veículo #%s inicializado.\n", this.getId());
        VehicleService vehicleService = new VehicleService();
        vehicleService.initializeVehicleToRandomRedistributor(this);

        while (ApplicationContext.getRemainingDelivery() > 0) {

        }
    }
}
