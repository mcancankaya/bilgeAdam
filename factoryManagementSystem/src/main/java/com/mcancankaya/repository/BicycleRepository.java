package com.mcancankaya.repository;

import com.mcancankaya.entities.concretes.Bicycle;
import com.mcancankaya.entities.concretes.Car;
import com.mcancankaya.enums.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BicycleRepository {
    private final VehicleRepository vehicleRepository;
    private final Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public BicycleRepository() {
        this.connection = DBConnection.getConnection();
        this.vehicleRepository = new VehicleRepository();
    }

    public void add(Bicycle bicycle) {
        try {
            int lastId = vehicleRepository.add(bicycle);
            if (lastId != -1) {
                String query = "INSERT INTO bicycles (vehicle_id) VALUES " +
                        "(?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, lastId);

                int addEffectedRow = statement.executeUpdate();
                if (addEffectedRow > 0)
                    System.out.println("Bicycle is added.");
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
                String query = "DELETE FROM bicycles WHERE vehicle_id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                int deleteEffectedRow = statement.executeUpdate();
                if (deleteEffectedRow > 0)
                    System.out.println("Bicycle is deleted.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateStockAmount(int id, int newStockAmount) {
        int updateRow = vehicleRepository.updateStockAmount(id, newStockAmount);
        if (updateRow > 0)
            System.out.println("Bicycle stock amount update.");
    }

    public List<Bicycle> getAll() {
        List<Bicycle> bicycles = new ArrayList<>();
        try {
            String query = "SELECT v.* FROM bicycles b JOIN vehicles v ON v.id = b.vehicle_id ";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bicycle bicycle = mappingResultSet(resultSet);
                bicycles.add(bicycle);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return bicycles;
    }

    public Bicycle getByID(int id) {
        try {
            String query = "SELECT v.* FROM bicycles b JOIN vehicles v ON v.id = b.vehicle_id WHERE b.vehicle_id = ?";
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

    public Bicycle mappingResultSet(ResultSet resultSet) {
        Bicycle bicycle = null;
        try {
            bicycle = new Bicycle();
            bicycle.setId(resultSet.getInt("id"));
            bicycle.setEngineType(EngineType.valueOf(resultSet.getString("engine_type")));
            bicycle.setYear(resultSet.getInt("year"));
            bicycle.setTireCount(TireCount.valueOf(resultSet.getString("tire_count")));
            bicycle.setModel(resultSet.getString("model"));
            bicycle.setColour(Colour.valueOf(resultSet.getString("colour")));
            bicycle.setGearType(GearType.valueOf(resultSet.getString("gear_type")));
            bicycle.setDimension(VehicleDimension.valueOf(resultSet.getString("vehicle_dimension")));
            bicycle.setStockAmount(resultSet.getInt("stock_amount"));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return bicycle;
    }
}
