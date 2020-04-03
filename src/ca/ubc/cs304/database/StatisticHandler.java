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
            String minQuery = "SELECT I1.name AS name, SUM(C1.quantity) AS quantity\n" +
                    "FROM CONSUME C1, INGREDIENTS I1\n" +
                    "WHERE I1.lotNumber = C1.lotNumber\n" +
                    "GROUP BY I1.name\n" +
                    "HAVING SUM(C1.quantity) <= ALL(\n" +
                    "    SELECT SUM(C2.quantity)\n" +
                    "    FROM CONSUME C2, INGREDIENTS I2\n" +
                    "    WHERE I2.LOTNUMBER = C2.LOTNUMBER\n" +
                    "    GROUP BY I2.name)";
            ResultSet rs = stmt.executeQuery(minQuery);

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
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
            String maxQuery = "SELECT I1.name AS name, SUM(C1.quantity) AS quantity\n" +
                    "FROM CONSUME C1, INGREDIENTS I1\n" +
                    "WHERE I1.lotNumber = C1.lotNumber\n" +
                    "GROUP BY I1.name\n" +
                    "HAVING SUM(C1.quantity) >= ALL(\n" +
                    "    SELECT SUM(C2.quantity)\n" +
                    "    FROM CONSUME C2, INGREDIENTS I2\n" +
                    "    WHERE I2.LOTNUMBER = C2.LOTNUMBER\n" +
                    "    GROUP BY I2.name)";
            ResultSet rs = stmt.executeQuery(maxQuery);

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
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
            String query = "SELECT I1.name AS name, C1.quantity AS quantity\n" +
                    "FROM CONSUME C1, INGREDIENTS I1\n";
            ResultSet rs = stmt.executeQuery(query);

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
            ResultSet rs = stmt.executeQuery("SELECT AVG(RefrigeratorTemp) AS avgTemp FROM REFRIGERATOR");
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
            ResultSet rs = stmt.executeQuery("SELECT AVG(FreezerTemp) AS avgTemp FROM FREEZER");
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

    protected static Vector<Vector<String>> ICountDishes(Connection connection) {
        Vector<Vector<String>> summary = new Vector<>();
        try {
            Statement stmt = connection.createStatement();
            String count = "SELECT I.name AS ingredient, COUNT(DISTINCT C.dishesName) AS numOfDishes\n" +
                    "FROM INGREDIENTS I, CONSUME C\n" +
                    "WHERE I.lotNumber = C.lotNumber\n" +
                    "GROUP BY I.name";
            ResultSet rs = stmt.executeQuery(count);

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("ingredient"));
                tuple.add(Integer.toString(rs.getInt("numOfDishes")));

                summary.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return summary;
    }

    protected static Vector<String> ICountColumn(Connection connection){
        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            String count = "SELECT I.name AS ingredient, COUNT(DISTINCT C.dishesName) AS numOfDishes\n" +
                    "FROM INGREDIENTS I, CONSUME C\n" +
                    "WHERE I.lotNumber = C.lotNumber\n" +
                    "GROUP BY I.name";
            ResultSet rs = stmt.executeQuery(count);

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


    protected static Vector<Vector<String>> DPCountIngredient(Connection connection) {
        Vector<Vector<String>> summary = new Vector<>();
        try {
            Statement stmt = connection.createStatement();
            String count = "SELECT D.sin,D.name,COUNT(I.lotNumber) AS frequency\n" +
                    "FROM INGREDIENTS I, DELIVERYPEOPLE D\n" +
                    "WHERE I.sin = D.sin\n" +
                    "GROUP BY D.sin, D.name";
            ResultSet rs = stmt.executeQuery(count);

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("sin"));
                tuple.add(rs.getString("name"));
                tuple.add(Integer.toString(rs.getInt("frequency")));

                summary.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return summary;
    }

    protected static Vector<String> DPCountColumn(Connection connection){
        Vector<String> column = new Vector<>();

        try {
            String count = "SELECT D.sin,D.name,COUNT(I.lotNumber) AS frequency\n" +
                    "FROM INGREDIENTS I, DELIVERYPEOPLE D\n" +
                    "WHERE I.sin = D.sin\n" +
                    "GROUP BY D.sin, D.name";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(count);

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
