package ca.ubc.cs304.database;

import ca.ubc.cs304.model.CookModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
