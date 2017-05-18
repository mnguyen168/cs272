package edu.orangecoastcollege.cs272.foodiefit.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The <code>ViewNavigator</code> stores all the public scenes available to the application.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class ViewNavigator {
	
	/**
	 * The <code>SIGN_IN_SCENE</code> links to the SignInScene.fxml.
	 */
	public static final String SIGN_IN_SCENE = "SignInScene.fxml";
	
	/**
	 * The <code>SIGN_UP_SCENE</code> links to the SignUpScene.fxml.
	 */
	public static final String SIGN_UP_SCENE = "SignUpScene.fxml";
	
	/**
	 * The <code>FOOD_LIST_SCENE</code> links to the FoodsListScene.fxml.
	 */
	public static final String FOOD_LIST_SCENE = "FoodsListScene.fxml";
	
	/**
	 * The <code>BMR_SCENE</code> links to the BMRScene.fxml.
	 */
	public static final String BMR_SCENE = "BMRScene.fxml";
	
	/**
	 * The <code>ADD_FOOD_SCENE</code> links to the AddFoodScene.fxml.
	 */
	public static final String ADD_FOOD_SCENE = "AddFoodScene.fxml";
	
	/**
	 * The <code>mainStage</code> is the main stage for all scenes to be used.
	 */
	public static Stage mainStage;
	
	/**
	 * The <code>setStage</code> sets the main stage as the stage.
	 * @param stage The stage to be staged.
	 */
	public static void setStage(Stage stage) {
		mainStage = stage;
	}
	
	/**
	 * The <code>loadScene</code> loads the scene depending on the actions called for the
	 * scene to be logged onto the main stage.
	 * @param title The title of the scene.
	 * @param sceneFXML The sceneFXML of the scene.
	 */
	public static void loadScene(String title, String sceneFXML) {
		try {
			mainStage.setTitle(title);
			Scene scene = new Scene(FXMLLoader.load(ViewNavigator.class.getResource(sceneFXML)));
			mainStage.setScene(scene);
			mainStage.show();
		} catch (IOException e) {
			System.err.println("Error loading: " + sceneFXML + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}
}
