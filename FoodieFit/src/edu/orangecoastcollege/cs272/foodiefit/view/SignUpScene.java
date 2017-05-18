package edu.orangecoastcollege.cs272.foodiefit.view;

import edu.orangecoastcollege.cs272.foodiefit.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpScene {
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private TextField usernameTF;
	@FXML
	private TextField passwordTF;
	@FXML
	private TextField nameTF;
	@FXML
	private TextField ageTF;
	@FXML
	private TextField heightTF;
	@FXML
	private TextField weightTF;
	@FXML
	private Label usernameErrorLabel;
	@FXML
	private Label passwordErrorLabel;
	@FXML
	private Label nameErrorLabel;
	@FXML
	private Label ageErrorLabel;
	@FXML
	private Label heightErrorLabel;
	@FXML
	private Label weightErrorLabel;
	@FXML
	private Label signUpErrorLabel;
	
	@FXML
	public Object signUp() {
		String username = usernameTF.getText();
		String password = passwordTF.getText();
		String name = nameTF.getText();
		String age = ageTF.getText();
		String height = heightTF.getText();
		String weight = weightTF.getText();
		
		if (username.isEmpty())
			usernameErrorLabel.setVisible(true);
		else if (!username.isEmpty())
			usernameErrorLabel.setVisible(false);
		if (password.isEmpty())
			passwordErrorLabel.setVisible(true);
		else if (!password.isEmpty())
			usernameErrorLabel.setVisible(false);
		if (name.isEmpty())
			nameErrorLabel.setVisible(true);
		else if (!name.isEmpty())
			nameErrorLabel.setVisible(false);
		if (age.isEmpty())
			ageErrorLabel.setVisible(true);
		else if (!age.isEmpty())
			ageErrorLabel.setVisible(false);
		if (height.isEmpty())
			heightErrorLabel.setVisible(true);
		else if (!height.isEmpty())
			heightErrorLabel.setVisible(false);
		if (weight.isEmpty())
			weightErrorLabel.setVisible(true);
		else if (!weight.isEmpty())
			weightErrorLabel.setVisible(false);
		
		String result = controller.signUpUser(username, name, age, height, weight, password);
		if (result.equalsIgnoreCase("SUCCESS")) {
			loadSignIn();
		}
		else {
			signUpErrorLabel.setText(result);
			signUpErrorLabel.setVisible(true);
		}
		return this;
	}
	
	@FXML
	public Object loadSignIn() {
		ViewNavigator.loadScene("Sign In", ViewNavigator.SIGN_IN_SCENE);
		return this;
	}
}
