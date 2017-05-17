package edu.orangecoastcollege.cs272.foodiefit.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.foodiefit.controller.Controller;
import edu.orangecoastcollege.cs272.foodiefit.model.Food;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class FoodsListScene implements Initializable {
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private ListView<Food> allFoodsLV;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		allFoodsLV.setItems(controller.getAllFoods());
	}
	
	@FXML
	public Object calculateBMR() {
		ViewNavigator.loadScene("Calculate BMR", ViewNavigator.BMR_SCENE);
		return this;
	}
}
