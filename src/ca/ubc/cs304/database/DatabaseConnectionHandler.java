package ca.ubc.cs304.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import ca.ubc.cs304.model.*;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	
	private static Connection connection = null;
	
	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void deleteBranch(int branchId) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM branch WHERE branch_id = ?");
			ps.setInt(1, branchId);
			
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
			}
			
			connection.commit();
	
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	/* Insert tuples in database */
	public void insertChef(ChefModel model, ChefAddressModel aModel){
		ChefHandler.insertChef(model, aModel, connection);
	}
	public void insertDeliveryPeople(DeliveryPeopleModel model){
		DeliveryPeopleHandler.insertDeliveryPeople(model, connection);
	}
	public void insertIngredient(IngredientsModel iModel, CategoryModel cModel){
		IngredientHandler.insertIngredient(iModel, cModel, connection);
	}
	public void insertCook(CookModel model){
		CookHandler.insertCook(model, connection);
	}
	public void insertConsume(ConsumeModel model){
		ConsumeHandler.insertConsume(model, connection);
	}
	public void insertFoodSupplier(FoodSupplierModel model){
		FoodSupplierHandler.insertFoodSupplier(model, connection);
	}
	public void insertProvide(ProvideModel model){
		ProvideHandler.insertProvide(model, connection);
	}
	public void insertDish(DishesModel model){
		DishesHandler.insertDish(model, connection);
	}
	public void insertOrder(OrdersModel model){
		OrderHandler.insertOrder(model, connection);
	}

	/* Delete tuples in database */
	public void deleteOrder(int OrderNumber){
		DishesHandler.deleteAllDishes(OrderNumber, connection);
		OrderHandler.deleteOrder(OrderNumber, connection);
	}
	public void deleteChef(String sin){
		ChefHandler.deleteChef(sin, connection);
	}
	public void deleteDeliveryPeople(String sin){
		DeliveryPeopleHandler.deleteDeliveryPeople(sin, connection);
	}
	public void deleteFoodSupplier(String company_name){
		FoodSupplierHandler.deleteFoodSupplier(company_name, connection);
	}

	/* Select all tuples in orders */
	public Vector<Vector<String>> getAllOrder(){
		return OrderHandler.getAllOrder(connection);
	}

	/* Column names in orders */
	public Vector<String> getOrderColumn(){
		return OrderHandler.getOrderColumn(connection);
	}

	/* Select all tuples in dishes */
	public Vector<Vector<String>> getAllDishes(){
		return DishesHandler.getAllDishes(connection);
	}

	/* Columns names in orders */
	public Vector<String> getDishesColumn(){
		return DishesHandler.getDishesColumn(connection);
	}

	/* Select all tuples in ingredients */
	public Vector<Vector<String>> getAllIngredients(){
		return IngredientHandler.getAllIngreidents(connection);
	}

	/* Columns names in ingredients */
	public Vector<String> getIngredientsColumn(){
		return IngredientHandler.getIngredientsColumn(connection);
	}

	/* Select all tuples in cook */
	public Vector<Vector<String>> getAllCook(){
		return CookHandler.getAllCook(connection);
	}

	/* Columns names in cook */
	public Vector<String> getCookColumn(){
		return CookHandler.getCookColumn(connection);
	}

	/* Select all tuples in chef */
	public Vector<Vector<String>> getAllChef(){
		return ChefHandler.getAllChef(connection);
	}

	/* Columns names in chef */
	public Vector<String> getChefColumn(){
		return ChefHandler.getChefColumn(connection);
	}

	/* Select all tuples in delivery people */
	public Vector<Vector<String>> getAllDeliveryPeople(){
		return DeliveryPeopleHandler.getAllDeliveryPeople(connection);
	}

	/* Columns names in delivery people */
	public Vector<String> getDeliveryPeopleColumn(){
		return DeliveryPeopleHandler.getDeliveryPeopleColumn(connection);
	}

	/* Select all tuples in consume */
	public Vector<Vector<String>> getAllConsume(){
		return ConsumeHandler.getAllConsume(connection);
	}

	/* Columns names in consume */
	public Vector<String> getConsumeColumn(){
		return ConsumeHandler.getConsumeColumn(connection);
	}

	/* Select all tuples in food supplier */
	public Vector<Vector<String>> getAllFoodSupplier(){
		return FoodSupplierHandler.getAllFoodSupplier(connection);
	}

	/* Columns names in food supplier */
	public Vector<String> getFoodSupplierColumn(){
		return FoodSupplierHandler.getFoodSupplierColumn(connection);
	}

	/* Select all tuples in provide */
	public Vector<Vector<String>> getAllProvide(){
		return ProvideHandler.getAllProvide(connection);
	}

	/* Columns names in provide */
	public Vector<String> getProvideColumn(){
		return ProvideHandler.getProvideColumn(connection);
	}

	/* Ingredients that are not popular / ingredients with minimum quantity in consume */
	public Vector<Vector<String>> minIngredient(){
		return StatisticHandler.minIngredient(connection);
	}

	/* Ingredients that are most popular / ingredients with maximum quantity in consume */
	public Vector<Vector<String>> maxIngredient(){
		return StatisticHandler.maxIngredient(connection);
	}

	/* Column names of min/max ingredients */
	public Vector<String> minMaxColumn(){
		return StatisticHandler.minMaxColumn(connection);
	}

	/* average temperature of shelf */
	public float avgTempShelf(){
		return StatisticHandler.avgTempShelf(connection);
	}

	/* average temperature of refrigerator */
	public float avgTempRef(){
		return StatisticHandler.avgTempRef(connection);
	}

	/* average temperature of freezer */
	public float avgTempFreezer(){
		return StatisticHandler.avgTempFreezer(connection);
	}

	/* Number of dishes for each ingredient */
	public Vector<Vector<String>> ICountDishes(){
		return StatisticHandler.ICountDishes(connection);
	}

	/* Column names of ICountDishes */
	public Vector<String> ICountColumn(){
		return StatisticHandler.ICountColumn(connection);
	}

	/* Number of ingredients delivered by each delivery people */
	public Vector<Vector<String>> DPCountIngredient(){
		return StatisticHandler.DPCountIngredient(connection);
	}

	/* Column names of DPCountIngredients */
	public Vector<String> DPCountColumn(){
		return StatisticHandler.DPCountColumn(connection);
	}

	/* Chef information given the order number */
	public Vector<Vector<String>> findChefByOrder(int OrderNumber){
		return SelectHandler.findChefByOrder(OrderNumber, connection);
	}

	/* Column names of findChefByOrder */
	public Vector<String> chefOrderColumn(){
		return SelectHandler.chefOrderColumn(connection);
	}

	/* Ingredient information given the order number */
	public Vector<Vector<String>> findIngredientByOrder(int OrderNumber){
		return SelectHandler.findIngredientByOrder(OrderNumber, connection);
	}

	/* Column names of findIngredientByOrder */
	public Vector<String> ingredientOrderColumn(){
		return SelectHandler.ingredientOrderColumn(connection);
	}

	/* Delivery people information given the order number */
	public Vector<Vector<String>> findDeliveryByOrder(int OrderNumber){
		return SelectHandler.findDeliveryByOrder(OrderNumber, connection);
	}

	/* Column names of findDeliveryByOrder */
	public Vector<String> deliveryOrderColumn(){
		return SelectHandler.deliveryOrderColumn(connection);
	}

	/* Food suppplier information given the order number */
	public Vector<Vector<String>> findSupplierByOrder(int OrderNumber){
		return SelectHandler.findSupplierByOrder(OrderNumber, connection);
	}

	/* Column names of findSupplierByOrder */
	public Vector<String> supplierOrderColumn(){
		return SelectHandler.supplierOrderColumn(connection);
	}


	public void insertBranch(BranchModel model) {
//		try {
//			PreparedStatement ps = connection.prepareStatement("INSERT INTO branch VALUES (?,?,?,?,?)");
//			ps.setInt(1, model.getId());
//			ps.setString(2, model.getName());
//			ps.setString(3, model.getAddress());
//			ps.setString(4, model.getCity());
//			if (model.getPhoneNumber() == 0) {
//				ps.setNull(5, java.sql.Types.INTEGER);
//			} else {
//				ps.setInt(5, model.getPhoneNumber());
//			}
//
//			ps.executeUpdate();
//			connection.commit();
//
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
		return;
	}
	
	public BranchModel[] getBranchInfo() {
		ArrayList<BranchModel> result = new ArrayList<BranchModel>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM branch");
		
//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}
			
			while(rs.next()) {
//				BranchModel model = new BranchModel(rs.getString("branch_addr"),
//													rs.getString("branch_city"),
//													rs.getInt("branch_id"),
//													rs.getString("branch_name"),
//													rs.getInt("branch_phone"));
//				result.add(model);
				break;
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}	
		
		return result.toArray(new BranchModel[result.size()]);
	}
	
	public void updateBranch(int id, String name) {
		try {
		  PreparedStatement ps = connection.prepareStatement("UPDATE branch SET branch_name = ? WHERE branch_id = ?");
		  ps.setString(1, name);
		  ps.setInt(2, id);
		
		  int rowCount = ps.executeUpdate();
		  if (rowCount == 0) {
		      System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
		  }
	
		  connection.commit();
		  
		  ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}	
	}
	
	public boolean login(String username, String password) {
		try {
			if (connection != null) {
				connection.close();
			}
	
			connection = DriverManager.getConnection(ORACLE_URL, username, password);
			connection.setAutoCommit(false);
	
			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			return false;
		}
	}

	protected static void rollbackConnection() {
		try  {
			connection.rollback();	
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
}
