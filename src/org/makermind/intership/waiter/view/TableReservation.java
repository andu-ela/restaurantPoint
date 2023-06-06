package org.makermind.intership.waiter.view;

/*
 * @author Anduela Nurshaba
 */

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.makermind.intership.java.view.AbstratctContentPanel;
import org.makermind.intership.java.view.IView;
import org.makerminds.intership.java.restaurantpoint.dataprovider.RestaurantDataProvider;
import org.makerminds.intership.java.restaurantpoint.model.Restaurant;
import org.makerminds.intership.java.restaurantpoint.waiter.model.Order;
import org.makerminds.intership.java.restaurantpoint.waiter.model.TableReservationEnum;

import javax.swing.JScrollPane;
import javax.swing.JButton;

public class TableReservation extends AbstratctContentPanel implements IView {

	private JTable table;

	private DefaultTableModel tableModel = new DefaultTableModel();

	private String[] tableReservationDataRow;

	private JButton changeButton;

	public JPanel prepareView() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBounds(260, 0, 700, 500);
		createTable(contentPanel);
		contentPanel.add(createCahngeButton());

		return contentPanel;
	}

	private JButton createCahngeButton() {
		changeButton = new JButton("Change");
		changeButton.setBounds(521, 236, 131, 34);
		changeButton.setBackground(new Color(70, 130, 180));
		changeButton.setForeground(Color.white);
		changeButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		changeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String tablestatus = (String) tableModel.getValueAt(selectedRow, 2);
					if (tablestatus.equals(TableReservationEnum.FREE.toString())) {
						tableModel.setValueAt(TableReservationEnum.RESERVED.toString(), selectedRow, 2);
					} else if (tablestatus.equals(TableReservationEnum.RESERVED.toString())) {
						tableModel.setValueAt(TableReservationEnum.FREE.toString(), selectedRow, 2);
					}
				}
			}

		});
		return changeButton;

	}

	private void createTable(JPanel containerPanel) {
		table = new JTable();
		createDataModel();
		table.setBounds(40, 50, 500, 224);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 11, 452, 501);

		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(4620980));
		header.setForeground(Color.white);

		containerPanel.add(scrollPane);
	}

	private void createDataModel() {
		tableModel = new DefaultTableModel();
		String[] columnName = { "Table ID", "Seats", "Status" };
		tableModel.setColumnIdentifiers(columnName);
		createTableData();
	}

	private void createTableData() {

		RestaurantDataProvider rest = new RestaurantDataProvider();

		List<Restaurant> restaurantList = rest.getRestaurant();
		Restaurant restaurant = restaurantList.get(2);
		List<Order> orderList = restaurant.getOrderList();
		tableReservationDataRow = new String[3];
		for (Order order : orderList) {
			tableReservationDataRow[0] = Integer.toString(order.getId());
			tableReservationDataRow[1] = Integer.toString(order.getSeats());
			tableReservationDataRow[2] = order.getTablereservationEnum().toString();

			tableModel.addRow(tableReservationDataRow);
		}
		table.setModel(tableModel);

	}
}
