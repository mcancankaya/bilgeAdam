package com.mcancankaya.entities.concretes;

import com.mcancankaya.enums.SaleStatus;

import java.time.LocalDate;

public class Sale {
    private int id;
    private LocalDate saleDate;
    private int vehicle_id;
    private int quantity;
    private SaleStatus status;

    public Sale(LocalDate saleDate, int vehicle_id, int quantity, SaleStatus status) {
        this.saleDate = saleDate;
        this.vehicle_id = vehicle_id;
        this.quantity = quantity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }
}
