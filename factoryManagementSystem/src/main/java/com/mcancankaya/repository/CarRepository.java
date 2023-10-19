package com.mcancankaya.repository;

import com.mcancankaya.entities.concretes.Car;
import com.mcancankaya.enums.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private final VehicleRepository vehicleRepository;
    private final Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public CarRepository() {
        this.connection = DBConnection.getConnection();
        this.vehicleRepository = new VehicleRepository();
    }

    public void add(Car car) {
        try {
            int lastId = vehicleRepository.add(car);
            if (lastId != -1) {
                String query = "INSERT INTO cars (vehicle_id, fuel_type, fuel_tank_volume) VALUES " +
                        "(?,?,?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, lastId);
                statement.setString(2, car.getFuelType().name());
                statement.setInt(3, car.getFuelTankVolume());
                int addEffectedRow = statement.executeUpdate();
                if (addEffectedRow > 0)
                    System.out.println("Car is added.");
            } else {
                System.err.println("Vehicle Not Added.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void delete(int carId) {
        try {
            int deleteRow = vehicleRepository.delete(carId);
            if (deleteRow > 0) {
                String query = "DELETE FROM cars WHERE vehicle_id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, carId);
                int deleteEffectedRow = statement.executeUpdate();
                if (deleteEffectedRow > 0)
                    System.out.println("Car is deleted.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateStockAmount(int carId, int newStockAmount) {
        int updateRow = vehicleRepository.updateStockAmount(carId, newStockAmount);
        if (updateRow > 0)
            System.out.println("Car stock amount update.");
    }

    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try {
            String query = "SELECT v.*,c.fuel_type, c.fuel_tank_volume FROM cars c JOIN vehicles v ON v.id = c.vehicle_id ";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = mappingResultSet(resultSet);
                cars.add(car);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return cars;
    }

    public Car getByID(int carId) {
        try {
            String query = "SELECT v.*, c.fuel_type, c.fuel_tank_volume FROM cars c JOIN vehicles v ON v.id = c.vehicle_id WHERE c.vehicle_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, carId);
            resultSet = statement.executeQuery();
            if (resultSet.next())
                return mappingResultSet(resultSet);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;

    }

    public Car mappingResultSet(ResultSet resultSet) {
        Car car = null;
        try {
            car = new Car();
            car.setId(resultSet.getInt("id"));
            car.setEngineType(EngineType.valueOf(resultSet.getString("engine_type")));
            car.setYear(resultSet.getInt("year"));
            car.setTireCount(TireCount.valueOf(resultSet.getString("tire_count")));
            car.setModel(resultSet.getString("model"));
            car.setColour(Colour.valueOf(resultSet.getString("colour")));
            car.setGearType(GearType.valueOf(resultSet.getString("gear_type")));
            car.setDimension(VehicleDimension.valueOf(resultSet.getString("vehicle_dimension")));
            car.setStockAmount(resultSet.getInt("stock_amount"));
            car.setFuelType(FuelType.valueOf(resultSet.getString("fuel_type")));
            car.setFuelTankVolume(resultSet.getInt("fuel_tank_volume"));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return car;
    }
}
