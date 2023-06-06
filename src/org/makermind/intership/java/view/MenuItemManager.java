package org.makermind.intership.java.view;
/*
 * @author Anduela Nurshaba
 */

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.makerminds.intership.java.controller.MenuItemManagerController;
import org.makerminds.intership.java.restaurantpoint.controller.login.LoginController;
import org.makerminds.intership.java.restaurantpoint.dataprovider.RestaurantDataProvider;
import org.makerminds.intership.java.restaurantpoint.login.model.User;
import org.makerminds.intership.java.restaurantpoint.model.Menu;
import org.makerminds.intership.java.restaurantpoint.model.Product;
import org.makerminds.intership.java.restaurantpoint.model.Restaurant;

/*
 * @author Anduela Nurshaba
 */

public class MenuItemManager extends AbstratctContentPanel implements IView {

	private JTextField itemIdTextField;
	private JTextField itemNameTextField;
	private JTextField ItemPriceTextField;

	private DefaultTableModel menuTableModel;
	private JTable menuItemTable;
	private String[] menuItemDataRow;

	private JComboBox<Restaurant> restaurantSelector;
	private JComboBox<Menu> menuSelector;

	private JRadioButton radioButtonMeal;
	private JRadioButton radioButtonDrink;

	MenuItemManagerController menuItemManagerController = new MenuItemManagerController();
	RestaurantDataProvider restaurantDataProvider = new RestaurantDataProvider();

	public JPanel prepareView() {
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setLayout(null);
		contentPanel.setBounds(250, 0, 700, 500);
		createInputDataPanel(contentPanel);
		createButtonGroup(contentPanel);
		createButtons(contentPanel);
		createComboBoxPanel(contentPanel);
		createTable(contentPanel);

		return contentPanel;

	}

	private void createInputDataPanel(JPanel containerPanel) {
		JLabel itemIdLabel = createLabel(20, "Item ID");
		JLabel itemNameLable = createLabel(80, "Item Name");
		JLabel itemPriceLabel = createLabel(140, "Item Price");

		itemIdTextField = createTextField(40);
		itemNameTextField = createTextField(100);
		ItemPriceTextField = createTextField(160);

		containerPanel.add(itemIdLabel);
		containerPanel.add(itemIdTextField);
		containerPanel.add(itemNameLable);
		containerPanel.add(itemNameTextField);
		containerPanel.add(itemPriceLabel);
		containerPanel.add(ItemPriceTextField);

	}

	private JTextField createTextField(int verticalPosition) {

		JTextField textField = new JTextField();
		textField.setBounds(20, verticalPosition, 200, 27);
		textField.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 15));

		return textField;

	}

	private JLabel createLabel(int verticalPosition, String message) {

		JLabel label = new JLabel(message);
		label.setBounds(20, verticalPosition, 100, 20);
		label.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 13));

		return label;
	}

	private ButtonGroup createButtonGroup(JPanel contentPanel) {

		ButtonGroup radioButton = new ButtonGroup();
		radioButtonMeal = createRadioButton("Meal", 258);
		radioButtonMeal.setSelected(true);
		radioButtonDrink = createRadioButton("Drink", 300);
		radioButton.add(radioButtonMeal);
		radioButton.add(radioButtonDrink);

		contentPanel.add(radioButtonDrink);
		contentPanel.add(radioButtonMeal);
		return radioButton;
	}

	private JRadioButton createRadioButton(String message, int verticalposition) {

		JRadioButton radioButtton = new JRadioButton(message);
		radioButtton.setBackground(new Color(255, 255, 255));
		radioButtton.setBounds(15, verticalposition, 100, 40);
		radioButtton.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 13));

		return radioButtton;
	}

	private void createButtons(JPanel containerPanel) {
		containerPanel.add(createAddButton());
		containerPanel.add(createUpdateButton());
		containerPanel.add(createDeleteButton());
	}

	private JButton createAddButton() {
		JButton addButton = new JButton("ADD");
		addButton.setBounds(20, 357, 100, 30);
		addButton.setBackground(new Color(70, 130, 180));
		addButton.setForeground(Color.white);
		addButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (areDataValid()) {
					String[] menuItemDataArray = new String[4];
					menuItemDataArray[0] = itemIdTextField.getText();
					menuItemDataArray[1] = itemNameTextField.getText();
					menuItemDataArray[2] = ItemPriceTextField.getText();
					menuItemDataArray[3] = getSelectedMenuType();
					Menu selectedMenu = (Menu) menuSelector.getSelectedItem();
					MenuItemManagerController.addProduct(menuItemDataArray, selectedMenu);
					menuTableModel.addRow(menuItemDataArray);
					clearData();

				} else {
					JOptionPane.showMessageDialog(null, "Please provide all Data!");
				}

			}

		});

		return addButton;

	}

	private JButton createUpdateButton() {
		JButton updateButton = new JButton("UPDATE");
		updateButton.setBounds(130, 357, 100, 30);
		updateButton.setBackground(new Color(70, 130, 180));
		updateButton.setForeground(Color.white);
		updateButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedRow = menuItemTable.getSelectedRow();
				if (selectedRow != -1) {
					if (areDataValid()) {
						String[] oldMenuDataArray = new String[4];
						oldMenuDataArray[0] = menuItemTable.getValueAt(selectedRow, 0).toString();
						oldMenuDataArray[1] = menuItemTable.getValueAt(selectedRow, 1).toString();
						oldMenuDataArray[2] = menuItemTable.getValueAt(selectedRow, 2).toString();
						oldMenuDataArray[3] = menuItemTable.getValueAt(selectedRow, 3).toString();

						String[] newMenuItemDataRow = new String[4];
						newMenuItemDataRow[0] = itemIdTextField.getText();
						newMenuItemDataRow[1] = itemNameTextField.getText();
						newMenuItemDataRow[2] = ItemPriceTextField.getText();
						newMenuItemDataRow[3] = getSelectedMenuType();

						Menu selectedMenu = (Menu) menuSelector.getSelectedItem();
						MenuItemManagerController.updateProduct(oldMenuDataArray, newMenuItemDataRow, selectedMenu);
						menuTableModel.setValueAt(newMenuItemDataRow[0], selectedRow, 0);
						menuTableModel.setValueAt(newMenuItemDataRow[1], selectedRow, 1);
						menuTableModel.setValueAt(newMenuItemDataRow[2], selectedRow, 2);
						menuTableModel.setValueAt(newMenuItemDataRow[3], selectedRow, 3);

						clearData();

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
		deleteButton.setBounds(241, 357, 100, 30);
		deleteButton.setBackground(new Color(70, 130, 180));
		deleteButton.setForeground(Color.white);
		deleteButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedRow = menuItemTable.getSelectedRow();
				if (selectedRow != -1) {
					String[] oldMenuDataArray = new String[4];
					oldMenuDataArray[0] = menuItemTable.getValueAt(selectedRow, 0).toString();
					oldMenuDataArray[1] = menuItemTable.getValueAt(selectedRow, 1).toString();
					oldMenuDataArray[2] = menuItemTable.getValueAt(selectedRow, 2).toString();
					oldMenuDataArray[3] = menuItemTable.getValueAt(selectedRow, 3).toString();
					Menu selectedMenu = (Menu) menuSelector.getSelectedItem();

					MenuItemManagerController.deleteProduct(oldMenuDataArray, selectedMenu);
					menuTableModel.removeRow(selectedRow);
					clearData();
				} else {
					JOptionPane.showMessageDialog(null, "Please Select a Row you want to Delete!");
				}
			}

		});
		return deleteButton;

	}

	private String getSelectedMenuType() {
		if (radioButtonMeal.isSelected()) {
			return "MEAL";
		} else {
			return "DRINK";
		}
	}

	private JPanel createComboBoxPanel(JPanel containerPanel) {
		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.setBounds(400, 30, 200, 40);
		comboBoxPanel.setLayout(null);
		comboBoxPanel.add(createRestaurantComboBox());
		comboBoxPanel.add(createMenuComboBox());

		containerPanel.add(restaurantSelector);
		containerPanel.add(menuSelector);

		return comboBoxPanel;
	}

	private JComboBox<Restaurant> createRestaurantComboBox() {
		restaurantSelector = new JComboBox<>();
		restaurantSelector.setBounds(450, 20, 200, 40);
		createRestaurantComboBoxData();
		restaurantSelector.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				createMenuComboBoxData();
			}
		});

		return restaurantSelector;

	}

	private void createRestaurantComboBoxData() {
		User logedInUser = LoginController.getInstance().getLoggedInUser();
		restaurantSelector.removeAllItems();
		List<Restaurant> restaurantList = logedInUser.getRestaurantList();
		for (Restaurant restaurant : restaurantList) {
			restaurantSelector.addItem(restaurant);
		}
	}

	private JComboBox<Menu> createMenuComboBox() {
		menuSelector = new JComboBox<>();
		menuSelector.setBounds(450, 70, 200, 40);
		createMenuComboBoxData();
		menuSelector.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					menuTableModel.setRowCount(0);
					Menu selectedMenu = (Menu) menuSelector.getSelectedItem();
					prepareProductDataModel(selectedMenu);
				}
			}

		});
		return menuSelector;
	}

	private void createMenuComboBoxData() {
		menuSelector.removeAllItems();
		Restaurant selectedRestaurant = (Restaurant) restaurantSelector.getSelectedItem();
		List<Menu> menuProduct = selectedRestaurant.getMenu();
		for (Menu menu : menuProduct) {
			menuSelector.addItem(menu);

		}
	}

	private void createTable(JPanel containerPanel) {
		menuItemTable = new JTable();
		createDataModel();
		JScrollPane scrollPane = new JScrollPane(menuItemTable);
		scrollPane.setBounds(370, 170, 300, 217);
		menuItemTable.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int selectedRow = menuItemTable.getSelectedRow();
				itemIdTextField.setText(menuItemTable.getValueAt(selectedRow, 0).toString());
				itemNameTextField.setText(menuItemTable.getValueAt(selectedRow, 1).toString());
				ItemPriceTextField.setText(menuItemTable.getValueAt(selectedRow, 2).toString());
				getSelectedMenuType();
			}
		});

		JTableHeader header = menuItemTable.getTableHeader();
		header.setBackground(new Color(4620980));
		header.setForeground(Color.white);
		containerPanel.add(scrollPane);
	}

	private void createDataModel() {
		menuTableModel = new DefaultTableModel();
		String[] columnName = { "ID", "Name", "Price", "Type" };
		menuTableModel.setColumnIdentifiers(columnName);
		Menu selectedMenu = (Menu) menuSelector.getSelectedItem();
		prepareProductDataModel(selectedMenu);

	}

	private void prepareProductDataModel(Menu selectedMenu) {

		menuItemDataRow = new String[4];
		List<Product> productList = selectedMenu.getProductList();
		for (Product product : productList) {
			menuItemDataRow[0] = Integer.toString(product.getMenuId());
			menuItemDataRow[1] = product.getName();
			menuItemDataRow[2] = Double.toString(product.getPrice());
			menuItemDataRow[3] = product.getMenuType().toString();

			menuTableModel.addRow(menuItemDataRow);

		}
		menuItemTable.setModel(menuTableModel);

	}

	private boolean areDataValid() {
		return !itemIdTextField.getText().isBlank() && !itemNameTextField.getText().isBlank()
				&& !ItemPriceTextField.getText().isBlank();
	}

	private void clearData() {
		itemIdTextField.setText("");
		itemNameTextField.setText("");
		ItemPriceTextField.setText("");
	}

}
