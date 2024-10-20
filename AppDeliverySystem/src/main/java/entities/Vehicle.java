package entities;

import java.util.List;

public class Vehicle {
    private Integer id;
    private Integer maxSpace;
    private List<Delivery> listOfDeliveries;

    public Vehicle(Integer id, Integer maxSpace, List<Delivery> listOfDeliveries) {
        this.id = id;
        this.maxSpace = maxSpace;
        this.listOfDeliveries = listOfDeliveries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
