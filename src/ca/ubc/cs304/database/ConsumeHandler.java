package ca.ubc.cs304.database;

import ca.ubc.cs304.model.ChefAddressModel;
import ca.ubc.cs304.model.ConsumeModel;

import java.sql.*;
import java.util.Vector;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class ConsumeHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static void insertConsume(ConsumeModel model, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO CONSUME VALUES (?,?,?,?)");
            ps.setString(1, model.getDishesName());
            ps.setInt(2, model.getOrderNumber());
            ps.setString(3, model.getLotNumber());
            ps.setInt(4, model.getQuantity());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection();
        }

        return;
    }

    protected static Vector<Vector<String>> getAllConsume(Connection connection) {
        Vector<Vector<String>> Consume = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CONSUME");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("DishesName"));
                tuple.add(Integer.toString(rs.getInt("OrderNumber")));
                tuple.add(rs.getString("LotNumber"));
                tuple.add(Integer.toString(rs.getInt("Quantity")));

                Consume.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Consume;
    }

    protected static Vector<String> getConsumeColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CONSUME");

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
