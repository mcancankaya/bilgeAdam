package com.mcancankaya.repository;

import com.mcancankaya.entities.abstracts.AbstractVehicle;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleRepository {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public VehicleRepository() {
        this.connection = DBConnection.getConnection();
    }

    public int add(AbstractVehicle vehicle) {
        try {
            String query = "INSERT INTO vehicles (engine_type, year, tire_count, model, colour, gear_type, vehicle_dimension, stock_amount) VALUES " +
                    "(?,?,?,?,?,?,?,?) RETURNING id";
            statement = connection.prepareStatement(query);
            statement.setString(1, vehicle.getEngineType().name());
            statement.setInt(2, vehicle.getYear());
            statement.setString(3, vehicle.getTireCount().name());
            statement.setString(4, vehicle.getModel());
            statement.setString(5, vehicle.getColour().name());
            statement.setString(6, vehicle.getGearType().name());
            statement.setString(7, vehicle.getDimension().name());
            statement.setInt(8, vehicle.getStockAmount());
            if (statement.execute()) {
                resultSet = statement.getResultSet();
                if (resultSet.next())
                    return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }

    public int delete(int vehicle_id) {
        try {
            String query = "DELETE FROM vehicles WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, vehicle_id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public int updateStockAmount(int id, int newStockAmount) {
        try {
            String query = "UPDATE vehicles SET stock_amount = ? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, newStockAmount);
            statement.setInt(2, id);
            int updateEffectedRow =  statement.executeUpdate();
            return updateEffectedRow;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public int getStockAmountByID(int vehicle_id) {
        try {
            String query = "SELECT stock_amount FROM vehicles WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, vehicle_id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {

                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }
}
