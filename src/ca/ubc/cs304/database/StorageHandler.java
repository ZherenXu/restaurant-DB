package ca.ubc.cs304.database;

import java.sql.*;
import java.util.Vector;

public class StorageHandler {

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

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
