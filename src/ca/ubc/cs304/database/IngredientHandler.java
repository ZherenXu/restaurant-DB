package ca.ubc.cs304.database;

import ca.ubc.cs304.model.CategoryModel;
import ca.ubc.cs304.model.IngredientsModel;

import java.sql.*;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class IngredientHandler {

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    protected static void insertIngredient(IngredientsModel iModel, CategoryModel cModel, Connection connection){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS");

            if(!rs.next()){
                PreparedStatement ps1 = connection.prepareStatement("INSERT INTO CATEGORY VALUES (?,?)");
                ps1.setString(1, cModel.getName());
                ps1.setString(2, cModel.getType());
                ps1.executeUpdate();
                connection.commit();

                ps1.close();

                PreparedStatement ps2 = connection.prepareStatement("INSERT INTO INGREDIENTS VALUES (?,?,?,?,?,?)");
                ps2.setString(1, iModel.getLotNumber());
                ps2.setString(2, iModel.getName());
                ps2.setDate(3, iModel.getProductionDate());
                ps2.setInt(4, iModel.getQuantity());
                ps2.setInt(5, iModel.getPosID());
                ps2.setString(6, iModel.getSin());
                ps2.executeUpdate();
                connection.commit();

                ps2.close();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection();
        }

        return;
    }
}
