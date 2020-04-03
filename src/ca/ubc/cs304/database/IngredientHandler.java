package ca.ubc.cs304.database;

import ca.ubc.cs304.model.CategoryModel;
import ca.ubc.cs304.model.IngredientsModel;

import java.sql.*;
import java.util.Vector;

import static ca.ubc.cs304.database.DatabaseConnectionHandler.rollbackConnection;

public class IngredientHandler {

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    protected static void insertIngredient(IngredientsModel iModel, CategoryModel cModel, Connection connection){
        try {
            String delete = "DELETE FROM CATEGORY WHERE name = \'"+ cModel.getName() + "\'";
            PreparedStatement ps = connection.prepareStatement(delete);
            ps.executeUpdate();
            ps.close();
            System.out.println("21");

                PreparedStatement ps1 = connection.prepareStatement("INSERT INTO CATEGORY VALUES (?,?)");
                ps1.setString(1, cModel.getName());
                ps1.setString(2, cModel.getType());
                ps1.executeUpdate();
                connection.commit();

                ps1.close();
                System.out.println(iModel.getLotNumber());
                System.out.println(iModel.getName());
                System.out.println(iModel.getProductionDate());
                System.out.println(iModel.getQuantity());
                System.out.println(iModel.getPosID());
                System.out.println(iModel.getSin());

                PreparedStatement ps2 = connection.prepareStatement("INSERT INTO INGREDIENTS VALUES (?,?,?,?,?,?)");
                ps2.setString(1, iModel.getLotNumber());
                ps2.setString(2, iModel.getName());
                ps2.setDate(3, iModel.getProductionDate());
                ps2.setInt(4, iModel.getQuantity());
                ps2.setInt(5, iModel.getPosID());
                ps2.setString(6, iModel.getSin());
                ps2.executeUpdate();
                connection.commit();
                System.out.println("debug");

                ps2.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection();
        }

        return;
    }

    protected static void deleteIngredient(String lotNumber, Connection connection) {
        try {
            String delete = "delete from INGREDIENTS where lotNumber = \'" + lotNumber + "\'";
            PreparedStatement ps = connection.prepareStatement(delete);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " ingredient " + lotNumber + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    protected static Vector<Vector<String>> getAllIngreidents(Connection connection) {
        Vector<Vector<String>> Ingredients = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            String ingredient = "SELECT I.LotNumber AS LotNumber, I.Name AS Name,\n" +
                    "       C.Type AS Type, I.ProductionDate AS ProductionDate,\n" +
                    "       I.Quantity AS Quantity, I.PosID AS StoragePosition, I.SIN AS DeliveryPeopleSIN\n" +
                    " FROM INGREDIENTS I, CATEGORY C\n" +
                    " WHERE I.Name = C.Name";
            ResultSet rs = stmt.executeQuery(ingredient);

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("LotNumber"));
                tuple.add(rs.getString("Name"));
                tuple.add(rs.getString("Type"));
                tuple.add(rs.getDate("ProductionDate").toString());
                tuple.add(Integer.toString(rs.getInt("Quantity")));
                tuple.add(Integer.toString(rs.getInt("StoragePosition")));
                tuple.add(rs.getString("DeliveryPeopleSIN"));

                Ingredients.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Ingredients;
    }

    protected static Vector<String> getIngredientsColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            String ingredient = "SELECT I.LotNumber AS LotNumber, I.Name AS Name,\n" +
                    "       C.Type AS Type, I.ProductionDate AS ProductionDate,\n" +
                    "       I.Quantity AS Quantity, I.PosID AS StoragePosition, I.SIN AS DeliveryPeopleSIN\n" +
                    " FROM INGREDIENTS I, CATEGORY C\n" +
                    " WHERE I.Name = C.Name";
            ResultSet rs = stmt.executeQuery(ingredient);

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
