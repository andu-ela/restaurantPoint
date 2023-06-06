package org.makerminds.intership.java.restaurantpoint.model;
/*
 * @author Anduela Nurshaba
 */

public class Product {

	private int menuId;
	private String name;
	private double price;
	private int quantity;
	private MenuType menuType;
	
	
	public Product( String name, double price) {
	
		this.name=name;
		this.price=price;
	}
	
	
	public Product(int menuId, String name, double price,MenuType menuType) {
		this.menuId=menuId;
		this.name=name;
		this.price=price;
		this.setMenuType(menuType);
				
	}
	public Product(String string) {
		// TODO Auto-generated constructor stub
	}
	 public String toString(){
		 return name;
	 }
	

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public MenuType getMenuType() {
		return menuType;
	}
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



}