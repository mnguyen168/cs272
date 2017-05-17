package edu.orangecoastcollege.cs272.ic14.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.ic14.controller.Controller;
import edu.orangecoastcollege.cs272.ic14.model.VideoGame;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;

/**
 * The <code>VideoGamesListScene</code> displays the video games list scene to the user,
 * which allows the user to view all the video games.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class VideoGamesListScene implements Initializable {

	private static Controller controller = Controller.getInstance();

	@FXML
	private ListView<VideoGame> allVideoGamesLV;
	@FXML
	private ComboBox<String> publishersCB;
	@FXML
	private ComboBox<String> platformsCB;
	@FXML
	private Slider yearSlider;
	
	/**
	 * The <code>initialize</code> initializes the database with the platform, publishers
	 * of the video games list.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		allVideoGamesLV.setItems(controller.getAllVideoGames());
		platformsCB.setItems(controller.getDistinctPlatforms());
		publishersCB.setItems(controller.getDistinctPublishers());
	}
	
	/**
	 * The <code>addGameToInventory</code> adds a selected game into the inventory.
	 * @return WHen called upon, adds the game to inventory.
	 */
	@FXML
	public Object addGameToInventory()
	{
		VideoGame selectedGame = allVideoGamesLV.getSelectionModel().getSelectedItem();
		if (controller.addGameToInventory(selectedGame))
			System.out.println("SUCCESS");
		else
			System.out.println("Could not add game.");
		return this;
	}
	
	/**
	 * The <code>viewInventory</code> allows the user to view the inventory of video games
	 * added.
	 * @return The list of video games added into inventory.
	 */
	@FXML
	public Object viewInventory()
	{
		ViewNavigator.loadScene("User's Video Games", ViewNavigator.VIEW_INVENTORY_SCENE);
		return this;
	}
	
	/**
	 * The <code>filter</code> filters the video games database from either publishers, platforms,
	 * or years.
	 */
	@FXML
	private void filter() {
		ObservableList<VideoGame> gamesList;
		gamesList = controller.filter(publishersCB.getSelectionModel().getSelectedItem(), platformsCB.getSelectionModel().getSelectedItem(), yearSlider.getValue());
		allVideoGamesLV.setItems(gamesList);
	}
}
