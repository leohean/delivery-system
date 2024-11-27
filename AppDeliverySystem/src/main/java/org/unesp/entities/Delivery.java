package org.unesp.entities;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Delivery implements Runnable {
    private static List<File> listFiles = Collections.synchronizedList(new ArrayList<>());

    private String id;
    private Redistributor redistributorOrigin;
    private Redistributor redistributorDestination;
    private LocalDate originTime;
    private LocalDate finishTime;
    private Vehicle associatedVehicle;

    public Delivery(int id, Redistributor redistributorOrigin,
                    Redistributor redistributorDestination, LocalDate originTime) {
        this.redistributorOrigin = redistributorOrigin;
        this.redistributorDestination = redistributorDestination;
        this.originTime = originTime;
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = "D" + id;
    }

    public Redistributor getRedistributorOrigin() {
        return redistributorOrigin;
    }

    public void setRedistributorOrigin(Redistributor redistributorOrigin) {
        this.redistributorOrigin = redistributorOrigin;
    }

    public Redistributor getRedistributorDestination() {
        return redistributorDestination;
    }

    public void setRedistributorDestination(Redistributor redistributorDestination) {
        this.redistributorDestination = redistributorDestination;
    }

    public LocalDate getOriginTime() {
        return originTime;
    }

    public void setOriginTime(LocalDate originTime) {
        this.originTime = originTime;
    }

    public LocalDate getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDate finishTime) {
        this.finishTime = finishTime;
    }

    public Vehicle getAssociatedVehicle() {
        return associatedVehicle;
    }

    public void setAssociatedVehicle(Vehicle associatedVehicle) {
        this.associatedVehicle = associatedVehicle;
    }

    public static List<File> getListFiles() {
        return listFiles;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id='" + id + '\'' +
                ", redistributorOrigin=" + redistributorOrigin +
                ", redistributorDestination=" + redistributorDestination +
                ", originTime=" + originTime +
                ", finishTime=" + finishTime +
                '}';
    }

    @Override
    public void run() {

    }
}
