package ca.ubc.cs304.database;

import ca.ubc.cs304.model.FoodSupplierModel;
import ca.ubc.cs304.model.FreezerModel;
import ca.ubc.cs304.model.RefrigeratorModel;
import ca.ubc.cs304.model.ShelfModel;

import java.sql.*;
import java.util.Vector;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class StorageHandler {

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static void insertFreezer(FreezerModel model, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO FREEZER VALUES (?,?)");
            ps.setInt(1, model.getPosID());
            ps.setFloat(2, model.getFreezerTemp());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection();
        }

        return;
    }

    protected static void insertRefrigerator(RefrigeratorModel model, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO REFRIGERATOR VALUES (?,?)");
            ps.setInt(1, model.getPosID());
            ps.setFloat(2, model.getRefrigeratorTemp());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection();
        }

        return;
    }

    protected static void insertShelf(ShelfModel model, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO SHELF VALUES (?,?)");
            ps.setInt(1, model.getPosID());
            ps.setFloat(2, model.getRoomTemp());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection();
        }

        return;
    }

    protected static Vector<Vector<String>> getAllFreezer(Connection connection) {
        Vector<Vector<String>> Freezer = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FREEZER");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(Integer.toString(rs.getInt("PosID")));
                tuple.add(Float.toString(rs.getFloat("FreezerTemp")));

                Freezer.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Freezer;
    }

    protected static Vector<Vector<String>> getAllRefrigerator(Connection connection) {
        Vector<Vector<String>> Ref = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM REFRIGERATOR");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(Integer.toString(rs.getInt("PosID")));
                tuple.add(Float.toString(rs.getFloat("RefrigeratorTemp")));

                Ref.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Ref;
    }

    protected static Vector<Vector<String>> getAllShelf(Connection connection) {
        Vector<Vector<String>> Shelf = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SHELF");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(Integer.toString(rs.getInt("PosID")));
                tuple.add(Float.toString(rs.getFloat("RoomTemp")));

                Shelf.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Shelf;
    }

    protected static Vector<Vector<String>> getAllStorage(Connection connection) {
        Vector<Vector<String>> Shelf = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            String stor = "SELECT POSID, FREEZERTEMP AS TEMPERATURE\n" +
                    "FROM(\n" +
                    "    SELECT * FROM FREEZER UNION\n" +
                    "    SELECT * FROM SHELF UNION\n" +
                    "    SELECT * FROM REFRIGERATOR)\n" +
                    "ORDER BY POSID";
            ResultSet rs = stmt.executeQuery(stor);

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(Integer.toString(rs.getInt("PosID")));
                tuple.add(Float.toString(rs.getFloat("temperature")));

                Shelf.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Shelf;
    }


    protected static Vector<String> getStorageColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT F.PosID AS POSID, F.FreezerTemp AS Temperature FROM FREEZER F");

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
