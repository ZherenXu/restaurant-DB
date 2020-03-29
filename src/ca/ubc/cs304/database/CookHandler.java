package ca.ubc.cs304.database;

import ca.ubc.cs304.model.CookModel;

import java.sql.*;
import java.util.Vector;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class CookHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static void insertCook(CookModel model, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO COOK VALUES (?,?,?)");
            ps.setString(1, model.getSin());
            ps.setString(2, model.getDishesName());
            ps.setInt(3, model.getOrderNumber());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection();
        }

        return;
    }

    protected static Vector<Vector<String>> getAllCook(Connection connection) {
        Vector<Vector<String>> Cook = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COOK");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("SIN"));
                tuple.add(rs.getString("DishName"));
                tuple.add(Integer.toString(rs.getInt("OrderNumber")));

                Cook.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Cook;
    }

    protected static Vector<String> getCookColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COOK");

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
