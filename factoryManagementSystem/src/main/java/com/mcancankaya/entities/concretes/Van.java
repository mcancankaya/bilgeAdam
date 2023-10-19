package com.mcancankaya.entities.concretes;

import com.mcancankaya.entities.abstracts.AbstractVehicle;
import com.mcancankaya.enums.FuelType;
import com.mcancankaya.enums.VehicleDimension;
import com.mcancankaya.entities.interfaces.IFuel;

public class Van extends AbstractVehicle implements IFuel {

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
        setDimension(VehicleDimension.LONG);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ID : ").append(this.getId());
        sb.append("\nEngine Type : ").append(this.getEngineType());
        sb.append("\nYear : ").append(this.getYear());
        sb.append("\nTire Count : ").append(this.getTireCount());
        sb.append("\nModel : ").append(this.getModel());
        sb.append("\nColour : ").append(this.getColour());
        sb.append("\nGear Type : ").append(this.getGearType());
        sb.append("\nDimension : ").append(this.getDimension());
        sb.append("\nFuel Type : ").append(this.getFuelType());
        sb.append("\nFuel Tank Volume : ").append(this.getFuelTankVolume());
        return sb.toString();
    }
}
