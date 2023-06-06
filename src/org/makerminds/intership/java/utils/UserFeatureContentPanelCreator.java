package org.makerminds.intership.java.utils;

import java.util.HashMap;
import java.util.Map;

import org.makermind.intership.java.chef.view.OrderedItems;
import org.makermind.intership.java.view.IView;
import org.makermind.intership.java.view.MenuItemManager;
import org.makermind.intership.java.view.MenuManager;
import org.makermind.intership.java.view.RestaurantManager;
import org.makermind.intership.java.view.TableManager;
import org.makermind.intership.waiter.view.OrderView;
import org.makermind.intership.waiter.view.TableReservation;
import org.makermind.intership.waiter.view.TableView;

import org.makerminds.intership.java.restaurantpoint.login.model.UserFeature;

public class UserFeatureContentPanelCreator {
	
	private static Map<UserFeature, IView> userFeatureIViewMap;
	
	private static Map<UserFeature, IView> createUserFeastureIViewMap(){
		if(userFeatureIViewMap == null) {
			userFeatureIViewMap = new HashMap<>();

			userFeatureIViewMap.put(UserFeature.RESTAURANT_MANAGER, new RestaurantManager() );
			userFeatureIViewMap.put(UserFeature.NENU_MANAGER, new MenuManager());
			userFeatureIViewMap.put(UserFeature.MENU_ITEM_MANAGER, new MenuItemManager());
			userFeatureIViewMap.put(UserFeature.TABLE_MANAGER, new TableManager() );
			userFeatureIViewMap.put(UserFeature.TABLE_ORDERS, new TableView());
			userFeatureIViewMap.put(UserFeature.TABLE_RESERVATION, new TableReservation());
			userFeatureIViewMap.put(UserFeature.SHOW_ORDERS, new OrderView());
			userFeatureIViewMap.put(UserFeature.ORDERED_ITEMS, new OrderedItems());
		}
		return  userFeatureIViewMap;
	}
	
	public static IView getUserFeatureIView(UserFeature userFeature) {
		createUserFeastureIViewMap();
		return userFeatureIViewMap.get(userFeature);
	}

}
