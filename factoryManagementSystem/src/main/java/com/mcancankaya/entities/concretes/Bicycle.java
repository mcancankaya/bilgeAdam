package com.mcancankaya.entities.concretes;

import com.mcancankaya.entities.abstracts.AbstractVehicle;

public class Bicycle extends AbstractVehicle {

    @Override
    protected void assignVehicleDimension() {
        System.out.println("Empty Method...");
    }

    @Override
    public void run() {
        System.out.println("This method not use in Bicycle...");
    }

    @Override
    public void stop() {
        System.out.println("This method not use in Bicycle...");
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
        return sb.toString();
    }
}
