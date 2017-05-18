package edu.orangecoastcollege.cs272.foodiefit.view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.foodiefit.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * The <code>BMRScene</code> calculates the BMR for the current user signed in.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class BMRScene implements Initializable {
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private TextArea bmrTA;
	@FXML
	private Label bmrLabel;
	
	/**
	 * The <code>initialize</code> initializes the calculated BMR once called upon.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		calculateBMR();
	}
	
	@FXML
	private Object calculateBMR() {
		DecimalFormat oneDP = new DecimalFormat("#.0");
		int age = controller.getCurrentUser().getAge();
		int height = controller.getCurrentUser().getHeight();
		int weight = controller.getCurrentUser().getWeight();
		double bmr = 66.0 + (6.23 * weight) + (12.7 * height) - (6.8 * age);
		
		String name = controller.getCurrentUser().getName();
		
		bmrTA.setText("Name:   " + name + "\n" +
						"Age:    " + age + "\n" +
						"Height: " + height + "\n" +
						"Weight: " + weight + "\n" + 
						"BMR:    " + String.valueOf(oneDP.format(bmr)));
		return bmrTA;
	}
	
	@FXML
	private Object backToAllFoods() {
		ViewNavigator.loadScene("Food List", ViewNavigator.FOOD_LIST_SCENE);
		return this;
	}
}
