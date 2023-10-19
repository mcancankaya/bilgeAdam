package com.mcancankaya.repository;

import com.mcancankaya.entities.concretes.Van;
import com.mcancankaya.enums.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VanRepository {
    private final VehicleRepository vehicleRepository;
    private final Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public VanRepository() {
        this.connection = DBConnection.getConnection();
        this.vehicleRepository = new VehicleRepository();
    }

    public void add(Van van) {
        try {
            int lastId = vehicleRepository.add(van);
            if (lastId != -1) {
                String query = "INSERT INTO vans (vehicle_id, fuel_type, fuel_tank_volume) VALUES " +
                        "(?,?,?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, lastId);
                statement.setString(2, van.getFuelType().name());
                statement.setInt(3, van.getFuelTankVolume());
                int addEffectedRow = statement.executeUpdate();
                if (addEffectedRow > 0)
                    System.out.println("Van is added.");
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
                String query = "DELETE FROM vans WHERE vehicle_id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                int deleteEffectedRow = statement.executeUpdate();
                if (deleteEffectedRow > 0)
                    System.out.println("Van is deleted.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateStockAmount(int id, int newStockAmount) {
        int updateRow = vehicleRepository.updateStockAmount(id, newStockAmount);
        if (updateRow > 0)
            System.out.println("Van stock amount update.");
    }


    public List<Van> getAll() {
        List<Van> vans = new ArrayList<>();
        try {
            String query = "SELECT v.*,c.fuel_type, c.fuel_tank_volume FROM vans c JOIN vehicles v ON v.id = c.vehicle_id ";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Van van = mappingResultSet(resultSet);
                vans.add(van);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return vans;
    }

    public Van getByID(int id) {
        try {
            String query = "SELECT v.*, c.fuel_type, c.fuel_tank_volume FROM vans c JOIN vehicles v ON v.id = c.vehicle_id WHERE c.vehicle_id = ?";
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

    public Van mappingResultSet(ResultSet resultSet) {
        Van van = null;
        try {
            van = new Van();
            van.setId(resultSet.getInt("id"));
            van.setEngineType(EngineType.valueOf(resultSet.getString("engine_type")));
            van.setYear(resultSet.getInt("year"));
            van.setTireCount(TireCount.valueOf(resultSet.getString("tire_count")));
            van.setModel(resultSet.getString("model"));
            van.setColour(Colour.valueOf(resultSet.getString("colour")));
            van.setGearType(GearType.valueOf(resultSet.getString("gear_type")));
            van.setDimension(VehicleDimension.valueOf(resultSet.getString("vehicle_dimension")));
            van.setStockAmount(resultSet.getInt("stock_amount"));
            van.setFuelType(FuelType.valueOf(resultSet.getString("fuel_type")));
            van.setFuelTankVolume(resultSet.getInt("fuel_tank_volume"));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return van;
    }
}
