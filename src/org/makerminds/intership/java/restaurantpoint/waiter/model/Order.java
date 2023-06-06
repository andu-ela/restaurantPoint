package org.makerminds.intership.java.restaurantpoint.waiter.model;

/*
 * @author Anduela Nurshaba
 */

import java.util.List;

import org.makerminds.intership.java.restaurantpoint.model.Product;

public class Order {

	private String name;
	private int Id;
	private int seats;
	private int quantity;
	private StatusEnum statusEnum;
	private TableReservationEnum tablereservationEnum;
	private List<Product> productList;

	public Order(int id, String name, int quantity, StatusEnum statusEnum) {
		this.Id = id;
		this.name = name;
		this.setQuantity(quantity);
		this.statusEnum = statusEnum;

	}

	public Order(int Id, int seats, List<Product> productList, StatusEnum statusEnum,
			TableReservationEnum tablereservationEnum, int quantity, String name) {
		this.setId(Id);
		this.setSeats(seats);
		this.setStatusEnum(statusEnum);
		this.setProductList(productList);
		this.tablereservationEnum = tablereservationEnum;
		this.quantity = quantity;
		this.name = name;

	}

	public Order(String string) {
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public TableReservationEnum getTablereservationEnum() {
		return tablereservationEnum;
	}

	public void setTablereservationEnum(TableReservationEnum tablereservationEnum) {
		this.tablereservationEnum = tablereservationEnum;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
