package org.makerminds.intership.java.restaurantpoint.model;

/*
 * @author Anduela Nurshaba
 */
import java.util.List;

import org.makerminds.intership.java.restaurantpoint.waiter.model.Order;

public class Restaurant {

	private String name;
	private String address;
	private List<Menu> menuList;
	private List<Table> tableList;
	private List<Order> orderList;

	public Restaurant(String name, List<Menu> menuList) {
		this.name = name;
		this.menuList = menuList;
	}

	public Restaurant(String name, String address, List<Menu> menuList, List<Table> tableList, List<Order> orderList) {
		this.name = name;
		this.address = address;
		this.menuList = menuList;
		this.tableList = tableList;
		this.orderList = orderList;

	}

	public Restaurant(String string) {
		// TODO Auto-generated constructor stub
	}

	public List<Menu> getMenu() {
		return menuList;
	}

	public void setMenu(List<Menu> menu) {
		this.menuList = menu;
	}

	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return name;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

}