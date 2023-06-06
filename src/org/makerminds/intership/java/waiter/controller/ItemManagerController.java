package org.makerminds.intership.java.waiter.controller;

/*
 * @author Anduela Nurshaba
 */

import java.util.Iterator;

import org.makerminds.intership.java.restaurantpoint.model.Menu;
import org.makerminds.intership.java.restaurantpoint.model.Product;

public class ItemManagerController {

	public ItemManagerController() {

	}

	private static Product createProduct(String[] productDataArray) {

		String productName = productDataArray[0];
		double price = Double.valueOf(productDataArray[1]);
		Product product = new Product(productName, price);

		return product;
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
}
