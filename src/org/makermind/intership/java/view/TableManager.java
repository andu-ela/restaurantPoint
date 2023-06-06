package org.makermind.intership.java.view;

/*
 * @author Anduela Nurshaba
 */

import java.awt.Color;

/*
 * @author Anduela Nurshaba
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.makerminds.intership.java.controller.TableManagerController;
import org.makerminds.intership.java.restaurantpoint.controller.login.LoginController;
import org.makerminds.intership.java.restaurantpoint.dataprovider.RestaurantDataProvider;
import org.makerminds.intership.java.restaurantpoint.login.model.User;
import org.makerminds.intership.java.restaurantpoint.model.Restaurant;
import org.makerminds.intership.java.restaurantpoint.model.Table;

public class TableManager extends AbstratctContentPanel implements IView {
	private JTextField tableIdTextField;
	private JTextField seatsTextField;

	private JComboBox<Restaurant> tableSelector;

	private DefaultTableModel tableListModel;
	private JTable tableList;

	private JPanel contentPanel;

	private String[] tableDataRow;

	RestaurantDataProvider restaurantDataProvider = new RestaurantDataProvider();

	public JPanel prepareView() {
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setLayout(null);
		contentPanel.setBounds(250, 0, 700, 500);
		createInputDataPanel(contentPanel);
		createButtons(contentPanel);
		contentPanel.add(createComboBoxPanel());
		createTable(contentPanel);

		return contentPanel;
	}

	private void createInputDataPanel(JPanel containerPanel) {
		JLabel tableIdLabel = createLabel(20, "Table Id");
		JLabel seatsLabel = createLabel(80, "Seats");

		tableIdTextField = createTableTextField(40);
		seatsTextField = createTableTextField(100);
		contentPanel.setLayout(null);

		containerPanel.add(tableIdLabel);
		containerPanel.add(tableIdTextField);
		containerPanel.add(seatsLabel);
		containerPanel.add(seatsTextField);
	}

	private JTextField createTableTextField(int verticalPosition) {
		JTextField tableTextField = new JTextField();
		tableTextField.setBounds(20, verticalPosition, 200, 27);
		tableTextField.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 15));

		return tableTextField;

	}

	private JLabel createLabel(int verticalPosition, String message) {
		JLabel label = new JLabel(message);
		label.setBounds(20, verticalPosition, 141, 16);
		label.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 13));

		return label;
	}

	private void createButtons(JPanel containerPanel) {
		containerPanel.add(createAddButton());
		containerPanel.add(createUpdateButton());
		containerPanel.add(createDeleteButton());
	}

	private JButton createAddButton() {
		JButton addButton = new JButton("ADD");
		addButton.setBounds(10, 296, 100, 30);
		addButton.setBackground(new Color(70, 130, 180));
		addButton.setForeground(Color.white);
		addButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Restaurant selectedRestaurant = (Restaurant) tableSelector.getSelectedItem();
				tableDataRow = new String[2];
				tableDataRow[0] = tableIdTextField.getText();
				tableDataRow[1] = seatsTextField.getText();

				if (areDataValid(tableDataRow)) {
					tableListModel.addRow(tableDataRow);
					TableManagerController.addTable(tableDataRow, selectedRestaurant);
					clearData();
				} else {
					JOptionPane.showMessageDialog(null, "Please provide All Data!");

				}
			}
		});
		return addButton;
	}

	private JButton createUpdateButton() {
		JButton updateButton = new JButton("UPDATE");
		updateButton.setBounds(120, 296, 100, 30);
		updateButton.setBackground(new Color(70, 130, 180));
		updateButton.setForeground(Color.white);
		updateButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] oldTableDataArray = new String[2];
				Restaurant selctedRestaurant = (Restaurant) tableSelector.getSelectedItem();
				int selectedRow = tableList.getSelectedRow();
				if (selectedRow != -1) {
					oldTableDataArray[0] = tableList.getValueAt(selectedRow, 0).toString();
					oldTableDataArray[1] = tableList.getValueAt(selectedRow, 1).toString();

					tableDataRow = new String[2];
					tableDataRow[0] = tableIdTextField.getText();
					tableDataRow[1] = seatsTextField.getText();

					if (areDataValid(tableDataRow)) {
						TableManagerController.updateTable(oldTableDataArray, tableDataRow, selctedRestaurant);
						tableListModel.setValueAt(tableIdTextField.getText(), selectedRow, 0);
						tableListModel.setValueAt(seatsTextField.getText(), selectedRow, 1);
						clearData();
					} else {
						JOptionPane.showMessageDialog(null, "Please select a Row you want to Update!");
					}
				} else {

					JOptionPane.showMessageDialog(null, "Please select a Row you want to Update!");
				}
			}

		});
		return updateButton;
	}

	private JButton createDeleteButton() {
		JButton deleteButton = new JButton("DELETE");
		deleteButton.setBounds(227, 296, 100, 30);
		deleteButton.setBackground(new Color(70, 130, 180));
		deleteButton.setForeground(Color.white);
		deleteButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Restaurant selectedRestaurant = (Restaurant) tableSelector.getSelectedItem();
				int selectedRowIndex = tableList.getSelectedRow();
				if (selectedRowIndex != -1) {
					tableDataRow = new String[2];
					tableDataRow[0] = tableList.getValueAt(selectedRowIndex, 0).toString();
					tableDataRow[1] = tableList.getValueAt(selectedRowIndex, 1).toString();

					TableManagerController.deleteTable(tableDataRow, selectedRestaurant);
					tableListModel.removeRow(selectedRowIndex);
					clearData();
				} else {
					JOptionPane.showMessageDialog(null, "Please Select a Row you want to Delete!");
				}
			}
		});
		return deleteButton;
	}

	private JPanel createComboBoxPanel() {

		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.setBackground(new Color(255, 255, 255));
		comboBoxPanel.setBounds(370, 30, 279, 66);
		comboBoxPanel.setLayout(null);
		comboBoxPanel.add(createComboBox());

		return comboBoxPanel;
	}

	private JComboBox<Restaurant> createComboBox() {
		tableSelector = new JComboBox<>();
		tableSelector.setBounds(10, 23, 233, 32);
		createComboBoxData();
		tableSelector.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				createDataModel();

			}

		});
		return tableSelector;
	}

	private void createComboBoxData() {
		User logedInUser = LoginController.getInstance().getLoggedInUser();
		List<Restaurant> restaurantList = logedInUser.getRestaurantList();
		for (Restaurant table : restaurantList) {
			tableSelector.addItem(table);
		}
	}

	private void createTable(JPanel containerPanel) {
		tableList = new JTable();
		createDataModel();
		JScrollPane scrollPane = new JScrollPane(tableList);
		scrollPane.setBounds(351, 115, 303, 211);
		tableList.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int selectedRow = tableList.getSelectedRow();
				tableIdTextField.setText(tableList.getValueAt(selectedRow, 0).toString());
				seatsTextField.setText(tableList.getValueAt(selectedRow, 1).toString());
			}

		});
		JTableHeader header = tableList.getTableHeader();
		header.setBackground(new Color(4620980));
		header.setForeground(Color.white);
		containerPanel.add(scrollPane);
	}

	private void createDataModel() {
		tableListModel = new DefaultTableModel();
		String[] columnName = { "ID", "Seats" };
		tableListModel.setColumnIdentifiers(columnName);
		createJTableData();

	}

	private void createJTableData() {
		tableDataRow = new String[2];
		List<Table> tableLt = ((Restaurant) tableSelector.getSelectedItem()).getTableList();
		for (Table table : tableLt) {
			tableDataRow[0] = Integer.toString(table.getId());
			tableDataRow[1] = Integer.toString(table.getSeats());
			tableListModel.addRow(tableDataRow);

		}

		tableList.setModel(tableListModel);
	}

	private boolean areDataValid(String[] tableDataArray) {
		return !tableDataArray[0].isBlank() && !tableDataArray[1].isBlank();
	}

	private void clearData() {
		tableIdTextField.setText("");
		seatsTextField.setText("");
	}
}
