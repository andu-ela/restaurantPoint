package org.makermind.intership.waiter.view;

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
import org.makerminds.intership.java.controller.TableManagerController;
import org.makerminds.intership.java.restaurantpoint.dataprovider.RestaurantDataProvider;
import org.makerminds.intership.java.restaurantpoint.model.Restaurant;
import org.makerminds.intership.java.restaurantpoint.waiter.model.Order;
import org.makerminds.intership.java.restaurantpoint.waiter.model.StatusEnum;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class OrderView extends AbstratctContentPanel implements IView {

	private JTable orderViewTable;

	private DefaultTableModel orderTableModel;

	private String[] tableOrderDataRow;

	TableManagerController tablec = new TableManagerController();

	public JPanel prepareView() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBounds(260, 0, 700, 545);
		createTable(contentPanel);
		createButtons(contentPanel);

		return contentPanel;

	}

	private void createButtons(JPanel containerPanel) {
		containerPanel.add(createUpdateStatusButton());
		containerPanel.add(createRevertStatusButton());
		containerPanel.add(createRefreshButtonButton());
	}

	private JButton createUpdateStatusButton() {
		JButton updateStatusButton = new JButton("Update Status");
		updateStatusButton.setBounds(122, 449, 129, 40);
		updateStatusButton.setBackground(new Color(70, 130, 180));
		updateStatusButton.setForeground(Color.white);
		updateStatusButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		updateStatusButton.setSelected(true);
		updateStatusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = orderViewTable.getSelectedRow();
				if (selectedRow != -1) {
					String status = (String) orderTableModel.getValueAt(selectedRow, 1);
					if (status.equals(StatusEnum.QUEUE.toString())) {
						orderTableModel.setValueAt(StatusEnum.IN_PROGRESS.toString(), selectedRow, 1);

					} else if (status.equals(StatusEnum.READY.toString())) {
						orderTableModel.setValueAt(StatusEnum.PAID.toString(), selectedRow, 1);
					} else if (status.equals(StatusEnum.PAID.toString())
							|| status.equals(StatusEnum.QUEUE.toString())) {
						orderTableModel.removeRow(selectedRow);

					}
				}
			}
		});
		return updateStatusButton;
	}

	private JButton createRevertStatusButton() {
		JButton revertStatusButton = new JButton("Revert Status");
		revertStatusButton.setBounds(276, 449, 129, 40);
		revertStatusButton.setBackground(new Color(70, 130, 180));
		revertStatusButton.setForeground(Color.white);
		revertStatusButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		revertStatusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = orderViewTable.getSelectedRow();
				if (selectedRow != -1) {
					String status = (String) orderTableModel.getValueAt(selectedRow, 1);
					if (status.equals(StatusEnum.PAID.toString())) {
						orderTableModel.setValueAt(StatusEnum.READY.toString(), selectedRow, 1);
					} else if (status.equals(StatusEnum.READY.toString())) {
						orderTableModel.setValueAt(StatusEnum.IN_PROGRESS.toString(), selectedRow, 1);
					} else if (status.equals(StatusEnum.IN_PROGRESS.toString())) {
						orderTableModel.setValueAt(StatusEnum.QUEUE.toString(), selectedRow, 1);
					}
				}
			}
		});
		return revertStatusButton;
	}

	private JButton createRefreshButtonButton() {
		JButton refreshButton = new JButton("Refresh");
		refreshButton.setBounds(438, 449, 129, 40);
		refreshButton.setBackground(new Color(70, 130, 180));
		refreshButton.setForeground(Color.white);
		refreshButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = orderTableModel.getRowCount() - 1; i >= 0; i--) {
					String status = orderTableModel.getValueAt(i, 1).toString();
					if (status.equals(StatusEnum.PAID.toString())) {
						orderTableModel.removeRow(i);
					}
				}

			}
		});

		return refreshButton;
	}

	private void createTable(JPanel containerPanel) {
		orderViewTable = new JTable();
		createDataModel();
		orderViewTable.setBounds(26, 11, 505, 369);
		JScrollPane scrollPane = new JScrollPane(orderViewTable);
		scrollPane.setBounds(108, 11, 459, 414);

		JTableHeader header = orderViewTable.getTableHeader();
		header.setBackground(new Color(4620980));
		header.setForeground(Color.white);

		containerPanel.add(scrollPane);

	}

	private void createDataModel() {
		orderTableModel = new DefaultTableModel();
		String[] columnName = { "Table Number", "Status" };
		orderTableModel.setColumnIdentifiers(columnName);

		createTableData();
	}

	private void createTableData() {

		RestaurantDataProvider restaurantDataProvider = new RestaurantDataProvider();

		List<Restaurant> restaurantList = restaurantDataProvider.getRestaurant();
		Restaurant restaurant = restaurantList.get(0);
		List<Order> orderList = restaurant.getOrderList();

		for (Order order : orderList) {
			tableOrderDataRow = new String[2];
			tableOrderDataRow[0] = Integer.toString(order.getId());
			tableOrderDataRow[1] = order.getStatusEnum().toString();

			orderTableModel.addRow(tableOrderDataRow);

		}
		orderViewTable.setModel(orderTableModel);
	}

}
