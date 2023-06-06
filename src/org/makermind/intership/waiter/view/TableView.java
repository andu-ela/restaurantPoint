package org.makermind.intership.waiter.view;

/*
 * @author Anduela Nurshaba
 */

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.makermind.intership.java.view.AbstratctContentPanel;
import org.makermind.intership.java.view.IView;
import org.makermind.intership.java.view.UserRoleView;
import org.makerminds.intership.java.restaurantpoint.dataprovider.RestaurantDataProvider;
import org.makerminds.intership.java.restaurantpoint.model.Restaurant;
import org.makerminds.intership.java.restaurantpoint.model.Table;

public class TableView extends AbstratctContentPanel implements IView {

	private JTable orderTable = new JTable();

	private DefaultTableModel orderTableModel;
	JLayeredPane layeredPane = new JLayeredPane();

	private String[] tableOrderDataRow;

	RestaurantDataProvider restaurantDataProvider = new RestaurantDataProvider();

	public JPanel prepareView() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBounds(250, 0, 700, 500);
		createTable(contentPanel);
		createScrollPane(contentPanel);

		return contentPanel;

	}

	private void createTable(JPanel containerPanel) {
		orderTable = new JTable();
		createDataModel();
		orderTable.setBounds(26, 11, 505, 369);
		orderTable.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				JLayeredPane layeredPane = UserRoleView.getContentPane();

				layeredPane.removeAll();

				TableOrderDetails order = new TableOrderDetails();

				layeredPane.add(order.prepareView());

			}
		});

	}

	private void createScrollPane(JPanel containerPanel) {
		JScrollPane scrollPane = new JScrollPane(orderTable);
		scrollPane.setBounds(74, 20, 607, 362);

		JTableHeader header = orderTable.getTableHeader();
		header.setBackground(new Color(4620980));
		header.setForeground(Color.white);

		containerPanel.add(scrollPane);

	}

	private void createDataModel() {
		orderTableModel = new DefaultTableModel();
		String[] columnName = { "Table Panel" };
		orderTableModel.setColumnIdentifiers(columnName);
		createTableData();

	}

	private void createTableData() {
		RestaurantDataProvider restaurantDataProvider = new RestaurantDataProvider();

		List<Restaurant> restaurantList = restaurantDataProvider.getRestaurant();
		Restaurant restaurant = restaurantList.get(0);
		List<Table> listTable = restaurant.getTableList();

		tableOrderDataRow = new String[1];
		for (Table table1 : listTable) {
			tableOrderDataRow[0] = Integer.toString(table1.getId());

			orderTableModel.addRow(tableOrderDataRow);

		}
		orderTable.setModel(orderTableModel);

	}
}
