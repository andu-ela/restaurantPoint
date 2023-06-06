package org.makermind.intership.java.view;

import java.awt.Color;

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

import org.makerminds.intership.java.controller.MenuManagerController;
import org.makerminds.intership.java.restaurantpoint.controller.login.LoginController;
import org.makerminds.intership.java.restaurantpoint.login.model.User;
import org.makerminds.intership.java.restaurantpoint.model.Menu;
import org.makerminds.intership.java.restaurantpoint.model.Restaurant;

/*
 * @author Anduela Nurshaba
 */

public class MenuManager extends AbstratctContentPanel implements IView {

	private JTextField menuNameTextField;

	private JComboBox<Restaurant> restaurantSelector;

	private DefaultTableModel menuTableModel;

	private JTable menuTable;

	private String[] menuDataRow;

	private JPanel contentPanel;

	public JPanel prepareView() {
		contentPanel = createBasePanel();
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(260, 0, 700, 500);
		createInputDataPanel(contentPanel);
		createButtons(contentPanel);
		contentPanel.add(createComboBoxPanel());
		createTable(contentPanel);

		return contentPanel;

	}

	private void createInputDataPanel(JPanel containerPanel) {
		JLabel nameLabel = createLabel(20, "Menu Name:");

		menuNameTextField = createMenuTextField(40);
		contentPanel.setLayout(null);

		containerPanel.add(nameLabel);
		containerPanel.add(menuNameTextField);
	}

	private JTextField createMenuTextField(int verticalPosition) {
		JTextField menuTextField = new JTextField();
		menuTextField.setBounds(10, 57, 200, 27);
		menuTextField.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 15));

		return menuTextField;
	}

	private JLabel createLabel(int verticalPosition, String message) {
		JLabel label = new JLabel(message);
		label.setBounds(10, 34, 141, 16);
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
				Restaurant selectedRestaurant = (Restaurant) restaurantSelector.getSelectedItem();
				menuDataRow = new String[1];
				menuDataRow[0] = menuNameTextField.getText();

				if (areDataValid(menuDataRow)) {
					menuTableModel.addRow(menuDataRow);
					MenuManagerController.addMenu(menuDataRow, selectedRestaurant);
					clearData();
				} else {
					JOptionPane.showMessageDialog(null, "Please provide us Name!");

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

				String[] oldMenuDataArray = new String[1];
				Restaurant selectedRestaurant = (Restaurant) restaurantSelector.getSelectedItem();
				int selectedRow = menuTable.getSelectedRow();
				if (selectedRow != -1) {
					oldMenuDataArray[0] = menuTable.getValueAt(selectedRow, 0).toString();

					menuDataRow = new String[1];
					menuDataRow[0] = menuNameTextField.getText();

					if (areDataValid(menuDataRow)) {
						MenuManagerController.updateMenu(oldMenuDataArray, menuDataRow, selectedRestaurant);
						menuTable.setValueAt(menuDataRow[0], selectedRow, 0);
						clearData();
					}
				} else {
					{
						JOptionPane.showMessageDialog(null, "Please select a Row you want to Update!");
					}
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
				Restaurant selectedRestaurant = (Restaurant) restaurantSelector.getSelectedItem();
				int selectedRowIndex = menuTable.getSelectedRow();
				if (selectedRowIndex != -1) {
					menuDataRow = new String[1];
					menuDataRow[0] = menuTable.getValueAt(selectedRowIndex, 0).toString();

					MenuManagerController.deleteMenu(menuDataRow, selectedRestaurant);
					menuTableModel.removeRow(selectedRowIndex);
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
		restaurantSelector = new JComboBox<>();
		restaurantSelector.setBounds(10, 23, 233, 32);
		createComboBoxData();
		restaurantSelector.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				createDataModel();
			}
		});
		return restaurantSelector;
	}

	private void createComboBoxData() {
		User loggedInUser = LoginController.getInstance().getLoggedInUser();
		List<Restaurant> restaurantList = loggedInUser.getRestaurantList();
		for (Restaurant restaurant : restaurantList) {
			restaurantSelector.addItem(restaurant);
		}
	}

	private void createTable(JPanel containerPanel) {
		menuTable = new JTable();
		createDataModel();
		JScrollPane scrollPane = new JScrollPane(menuTable);
		scrollPane.setBounds(351, 115, 303, 211);
		menuTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = menuTable.getSelectedRow();
				menuNameTextField.setText(menuTable.getValueAt(selectedRow, 0).toString());

			}

		});
		JTableHeader header = menuTable.getTableHeader();
		header.setBackground(new Color(4620980));
		header.setForeground(Color.white);
		containerPanel.add(scrollPane);
	}

	private void createDataModel() {
		menuTableModel = new DefaultTableModel();
		String[] columnName = { "Menu List" };
		menuTableModel.setColumnIdentifiers(columnName);
		createJTableData();
	}

	private void createJTableData() {
		Restaurant restaurant = (Restaurant) restaurantSelector.getSelectedItem();
		menuDataRow = new String[1];
		List<Menu> menuList = restaurant.getMenu();
		for (Menu menu : menuList) {
			menuDataRow[0] = menu.getName();

			menuTableModel.addRow(menuDataRow);
		}
		menuTable.setModel(menuTableModel);

	}

	private boolean areDataValid(String[] menuDataArray) {
		return !menuDataArray[0].isBlank();
	}

	private void clearData() {
		menuNameTextField.setText("");
	}

}
