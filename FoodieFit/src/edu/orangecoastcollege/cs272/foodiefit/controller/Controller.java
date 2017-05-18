package edu.orangecoastcollege.cs272.foodiefit.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import edu.orangecoastcollege.cs272.foodiefit.model.CheatMeal;
import edu.orangecoastcollege.cs272.foodiefit.model.DBModel;
import edu.orangecoastcollege.cs272.foodiefit.model.Food;
import edu.orangecoastcollege.cs272.foodiefit.model.Meal;
import edu.orangecoastcollege.cs272.foodiefit.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The <code>Controller</code> connects the databases to the JavaFX user interface. The Controller is a Singleton
 * which serves as the one intermediary between the back-end and front-end of the application.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class Controller {
	
	private static Controller theOne;
	private static final String DB_NAME = "foodie_fit.db";
	
	private static final String USER_TABLE_NAME = "user";
	private static final String[] USER_FIELD_NAMES = { "id", "username", "name", "age", "height", "weight", "password" };
	private static final String[] USER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "INTEGER", "INTEGER", "INTEGER", "TEXT" };
	
	private static final String FOOD_TABLE_NAME = "food";
	private static final String[] FOOD_FIELD_NAMES = { "id", "name", "calories", "weight", "measure" };
	private static final String[] FOOD_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "INTEGER", "REAL", "TEXT" };
	private static final String FOOD_DATA_FILE = "food_lite.csv";
	
	private static final String MEAL_TABLE_NAME = "meal";
	private static final String[] MEAL_FIELD_NAMES = { "id", "name", "plan", "calories" };
	private static final String[] MEAL_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "INTEGER" };
	
	private static final String CHEAT_MEAL_TABLE_NAME = "cheat_meal";
	private static final String[] CHEAT_MEAL_FIELD_NAMES = { "id", "name", "cheat_meal", "calories" };
	private static final String[] CHEAT_MEAL_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "INTEGER" };
	
	private static final String FOOD_MEAL_TABLE_NAME = "meal_food";
	private static final String[] FOOD_MEAL_FIELD_NAMES = { "food_id", "meal_id" };
	private static final String[] FOOD_MEAL_FIELD_TYPES = { "INTEGER", "INTEGER" };
	
	private static final String USER_MEAL_TABLE_NAME = "user_meal";
	private static final String[] USER_MEAL_FIELD_NAMES = { "user_id", "meal_id" };
	private static final String[] USER_MEAL_FIELD_TYPES = { "INTEGER", "INTEGER" };
	
	private User mCurrentUser;
	private DBModel mUserDB;
	private DBModel mFoodDB;
	private DBModel mMealDB;
	private DBModel mCheatMealDB;
	private DBModel mFoodMealDB;
	private DBModel mUserMealDB;
	
	private ObservableList<User> mAllUsersList;
	private ObservableList<Food> mAllFoodsList;
	private ObservableList<Meal> mAllMealsList;
	private ObservableList<CheatMeal> mAllCheatMealsList;
	
	private Controller() {}
	
	/**
	 * The <code>getInstance</code> gets the instance of the Controller.
	 * @return The controller.
	 */
	public static Controller getInstance() {
		if (theOne == null) {
			theOne = new Controller();
			theOne.mAllUsersList = FXCollections.observableArrayList();
			theOne.mAllFoodsList = FXCollections.observableArrayList();
			theOne.mAllMealsList = FXCollections.observableArrayList();
			
			try {
				theOne.mUserDB = new DBModel(DB_NAME, USER_TABLE_NAME, USER_FIELD_NAMES, USER_FIELD_TYPES);
				ArrayList<ArrayList<String>> resultsList = theOne.mUserDB.getAllRecords();
				for (ArrayList<String> values : resultsList) {
					int id = Integer.parseInt(values.get(0));
					String username = values.get(1);
					String name = values.get(2);
					int age = Integer.parseInt(values.get(3));
					int height = Integer.parseInt(values.get(4));
					int weight = Integer.parseInt(values.get(5));
					theOne.mAllUsersList.add(new User(id, username, name, age, height, weight));
				}
				
				theOne.mFoodDB = new DBModel(DB_NAME, FOOD_TABLE_NAME, FOOD_FIELD_NAMES, FOOD_FIELD_TYPES);
				theOne.initializeFoodDBFromFile();
				resultsList = theOne.mFoodDB.getAllRecords();
				for (ArrayList<String> values : resultsList) {
					int id = Integer.parseInt(values.get(0));
					String name = values.get(1);
					int calories = Integer.parseInt(values.get(2));
					double weight = Double.parseDouble(values.get(3));
					String measure = values.get(4);
					theOne.mAllFoodsList.add(new Food(id, name, calories, weight, measure));
				}
				
				theOne.mMealDB = new DBModel(DB_NAME, MEAL_TABLE_NAME, MEAL_FIELD_NAMES, MEAL_FIELD_TYPES);
				resultsList = theOne.mMealDB.getAllRecords();
				for (ArrayList<String> values : resultsList) {
					int id = Integer.parseInt(values.get(0));
					String name = values.get(1);
					String plan = values.get(2);
					int calories = Integer.parseInt(values.get(3));
					theOne.mAllMealsList.add(new Meal(id, name, plan, calories));
				}
				
				theOne.mCheatMealDB = new DBModel(DB_NAME, CHEAT_MEAL_TABLE_NAME, CHEAT_MEAL_FIELD_NAMES, CHEAT_MEAL_FIELD_TYPES);
				resultsList = theOne.mCheatMealDB.getAllRecords();
				for (ArrayList<String> values : resultsList) {
					int id = Integer.parseInt(values.get(0));
					String name = values.get(1);
					String cheatMeal = values.get(2);
					int calories = Integer.parseInt(values.get(3));
					theOne.mAllCheatMealsList.add(new CheatMeal(id, name, cheatMeal, calories));
				}
				
				theOne.mFoodMealDB = new DBModel(DB_NAME, FOOD_MEAL_TABLE_NAME, FOOD_MEAL_FIELD_NAMES, FOOD_MEAL_FIELD_TYPES);
				theOne.mUserMealDB = new DBModel(DB_NAME, USER_MEAL_TABLE_NAME, USER_MEAL_FIELD_NAMES, USER_MEAL_FIELD_TYPES);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return theOne;
	}
	
	/**
	 * The <code>initializeDBFromFile</code> initializes the database from the file. This is for the food list.
	 * @return The records created.
	 * @throws SQLException
	 */
	private int initializeFoodDBFromFile() throws SQLException {
		int recordsCreated = 0;
		
		if (theOne.mUserDB.getRecordCount() > 0)
			return 0;
		
		try {
			Scanner fileScanner = new Scanner(new File(FOOD_DATA_FILE));
			fileScanner.nextLine();
			while (fileScanner.hasNextLine()) {
				String[] data = fileScanner.nextLine().split(",");
				String[] values = new String[FOOD_FIELD_NAMES.length - 1];
				values[0] = data[1].replaceAll("'", "''");
				values[1] = data[2];
				values[2] = data[3];
				values[3] = data[4].replaceAll("'", "''");
				theOne.mFoodDB.createRecord(Arrays.copyOfRange(FOOD_FIELD_NAMES, 1, FOOD_FIELD_NAMES.length), values);
				recordsCreated++;
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			return 0;
		}
		return recordsCreated;
	}
	
	/**
	 * The <code>signInUser</code> signs in a user with a registered username and password.
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @return Success if the user passes all the requirements for signing in. For every invalidation, the return
	 * displays the errorLabel.
	 */
	public String signInUser(String username, String password) {
		for (User user : theOne.mAllUsersList)
			if (user.getUsername().equalsIgnoreCase(username)) {
				try {
					ArrayList<ArrayList<String>> resultsList = theOne.mUserDB.getRecord(String.valueOf(user.getId()));
					String storedPassword = resultsList.get(0).get(6);
					if (password.equals(storedPassword)) {
						theOne.mCurrentUser = user;
						return "SUCCESS";
					}
				} catch (SQLException e) {}
				return "Incorrect password. Please try again.";
			}
		return "Username not found. Please try again.";
	}
	
	/**
	 * The <code>signUpUser</code> signs up a user with a username, name, age, height, weight, and password.
	 * @param username The username of the user.
	 * @param name The name of the user.
	 * @param age The age of the user.
	 * @param height The height of the user.
	 * @param weight The weight of the user.
	 * @param password The password of the user.
	 * @return Success if the user passes all the requirements for signing up. For every invalidation, the return displays
	 * the errorLabel.
	 */
	public String signUpUser(String username, String name, String age, String height, String weight, String password) {
		if (username.isEmpty() || name.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty() || password.isEmpty())
			return "";
		if (!isValidAge(age) || !isValidHeight(height) || !isValidWeight(weight) || username.isEmpty() || name.isEmpty() || password.isEmpty()) {
			if (age.isEmpty() || height.isEmpty() || weight.isEmpty())
				return "";
			else if (!age.isEmpty())
				return "Please enter a valid age.";
			else if (!height.isEmpty())
				return "Please enter a valid height.";
			else if (!weight.isEmpty())
				return "Please enter a valid weight.";
		}
		if (!isValidPassword(password) && !password.isEmpty() || username.isEmpty() || name.isEmpty() || age.isEmpty() || height.isEmpty() || weight.isEmpty())
			return "Password: 8-16 characters, 1 lowercase, 1 uppercase, 1 digit, 1 symbol";
		
		for (User user : theOne.mAllUsersList)
			if (user.getUsername().equalsIgnoreCase(username))
				return "Username already exists. Please try a different username.";
		
		String[] values = {username, name, age, height, weight, password};
		
		try {
			int id = theOne.mUserDB.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES, 1, USER_FIELD_NAMES.length), values);
			User newUser = new User(id, username, name, Integer.parseInt(age), Integer.parseInt(height), Integer.parseInt(weight));
			theOne.mAllUsersList.add(newUser);
			theOne.mCurrentUser = newUser;
			return "SUCCESS";
		} catch (SQLException e) {
			return "Account not created. Please try again.";
		}
	}
	
	/**
	 * The <code>isValidPassword</code> checks to see whether or not a created password is valid.
	 * @param password The created password from the user.
	 * @return The errorLabel set to true if false or the errorLabel set to false if true.
	 */
	public boolean isValidPassword(String password) {
		return password.matches("((?=.*[a-z])(?=.*[0-9])(?=.*[@#$%!])(?=.*[A-Z]).{8,16})");
	}
	
	/**
	 * The <code>isValidAge</code> checks to see whether or not an age entered is valid.
	 * @param age The age entered from the user.
	 * @return The errorLabel set to true if false or the errorLabel set to false if true.
	 */
	public boolean isValidAge(String age) {
		return age.matches("[0-9]+");
	}
	
	/**
	 * The <code>isValidHeight</code> checks to see whether or not a height entered is valid.
	 * @param height The height entered from the user.
	 * @return The errorLabel set to true if false or the errorLabel set to false is true.
	 */
	public boolean isValidHeight(String height) {
		return height.matches("[0-9]+");
	}
	
	/**
	 * The <code>isValidWeight</code> checks to see whether or not a weight entered is valid.
	 * @param weight The weight entered from the user.
	 * @return The errorLabel set to true if false or the errorLabel set to false is true.
	 */
	public boolean isValidWeight(String weight) {
		return weight.matches("[0-9]+");
	}
	
	/**
	 * The <code>getFoodsForCurrentUser</code> gets the foods for the current user, logged in.
	 * @return The foods of the current user.
	 */
	public ObservableList<Food> getFoodsForCurrentUser() {
		ObservableList<Food> userFoodsList = FXCollections.observableArrayList();
		if (theOne.mCurrentUser != null) {
			try {
				ArrayList<ArrayList<String>> resultsList = theOne.mUserMealDB.getRecord(String.valueOf(mCurrentUser.getId()));
				for (ArrayList<String> values : resultsList) {
					int foodId = Integer.parseInt(values.get(1));
					for (Food f : theOne.mAllFoodsList)
						if (f.getId() == foodId)
							userFoodsList.add(f);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userFoodsList;
	}
	
	/**
	 * The <code>addFood</code> adds a new food to the current list of food.
	 * @param name The name of the food.
	 * @param calories The calories of the food.
	 * @param weight The weight in grams of the food.
	 * @param measure The measure of the food.
	 * @return Adding in food if all fields are met.
	 */
	public boolean addFood(String name, int calories, double weight, String measure) {
		String[] values = {name, String.valueOf(calories), String.valueOf(weight), measure};
		try {
			int id = theOne.mCheatMealDB.createRecord(Arrays.copyOfRange(FOOD_FIELD_NAMES, 1, FOOD_FIELD_NAMES.length), values);
			theOne.mAllFoodsList.add(new Food(id, name, calories, weight, measure));
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * The <code>filter</code> filters the database with fields of calories.
	 * @param calories The calories of the food.
	 * @return The filtered list of foods.
	 */
	public ObservableList<Food> filter(double calories) {
		ObservableList<Food> filteredFoodsList = FXCollections.observableArrayList();
		for (Food f : theOne.mAllFoodsList) {
			if (f.getCalories() >= calories)
				filteredFoodsList.add(f);
		}
		return filteredFoodsList;
	}
	
	/**
	 * The <code>getCurrentUser</code> gets the current user.
	 * @return The current user.
	 */
	public User getCurrentUser() {
		return mCurrentUser;
	}
	
	/**
	 * The <code>getAllUsers</code> gets all the users from the database.
	 * @return The users from the database.
	 */
	public ObservableList<User> getAllUsers() {
		return theOne.mAllUsersList;
	}
	
	/**
	 * The <code>getAllFoods</code> gets all the foods from the database.
	 * @return The foods from the database.
	 */
	public ObservableList<Food> getAllFoods() {
		return theOne.mAllFoodsList;
	}
	
	/**
	 * The <code>getAllMeals</code> gets all the meals from the database.
	 * @return The meals from the database.
	 */
	public ObservableList<Meal> getAllMeals() {
		return theOne.mAllMealsList;
	}
}
