package com.mcancankaya;

import com.mcancankaya.entities.abstracts.AbstractVehicle;
import com.mcancankaya.entities.concretes.*;
import com.mcancankaya.enums.*;
import com.mcancankaya.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class VehicleFabric2 {
    private static final Scanner scanner = new Scanner(System.in);
    private List<Car> cars;
    private List<Motorcycle> motorcycles;
    private List<Bicycle> bicycles;
    private List<Van> vans;
    private List<Sale> sales;
    private List<ReturnSale> returnSales;
    private List<SaleDetail> saleDetails;
    private final VehicleRepository vehicleRepository = new VehicleRepository();
    private final CarRepository carRepository = new CarRepository();
    private final BicycleRepository bicycleRepository = new BicycleRepository();
    private final VanRepository vanRepository = new VanRepository();
    private final MotorcycleRepository motorcycleRepository = new MotorcycleRepository();
    private final SaleRepository saleRepository = new SaleRepository();
    private final ReturnSaleRepository returnSaleRepository = new ReturnSaleRepository();


    public VehicleFabric2() {
        this.cars = carRepository.getAll();
        this.motorcycles = motorcycleRepository.getAll();
        this.bicycles = bicycleRepository.getAll();
        this.vans = vanRepository.getAll();
        this.sales = saleRepository.getSales();
        this.returnSales = returnSaleRepository.getAll();
        this.saleDetails = saleRepository.getSaleDetails();
    }

    public void updateLists() {
        this.cars = carRepository.getAll();
        this.motorcycles = motorcycleRepository.getAll();
        this.bicycles = bicycleRepository.getAll();
        this.vans = vanRepository.getAll();
        this.saleDetails = saleRepository.getSaleDetails();
    }

    public void produceCar(int count, String model, int year) {
        Car car = new Car();
        car.setFuelTankVolume(30);
        car.setColour(Colour.BLACK);
        car.setEngineType(EngineType.HAS_ENGINE);
        car.setGearType(GearType.MANUEL);
        car.setModel(model);
        car.setYear(year);
        car.setTireCount(TireCount.FOUR_TIRE);
        car.setFuelType(FuelType.DIESEL);
        car.setDimension(VehicleDimension.NORMAL);

        car.setStockAmount(count);
        carRepository.add(car);
    }

    public void produceMotorcycle(int count, String model, int year) {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setFuelTankVolume(10);
        motorcycle.setColour(Colour.RED);
        motorcycle.setEngineType(EngineType.HAS_ENGINE);
        motorcycle.setGearType(GearType.MANUEL);
        motorcycle.setTireCount(TireCount.THREE_TIRE);
        motorcycle.setFuelType(FuelType.ELECTRIC);
        motorcycle.setDimension(VehicleDimension.NORMAL);

        motorcycle.setModel(model);
        motorcycle.setYear(year);
        motorcycle.setStockAmount(count);

        motorcycleRepository.add(motorcycle);
    }

    public void produceVans(int count, String model, int year) {
        Van van = new Van();
        van.setFuelTankVolume(80);
        van.setColour(Colour.WHITE);
        van.setEngineType(EngineType.HAS_ENGINE);
        van.setGearType(GearType.OTOMATIC);
        van.setTireCount(TireCount.EIGHT_TIRE);
        van.setFuelType(FuelType.GASOLINE);
        van.setDimension(VehicleDimension.LONG);

        van.setModel(model);
        van.setYear(year);
        van.setStockAmount(count);
        vanRepository.add(van);
    }

    public void produceBicycle(int count, String model, int year) {

        Bicycle bicycle = new Bicycle();
        bicycle.setColour(Colour.GREEN);
        bicycle.setEngineType(EngineType.NO_ENGINE);
        bicycle.setGearType(GearType.MANUEL);
        bicycle.setTireCount(TireCount.TWO_TIRE);
        bicycle.setDimension(VehicleDimension.SHORT);

        bicycle.setModel(model);
        bicycle.setYear(year);
        bicycle.setStockAmount(count);

        bicycleRepository.add(bicycle);

    }

    public void sellVehicle(int vehicleId, int quantity) {
        Sale sale = new Sale(LocalDate.now(), vehicleId, quantity, SaleStatus.SUCCESS);
        saleRepository.add(sale);
        updateLists();
    }

    public void returnSaleVehicle(int saleId) {
        ReturnSale returnSale = new ReturnSale();
        returnSale.setSaleId(saleId);
        returnSale.setReturnDate(LocalDate.now());

        returnSaleRepository.add(returnSale);
    }

    public void updateStock(int id, int newStockAmount) {
        vehicleRepository.updateStockAmount(id, newStockAmount);
    }

    public void printCars() {

        for (Car car : cars) {
            printInfo(car);
        }

    }

    public void printBicycles() {
        for (Bicycle bicycle : bicycles)
            printInfo(bicycle);
    }

    public void printVans() {
        for (Van van : vans)
            printInfo(van);
    }


    public void printMotorcycles() {
        for (Motorcycle motorcycle : motorcycles)
            printInfo(motorcycle);
    }

    public void printAll() {
        printCars();
        printMotorcycles();
        printVans();
        printBicycles();
    }

    public void printSaleDetails() {
        for (SaleDetail saleDetail : saleDetails)
            System.out.println("----------------------------------------\n" + saleDetail);

    }

    public void printReturnSaleDetails() {
        var list = saleDetails.stream().filter(s -> s.getStatus().equals(SaleStatus.RETURNED_SALES.name())).toList();
        for (SaleDetail saleDetail : list)
            System.out.println("----------------------------------------\n" + saleDetail);

    }

    public void printInfo(AbstractVehicle vehicle) {
        System.out.println("----------------------------------------");
        StringBuilder b = new StringBuilder();
        b.append("Vehicle Id : ").append(vehicle.getId());
        b.append("\nModel : ").append(vehicle.getModel());
        b.append("\nYear : ").append(vehicle.getYear());
        b.append("\nColor : ").append(vehicle.getColour());
        b.append("\nStock Amount : ").append(vehicle.getStockAmount());

        System.out.println(b);

    }
}
