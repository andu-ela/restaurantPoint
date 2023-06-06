package org.makerminds.intership.java.restaurantpoint.dataprovider;
/*
 * @author Anduela Nurshaba
 */

import java.util.ArrayList;
import java.util.List;
import org.makerminds.intership.java.restaurantpoint.model.Menu;
import org.makerminds.intership.java.restaurantpoint.model.MenuType;
import org.makerminds.intership.java.restaurantpoint.model.Product;
import org.makerminds.intership.java.restaurantpoint.model.Restaurant;
import org.makerminds.intership.java.restaurantpoint.model.Table;
import org.makerminds.intership.java.restaurantpoint.waiter.model.Order;
import org.makerminds.intership.java.restaurantpoint.waiter.model.StatusEnum;
import org.makerminds.intership.java.restaurantpoint.waiter.model.TableReservationEnum;

public class RestaurantDataProvider {

	// create a list
	private List<Restaurant> restList = new ArrayList<>();
	private List<Menu> menuList = new ArrayList<>();
	private List<Product> productList = new ArrayList<>();
	private List<Table> tableList = new ArrayList<>();

	public RestaurantDataProvider() {
		createRestaurantList();
		createMenuList();
		createProductList();

	}

	private List<Product> createProductList() {

		Product product1 = new Product(1, "Product1", 2.2, MenuType.DRINK);
		Product product2 = new Product(1, "Product2", 2.2, MenuType.MEAL);
		Product product3 = new Product(1, "Product3", 2.2, MenuType.MEAL);
		Product product4 = new Product(1, "Product4", 2.2, MenuType.DRINK);

		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		productList.add(product4);

		return productList;

	}

	private List<Menu> createMenuList() {

		List<Product> menuProduct1 = createMenuProduct1();
		List<Product> menuProduct2 = createMenuProduct2();
		List<Product> menuProduct3 = createMenuProduct3();
		List<Product> menuProduct4 = createMenuProduct4();

		Menu menu1 = new Menu("Menu1", menuProduct1);
		Menu menu2 = new Menu("Menu2", menuProduct2);
		Menu menu3 = new Menu("Menu3", menuProduct3);
		Menu menu4 = new Menu("Menu4", menuProduct4);

		menuList.add(menu1);
		menuList.add(menu2);
		menuList.add(menu3);
		menuList.add(menu4);

		return menuList;
	}

	private List<Restaurant> createRestaurantList() {
		List<Menu> restaurantMenu1 = createRestaurantMenu1();
		List<Menu> restaurantMenu2 = createRestaurantMenu2();
		List<Menu> restaurantMenu3 = createRestaurantMenu3();
		List<Menu> restaurantMenu4 = createRestaurantMenu4();

		Restaurant restaurant1 = new Restaurant("Restaurant1", "address1", restaurantMenu1, createTableSeatsList1(),
				orderList());
		Restaurant restaurant2 = new Restaurant("Restaurant2", "address2", restaurantMenu2, createTableSeatsList2(),
				orderList());
		Restaurant restaurant3 = new Restaurant("Restaurant3", "address3", restaurantMenu3, createTableSeatsList3(),
				orderList());
		Restaurant restaurant4 = new Restaurant("Restaurant4", "address4", restaurantMenu4, createTableSeatsList4(),
				orderList());

		restList.add(restaurant1);
		restList.add(restaurant2);
		restList.add(restaurant3);
		restList.add(restaurant4);

		return restList;

	}

	private List<Menu> createRestaurantMenu1() {
		List<Menu> restaurantMenu1 = new ArrayList<>();
		restaurantMenu1.add(new Menu("1Menu1#", createMenuProduct1()));
		restaurantMenu1.add(new Menu("1Menu2#", createMenuProduct2()));
		restaurantMenu1.add(new Menu("1Menu3#", createMenuProduct3()));
		restaurantMenu1.add(new Menu("1Menu4#", createMenuProduct4()));

		return restaurantMenu1;

	}

	private List<Menu> createRestaurantMenu2() {
		List<Menu> restaurantMenu2 = new ArrayList<>();
		restaurantMenu2.add(new Menu("2Menu1#", createMenuProduct1()));
		restaurantMenu2.add(new Menu("2Menu2#", createMenuProduct2()));
		restaurantMenu2.add(new Menu("2Menu3#", createMenuProduct3()));
		restaurantMenu2.add(new Menu("2Menu4#", createMenuProduct4()));
		return restaurantMenu2;
	}

	private List<Menu> createRestaurantMenu3() {
		List<Menu> restaurantMenu3 = new ArrayList<>();
		restaurantMenu3.add(new Menu("3Menu1#", createMenuProduct1()));
		restaurantMenu3.add(new Menu("3Menu2#", createMenuProduct2()));
		restaurantMenu3.add(new Menu("3Menu3#", createMenuProduct3()));
		restaurantMenu3.add(new Menu("3Menu4#", createMenuProduct4()));
		return restaurantMenu3;
	}

	private List<Menu> createRestaurantMenu4() {
		List<Menu> restaurantMenu4 = new ArrayList<>();
		restaurantMenu4.add(new Menu("4Menu1#", createMenuProduct1()));
		restaurantMenu4.add(new Menu("4Menu2#", createMenuProduct2()));
		restaurantMenu4.add(new Menu("4Menu3#", createMenuProduct3()));
		restaurantMenu4.add(new Menu("4Menu4#", createMenuProduct4()));
		return restaurantMenu4;
	}

	private List<Product> createMenuProduct1() {
		List<Product> menuProduct1 = new ArrayList<>();
		menuProduct1.add(new Product(1, "Chocolate mousse", 2.2, MenuType.MEAL));
		menuProduct1.add(new Product(2, "Pizza", 2.5, MenuType.MEAL));
		menuProduct1.add(new Product(3, "RedBull", 2.8, MenuType.DRINK));
		menuProduct1.add(new Product(4, "Green Salad", 3, MenuType.MEAL));
		return menuProduct1;

	}

	private List<Product> createMenuProduct2() {
		List<Product> menuProduct2 = new ArrayList<>();
		menuProduct2.add(new Product(1, "Mortadella Sandwich", 2.2, MenuType.MEAL));
		menuProduct2.add(new Product(2, "Sandwich", 2.5, MenuType.MEAL));
		menuProduct2.add(new Product(3, "Tea", 2.8, MenuType.DRINK));
		menuProduct2.add(new Product(4, "Cream puff", 3, MenuType.MEAL));
		return menuProduct2;

	}

	private List<Product> createMenuProduct3() {
		List<Product> menuProduct3 = new ArrayList<>();
		menuProduct3.add(new Product(1, "Schwepps", 2.2, MenuType.DRINK));
		menuProduct3.add(new Product(2, "Cocktail", 2.5, MenuType.DRINK));
		menuProduct3.add(new Product(3, "Coconut custard", 2.8, MenuType.MEAL));
		menuProduct3.add(new Product(4, "Pizza", 3, MenuType.MEAL));
		return menuProduct3;

	}

	private List<Product> createMenuProduct4() {
		Product product1 = new Product(1, "Chocolate mousse", 2.2, MenuType.MEAL);
		Product product2 = new Product(2, "Pizza", 2.5, MenuType.MEAL);
		Product product3 = new Product(3, "Tea", 2.8, MenuType.DRINK);
		Product prodcut4 = new Product(4, "Beer", 2.8, MenuType.DRINK);

		List<Product> menuProduct = new ArrayList<>();
		menuProduct.add(product1);
		menuProduct.add(product2);
		menuProduct.add(product3);
		menuProduct.add(prodcut4);
		return menuProduct;
	}

	private List<Table> createTableSeatsList1() {
		List<Table> tableList1 = new ArrayList<>();
		tableList1.add(new Table(1, 10, orderList()));
		tableList1.add(new Table(2, 2, orderList()));
		tableList1.add(new Table(3, 4, orderList()));
		tableList1.add(new Table(4, 8, orderList()));
		tableList1.add(new Table(5, 9, orderList()));
		tableList1.add(new Table(6, 10, orderList()));
		tableList1.add(new Table(7, 11, orderList()));
		tableList1.add(new Table(8, 12, orderList()));
		tableList1.add(new Table(9, 13, orderList()));

		return tableList1;

	}

	private List<Table> createTableSeatsList2() {
		List<Table> tableList2 = new ArrayList<>();
		tableList2.add(new Table(5, 5, orderList()));
		tableList2.add(new Table(6, 3, orderList()));
		tableList2.add(new Table(7, 14, orderList()));
		tableList2.add(new Table(8, 15, orderList()));
		tableList2.add(new Table(9, 16, orderList()));
		tableList2.add(new Table(10, 17, orderList()));
		tableList2.add(new Table(11, 18, orderList()));
		tableList2.add(new Table(12, 19, orderList()));
		tableList2.add(new Table(13, 20, orderList()));

		return tableList2;

	}

	private List<Table> createTableSeatsList3() {
		List<Table> tableList3 = new ArrayList<>();
		tableList3.add(new Table(9, 17, orderList()));
		tableList3.add(new Table(10, 4, orderList()));
		tableList3.add(new Table(11, 5, orderList()));
		tableList3.add(new Table(13, 7, orderList()));
		tableList3.add(new Table(14, 8, orderList()));
		tableList3.add(new Table(15, 2, orderList()));
		tableList3.add(new Table(16, 1, orderList()));
		tableList3.add(new Table(17, 9, orderList()));
		tableList3.add(new Table(18, 13, orderList()));
		tableList3.add(new Table(19, 20, orderList()));

		return tableList3;

	}

	private List<Table> createTableSeatsList4() {
		List<Table> tableList = new ArrayList<>();
		Table table1 = new Table(13, 12, orderList());
		Table table2 = new Table(14, 10, orderList());
		Table table3 = new Table(15, 8, orderList());
		Table table4 = new Table(16, 6, orderList());
		Table table5 = new Table(12, 4, orderList());
		Table table6 = new Table(15, 9, orderList());
		Table table7 = new Table(1, 3, orderList());
		Table table8 = new Table(6, 2, orderList());
		Table table9 = new Table(8, 1, orderList());

		tableList.add(table2);
		tableList.add(table1);
		tableList.add(table3);
		tableList.add(table4);
		tableList.add(table5);
		tableList.add(table6);
		tableList.add(table7);
		tableList.add(table8);
		tableList.add(table9);

		return tableList;
	}

	private List<Order> orderList() {
		List<Order> orderList = new ArrayList<>();
		orderList.add(new Order(8, 2, productList, StatusEnum.QUEUE, TableReservationEnum.FREE, 2, "Order1"));
		orderList
				.add(new Order(1, 12, productList, StatusEnum.IN_PROGRESS, TableReservationEnum.RESERVED, 4, "Order2"));
		orderList.add(new Order(2, 3, productList, StatusEnum.READY, TableReservationEnum.RESERVED, 2, "Order3"));
		orderList.add(new Order(3, 7, productList, StatusEnum.QUEUE, TableReservationEnum.FREE, 5, "Order4"));
		orderList.add(new Order(4, 9, productList, StatusEnum.PAID, TableReservationEnum.RESERVED, 3, "Order5"));
		orderList.add(new Order(5, 10, productList, StatusEnum.READY, TableReservationEnum.RESERVED, 2, "Order6"));
		orderList.add(new Order(6, 2, productList, StatusEnum.QUEUE, TableReservationEnum.RESERVED, 1, "Order7"));
		orderList.add(new Order(7, 4, productList, StatusEnum.IN_PROGRESS, TableReservationEnum.FREE, 5, "Order8"));

		return orderList;

	}

	public List<Restaurant> getRestaurant() {
		return restList;

	}

	public List<Menu> getMenu() {
		return menuList;
	}

	public List<Product> getProduct() {
		return productList;

	}

	public List<Table> getTable() {
		return tableList;
	}
}
