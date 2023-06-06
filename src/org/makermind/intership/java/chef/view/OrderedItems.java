package org.makermind.intership.java.chef.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.makermind.intership.java.view.AbstratctContentPanel;
import org.makermind.intership.java.view.IView;
import org.makerminds.intership.java.restaurantpoint.dataprovider.RestaurantDataProvider;

import org.makerminds.intership.java.restaurantpoint.model.Restaurant;

import org.makerminds.intership.java.restaurantpoint.waiter.model.Order;
import org.makerminds.intership.java.restaurantpoint.waiter.model.StatusEnum;

import javax.swing.JButton;

public class OrderedItems extends AbstratctContentPanel implements IView {

	/*
	 * @author Anduela Nurshaba
	 */

	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private String[] tableDataRow;
	private JPanel contentPanel;

	public JPanel prepareView() {
		contentPanel = new JPanel();
		contentPanel.setBounds(250, 0, 700, 500);
		createTable(contentPanel);
		createButton(contentPanel);

		return contentPanel;
	}

	private void createTable(JPanel containerPanel) {
		table = new JTable();
		createDataModel();
		table.setBounds(32, 11, 329, 227);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 46, 479, 334);

		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(4620980));
		header.setForeground(Color.white);
		contentPanel.setLayout(null);

		containerPanel.add(scrollPane);

	}

	private void createDataModel() {
		tableModel = new DefaultTableModel();
		String[] column = { "Table ID", "Item", "Quantity", "Status" };
		tableModel.setColumnIdentifiers(column);

		createTableData();

	}

	private void createTableData() {

		RestaurantDataProvider rest = new RestaurantDataProvider();
		List<Restaurant> restaurantList = rest.getRestaurant();
		Restaurant restaurant = restaurantList.get(0);
		List<Order> orderCookList = restaurant.getOrderList();

		tableDataRow = new String[4];
		for (Order order : orderCookList) {
			tableDataRow[0] = Integer.toString(order.getId());
			tableDataRow[1] = order.getName().toString();
			tableDataRow[2] = Integer.toString(order.getQuantity());

			tableDataRow[3] = order.getStatusEnum().toString();

			tableModel.addRow(tableDataRow);

			table.setModel(tableModel);

		}

	}

	private void createButton(JPanel containerPanel) {
		containerPanel.add(createUpdateStatusButton());
		containerPanel.add(createRevertStatusButton());

	}

	private JButton createUpdateStatusButton() {
		JButton updateStatus = new JButton("Update Status");
		updateStatus.setBounds(531, 305, 110, 23);
		updateStatus.setBackground(new Color(70, 130, 180));
		updateStatus.setForeground(Color.white);
		updateStatus.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		updateStatus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String status = (String) tableModel.getValueAt(selectedRow, 3);
					if (status.equals(StatusEnum.IN_PROGRESS.toString())) {
						tableModel.setValueAt(StatusEnum.READY.toString(), selectedRow, 3);
					} else if (status.equals(StatusEnum.READY.toString())
							|| status.equals(StatusEnum.READY.toString())) {
						tableModel.removeRow(selectedRow);

					}

				}

			}

		});
		return updateStatus;

	}

	private JButton createRevertStatusButton() {
		JButton revertStatusButton = new JButton("Revert Status");
		revertStatusButton.setBounds(531, 357, 110, 23);
		revertStatusButton.setBackground(new Color(70, 130, 180));
		revertStatusButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		revertStatusButton.setForeground(Color.white);
		revertStatusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String status = (String) tableModel.getValueAt(selectedRow, 3);
					if (status.equals(StatusEnum.READY.toString())) {
						tableModel.setValueAt(StatusEnum.IN_PROGRESS.toString(), selectedRow, 3);
					}
				}
			}
		});

		return revertStatusButton;

	}

}
