package ca.ubc.cs304.database;

import ca.ubc.cs304.model.ConsumeModel;
import ca.ubc.cs304.model.FoodSupplierModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class FoodSupplierHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static void insertFoodSupplier(FoodSupplierModel model, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO FOODSUPPLIER VALUES (?,?,?,?)");
            ps.setString(1, model.getCompanyName());
            ps.setString(2, model.getAddress());
            ps.setString(3, model.getContactNumber());
            ps.setString(4, model.getEmail());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection();
        }

        return;
    }

    protected static void deleteFoodSupplier(String CompanyName, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM FOODSUPPLIER WHERE CompanyName = ?");
            ps.setString(1, CompanyName);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Food supplier " + CompanyName + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }
}
