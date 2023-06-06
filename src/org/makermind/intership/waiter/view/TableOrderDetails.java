package org.makermind.intership.waiter.view;

/*
 * @author Anduela Nurshaba
 */

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.makermind.intership.java.view.AbstratctContentPanel;
import org.makermind.intership.java.view.IView;
import org.makerminds.intership.java.restaurantpoint.dataprovider.RestaurantDataProvider;
import org.makerminds.intership.java.restaurantpoint.model.Menu;
import org.makerminds.intership.java.restaurantpoint.model.Product;
import org.makerminds.intership.java.waiter.controller.ItemManagerController;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class TableOrderDetails extends AbstratctContentPanel implements IView {

	private JTextField quantityNameTextField;

	private JLabel quantityLable;

	private JTable orderItemTable;
	private DefaultTableModel orderTableModel;
	private static DefaultTableModel ordersTableModel;

	private String[] tableDataRow;

	private JComboBox<Menu> tableeSelector;

	private JTable table;

	public JPanel prepareView() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBounds(270, 0, 700, 500);
		createInputDataPanel(contentPanel);
		contentPanel.add(createComboBoxPanel());
		createTable(contentPanel);
		createOrderTable(contentPanel);
		createButtons(contentPanel);

		return contentPanel;
	}

	private void createInputDataPanel(JPanel containerPanel) {
		JLabel nameLabel = createLabel(20, "Quantity");

		quantityNameTextField = createQuantityTextField();

		containerPanel.add(nameLabel);
		containerPanel.add(quantityNameTextField);
	}

	private JTextField createQuantityTextField() {
		JTextField quantityTextField = new JTextField();

		quantityTextField.setBounds(365, 32, 96, 20);
		quantityTextField.setColumns(10);

		return quantityTextField;

	}

	private JLabel createLabel(int verticalPosition, String message) {
		quantityLable = new JLabel(message);
		quantityLable.setBounds(295, 35, 49, 14);
		quantityLable.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 10));

		return quantityLable;
	}

	private void createButtons(JPanel containerPanel) {
		containerPanel.add(createAddButton());
		containerPanel.add(createDeleteButton());

		JLabel subTotalLabel = new JLabel("");
		subTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		subTotalLabel.setFont(new Font("Microsoft YaHei", Font.ITALIC, 14));
		subTotalLabel.setBounds(397, 300, 158, 14);

		JLabel vatLabel = new JLabel("");
		vatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vatLabel.setFont(new Font("Microsoft YaHei", Font.ITALIC, 14));
		vatLabel.setBounds(397, 325, 158, 14);

		JLabel totalLabel = new JLabel("");
		totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalLabel.setFont(new Font("Microsoft YaHei", Font.BOLD | Font.ITALIC, 15));
		totalLabel.setBounds(397, 356, 158, 14);

		containerPanel.add(subTotalLabel);
		containerPanel.add(vatLabel);
		containerPanel.add(totalLabel);

		containerPanel.add(createPrintInvoiceButton(subTotalLabel, vatLabel, totalLabel));

	}

	private JButton createAddButton() {
		JButton addButton = new JButton("ADD");
		addButton.setBounds(20, 387, 99, 23);
		addButton.setBackground(new Color(70, 130, 180));
		addButton.setForeground(Color.white);
		addButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tableDataRow = new String[3];
				String additionalInfo = quantityNameTextField.getText();
				int selectedRow = orderItemTable.getSelectedRow();

				if (selectedRow != -1) {

					tableDataRow[0] = (String) orderItemTable.getValueAt(selectedRow, 1);
					tableDataRow[1] = (String) orderItemTable.getValueAt(selectedRow, 2);
					tableDataRow[2] = additionalInfo;
					clearData();

					Menu selectedRestaurant = (Menu) tableeSelector.getSelectedItem();
					ItemManagerController.addProduct(tableDataRow, selectedRestaurant);
					ordersTableModel.addRow(tableDataRow);
				}
			}
		});

		return addButton;

	}

	private JButton createDeleteButton() {
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(182, 387, 99, 23);
		deleteButton.setBackground(new Color(70, 130, 180));
		deleteButton.setForeground(Color.white);
		deleteButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String[] oldMenuDataArray = new String[3];
					oldMenuDataArray[0] = table.getValueAt(selectedRow, 0).toString();
					oldMenuDataArray[1] = table.getValueAt(selectedRow, 1).toString();
					oldMenuDataArray[2] = table.getValueAt(selectedRow, 2).toString();

					Menu selectedMenu = (Menu) tableeSelector.getSelectedItem();
					ItemManagerController.deleteProduct(oldMenuDataArray, selectedMenu);
					ordersTableModel.removeRow(selectedRow);
					clearData();

				} else {
					JOptionPane.showMessageDialog(null, "Please Select a Row you want to Delete!");
				}

			}

		});

		return deleteButton;
	}

	private JButton createPrintInvoiceButton(JLabel subTotaLabel, JLabel vatLabel, JLabel totalLabel) {
		JButton printInvoiceButton = new JButton("Print Invoice");
		printInvoiceButton.setBounds(397, 242, 158, 23);
		printInvoiceButton.setBackground(new Color(70, 130, 180));
		printInvoiceButton.setForeground(Color.white);
		printInvoiceButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		printInvoiceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == printInvoiceButton) {
					double subTotal = calculateOrder();
					double vat = subTotal * 0.18;
					double total = subTotal + vat;

					String subTotalText = "";
					String vatTotalText = "";
					String totalText = "";

					subTotalText += ordersTableModel.getRowCount();

					subTotalText += "Sub-Total:\n" + subTotal + "\n";
					subTotalText += "\n";
					vatTotalText += "\n" + "VAT:\n" + vat + "\n";
					totalText += "\n" + "Total:\n" + total;

					subTotaLabel.setText(subTotalText);
					vatLabel.setText(vatTotalText);
					totalLabel.setText(totalText);
				}
			}
		});

		return printInvoiceButton;
	}

	private JPanel createComboBoxPanel() {

		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.setBackground(new Color(255, 255, 255));
		comboBoxPanel.setLayout(null);
		comboBoxPanel.setBounds(30, 41, 239, 31);

		comboBoxPanel.add(createComboBox());

		return comboBoxPanel;

	}

	private JComboBox<Menu> createComboBox() {
		tableeSelector = new JComboBox<>();
		tableeSelector.setBounds(0, 0, 239, 31);
		createComboBoxData();
		tableeSelector.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				createDataModel();
			}
		});
		return tableeSelector;

	}

	private void createComboBoxData() {
		RestaurantDataProvider restaurantDataProvider = new RestaurantDataProvider();
		List<Menu> productList = restaurantDataProvider.getMenu();
		for (Menu menu : productList) {
			tableeSelector.addItem(menu);
		}
	}

	private void createTable(JPanel contentPanel) {
		orderItemTable = new JTable();
		createDataModel();
		JScrollPane scrollPane = new JScrollPane(orderItemTable);
		scrollPane.setBounds(20, 103, 261, 236);

		JTableHeader header = orderItemTable.getTableHeader();
		header.setBackground(new Color(4620980));
		header.setForeground(Color.white);
		contentPanel.add(scrollPane);
	}

	private void createDataModel() {
		orderTableModel = new DefaultTableModel();
		String[] columnName = { "ID", "Name", "Price" };
		orderTableModel.setColumnIdentifiers(columnName);
		Menu selectedMenu = (Menu) tableeSelector.getSelectedItem();
		createJTableData(selectedMenu);

	}

	private void createJTableData(Menu selectedMenu) {
		tableDataRow = new String[3];
		List<Product> productList = selectedMenu.getProductList();
		for (Product product : productList) {
			tableDataRow[0] = Integer.toString(product.getMenuId());
			tableDataRow[1] = product.getName();
			tableDataRow[2] = Double.toString(product.getPrice());

			orderTableModel.addRow(tableDataRow);
		}
		orderItemTable.setModel(orderTableModel);

	}

	private void createOrderTable(JPanel containerPanel) {
		table = new JTable();
		createDataModelForOrders();
		JScrollPane scrollPane1 = new JScrollPane(table);
		scrollPane1.setBounds(316, 103, 284, 128);

		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(4620980));
		header.setForeground(Color.white);
		containerPanel.add(scrollPane1);

	}

	private void createDataModelForOrders() {
		ordersTableModel = new DefaultTableModel();

		String[] columnname = { "MenuItem", "Price", "Quantity" };
		ordersTableModel.setColumnIdentifiers(columnname);
		table.setModel(ordersTableModel);
	}

	private static double calculateOrder() {
		double subTotal = 0;
		int rowCount = ordersTableModel.getRowCount();

		for (int i = 0; i < rowCount; i++) {
			double price = Double.parseDouble(ordersTableModel.getValueAt(i, 1).toString());
			int quantity =  Integer.valueOf(ordersTableModel.getValueAt(i, 2).toString());
			
			subTotal += price*quantity;
		}
		return subTotal;

	}

	private void clearData() {
		quantityNameTextField.setText("");

	}
}
