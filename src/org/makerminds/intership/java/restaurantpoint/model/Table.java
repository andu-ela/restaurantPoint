package org.makerminds.intership.java.restaurantpoint.model;
/*
 * @author Anduela Nurshaba
 */

import java.util.List;
import org.makerminds.intership.java.restaurantpoint.waiter.model.Order;

public class Table {

	private int Id;
	private int seats;
	private List<Order> orderList;

	public Table(int Id, int seats) {
		this.Id = Id;
		this.seats = seats;

	}

	public Table(int Id, int seats, List<Order> orderList) {
		this.Id = Id;
		this.seats = seats;
		this.orderList = orderList;
	}

	public Table(String string) {

	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
}
