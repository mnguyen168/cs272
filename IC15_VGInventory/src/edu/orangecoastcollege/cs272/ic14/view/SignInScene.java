package edu.orangecoastcollege.cs272.ic14.view;

import edu.orangecoastcollege.cs272.ic14.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The <code>SignInScene</code> displays the sign in scene to the user, which requires
 * an email and password to view the video games list.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class SignInScene {

	private static Controller controller = Controller.getInstance();

	@FXML
	private TextField emailAddressTF;
	@FXML
	private TextField passwordTF;
	@FXML
	private Label emailErrorLabel;
	@FXML
	private Label passwordErrorLabel;
	@FXML
	private Label signInErrorLabel;

	/**
	 * The <code>signIn</code> signs in the user.
	 * @return If success, the user is allowed to view the video games list. Otherwise, the user
	 * must enter the correct credentials.
	 */
	@FXML
	public Object signIn() {
		String email = emailAddressTF.getText();
		String password = passwordTF.getText();

		emailErrorLabel.setVisible(email.isEmpty());
		passwordErrorLabel.setVisible(password.isEmpty());

		if (emailErrorLabel.isVisible() || passwordErrorLabel.isVisible())
			return null;

		String result = controller.signInUser(email, password);
		if (result.equalsIgnoreCase("SUCCESS")) {
			signInErrorLabel.setVisible(false);
			// Go to next scene
			ViewNavigator.loadScene("All Video Games", ViewNavigator.VIDEO_GAME_LIST_SCENE);
			return this;
		}
		signInErrorLabel.setText(result);
		signInErrorLabel.setVisible(true);
		return null;
	}
	
	/**
	 * The <code>loadSignUp</code> loads up the sign up scene upon clicked.
	 * @return The sign up scene when called upon.
	 */
	@FXML
	public Object loadSignUp()
	{
		ViewNavigator.loadScene("Sign Up", ViewNavigator.SIGN_UP_SCENE);
		return this;
	}

}
