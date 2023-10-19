package com.mcancankaya.repository;

import com.mcancankaya.entities.concretes.Motorcycle;
import com.mcancankaya.enums.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotorcycleRepository {
    private final VehicleRepository vehicleRepository;
    private final Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public MotorcycleRepository() {
        this.connection = DBConnection.getConnection();
        this.vehicleRepository = new VehicleRepository();
    }

    public void add(Motorcycle motorcycle) {
        try {
            int lastId = vehicleRepository.add(motorcycle);
            if (lastId != -1) {
                String query = "INSERT INTO motorcycles (vehicle_id, fuel_type, fuel_tank_volume) VALUES " +
                        "(?,?,?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, lastId);
                statement.setString(2, motorcycle.getFuelType().name());
                statement.setInt(3, motorcycle.getFuelTankVolume());
                int addEffectedRow = statement.executeUpdate();
                if (addEffectedRow > 0)
                    System.out.println("Motorcycles is added.");
            } else {
                System.err.println("Vehicle Not Added.");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            int deleteRow = vehicleRepository.delete(id);
            if (deleteRow > 0) {
                String query = "DELETE FROM motorcycles WHERE vehicle_id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                int deleteEffectedRow = statement.executeUpdate();
                if (deleteEffectedRow > 0)
                    System.out.println("Motorcycles is deleted.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateStockAmount(int id, int newStockAmount) {
        int updateRow = vehicleRepository.updateStockAmount(id, newStockAmount);
        if (updateRow > 0)
            System.out.println("Motorcycle stock amount update.");
    }

    public List<Motorcycle> getAll() {
        List<Motorcycle> motorcycles = new ArrayList<>();
        try {
            String query = "SELECT v.*,m.fuel_type, m.fuel_tank_volume FROM motorcycles m JOIN vehicles v ON v.id = m.vehicle_id ";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Motorcycle motorcycle = mappingResultSet(resultSet);
                motorcycles.add(motorcycle);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return motorcycles;
    }

    public Motorcycle getByID(int id) {
        try {
            String query = "SELECT v.*, m.fuel_type, m.fuel_tank_volume FROM motorcycle m JOIN vehicles v ON v.id = m.vehicle_id WHERE m.vehicle_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next())
                return mappingResultSet(resultSet);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Motorcycle mappingResultSet(ResultSet resultSet) {
        Motorcycle motorcycle = null;
        try {
            motorcycle = new Motorcycle();
            motorcycle.setId(resultSet.getInt("id"));
            motorcycle.setEngineType(EngineType.valueOf(resultSet.getString("engine_type")));
            motorcycle.setYear(resultSet.getInt("year"));
            motorcycle.setTireCount(TireCount.valueOf(resultSet.getString("tire_count")));
            motorcycle.setModel(resultSet.getString("model"));
            motorcycle.setColour(Colour.valueOf(resultSet.getString("colour")));
            motorcycle.setGearType(GearType.valueOf(resultSet.getString("gear_type")));
            motorcycle.setDimension(VehicleDimension.valueOf(resultSet.getString("vehicle_dimension")));
            motorcycle.setStockAmount(resultSet.getInt("stock_amount"));
            motorcycle.setFuelType(FuelType.valueOf(resultSet.getString("fuel_type")));
            motorcycle.setFuelTankVolume(resultSet.getInt("fuel_tank_volume"));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return motorcycle;
    }
}
