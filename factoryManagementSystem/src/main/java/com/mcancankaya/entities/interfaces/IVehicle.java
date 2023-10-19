package com.mcancankaya.entities.interfaces;

import com.mcancankaya.enums.Colour;
import com.mcancankaya.enums.EngineType;
import com.mcancankaya.enums.GearType;
import com.mcancankaya.enums.TireCount;

public interface IVehicle {

    void assignAttribute(EngineType engineType, Integer year, TireCount tireCount, String model, Colour colour, GearType gearType);

    void run();

    void stop();
}
