package org.makerminds.intership.java.controller;
/*
 * @author Anduela Nurshaba
 */

import java.util.Iterator;
import org.makerminds.intership.java.restaurantpoint.model.Menu;
import org.makerminds.intership.java.restaurantpoint.model.MenuType;
import org.makerminds.intership.java.restaurantpoint.model.Product;

public class MenuItemManagerController {

	public MenuItemManagerController() {

	}

	private static Product createProduct(String[] productDataArray) {
		int id = Integer.valueOf(productDataArray[0]);
		String productName = productDataArray[1];
		double price = Double.valueOf(productDataArray[2]);
		MenuType menuType = getMenuType(productDataArray[3]);
		Product product = new Product(id, productName, price, menuType);
		return product;
	}

	private static MenuType getMenuType(String menuTypeAsString) {
		if (menuTypeAsString.equals("Meal")) {
			return MenuType.MEAL;
		} else {
			return MenuType.DRINK;
		}

	}

	public static void addProduct(String[] productDataArray, Menu menu) {

		Product product = createProduct(productDataArray);
		menu.getProductList().add(product);

	}

	public static void deleteProduct(String[] productDataArray, Menu menu) {
		Product productToDelete = createProduct(productDataArray);
		Iterator<Product> iterator = menu.getProductList().iterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			if (product.getName().equals(productToDelete.getName())) {
				iterator.remove();
			}
		}
	}

	public static void updateProduct(String[] oldProductDataArray, String[] newProductDataArray, Menu menu) {
		deleteProduct(oldProductDataArray, menu);
		addProduct(newProductDataArray, menu);
	}
}
