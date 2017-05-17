package edu.orangecoastcollege.cs272.ic14.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import edu.orangecoastcollege.cs272.ic14.model.DBModel;
import edu.orangecoastcollege.cs272.ic14.model.User;
import edu.orangecoastcollege.cs272.ic14.model.VideoGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The <code>Controller</code> connects the databases to the JavaFX user interface. The Controller is a singleton
 * which serves as the one intermediary between the back-end and front-end of the application.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class Controller {

	private static Controller theOne;

	private static final String DB_NAME = "vg_inventory.db";
	
	private static final String USER_TABLE_NAME = "user";
	private static final String[] USER_FIELD_NAMES = { "id", "name", "email", "role", "password"};
	private static final String[] USER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT"};

	private static final String VIDEO_GAME_TABLE_NAME = "video_game";
	private static final String[] VIDEO_GAME_FIELD_NAMES = { "id", "name", "platform", "year", "genre", "publisher"};
	private static final String[] VIDEO_GAME_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "INTEGER", "TEXT", "TEXT"};
	private static final String VIDEO_GAME_DATA_FILE = "videogames_lite.csv";

	private static final String USER_GAMES_TABLE_NAME = "user_games";
	private static final String[] USER_GAMES_FIELD_NAMES = { "user_id", "game_id"};
	private static final String[] USER_GAMES_FIELD_TYPES = { "INTEGER", "INTEGER"};

	private User mCurrentUser;
	private DBModel mUserDB;
	private DBModel mVideoGameDB;
	private DBModel mUserGamesDB;
	
	private ObservableList<User> mAllUsersList;
	private ObservableList<VideoGame> mAllGamesList;
	
	private Controller() {
	}
	
	/**
	 * The <code>getInstance</code> gets the instance of the Controller.
	 * @return The controller.
	 */
	public static Controller getInstance() {
		if (theOne == null) {
			theOne = new Controller();
			theOne.mAllUsersList = FXCollections.observableArrayList();
			theOne.mAllGamesList = FXCollections.observableArrayList();

			try {
				theOne.mUserDB = new DBModel(DB_NAME, USER_TABLE_NAME, USER_FIELD_NAMES, USER_FIELD_TYPES);

				ArrayList<ArrayList<String>> resultsList = theOne.mUserDB.getAllRecords();
				
				for (ArrayList<String> values : resultsList) {
					int id = Integer.parseInt(values.get(0));
					String name = values.get(1);
					String email = values.get(2);
					String role = values.get(3);
					theOne.mAllUsersList.add(new User(id, name, email, role));
				}
				
				theOne.mVideoGameDB = new DBModel(DB_NAME, VIDEO_GAME_TABLE_NAME, VIDEO_GAME_FIELD_NAMES, VIDEO_GAME_FIELD_TYPES);
				theOne.initializeVideoGameDBFromFile();
				resultsList = theOne.mVideoGameDB.getAllRecords();
				for (ArrayList<String> values : resultsList)
				{
					int id = Integer.parseInt(values.get(0));
					String name = values.get(1);
					String platform = values.get(2);
					int year = Integer.parseInt(values.get(3));
					String genre = values.get(4);
					String publisher = values.get(5);
					theOne.mAllGamesList.add(new VideoGame(id, name, platform, year, genre, publisher));
				}
				
				theOne.mUserGamesDB= new DBModel(DB_NAME, USER_GAMES_TABLE_NAME, USER_GAMES_FIELD_NAMES, USER_GAMES_FIELD_TYPES);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return theOne;
	}
	
	/**
	 * The <code>isValidPassword</code> checks to see whether or not a created password is valid.
	 * @param password The created password from the user.
	 * @return The errorLabel set to true if false or the errorLabel set to false if true.
	 */
	public boolean isValidPassword(String password)
	{
		// Valid password must contain (see regex below):
		// At least one lower case letter
		// At least one digit
		// At least one special character (@, #, $, %, !)
		// At least one upper case letter
		// At least 8 characters long, but no more than 16
		return password.matches("((?=.*[a-z])(?=.*d)(?=.*[@#$%!])(?=.*[A-Z]).{8,16})");
	}
	
	/**
	 * The <code>isValidEmail</code> checks to see whether or not a created email is valid.
	 * @param email The created email from the user.
	 * @return The errorLabel set to true if false or the errorLable set to false if true.
	 */
	public boolean isValidEmail(String email)
	{
		return email.matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}
	
	/**
	 * The <code>signUpUser</code> signs up a user with a name, email, and password.
	 * @param name The name of the user.
	 * @param email The email of the user.
	 * @param password The password of the user.
	 * @return Success if the user passes all the requirements for signing up. For every invalidation,
	 * the return displays the errorLabel.
	 */
	public String signUpUser(String name, String email, String password)
	{
		if (!isValidEmail(email))
			return "Email address is not valid.  Please use different address.";
		
		for (User u : theOne.mAllUsersList)
			if (u.getEmail().equalsIgnoreCase(email))
				return "Email address already used.  Please sign in or use different email.";
		
		if (!isValidPassword(password))
			return "Password must be at least 8 characters, including 1 upper case letter, 1 lower case letter, 1 digit and one symbol.";
		
		try {
			// In practice, passwords should always be encrypted before storing in database:
			// See http://www.jasypt.org/howtoencryptuserpasswords.html for a useful tutorial
			String[] values = {name, email, "STANDARD", password};
			int id = theOne.mUserDB.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES, 1, USER_FIELD_NAMES.length), values);
			mCurrentUser = new User(id, name, email, "STANDARD");
			theOne.mAllUsersList.add(mCurrentUser);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error creating user, please try again.";
		} 		
		return "SUCCESS";
	}	
	
	/**
	 * The <code>signInUser</code> signs in a user with a registered email and password.
	 * @param email The email of the user.
	 * @param password The password of the user.
	 * @return SUccess if the user passes all the requirements for signing in. For every invalidation,
	 * the return displays the errorLabel.
	 */
	public String signInUser(String email, String password) {
		for (User u : theOne.mAllUsersList)
			if (u.getEmail().equalsIgnoreCase(email))
			{
				try {
					ArrayList<ArrayList<String>> resultsList = theOne.mUserDB.getRecord(String.valueOf(u.getId()));
					String storedPassword = resultsList.get(0).get(4);
					if (password.equals(storedPassword))
					{
						mCurrentUser = u;
						return "SUCCESS";
					}
						
						
				} catch (Exception e) {}
				return "Incorrect password.  Please try again.";		
			}		
		return "Email address not found.  Please try again.";
	}
	
	/**
	 * The <code>getCurrentUser</code> gets the current user.
	 * @return The current user.
	 */
	public User getCurrentUser()
	{
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
	 * The <code>getAllVideoGames</code> gets all the video games from the database.
	 * @return The video games from the database.
	 */
	public ObservableList<VideoGame> getAllVideoGames() {
		return theOne.mAllGamesList;
	}
	
	/**
	 * The <code>getDisticntPlatforms</code> gets distinct platforms from the database.
	 * @return The distinct platforms from the database.
	 */
	public ObservableList<String> getDistinctPlatforms() {
		ObservableList<String> platforms = FXCollections.observableArrayList();
		platforms.add("");
		for (VideoGame vg : theOne.mAllGamesList)
			if (!platforms.contains(vg.getPlatform()))
				platforms.add(vg.getPlatform());
		FXCollections.sort(platforms);
		return platforms;
	}
	
	/**
	 * The <code>getDistinctPublishers</code> gets distinct publishers from the database.
	 * @return The distinct publishers from the database.
	 */
	public ObservableList<String> getDistinctPublishers() {
		ObservableList<String> publishers = FXCollections.observableArrayList();
		publishers.add("");
		for (VideoGame vg : theOne.mAllGamesList)
			if (!publishers.contains(vg.getPublisher()))
				publishers.add(vg.getPublisher());
		FXCollections.sort(publishers);
		return publishers;
	}
	
	/**
	 * The <code>getGamesForCurrentUser</code> gets the games for the user current user, logged in.
	 * @return The games of the current user.
	 */
	public ObservableList<VideoGame> getGamesForCurrentUser()
	{
		ObservableList<VideoGame> userGamesList = FXCollections.observableArrayList();
		if (mCurrentUser != null)
		{
			try {
				ArrayList<ArrayList<String>> resultsList= theOne.mUserGamesDB.getRecord(String.valueOf(mCurrentUser.getId()));
				for (ArrayList<String> values : resultsList)
				{
					int gameId = Integer.parseInt(values.get(1));
					for (VideoGame vg : theOne.mAllGamesList)
						if (vg.getId() == gameId)
							userGamesList.add(vg);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return userGamesList;
	}
	
	/**
	 * The <code>addGameToUsersInventory</code> adds a game to the current user, logged in, to the inventory.
	 * @param selectedGame The selected game selected from the user.
	 * @return When called, the selected game will be added into inventory. Otherwise, nothing will be added.
	 */
	public boolean addGameToInventory(VideoGame selectedGame)  {
		ObservableList<VideoGame> userGamesList = theOne.getGamesForCurrentUser();
		if (userGamesList.contains(selectedGame))
			return false;
		String[] values = {String.valueOf(mCurrentUser.getId()), String.valueOf(selectedGame.getId())};
		try {
			this.mUserGamesDB.createRecord(USER_GAMES_FIELD_NAMES, values);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * The <code>initializeVideoGameDBFromFile</code> initializes the video game database from the csv file.
	 * @return The video game database from the csv file.
	 * @throws SQLException
	 */
	private int initializeVideoGameDBFromFile() throws SQLException {
		int recordsCreated = 0;

		// If the result set contains results, database table already has
		// records, no need to populate from file (so return false)
		if (theOne.mUserDB.getRecordCount() > 0)
			return 0;

		try {
			// Otherwise, open the file (CSV file) and insert user data
			// into database
			Scanner fileScanner = new Scanner(new File(VIDEO_GAME_DATA_FILE));
			// First read is for headings:
			fileScanner.nextLine();
			// All subsequent reads are for user data
			while (fileScanner.hasNextLine()) {
				String[] data = fileScanner.nextLine().split(",");
				// Length of values is one less than field names because values
				// does not have id (DB will assign one)
				String[] values = new String[VIDEO_GAME_FIELD_NAMES.length - 1];
				values[0] = data[1].replaceAll("'", "''");
				values[1] = data[2];
				values[2] = data[3];
				values[3] = data[4];
				values[4] = data[5];	
				theOne.mVideoGameDB.createRecord(Arrays.copyOfRange(VIDEO_GAME_FIELD_NAMES, 1, VIDEO_GAME_FIELD_NAMES.length), values);
				recordsCreated++;
			}

			// All done with the CSV file, close the connection
			fileScanner.close();
		} catch (FileNotFoundException e) {
			return 0;
		}
		return recordsCreated;
	}
	
	/**
	 * The <code>filter</code> filters the database with fields of publisher, platform, and year.
	 * @param publisher The publisher of the video game.
	 * @param platform The platform of the video game.
	 * @param year The year of the video game.
	 * @return The filtered list of video games.
	 */
	public ObservableList<VideoGame> filter(String publisher, String platform, double year) {
		ObservableList<VideoGame> filteredVideoGamesList = FXCollections.observableArrayList();
		for (VideoGame vg : theOne.mAllGamesList) {
			if ((publisher == null || vg.getPublisher().equals(publisher) || publisher.isEmpty()) && (platform == null || vg.getPlatform().equals(platform) || platform.isEmpty())
					&& (vg.getYear() >= year))
				filteredVideoGamesList.add(vg);
		}
		return filteredVideoGamesList;
	}
	
}
