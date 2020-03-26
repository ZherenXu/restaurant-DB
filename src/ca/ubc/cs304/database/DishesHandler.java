package ca.ubc.cs304.database;

import ca.ubc.cs304.model.DishesModel;

import java.sql.*;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class DishesHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static void insertDish(DishesModel model, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO DISHES VALUES (?,?,?)");
            ps.setString(1, model.getName());
            ps.setInt(2, model.getOrderNumber());
            if(model.getTastePreference() == null) {
                ps.setNull(3, Types.CHAR);
            }
            else{
                ps.setString(3, model.getTastePreference());
            }

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

        return;
    }

    protected static void deleteDishes(String name, int OrderNumber, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM DISHES WHERE Name = ? AND OrderNumber = ?");
            ps.setString(1, name);
            ps.setInt(2, OrderNumber);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Dishes " + OrderNumber + ", " + name + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    protected static void deleteAllDishes(int OrderNumber, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM DISHES WHERE OrderNumber = ?");
            ps.setInt(1, OrderNumber);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Dishes " + OrderNumber + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }


}
