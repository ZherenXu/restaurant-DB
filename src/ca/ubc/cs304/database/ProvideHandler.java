package ca.ubc.cs304.database;

import ca.ubc.cs304.model.ConsumeModel;
import ca.ubc.cs304.model.ProvideModel;

import java.sql.*;
import java.util.Vector;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class ProvideHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static void insertProvide(ProvideModel model, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PROVIDE VALUES (?,?)");
            ps.setString(1, model.getCompanyName());
            ps.setString(2, model.getLotNumber());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection();
        }

        return;
    }

    protected static Vector<Vector<String>> getAllProvide(Connection connection) {
        Vector<Vector<String>> Provide = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PROVIDE");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("CompanyName"));
                tuple.add(rs.getString("LotNumber"));

                Provide.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Provide;
    }

    protected static Vector<String> getProvideColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PROVIDE");

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
