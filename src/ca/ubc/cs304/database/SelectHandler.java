package ca.ubc.cs304.database;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class SelectHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";

    protected static Vector<Vector<String>> findChefByOrder(int OrderNumber, Connection connection) {
        Vector<Vector<String>> chefInfo = new Vector<>();
        try {
            String select = "SELECT DISTINCT CH.SIN AS SIN, CH.Name AS Name, CH.ContactNumber AS ContactNumber,\n" +
                    "       CA.branchAddress AS Branch\n" +
                    "FROM CHEF CH, CHEFADDRESS CA, COOK C\n" +
                    "WHERE C.SIN = CH.SIN AND CH.SIN = CA.SIN AND C.OrderNumber = \'" + OrderNumber +"\'";
            PreparedStatement stmt = connection.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("sin"));
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
                    "SELECT DISTINCT CH.SIN AS SIN, CH.Name AS Name, CH.ContactNumber AS ContactNumber, " +
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
            String select = "SELECT I.name AS Name, I.lotNumber AS lotNumber,\n" +
                    "       I.ProductionDate AS ProductionDate\n" +
                    "FROM INGREDIENTS I, CONSUME C\n" +
                    "WHERE C.lotNumber = I.lotNumber AND C.OrderNumber = \'" + OrderNumber + "\'";
            PreparedStatement stmt = connection.prepareStatement(select);
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
            String select = "SELECT I.name AS Name, I.lotNumber AS lotNumber,\n" +
                    "       I.ProductionDate AS ProductionDate\n" +
                    "FROM INGREDIENTS I, CONSUME C\n";
            ResultSet rs = stmt.executeQuery(select);

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
            String select = "SELECT DISTINCT D.SIN AS SIN, D.Name AS Name, D.ContactNumber AS ContactNumber, D.Address AS Address\n" +
                    "FROM INGREDIENTS I, CONSUME C, DELIVERYPEOPLE D\n" +
                    "WHERE C.lotNumber = I.lotNumber AND I.SIN = D.SIN\n" +
                    "  AND C.OrderNumber = \'" + OrderNumber + "\'";
            PreparedStatement stmt = connection.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("sin"));
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
            String select =  "SELECT D.SIN AS SIN, D.Name AS Name, D.ContactNumber AS ContactNumber, D.Address AS Address\n" +
                    "FROM INGREDIENTS I, CONSUME C, DELIVERYPEOPLE D\n" +
                    "WHERE C.lotNumber = I.lotNumber AND I.SIN = D.SIN\n";
            ResultSet rs = stmt.executeQuery(select);

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
            String select = "SELECT DISTINCT F.CompanyName AS Company, F.ContactNumber AS ContactNumber,\n" +
                    "       F.Address AS Address, F.Email AS Email\n" +
                    "FROM FOODSUPPLIER F, CONSUME C, PROVIDE P\n" +
                    "WHERE C.lotNumber = P.lotNumber AND P.CompanyName = F.CompanyName\n" +
                    "  AND C.OrderNumber = \'" + OrderNumber + "\'";
            PreparedStatement stmt = connection.prepareStatement(select);
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
            String select = "SELECT F.CompanyName AS Company, F.ContactNumber AS ContactNumber,\n" +
                    "       F.Address AS Address, F.Email AS Email\n" +
                    "FROM FOODSUPPLIER F, CONSUME C, PROVIDE P\n";
            ResultSet rs = stmt.executeQuery(select);

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

    protected static Vector<Vector<String>> findTempByOrder(int OrderNumber, Connection connection) {
        Vector<Vector<String>> tempInfo = new Vector<>();
        try {
            String view = "CREATE VIEW temp(id, temp) AS\n" +
                    "    (SELECT * FROM FREEZER UNION\n" +
                    "    SELECT * FROM SHELF UNION\n" +
                    "    SELECT * FROM REFRIGERATOR)";
            Statement stmt1 = connection.createStatement();
            stmt1.executeQuery(view);
            connection.commit();

            stmt1.close();

            String select = "SELECT I.name AS Name, T.id AS PosID, T.temp AS Temperature\n" +
                    "FROM INGREDIENTS I, CONSUME C, temp T\n" +
                    "WHERE C.lotNumber = I.lotNumber AND I.PosID = T.id AND C.OrderNumber = \'" + OrderNumber + "\'";
            PreparedStatement stmt = connection.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("Name"));
                tuple.add(Integer.toString(rs.getInt("PosID")));
                tuple.add(Float.toString(rs.getFloat("Temperature")));

                tempInfo.add(tuple);
            }
            connection.commit();

            rs.close();
            stmt.close();

            String drop = "DROP VIEW Temp";
            Statement stmt2 = connection.createStatement();
            stmt2.executeQuery(drop);
            stmt2.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return tempInfo;
    }

    protected static Vector<String> tempOrderColumn(Connection connection){
        Vector<String> column = new Vector<>();

        try {
            String select = "SELECT I.name AS Name, F.PosID AS PosID, F.freezertemp AS Temperature\n" +
                    "FROM INGREDIENTS I, freezer F";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(select);

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



    protected static Vector<Vector<String>> findOrder(Timestamp timeStart, Timestamp timeEnd, String location, Connection connection) {
        Vector<Vector<String>> orders = new Vector<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String string1  = dateFormat.format(timeStart);
            String string2  = dateFormat.format(timeEnd);

            String order = "SELECT DISTINCT O.orderNumber AS OrderNumber\n" +
                    "FROM ORDERS O, HAS H, INGREDIENTS I, Consume C\n" +
                    "WHERE O.orderNumber = C.orderNumber\n" +
                    "  AND C.lotNumber = I.lotNumber\n" +
                    "  AND I.PosID = H.PosID\n" +
                    "  AND H.Address = \'"+ location + "\'\n" +
                    "  AND O.Time BETWEEN \'"+ string1 + "\' AND \'" + string2 + "\'";
            PreparedStatement stmt = connection.prepareStatement(order);
//            stmt.setString(1, location);
//            stmt.setTimestamp(2, timeStart);
//            stmt.setTimestamp(3, timeEnd);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(Integer.toString(rs.getInt("orderNumber")));

                orders.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return orders;
    }

    protected static Vector<String> orderColumn(Connection connection){
        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            String col = "SELECT orderNumber FROM ORDERS";
            ResultSet rs = stmt.executeQuery(col);

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

    protected static Vector<Vector<String>> findDishesByIngredient(String lotNumber, Connection connection) {
        Vector<Vector<String>> dishes = new Vector<>();
        try {
            String select = "SELECT OrderNumber, DishesName\n" +
                    "FROM Consume\n" +
                    "WHERE lotNumber = \'" + lotNumber + "\'";
            PreparedStatement stmt = connection.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(Integer.toString(rs.getInt("OrderNumber")));
                tuple.add(rs.getString("DishesName"));

                dishes.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return dishes;
    }

    protected static Vector<String> dishesIngreidentColumn(Connection connection){
        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            String select = "SELECT OrderNumber, DishesName FROM Consume";
            ResultSet rs = stmt.executeQuery(select);

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

    protected static Vector<Vector<String>> division(Connection connection) {
        Vector<Vector<String>> ingredients = new Vector<>();
        try {
            String div = "SELECT DISTINCT I.Name AS Name\n" +
                    "FROM INGREDIENTS I\n" +
                    "WHERE NOT EXISTS(\n" +
                    "    SELECT CH.SIN\n" +
                    "    FROM CHEF CH\n" +
                    "    WHERE NOT EXISTS(\n" +
                    "        SELECT C.LOTNUMBER, I2.NAME\n" +
                    "        FROM CONSUME C, INGREDIENTS I2, COOK CK\n" +
                    "        WHERE C.DISHESNAME = CK.DISHNAME AND C.ORDERNUMBER = CK.ORDERNUMBER\n" +
                    "          AND C.LOTNUMBER = I2.LOTNUMBER AND CK.SIN = CH.SIN AND I2.NAME = I.NAME))";
            PreparedStatement stmt = connection.prepareStatement(div);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("Name"));
                ingredients.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return ingredients;
    }

    protected static Vector<String> divisionColumn(Connection connection){
        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT Name FROM INGREDIENTS");

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
