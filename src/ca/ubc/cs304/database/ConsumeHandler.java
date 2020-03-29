package ca.ubc.cs304.database;

import ca.ubc.cs304.model.ChefAddressModel;
import ca.ubc.cs304.model.ConsumeModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
