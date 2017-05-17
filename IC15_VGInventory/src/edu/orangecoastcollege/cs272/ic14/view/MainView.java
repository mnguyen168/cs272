package edu.orangecoastcollege.cs272.ic14.view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The <code>MainView</code> displays the sign in scene to the video games list.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class MainView extends Application {
	
	/**
	 * The <code>start</code> displays the window from the settings set within the SignInScene.fxml.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {		
		// Set stage only needs to be called once for the view navigator
		ViewNavigator.setStage(primaryStage);
		ViewNavigator.loadScene("Welcome to VG in.vent.ory", ViewNavigator.SIGN_IN_SCENE);
	}
	
	/**
	 * The <code>main</code> launches the window to display to the user.
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
