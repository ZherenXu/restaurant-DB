package ca.ubc.cs304.database;

import java.sql.*;
import java.util.Vector;

public class SelectHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static Vector<Vector<String>> findChefByOrder(int OrderNumber, Connection connection) {
        Vector<Vector<String>> chefInfo = new Vector<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT CH.Name AS Name, CH.ContactNumber AS ContactNumber, " +
                            "CA.branchAddress AS Branch " +
                            "FROM CHEF CH, CHEFADDRESS CA, COOK C" +
                            "WHERE C.SIN = CH.SIN AND CH.HomeAddress = CA.HomeAddress" +
                            "AND C.OrderNumber = ?");
            stmt.setInt(1, OrderNumber);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("Name"));
                tuple.add(rs.getString("ContactNumber"));
                tuple.add(rs.getString("Branch"));

                chefInfo.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return chefInfo;
    }

    protected static Vector<String> chefOrderColumn(Connection connection){
        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT CH.Name AS Name, CH.ContactNumber AS ContactNumber, " +
                            "CA.branchAddress AS Branch " +
                            "FROM CHEF CH, CHEFADDRESS CA, COOK C");

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

    protected static Vector<Vector<String>> findIngredientByOrder(int OrderNumber, Connection connection) {
        Vector<Vector<String>> ingredientInfo = new Vector<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT I.name AS Name, I.lotNumber AS lotNumber, " +
                            "I.ProductionDate AS ProductionDate" +
                            "FROM INGREDIENTS I, CONSUME C" +
                            "WHERE C.lotNumber = I.lotNumber" +
                            "AND C.OrderNumber = ?");
            stmt.setInt(1, OrderNumber);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("Name"));
                tuple.add(rs.getString("lotNumber"));
                tuple.add(rs.getDate("ProductionDate").toString());

                ingredientInfo.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return ingredientInfo;
    }

    protected static Vector<String> ingredientOrderColumn(Connection connection){
        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT I.name AS Name, I.lotNumber AS lotNumber, " +
                            "I.ProductionDate AS ProductionDate" +
                            "FROM INGREDIENTS I, CONSUME C");

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

    protected static Vector<Vector<String>> findDeliveryByOrder(int OrderNumber, Connection connection) {
        Vector<Vector<String>> deliveryInfo = new Vector<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT D.Name AS Name, D.ContactNumber AS ContactNumber, D.Address AS Address" +
                            "FROM INGREDIENTS I, CONSUME C, DELIVERYPEOPLE D" +
                            "WHERE C.lotNumber = I.lotNumber AND I.SIN = D.SIN" +
                            "AND C.OrderNumber = ?");
            stmt.setInt(1, OrderNumber);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("Name"));
                tuple.add(rs.getString("ContactNumber"));
                tuple.add(rs.getString("Address"));

                deliveryInfo.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return deliveryInfo;
    }

    protected static Vector<String> deliveryOrderColumn(Connection connection){
        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT D.Name AS Name, D.ContactNumber AS ContactNumber, D.Address AS Address" +
                            "FROM INGREDIENTS I, CONSUME C, DELIVERYPEOPLE D" +
                            "WHERE C.lotNumber = I.lotNumber AND I.SIN = D.SIN");

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

    protected static Vector<Vector<String>> findSupplierByOrder(int OrderNumber, Connection connection) {
        Vector<Vector<String>> supplierInfo = new Vector<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT F.CompanyName AS Company, F.ContactNumber AS ContactNumber, " +
                            "F.Address AS Address, F.Email AS Email" +
                            "FROM FOODSUPPLIER F, CONSUME C, PROVIDE P" +
                            "WHERE C.lotNumber = P.lotNumber AND P.CompanyName = F.CompanyName" +
                            "AND C.OrderNumber = ?");
            stmt.setInt(1, OrderNumber);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("Company"));
                tuple.add(rs.getString("ContactNumber"));
                tuple.add(rs.getString("Address"));
                tuple.add(rs.getString("Email"));

                supplierInfo.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return supplierInfo;
    }

    protected static Vector<String> supplierOrderColumn(Connection connection){
        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT F.CompanyName AS Company, F.ContactNumber AS ContactNumber, " +
                            "F.Address AS Address, F.Email AS Email" +
                            "FROM FOODSUPPLIER F, CONSUME C, PROVIDE P");

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
