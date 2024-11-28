package org.unesp.entities;

import org.unesp.monitors.MessageMonitor;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Delivery implements Runnable {
    //private static List<File> listFiles = Collections.synchronizedList(new ArrayList<>());

    private String id;
    private Redistributor redistributorOrigin;
    private Redistributor redistributorDestination;
    private LocalDateTime originTime;
    private LocalDateTime finishTime;
    private Vehicle associatedVehicle;
    private final Semaphore deliverySemaphore;
    private File file;

    public Delivery(int id, Redistributor redistributorOrigin,
                    Redistributor redistributorDestination, LocalDateTime originTime) {
        this.redistributorOrigin = redistributorOrigin;
        this.redistributorDestination = redistributorDestination;
        this.originTime = originTime;
        this.deliverySemaphore = new Semaphore(1);
        setId(id);
        this.file = new File(getId()+".csv");
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

    public LocalDateTime getOriginTime() {
        return originTime;
    }

    public void setOriginTime(LocalDateTime originTime) {
        this.originTime = originTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public Vehicle getAssociatedVehicle() {
        return associatedVehicle;
    }

    public void setAssociatedVehicle(Vehicle associatedVehicle) {
        this.associatedVehicle = associatedVehicle;
    }

    //public static List<File> getListFiles() {
        //return listFiles;
    //}

    public File getFile() {return file;}

    public void setFile(File file) {this.file = file;}

    public Semaphore getDeliverySemaphore() {
        return deliverySemaphore;
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
        MessageMonitor messageMonitor = new MessageMonitor();
        messageMonitor.showInformation(" + Thread Pacote #"
                + this.getId()
                + " inicializado no ponto de redistribuição #"
                + this.getRedistributorDestination().getId(), this);
    }
}
