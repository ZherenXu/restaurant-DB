package ca.ubc.cs304.database;

import java.sql.*;
import java.util.Vector;

public class BranchHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static Vector<Vector<String>> getAllBranch(Connection connection) {
        Vector<Vector<String>> Branch = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BRANCH");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("address"));
                tuple.add(rs.getString("contact"));
                tuple.add(rs.getString("managerName"));
                Branch.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Branch;
    }

    protected static Vector<Vector<String>> getSpecificBranchInfo(String attribute, Connection connection) {
        Vector<Vector<String>> Branch = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            String proj = "SELECT " + attribute + "FROM BRANCH";
            ResultSet rs = stmt.executeQuery(proj);

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString(attribute));
                Branch.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return Branch;
    }

    protected static Vector<String> getBranchColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BRANCH");

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

    protected static Vector<String> getSpecificBranchColumn(String attribute){

        Vector<String> column = new Vector<>();
        column.add(attribute);

        return column;
    }

    protected static void updateBranch(String address, String contact, String manager, Connection connection) {
        try {
            String update = "UPDATE BRANCH SET contact = \'" + contact + "\', " +
                    "ManagerName = \'" + manager + "\'" +
                    " WHERE address = \'" + address + "\'";
            PreparedStatement ps = connection.prepareStatement(update);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Branch " + address + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            DatabaseConnectionHandler.rollbackConnection();
        }
    }
}
