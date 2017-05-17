package edu.orangecoastcollege.cs272.ic14.view;

import edu.orangecoastcollege.cs272.ic14.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The <code>SignUpScene</code> displays the sign up scene to the user, which requires
 * a name, email, and password.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class SignUpScene {

	private static Controller controller = Controller.getInstance();

	@FXML
	private TextField nameTF;
	@FXML
	private TextField emailAddressTF;
	@FXML
	private TextField passwordTF;
	@FXML
	private Label nameErrorLabel;
	@FXML
	private Label emailErrorLabel;
	@FXML
	private Label passwordErrorLabel;
	@FXML
	private Label signUpErrorLabel;
	
	/**
	 * The <code>signUp</code> signs up the user.
	 * @return If success, the user is allowed to log in through the sign in scene.
	 * Otherwise, the user must enter the correct requirements.
	 */
	@FXML
	public Object signUp() {
		String name = nameTF.getText();
		String email = emailAddressTF.getText();
		String password = passwordTF.getText();

		nameErrorLabel.setVisible(name.isEmpty());
		emailErrorLabel.setVisible(email.isEmpty());
		passwordErrorLabel.setVisible(password.isEmpty());

		if (nameErrorLabel.isVisible() || emailErrorLabel.isVisible() || passwordErrorLabel.isVisible())
			return null;

		String result = controller.signUpUser(name, email, password);
		if (result.equalsIgnoreCase("SUCCESS")) {
			signUpErrorLabel.setVisible(false);
			ViewNavigator.loadScene("Video Game List", ViewNavigator.VIDEO_GAME_LIST_SCENE);
			return this;
		}
		signUpErrorLabel.setText(result);
		signUpErrorLabel.setVisible(true);
		return null;
	}
	
	/**
	 * The <code>loadSignIn</code> loads up the sign in scene upon clicked.
	 * @return The sign in scene when called upon.
	 */
	@FXML
	public Object loadSignIn()
	{
		ViewNavigator.loadScene("Sign In", ViewNavigator.SIGN_IN_SCENE);
		return this;
	}

}
