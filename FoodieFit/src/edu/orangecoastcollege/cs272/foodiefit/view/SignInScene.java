package edu.orangecoastcollege.cs272.foodiefit.view;

import edu.orangecoastcollege.cs272.foodiefit.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The <code>SignInScene</code> displays the sign in scene to the user, which requires
 * an email and password to view the foods list.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class SignInScene {
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private TextField usernameTF;
	@FXML
	private TextField passwordTF;
	@FXML
	private Label usernameErrorLabel;
	@FXML
	private Label passwordErrorLabel;
	@FXML
	private Label signInErrorLabel;
	
	@FXML
	private Object signIn() {
		String username = usernameTF.getText();
		String password = passwordTF.getText();
		
		if (username.isEmpty())
			usernameErrorLabel.setVisible(true);
		else if (!username.isEmpty())
			usernameErrorLabel.setVisible(false);
		if (password.isEmpty())
			passwordErrorLabel.setVisible(true);
		else if (!password.isEmpty())
			passwordErrorLabel.setVisible(false);
		if (usernameErrorLabel.isVisible() || passwordErrorLabel.isVisible())
			return null;
		
		String result = controller.signInUser(username, password);
		if (result.equalsIgnoreCase("SUCCESS")) {
			ViewNavigator.loadScene("Food List", ViewNavigator.FOOD_LIST_SCENE);
		}
		else {
			signInErrorLabel.setText(result);
			signInErrorLabel.setVisible(true);
		}
		return this;
	}
	
	@FXML
	private Object loadSignUp() {
		ViewNavigator.loadScene("Sign Up", ViewNavigator.SIGN_UP_SCENE);
		return this;
	}
}
