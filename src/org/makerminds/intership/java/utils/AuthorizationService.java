package org.makerminds.intership.java.utils;
/*
 * @author Anduela Nurshaba
 */

import static org.makerminds.intership.java.restaurantpoint.login.model.UserFeature.*;

import java.util.Arrays;
import java.util.List;

import org.makerminds.intership.java.restaurantpoint.login.model.UserFeature;
import org.makerminds.intership.java.restaurantpoint.login.model.UserRole;

public class AuthorizationService {

	public static List<UserFeature> getUserFeatureListByUserRole(UserRole userRole) {
		switch (userRole) {
		case MANAGER:
			return Arrays.asList( RESTAURANT_MANAGER, NENU_MANAGER, MENU_ITEM_MANAGER, TABLE_MANAGER);
		case WAITER:
			return Arrays.asList(TABLE_RESERVATION,TABLE_ORDERS,SHOW_ORDERS);
		case CHEF:
			return Arrays.asList(ORDERED_ITEMS);
		default:
			throw new IllegalArgumentException("The user role" + userRole + "is not supossed!");
		}

	}
}
