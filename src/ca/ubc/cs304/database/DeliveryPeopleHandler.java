package ca.ubc.cs304.database;

import ca.ubc.cs304.model.CookModel;
import ca.ubc.cs304.model.DeliveryPeopleModel;

import java.sql.*;
import java.util.Vector;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class DeliveryPeopleHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static void insertDeliveryPeople(DeliveryPeopleModel model, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO DELIVERYPEOPLE VALUES (?,?,?,?)");
            ps.setString(1, model.getSin());
            ps.setString(2, model.getName());
            ps.setString(3, model.getContactNumber());
            ps.setString(4, model.getAddress());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection();
        }

        return;
    }

    protected static void deleteDeliveryPeople(String Sin, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM DELIVERYPEOPLE WHERE SIN = ?");
            ps.setString(1, Sin);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Delivery people " + Sin + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    protected static Vector<Vector<String>> getAllDeliveryPeople(Connection connection) {
        Vector<Vector<String>> DeliveryPeople = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DELIVERYPEOPLE");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("SIN"));
                tuple.add(rs.getString("Name"));
                tuple.add(rs.getString("ContactNumber"));
                tuple.add(rs.getString("Address"));

                DeliveryPeople.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return DeliveryPeople;
    }

    protected static Vector<String> getDeliveryPeopleColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DELIVERYPEOPLE");

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
