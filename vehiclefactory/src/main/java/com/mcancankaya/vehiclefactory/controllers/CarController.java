package com.mcancankaya.vehiclefactory.controllers;

import com.mcancankaya.vehiclefactory.entities.Car;
import com.mcancankaya.vehiclefactory.services.CarService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }
}
