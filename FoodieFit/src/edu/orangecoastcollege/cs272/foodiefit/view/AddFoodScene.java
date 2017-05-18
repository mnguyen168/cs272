package edu.orangecoastcollege.cs272.foodiefit.view;

import edu.orangecoastcollege.cs272.foodiefit.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The <code>AddFoodScene</code> allows the user to add a new food with a name, calories,
 * weight, and measure.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class AddFoodScene {
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private TextField nameTF;
	@FXML
	private TextField caloriesTF;
	@FXML
	private TextField weightTF;
	@FXML
	private TextField measureTF;
	@FXML
	private Label nameLabel;
	@FXML
	private Label caloriesLabel;
	@FXML
	private Label weightLabel;
	@FXML
	private Label measureLabel;
	
	@FXML
	private void saveNewFood() {
		if (nameTF.getText().isEmpty() ||
				caloriesTF.getText().isEmpty() ||
				weightTF.getText().isEmpty() ||
				measureTF.getText().isEmpty()) {
			return;
		}
		
		String name = nameTF.getText();
		int calories = Integer.parseInt(caloriesTF.getText());
		double weight = Double.parseDouble(weightTF.getText());
		String measure = measureTF.getText();
		
		controller.addFood(name, calories, weight, measure);
		clearFood();
	}
	
	@FXML
	private void clearFood() {
		nameTF.clear();
		caloriesTF.clear();
		weightTF.clear();
		measureTF.clear();
		
		ViewNavigator.loadScene("Food List", ViewNavigator.FOOD_LIST_SCENE);
	}
}
