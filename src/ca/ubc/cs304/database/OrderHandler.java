package ca.ubc.cs304.database;

import ca.ubc.cs304.model.OrdersModel;

import java.sql.*;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class OrderHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static void insertOrder(OrdersModel model, Connection connection){
    try {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO ORDERS VALUES (?,?)");
        ps.setInt(1, model.getOrderNumber());
        ps.setTimestamp(2, model.getTime());

        ps.executeUpdate();
        connection.commit();

        ps.close();
    } catch (SQLException e) {
        System.out.println(EXCEPTION_TAG + " " + e.getMessage());

        rollbackConnection();
    }

    return;
    }

    protected static void deleteOrder(int OrderNumber, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ORDERS WHERE OrderNumber = ?");
            ps.setInt(1, OrderNumber);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Order " + OrderNumber + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }


}
