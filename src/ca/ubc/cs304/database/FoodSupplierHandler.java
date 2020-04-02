package ca.ubc.cs304.database;

import ca.ubc.cs304.model.ConsumeModel;
import ca.ubc.cs304.model.FoodSupplierModel;

import java.sql.*;
import java.util.Vector;

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

    protected static Vector<Vector<String>> getAllFoodSupplier(Connection connection) {
        Vector<Vector<String>> FoodSupplier = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FOODSUPPLIER");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("CompanyName"));
                tuple.add(rs.getString("Address"));
                tuple.add(rs.getString("ContactNumber"));
                tuple.add(rs.getString("Email"));

                FoodSupplier.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return FoodSupplier;
    }

    protected static Vector<String> getFoodSupplierColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FOODSUPPLIER");

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name
                column.add(rsmd.getColumnName(i+1));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return column;
    }

    protected static void updateFoodSupplier(String company, String contact, String email, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE FOODSUPPLIER SET ContactNumber = ?, Email = ? WHERE CompanyName = ?");
            ps.setString(1, contact);
            ps.setString(2, email);
            ps.setString(3, company);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Food supplier " + company + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.rollbackConnection();
        }
    }
}
