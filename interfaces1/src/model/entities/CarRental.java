package model.entities;

import java.time.LocalDateTime;

public class CarRental {
    private Vehicle vehicle;
    private Invoice invoice;

    private LocalDateTime start;
    private LocalDateTime finish;

    public CarRental() {}

    public CarRental(Vehicle vehicle, LocalDateTime start, LocalDateTime finish) {
        this.vehicle = vehicle;
        this.start = start;
        this.finish = finish;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public LocalDateTime getStatr() {
        return start;
    }

    public void setStar(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }
}

