package ca.ubc.cs304.database;

import ca.ubc.cs304.model.ChefAddressModel;
import ca.ubc.cs304.model.ChefModel;

import java.sql.*;
import java.util.Vector;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class ChefHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static void insertChef(ChefModel model, ChefAddressModel aModel, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO CHEF VALUES (?,?,?,?)");
            ps.setString(1, model.getName());
            ps.setString(2, model.getSin());
            ps.setString(3, model.getContactNumber());
            ps.setString(4, model.getHomeAddress());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection();
            return;
        }

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO CHEFADDRESS VALUES (?,?)");
            ps.setString(1, aModel.getSIN());
            ps.setString(2, aModel.getBranchAddress());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            deleteChef(model.getSin(), connection);
            rollbackConnection();
        }

        return;
    }

    protected static void deleteChef(String Sin, Connection connection) {
        try {
            String delete = "delete from CHEF where sin = \'" + Sin + "\'";
            PreparedStatement ps = connection.prepareStatement(delete);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " chef " + Sin + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    protected static Vector<Vector<String>> getAllChef(Connection connection) {
        Vector<Vector<String>> Chef = new Vector<>();

        try {
            String select = "SELECT C.Name AS Name, C.SIN AS SIN,C.ContactNumber AS ContactNumber, C.HomeAddress AS HomeAddress, CA.BranchAddress AS BranchAddress FROM CHEF C, CHEFADDRESS CA WHERE C.SIN = CA.SIN";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(select);

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("Name"));
                tuple.add(rs.getString("SIN"));
                tuple.add(rs.getString("ContactNumber"));
                tuple.add(rs.getString("HomeAddress"));
                tuple.add(rs.getString("BranchAddress"));

                Chef.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Chef;
    }

    protected static Vector<String> getChefColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            String select = "SELECT C.Name AS Name, C.SIN AS SIN,C.ContactNumber AS ContactNumber, C.HomeAddress AS HomeAddress, CA.BranchAddress AS BranchAddress FROM CHEF C, CHEFADDRESS CA WHERE C.SIN = CA.SIN";
            ResultSet rs = stmt.executeQuery(select);

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

    protected static void updateChef(String sin, String contact, String address, Connection connection) {
        try {
            String update = "UPDATE CHEF SET contactNumber = \'" + contact + "\', " +
                    "HomeAddress = \'" + address + "\' " +
                    "WHERE SIN = \'" + sin +"\'";
            PreparedStatement ps = connection.prepareStatement(update);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Chef " + sin + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.rollbackConnection();
        }
    }
}
