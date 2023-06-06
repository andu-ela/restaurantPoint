package org.makerminds.intership.java.controller;
/*
 * @author Anduela Nurshaba
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.makerminds.intership.java.restaurantpoint.model.Menu;
import org.makerminds.intership.java.restaurantpoint.model.Product;
import org.makerminds.intership.java.restaurantpoint.model.Restaurant;

public class MenuManagerController {

	public MenuManagerController() {

	}

	private static Menu createMenu(String[] menuDataArray) {
		List<Product> productList = new ArrayList<>();
		Menu menu = new Menu(menuDataArray[0], productList);
		return menu;
	}

	public static void addMenu(String[] menuDataArray, Restaurant restaurant) {

		Menu menu = createMenu(menuDataArray);
		restaurant.getMenu().add(menu);

	}

	public static void deleteMenu(String[] menuDataArray, Restaurant restaurant) {
		Menu menuToDelete = createMenu(menuDataArray);
		Iterator<Menu> iterator = restaurant.getMenu().iterator();
		while (iterator.hasNext()) {
			Menu menu = iterator.next();
			if (menu.getName().equals(menuToDelete.getName())) {
				iterator.remove();
			}
		}
	}

	public static void updateMenu(String[] oldMenuDataArray, String[] newMenuDataArray, Restaurant restaurant) {
		deleteMenu(oldMenuDataArray, restaurant);
		addMenu(newMenuDataArray, restaurant);
	}
}
