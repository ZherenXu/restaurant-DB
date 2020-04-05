package ca.ubc.cs304.database;

import ca.ubc.cs304.model.OrdersModel;

import java.sql.*;
import java.util.Vector;

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

    protected static Vector<Vector<String>> getAllOrder(Connection connection) {
        Vector<Vector<String>> Orders = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(Integer.toString(rs.getInt("OrderNumber")));
                tuple.add(rs.getTimestamp("Time").toString());

                Orders.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Orders;
    }

    protected static Vector<String> getOrderColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS");

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


}
