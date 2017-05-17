package edu.orangecoastcollege.cs272.ic14.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.ic14.controller.Controller;
import edu.orangecoastcollege.cs272.ic14.model.VideoGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * The <code>ViewInventoryScene</code> displays the inventory scene to the user.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class ViewInventoryScene implements Initializable {

	private static Controller controller = Controller.getInstance();

	@FXML
	private ListView<VideoGame> userVideoGamesLV;
	@FXML
	private Label userLabel;
	
	/**
	 * The <code>initialize</code> initializes the database with the inventory of video
	 * games for the current user.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userLabel.setText(controller.getCurrentUser().getName());
		userVideoGamesLV.setItems(controller.getGamesForCurrentUser());
	}
	
	/**
	 * The <code>backToAllGames</code> allows the user to go back to the video games list
	 * scene.
	 * @return When called upon, brings the user back to the video games list scene.
	 */
	@FXML
	public Object backToAllGames()
	{
		ViewNavigator.loadScene("Video Game List", ViewNavigator.VIDEO_GAME_LIST_SCENE);
		return this;
	}
}
