package ca.ubc.cs304.database;

import ca.ubc.cs304.model.DishesModel;

import java.sql.*;
import java.util.Vector;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class DishesHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";

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

    protected static Vector<Vector<String>> getAllDishes(Connection connection) {
        Vector<Vector<String>> Dishes = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DISHES");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("Name"));
                tuple.add(Integer.toString(rs.getInt("OrderNumber")));
                String taste = rs.getString("TastePreference");

                if(taste == null){
                    tuple.add("");
                }
                else{
                    tuple.add(taste);
                }

                Dishes.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Dishes;
    }

    protected static Vector<String> getDishesColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DISHES");

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
