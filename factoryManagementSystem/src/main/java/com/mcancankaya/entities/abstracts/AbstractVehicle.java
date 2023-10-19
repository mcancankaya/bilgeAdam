package com.mcancankaya.entities.abstracts;

import com.mcancankaya.enums.*;
import com.mcancankaya.entities.interfaces.IVehicle;


public abstract class AbstractVehicle implements IVehicle {

    protected abstract void assignVehicleDimension();

    /**
     * Vehicle: id, , , , , ,
     * Car : vehicle_id, fueltype, volume
     * Select * from vehicle inner join car on vehicle.id = car.id
     * Insert into
     */
    private int id;
    private EngineType engineType;
    private Integer year;
    private TireCount tireCount;
    private String model;
    private Colour colour;
    private GearType gearType;
    private VehicleDimension dimension;
    private int stockAmount;
    @Override
    public void assignAttribute(EngineType engineType, Integer year, TireCount tireCount, String model, Colour colour, GearType gearType) {
        setEngineType(engineType);
        setColour(colour);
        setModel(model);
        setGearType(gearType);
        setTireCount(tireCount);
        setYear(year);
    }

    @Override
    public void run() {
        System.out.println("Vehicle is starting...");
    }

    @Override
    public void stop() {
        System.out.println("Vehicle is stopping...");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setTireCount(TireCount tireCount) {
        this.tireCount = tireCount;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public Integer getYear() {
        return year;
    }

    public TireCount getTireCount() {
        return tireCount;
    }

    public String getModel() {
        return model;
    }

    public Colour getColour() {
        return colour;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setDimension(VehicleDimension dimension) {
        this.dimension = dimension;
    }

    public VehicleDimension getDimension() {
        return dimension;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
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
        sb.append("\nStock Amount : ").append(this.getStockAmount());
        return sb.toString();
    }
}
