package entities;

import java.time.LocalDate;

public class Delivery {
    private Integer id;
    private Redistributor redistributorOrigin;
    private Redistributor redistributorDestination;
    private LocalDate originTime;
    private LocalDate loadTime;
    private LocalDate finishTime;
    private Vehicle associatedVehicle;

    public Delivery(Integer id, Redistributor redistributorOrigin, Redistributor redistributorDestination, LocalDate originTime, LocalDate loadTime, LocalDate finishTime, Vehicle associatedVehicle) {
        this.id = id;
        this.redistributorOrigin = redistributorOrigin;
        this.redistributorDestination = redistributorDestination;
        this.originTime = originTime;
        this.loadTime = loadTime;
        this.finishTime = finishTime;
        this.associatedVehicle = associatedVehicle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(LocalDate loadTime) {
        this.loadTime = loadTime;
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
}
