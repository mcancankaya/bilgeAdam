package com.mcancankaya.repository;

import com.mcancankaya.entities.concretes.ReturnSale;
import com.mcancankaya.entities.concretes.Sale;
import com.mcancankaya.enums.SaleStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReturnSaleRepository {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private VehicleRepository vehicleRepository;
    private SaleRepository saleRepository;

    public ReturnSaleRepository() {
        this.connection = DBConnection.getConnection();
        this.saleRepository = new SaleRepository();
        this.vehicleRepository = new VehicleRepository();
    }

    public void add(ReturnSale returnSale) {
        try {
            Sale sale = saleRepository.getSaleById(returnSale.getSaleId());
            if (sale != null) {
                sale.setStatus(SaleStatus.RETURNED_SALES);
                saleRepository.update(sale);
                String query = "INSERT INTO return_sales (sales_id, return_date) VALUES "
                        + "(?,?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, returnSale.getSaleId());
                statement.setDate(2, Date.valueOf(returnSale.getReturnDate()));

                int addedRow = statement.executeUpdate();
                if (addedRow > 0)
                    System.out.println("ReturnSale is added.");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<ReturnSale> getAll() {
        List<ReturnSale> returnSales = new ArrayList<>();
        try {
            String query = "SELECT * FROM return_sales";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ReturnSale returnSale = mappingResultSet(resultSet);
                returnSales.add(returnSale);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return returnSales;
    }

    private ReturnSale mappingResultSet(ResultSet resultSet) {
        ReturnSale returnSale = null;
        try {
            returnSale = new ReturnSale();
            returnSale.setId(resultSet.getInt("id"));
            returnSale.setSaleId(resultSet.getInt("sales_id"));
            returnSale.setReturnDate(resultSet.getDate("return_date").toLocalDate());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return returnSale;
    }
}
