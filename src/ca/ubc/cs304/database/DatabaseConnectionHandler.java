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
	public void insertFreezer(FreezerModel model){
		StorageHandler.insertFreezer(model, connection);
	}
	public void insertRefrigerator(RefrigeratorModel model){
		StorageHandler.insertRefrigerator(model, connection);
	}
	public void insertShelf(ShelfModel model){
		StorageHandler.insertShelf(model, connection);
	}

	/* Delete tuples in database */
	public void deleteOrder(int OrderNumber){
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
	public void deleteIngredient(String lotNumber){
		IngredientHandler.deleteIngredient(lotNumber, connection);
	}

	/* Update tuples in database */
	public void updateBranch(String address, String contact, String manager){
		BranchHandler.updateBranch(address, contact, manager, connection);
	}
	public void updateDeliveryPeople(String sin, String contact, String address){
		DeliveryPeopleHandler.updateDeliveryPeople(sin, contact, address, connection);
	}
	public void updateFoodSupplier(String company, String contact, String email){
		FoodSupplierHandler.updateFoodSupplier(company, contact, email, connection);
	}
	public void updateChef(String sin, String contact, String address){
		ChefHandler.updateChef(sin, contact, address, connection);
	}

	/* Select all tuples in one table in database */
	public Vector<Vector<String>> getAllOrder(){
		return OrderHandler.getAllOrder(connection);
	}
	public Vector<Vector<String>> getAllBranch(){
		return BranchHandler.getAllBranch(connection);
	}
	public Vector<Vector<String>> getAllFreezer(){
		return StorageHandler.getAllFreezer(connection);
	}
	public Vector<Vector<String>> getAllRefrigerator(){
		return StorageHandler.getAllRefrigerator(connection);
	}
	public Vector<Vector<String>> getAllShelf(){
		return StorageHandler.getAllShelf(connection);
	}
	public Vector<Vector<String>> getAllDishes(){
		return DishesHandler.getAllDishes(connection);
	}
	public Vector<Vector<String>> getAllIngredients(){
		return IngredientHandler.getAllIngreidents(connection);
	}
	public Vector<Vector<String>> getAllCook(){
		return CookHandler.getAllCook(connection);
	}
	public Vector<Vector<String>> getAllChef(){
		return ChefHandler.getAllChef(connection);
	}
	public Vector<Vector<String>> getAllDeliveryPeople(){
		return DeliveryPeopleHandler.getAllDeliveryPeople(connection);
	}
	public Vector<Vector<String>> getAllConsume(){
		return ConsumeHandler.getAllConsume(connection);
	}
	public Vector<Vector<String>> getAllFoodSupplier(){
		return FoodSupplierHandler.getAllFoodSupplier(connection);
	}
	public Vector<Vector<String>> getAllProvide(){
		return ProvideHandler.getAllProvide(connection);
	}
	public Vector<Vector<String>> getAllStorage(){
		return StorageHandler.getAllStorage(connection);
	}
	
	/* Column names of a specific table */
	public Vector<String> getOrderColumn(){
		return OrderHandler.getOrderColumn(connection);
	}
	public Vector<String> getBranchColumn(){
		return BranchHandler.getBranchColumn(connection);
	}
	public Vector<String> getStorageColumn(){
		return StorageHandler.getStorageColumn(connection);
	}
	public Vector<String> getDishesColumn(){
		return DishesHandler.getDishesColumn(connection);
	}
	public Vector<String> getIngredientsColumn(){
		return IngredientHandler.getIngredientsColumn(connection);
	}
	public Vector<String> getCookColumn(){
		return CookHandler.getCookColumn(connection);
	}
	public Vector<String> getChefColumn(){
		return ChefHandler.getChefColumn(connection);
	}
	public Vector<String> getDeliveryPeopleColumn(){
		return DeliveryPeopleHandler.getDeliveryPeopleColumn(connection);
	}
	public Vector<String> getConsumeColumn(){
		return ConsumeHandler.getConsumeColumn(connection);
	}
	public Vector<String> getFoodSupplierColumn(){
		return FoodSupplierHandler.getFoodSupplierColumn(connection);
	}
	public Vector<String> getProvideColumn(){
		return ProvideHandler.getProvideColumn(connection);
	}
	public Vector<Vector<String>> minIngredient(){
		return StatisticHandler.minIngredient(connection);
	}
	public Vector<Vector<String>> maxIngredient(){
		return StatisticHandler.maxIngredient(connection);
	}
	public Vector<String> minMaxColumn(){
		return StatisticHandler.minMaxColumn(connection);
	}

	/* average temperature*/
	public float avgTempShelf(){
		return StatisticHandler.avgTempShelf(connection);
	}
	public float avgTempRef(){
		return StatisticHandler.avgTempRef(connection);
	}
	public float avgTempFreezer(){
		return StatisticHandler.avgTempFreezer(connection);
	}

	/* Number of dishes for each ingredient */
	public Vector<Vector<String>> ICountDishes(){
		return StatisticHandler.ICountDishes(connection);
	}
	/* Number of ingredients delivered by each delivery people */
	public Vector<Vector<String>> DPCountIngredient(){
		return StatisticHandler.DPCountIngredient(connection);
	}
	/* Chef information given the order number */
	public Vector<Vector<String>> findChefByOrder(int OrderNumber){
		return SelectHandler.findChefByOrder(OrderNumber, connection);
	}
	/* Ingredient information given the order number */
	public Vector<Vector<String>> findIngredientByOrder(int OrderNumber){
		return SelectHandler.findIngredientByOrder(OrderNumber, connection);
	}
	/* Delivery people information given the order number */
	public Vector<Vector<String>> findDeliveryByOrder(int OrderNumber){
		return SelectHandler.findDeliveryByOrder(OrderNumber, connection);
	}
	/* Food suppplier information given the order number */
	public Vector<Vector<String>> findSupplierByOrder(int OrderNumber){
		return SelectHandler.findSupplierByOrder(OrderNumber, connection);
	}
	/* Storage temperature given the order number */
	public Vector<Vector<String>> findTempByOrder(int OrderNumber){
		return SelectHandler.findTempByOrder(OrderNumber, connection);
	}
	/* Order number given time and location */
	public Vector<Vector<String>> findOrder(Timestamp timeStart, Timestamp timeEnd, String location){
		return SelectHandler.findOrder(timeStart, timeEnd, location, connection);
	}
	/* Dishes information given the lot number */
	public Vector<Vector<String>> findDishesByIngredient(String lotNumber){
		return SelectHandler.findDishesByIngredient(lotNumber, connection);
	}
	/* Name of ingredients which are used by all chefs */
	public Vector<Vector<String>> division(){
		return SelectHandler.division(connection);
	}

	/* Other column names */
	public Vector<String> ICountColumn(){
		return StatisticHandler.ICountColumn(connection);
	}
	public Vector<String> DPCountColumn(){
		return StatisticHandler.DPCountColumn(connection);
	}
	public Vector<String> chefOrderColumn(){
		return SelectHandler.chefOrderColumn(connection);
	}
	public Vector<String> ingredientOrderColumn(){
		return SelectHandler.ingredientOrderColumn(connection);
	}
	public Vector<String> deliveryOrderColumn(){
		return SelectHandler.deliveryOrderColumn(connection);
	}
	public Vector<String> supplierOrderColumn(){
		return SelectHandler.supplierOrderColumn(connection);
	}
	public Vector<String> tempOrderColumn(){
		return SelectHandler.tempOrderColumn(connection);
	}
	public Vector<String> orderColumn(){
		return SelectHandler.orderColumn(connection);
	}
	public Vector<String> dishesIngredientColumn(){
		return SelectHandler.dishesIngreidentColumn(connection);
	}
	public Vector<String> divisionColumn(){
		return SelectHandler.divisionColumn(connection);
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
	
//	public void updateBranch(int id, String name) {
//		try {
//		  PreparedStatement ps = connection.prepareStatement("UPDATE branch SET branch_name = ? WHERE branch_id = ?");
//		  ps.setString(1, name);
//		  ps.setInt(2, id);
//
//		  int rowCount = ps.executeUpdate();
//		  if (rowCount == 0) {
//		      System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
//		  }
//
//		  connection.commit();
//
//		  ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
//	}
	
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
