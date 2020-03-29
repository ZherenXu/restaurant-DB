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
        }

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO CHEFADDRESS VALUES (?,?)");
            ps.setString(1, aModel.getHomeAddress());
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
            PreparedStatement ps = connection.prepareStatement("DELETE FROM CHEF WHERE SIN = ?");
            ps.setString(1, Sin);

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
}
