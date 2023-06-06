package org.makermind.intership.java.view;
/*
 * @author Anduela Nurshaba
 */

import java.awt.Color;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.makerminds.intership.java.restaurantpoint.controller.login.LoginController;
import org.makerminds.intership.java.restaurantpoint.login.model.User;
import org.makerminds.intership.java.restaurantpoint.model.Restaurant;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class RestaurantManager extends AbstratctContentPanel implements IView {

	private JTextField restaurantNameTextField;
	private JTextField restaurantAddressTextField;

	private DefaultTableModel restaurantTableModel;
	private JTable restaurantTable;
	
	private String[] restaurantDataRow;



	public JPanel prepareView() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBounds(250, 0, 700, 500);
		createInputDataPanel(contentPanel);
		createButtons(contentPanel);
		createTable(contentPanel);

		return contentPanel;

	}

	private void createInputDataPanel(JPanel containerPanel) {
		JLabel nameLabel = createLabel(20, "Restaurant Name:");
		JLabel addressLabel = createLabel(80, "Restaurant Address:");

		restaurantNameTextField = createTextField(40);
		restaurantAddressTextField = createTextField(100);

		containerPanel.add(nameLabel);
		containerPanel.add(restaurantNameTextField);
		containerPanel.add(addressLabel);
		containerPanel.add(restaurantAddressTextField);
	}

	private JTextField createTextField(int verticalPosition) {
		JTextField textField = new JTextField();
		textField.setBounds(20, verticalPosition, 200, 27);
		textField.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 15));

		return textField;

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
		addButton.setBounds(20, 245, 100, 30);
		addButton.setBackground(new Color(70, 130, 180));
		addButton.setForeground(Color.white);
		addButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));

		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				restaurantDataRow = new String[2];

				if (restaurantNameTextField.getText().equals("") || restaurantAddressTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill Complete Information");
				
				} else {
					restaurantDataRow[0] = restaurantNameTextField.getText();
					restaurantDataRow[1] = restaurantAddressTextField.getText();
					restaurantTableModel.addRow(restaurantDataRow);

					restaurantNameTextField.setText("");
					restaurantAddressTextField.setText("");
				}

			}
		});

		return addButton;
	}

	private JButton createUpdateButton() {
		JButton updateButton = new JButton("UPDATE");
		updateButton.setBounds(130, 245, 100, 30);
		updateButton.setBackground(new Color(70, 130, 180));
		updateButton.setForeground(Color.white);
		updateButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));

		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = restaurantTable.getSelectedRow();
				if (i >= 0) {
					restaurantTableModel.setValueAt(restaurantNameTextField.getText(), i, 0);
					restaurantTableModel.setValueAt(restaurantAddressTextField.getText(), i, 1);
					// clear data of JtextField
					restaurantNameTextField.setText("");
					restaurantAddressTextField.setText("");
					JOptionPane.showMessageDialog(null, "Update Successfully!");

				} else {
					JOptionPane.showMessageDialog(null, "Please Select a Row you want to Update!");

				}
			}

		});

		return updateButton;
	}

	private JButton createDeleteButton() {
		JButton deleteButton = new JButton("DELETE");
		deleteButton.setBounds(240, 245, 100, 30);
		deleteButton.setBackground(new Color(70, 130, 180));
		deleteButton.setForeground(Color.white);
		deleteButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = restaurantTable.getSelectedRow();
				if (i >= 0) {
					restaurantTableModel.removeRow(i);
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					// clear data of jTextField
					restaurantNameTextField.setText("");
					restaurantAddressTextField.setText("");
				} else {

					JOptionPane.showConfirmDialog(null, "Please Select a Row you want to Delete!", "Please",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});

		return deleteButton;
	}

	private void createTable(JPanel containerPanel) {
		restaurantTable = new JTable();
		createDataModel();
		JScrollPane scrollPane = new JScrollPane(restaurantTable);
		scrollPane.setBounds(352, 20, 303, 255);
		restaurantTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = restaurantTable.getSelectedRow();
				restaurantNameTextField.setText(restaurantTable.getValueAt(selectedRow, 0).toString());
				restaurantAddressTextField.setText(restaurantTable.getValueAt(selectedRow, 1).toString());
			}

		});

		JTableHeader header = restaurantTable.getTableHeader();
		header.setBackground(new Color(4620980));
		header.setForeground(Color.white);
		containerPanel.add(scrollPane);

	}

	private void createDataModel() {
		restaurantTableModel = new DefaultTableModel();
		String[] columnName = { "Name", "Address" };
		restaurantTableModel.setColumnIdentifiers(columnName);
		createJTableData();
	}

	private void createJTableData() {
		User logedInUser  = LoginController.getInstance().getLoggedInUser();
		List<Restaurant> restaurantList = logedInUser.getRestaurantList();
		restaurantDataRow = new String[2];
		for (Restaurant restaurant : restaurantList) {
			restaurantDataRow[0] = restaurant.getName();
			restaurantDataRow[1] = restaurant.getAddress();

			restaurantTableModel.addRow(restaurantDataRow);

		}
		restaurantTable.setModel(restaurantTableModel);
	}

	

}
