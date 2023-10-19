package com.mcancankaya.entities.concretes;

import com.mcancankaya.entities.abstracts.AbstractVehicle;
import com.mcancankaya.enums.FuelType;
import com.mcancankaya.enums.VehicleDimension;
import com.mcancankaya.entities.interfaces.IFuel;

public class Car extends AbstractVehicle implements IFuel {

    private FuelType fuelType;
    private Integer fuelTankVolume;

    public FuelType getFuelType() {
        return fuelType;
    }

    public Integer getFuelTankVolume() {

        return fuelTankVolume;
    }

    @Override
    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public void setFuelTankVolume(Integer volume) {
        this.fuelTankVolume = volume;
    }

    @Override
    protected void assignVehicleDimension() {
        setDimension(VehicleDimension.NORMAL);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nFuel Type : ").append(this.getFuelType());
        sb.append("\nFuel Tank Volume : ").append(this.getFuelTankVolume());

        return sb.toString();
    }
}
