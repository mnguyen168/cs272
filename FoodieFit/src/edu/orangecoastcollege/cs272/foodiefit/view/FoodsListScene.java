package edu.orangecoastcollege.cs272.foodiefit.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.foodiefit.controller.Controller;
import edu.orangecoastcollege.cs272.foodiefit.model.Food;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;

public class FoodsListScene implements Initializable {
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private ListView<Food> allFoodsLV;
	@FXML
	private Slider caloriesSlider;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		allFoodsLV.setItems(controller.getAllFoods());
	}
	
	@FXML
	public Object calculateBMR() {
		ViewNavigator.loadScene("Calculate BMR", ViewNavigator.BMR_SCENE);
		return this;
	}
	
	@FXML
	public Object addFood() {
		ViewNavigator.loadScene("Add Food...", ViewNavigator.ADD_FOOD_SCENE);
		return this;
	}
	
	@FXML
	public void filter() {
		ObservableList<Food> foodsList;
		foodsList = controller.filter(caloriesSlider.getValue());
		allFoodsLV.setItems(foodsList);
	}
}
