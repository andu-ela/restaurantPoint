package org.makerminds.intership.java.restaurantpoint.controller.login;
/*
 * @author Anduela Nurshaba
 */

import org.makerminds.intership.java.restaurantpoint.dataprovider.UserDataProvider;
import org.makerminds.intership.java.restaurantpoint.login.model.User;

public class LoginController {

	private UserDataProvider userDataProvider = new UserDataProvider();

	private User loggedInUser = null;

	private static final LoginController INSTANCE = new LoginController();

	private LoginController() {

	}

	public void loginUser(String username, String password) {
		for (User user : userDataProvider.getUserList()) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				loggedInUser = user;
			}
		}
	}

	public User getLoggedInUser() {
		return loggedInUser;

	}

	public static LoginController getInstance() {
		return INSTANCE;

	}

}
