package org.makerminds.intership.java.restaurantpoint.dataprovider;
/*
 * @author Anduela Nurshaba
 */

import java.util.ArrayList;
import java.util.List;

import org.makerminds.intership.java.restaurantpoint.login.model.User;
import org.makerminds.intership.java.restaurantpoint.login.model.UserRole;

public class UserDataProvider {

	private List<User> userList = new ArrayList<>();
	private RestaurantDataProvider restaurantDataProvider = new RestaurantDataProvider();

	public UserDataProvider() {
		User adminUser = new User("admin1", "100", UserRole.MANAGER);
		adminUser.setRestaurantList(restaurantDataProvider.getRestaurant());
		userList.add(adminUser);
		User waiterUser = new User("admin2", "200", UserRole.WAITER);
		waiterUser.setRestaurantList(restaurantDataProvider.getRestaurant());
		userList.add(waiterUser);
		User chefUser = new User("admin3", "300", UserRole.CHEF);
		chefUser.setRestaurantList(restaurantDataProvider.getRestaurant());
		userList.add(chefUser);
	
	}

	public List<User> getUserList() {
		return userList;
	}

}
