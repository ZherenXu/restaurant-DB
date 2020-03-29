package ca.ubc.cs304.database;

import ca.ubc.cs304.model.ConsumeModel;
import ca.ubc.cs304.model.ProvideModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
