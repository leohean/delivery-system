package org.unesp.entities;

import org.unesp.util.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Redistributor implements Runnable{
    private String id;
    private List<Delivery> listOfDeliveries;
    private Vehicle currentVehicle;
    private final Semaphore redistributorsSemaphore;

    public Redistributor(int id) {
        setId(id);
        listOfDeliveries = Collections.synchronizedList(new ArrayList<>());

        redistributorsSemaphore = new Semaphore(1);
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

    public Semaphore getRedistributorsSemaphore() {
        return redistributorsSemaphore;
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
