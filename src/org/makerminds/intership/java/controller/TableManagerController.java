package org.makerminds.intership.java.controller;
/*
 * @author Anduela Nurshaba
 */

import java.util.Iterator;
import org.makerminds.intership.java.restaurantpoint.model.Restaurant;
import org.makerminds.intership.java.restaurantpoint.model.Table;

public class TableManagerController {

	public TableManagerController() {

	}

	private static Table createTable(String[] tableDataArray) {
		int id = Integer.valueOf(tableDataArray[0]);
		int seats = Integer.valueOf(tableDataArray[1]);
		Table table = new Table(id, seats);

		return table;
	}

	public static void addTable(String[] tableDataArray, Restaurant restaurant) {

		Table table = createTable(tableDataArray);
		restaurant.getTableList().add(table);
	}

	public static void deleteTable(String[] tableDataArray, Restaurant restaurant) {
		Table tableToDelete = createTable(tableDataArray);
		Iterator<Table> iterator = restaurant.getTableList().iterator();
		while (iterator.hasNext()) {
			Table table = iterator.next();
			if (Integer.toString(table.getId()).equals(Integer.toString(tableToDelete.getId()))) {
				iterator.remove();

			}
		}
	}

	public static void updateTable(String[] oldTableDataArray, String[] newTableDataArray, Restaurant restaurant) {

		deleteTable(oldTableDataArray, restaurant);
		addTable(newTableDataArray, restaurant);
	}

}
