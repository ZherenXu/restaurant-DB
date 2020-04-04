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

    protected static Vector<Vector<String>> getAllStorage(Connection connection) {
        Vector<Vector<String>> Shelf = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            String stor = "SELECT S.POSID, S.FREEZERTEMP AS TEMPERATURE, H.Address AS Branch\n" +
                    "FROM\n" +
                    "    (SELECT * FROM FREEZER UNION\n" +
                    "    SELECT * FROM SHELF UNION\n" +
                    "    SELECT * FROM REFRIGERATOR) S, HAS H\n" +
                    "WHERE H.POSID = S.POSID\n" +
                    "ORDER BY S.POSID";
            ResultSet rs = stmt.executeQuery(stor);

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(Integer.toString(rs.getInt("PosID")));
                tuple.add(Float.toString(rs.getFloat("temperature")));
                tuple.add(rs.getString("Branch"));

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
            ResultSet rs = stmt.executeQuery("SELECT F.PosID AS POSID, F.FreezerTemp AS Temperature, H.Address AS Branch FROM FREEZER F, HAS H");

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
