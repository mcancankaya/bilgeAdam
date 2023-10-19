package com.mcancankaya.repository;

import com.mcancankaya.entities.concretes.Sale;
import com.mcancankaya.enums.SaleStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleRepository {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private VehicleRepository vehicleRepository;

    public SaleRepository() {
        this.connection = DBConnection.getConnection();
        this.vehicleRepository = new VehicleRepository();
    }

    public void add(Sale sale) {
        try {
            int vehicleStockAmount = vehicleRepository.getStockAmountByID(sale.getVehicle_id());
            int newStockAmount = vehicleStockAmount - sale.getQuantity();
            if (vehicleStockAmount != -1 && !(newStockAmount < 0)) {
                String query = "INSERT INTO sales (date, vehicle_id, quantity, status) VALUES (?,?,?,?)";
                statement = connection.prepareStatement(query);
                statement.setDate(1, Date.valueOf(sale.getSaleDate()));
                statement.setInt(2, sale.getVehicle_id());
                statement.setInt(3, sale.getQuantity());
                statement.setString(4, sale.getStatus().name());
                int addEffectedRow = statement.executeUpdate();
                vehicleRepository.updateStockAmount(sale.getVehicle_id(), newStockAmount);
                if (addEffectedRow > 0)
                    System.out.println("Satış işlemi başarılı.");
            } else {
                System.err.println("Stok Miktarı Yetersiz Satış Yapılamadı.");
            }


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            String query = "DELETE FROM sales WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int deleteEffectedRow = statement.executeUpdate();
            if (deleteEffectedRow > 0)
                System.out.println("Sales is deleted.");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateSalesQuantityById(int saleId, int newSaleQuantity) {
        try {
            String query = "UPDATE sales SET quantity = ? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, newSaleQuantity);
            statement.setInt(2, saleId);
            int updateEffectedRow = statement.executeUpdate();
            if (updateEffectedRow > 0)
                System.out.println("Sale Quantity is updated.");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Sale> getSales() {
        List<Sale> sales = new ArrayList<>();
        try {
            String query = "SELECT * FROM sales";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sale sale = mappingResultSet(resultSet);
                sales.add(sale);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sales;
    }

    private Sale mappingResultSet(ResultSet resultSet) {
        Sale sale = null;
        try {
            sale = new Sale(resultSet.getDate("date").toLocalDate(), resultSet.getInt("vehicle_id"), resultSet.getInt("quantity"), SaleStatus.valueOf(resultSet.getString("status")));
            sale.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sale;
    }
}
