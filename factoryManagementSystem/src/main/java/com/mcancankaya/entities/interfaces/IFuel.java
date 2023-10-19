package com.mcancankaya.entities.interfaces;

import com.mcancankaya.enums.FuelType;

public interface IFuel {
    void setFuelType(FuelType fuelType);

    void setFuelTankVolume(Integer volume);
}
