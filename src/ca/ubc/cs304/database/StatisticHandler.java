package ca.ubc.cs304.database;

import java.sql.*;
import java.util.Vector;

public class StatisticHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    protected static Vector<Vector<String>> minIngredient(Connection connection) {
        Vector<Vector<String>> min = new Vector<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT C1.lotNumber AS lotNumber, I1.name AS name, SUM(C1.quantity) AS quantity" +
                            "FROM CONSUME C1, INGREDIENTS I1 " +
                            "WHERE I1.lotNumber = C1.lotNumber" +
                            "GROUP BY C1.lotNumber " +
                            "HAVING SUM(C1.quantity) = MIN(" +
                            "SELECT SUM(C2.quantity) " +
                            "FROM CONSUME C2" +
                            "GROUP BY C2.lotNumber)");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("lotNumber"));
                tuple.add(rs.getString("name"));
                tuple.add(Integer.toString(rs.getInt("quantity")));

                min.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return min;
    }

    protected static Vector<Vector<String>> maxIngredient(Connection connection) {
        Vector<Vector<String>> max = new Vector<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT C1.lotNumber AS lotNumber, I1.name AS name, SUM(C1.quantity) AS quantity" +
                            "FROM CONSUME C1, INGREDIENTS I1 " +
                            "WHERE I1.lotNumber = C1.lotNumber" +
                            "GROUP BY C1.lotNumber " +
                            "HAVING SUM(C1.quantity) = MAX(" +
                            "SELECT SUM(C2.quantity) " +
                            "FROM CONSUME C2" +
                            "GROUP BY C2.lotNumber)");

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("lotNumber"));
                tuple.add(rs.getString("name"));
                tuple.add(Integer.toString(rs.getInt("quantity")));

                max.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return max;
    }

    protected static Vector<String> minMaxColumn(Connection connection){
        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT C1.lotNumber AS lotNumber, I1.name AS name, SUM(C1.quantity) AS quantity" +
                            "FROM CONSUME C1, INGREDIENTS I1 " +
                            "WHERE I1.lotNumber = C1.lotNumber" +
                            "GROUP BY C1.lotNumber");

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

    /* If there is no tuples in the table, the average will return 999.999 */
    protected static float avgTempShelf(Connection connection) {
        float avg;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT AVG(RoomTemp) AS avgTemp FROM SHELF");
            if(!rs.next()){
                System.out.println(EXCEPTION_TAG + " " + "There is no tuples in the table");
                avg = (float)999.999;
            }
            else{
                avg = rs.getFloat("avgTemp");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            avg = (float) 999.999;
        }
        return avg;
    }

    /* If there is no tuples in the table, the average will return 999.999 */
    protected static float avgTempRef(Connection connection) {
        float avg;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT AVG(RoomTemp) AS avgTemp FROM REFRIGERATOR");
            if(!rs.next()){
                System.out.println(EXCEPTION_TAG + " " + "There is no tuples in the table");
                avg = (float)999.999;
            }
            else{
                avg = rs.getFloat("avgTemp");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            avg = (float) 999.999;
        }
        return avg;
    }

    /* If there is no tuples in the table, the average will return 999.999 */
    protected static float avgTempFreezer(Connection connection) {
        float avg;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT AVG(RoomTemp) AS avgTemp FROM FREEZER");
            if(!rs.next()){
                System.out.println(EXCEPTION_TAG + " " + "There is no tuples in the table");
                avg = (float)999.999;
            }
            else{
                avg = rs.getFloat("avgTemp");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            avg = (float) 999.999;
        }
        return avg;
    }

}
