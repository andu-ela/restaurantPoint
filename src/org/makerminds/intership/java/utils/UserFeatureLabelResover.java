package org.makerminds.intership.java.utils;
/*
 * @author Anduela Nurshaba
 */

import java.util.HashMap;
import java.util.Map;

import org.makerminds.intership.java.restaurantpoint.login.model.UserFeature;

public class UserFeatureLabelResover {

	public static Map<UserFeature, String> userFeatureLabelMap;

	private static Map<UserFeature, String> createUserFeastureLabelMap() {
		if (userFeatureLabelMap == null) {
			userFeatureLabelMap = new HashMap<>();

			userFeatureLabelMap.put(UserFeature.RESTAURANT_MANAGER, "Restaurant Manager");
			userFeatureLabelMap.put(UserFeature.NENU_MANAGER, "Menu Manager");
			userFeatureLabelMap.put(UserFeature.MENU_ITEM_MANAGER, "Menu Item Manager");
			userFeatureLabelMap.put(UserFeature.TABLE_MANAGER, "Table Manager");
			userFeatureLabelMap.put(UserFeature.TABLE_ORDERS, "Table Order");
			userFeatureLabelMap.put(UserFeature.TABLE_RESERVATION, "Table Reservation");
			userFeatureLabelMap.put(UserFeature.SHOW_ORDERS, "Show Orders");
			userFeatureLabelMap.put(UserFeature.ORDERED_ITEMS, "Ordered Items");
		}
		return userFeatureLabelMap;
	}

	public static String getLabelResolver(UserFeature userFeature) {
		return createUserFeastureLabelMap().get(userFeature);
	}

}
