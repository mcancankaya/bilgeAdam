package com.mcancankaya.entities.concretes;

import java.time.LocalDate;

public class SaleDetail {
    private int saleId;
    private int vehicleId;
    private String vehicleModel;
    private int vehicleYear;
    private LocalDate salesDate;
    private int quantity;
    private String status;

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public LocalDate getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(LocalDate salesDate) {
        this.salesDate = salesDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("saleId : ").append(saleId);
        sb.append("\nVehicle Model : ").append(vehicleModel);
        sb.append("\nVehicleYear : ").append(vehicleYear);
        sb.append("\nSalesDate : ").append(salesDate);
        sb.append("\nquantity : ").append(quantity);
        sb.append("\nStatus : ").append(status);
        return sb.toString();
    }
}
