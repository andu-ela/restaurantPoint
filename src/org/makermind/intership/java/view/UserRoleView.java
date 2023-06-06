package org.makermind.intership.java.view;
/*
 * @author Anduela Nurshaba
 */

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import org.makerminds.intership.java.restaurantpoint.controller.login.LoginController;
import org.makerminds.intership.java.restaurantpoint.login.model.User;
import org.makerminds.intership.java.restaurantpoint.login.model.UserFeature;
import org.makerminds.intership.java.restaurantpoint.login.model.UserRole;
import org.makerminds.intership.java.utils.AuthorizationService;
import org.makerminds.intership.java.utils.UserFeatureContentPanelCreator;
import org.makerminds.intership.java.utils.UserFeatureLabelResover;

public class UserRoleView {

	private JFrame frame;
	private static JLayeredPane layeredPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRoleView window = new UserRoleView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
	}

	public UserRoleView() {
		initialize();

	}

	public void initialize() {

		LoginController loginController = LoginController.getInstance();
		User user = loginController.getLoggedInUser();
		UserRole userRole = user.getUserRole();
		List<UserFeature> userFeatureList = AuthorizationService.getUserFeatureListByUserRole(userRole);

		frame = new JFrame();
		frame.setBounds(100, 100, 984, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create navigation bar panel
		JPanel nagBar = createNavigationBarPanel(userFeatureList);

		// create content panel
		JPanel contentPanel = createContentPanel("Content Panel");

		// create a component to contain a content panel
		layeredPane = new JLayeredPane();
		layeredPane.add(contentPanel);

		// add those component in JFrame
		frame.getContentPane().add(nagBar);
		frame.getContentPane().add(layeredPane);

		frame.setVisible(true);

	}

	private JPanel createNavigationBarPanel(List<UserFeature> userRoleFeature) {

		JPanel navigationBarPanel = new JPanel();
		navigationBarPanel.setForeground(new Color(70, 130, 180));
		navigationBarPanel.setBackground(new Color(4620980));
		navigationBarPanel.setBounds(0, 0, 266, 582);
		TitledBorder navigationBarTitledBorder = BorderFactory.createTitledBorder("Navigation Bar");
		navigationBarTitledBorder.setTitleJustification(TitledBorder.CENTER);
		navigationBarTitledBorder.setTitleColor(Color.white);
		navigationBarPanel.setBorder(navigationBarTitledBorder);
		navigationBarPanel.setLayout(null);

		JButton signOutButton = new JButton("Sign Out");
		signOutButton.setFont(new Font("Cascadia Code", Font.BOLD | Font.ITALIC, 16));
		signOutButton.setBackground(new Color(4620980));
		signOutButton.setForeground(new Color(255, 255, 255));
		signOutButton.setBounds(65, 500, 140, 50);
		signOutButton.setBorder(BorderFactory.createLoweredBevelBorder());
		navigationBarPanel.add(signOutButton);

		signOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
		        LoginView loginView = new LoginView();
		        loginView.prepareView();

			}
		});

		List<JPanel> navigationBarPanelList = createNavgiationBarPanelButtons(userRoleFeature);
		for (JPanel panel : navigationBarPanelList) {
			navigationBarPanel.add(panel);

		}

		return navigationBarPanel;

	}

	private List<JPanel> createNavgiationBarPanelButtons(List<UserFeature> userRoleFreatureList) {
		List<JPanel> navigationBarPanelList = new ArrayList<>();
		JPanel navigationBarPanel = null;

		int navBarItemVerticalPosition = 0;
		int navBarItemSpacing = 60;

		for (UserFeature userRoleFreature : userRoleFreatureList) {
			navigationBarPanel = new JPanel();
			navBarItemVerticalPosition += navBarItemSpacing;
			navigationBarPanel.setBounds(20, navBarItemVerticalPosition, 200, 40);
			String label = UserFeatureLabelResover.getLabelResolver(userRoleFreature);
			JLabel navigationBarLabel = new JLabel(label);
			navigationBarLabel.setForeground(Color.white);
			Font labelFont = new Font("Segoe UI Semilight", Font.PLAIN, 17);
			navigationBarPanel.setBackground(new Color(4620980));
			navigationBarPanel.setForeground(Color.white);
			navigationBarLabel.setFont(labelFont);
			navigationBarPanel.add(navigationBarLabel);
			navigationBarPanel.setBorder(BorderFactory.createRaisedBevelBorder());


			new UserFeatureContentPanelCreator();
			IView iview = UserFeatureContentPanelCreator.getUserFeatureIView(userRoleFreature);
			prepareNavigationBarItemMouseListener(navigationBarPanel, iview);

			navigationBarPanelList.add(navigationBarPanel);

		}
		return navigationBarPanelList;

	}

	private JPanel createContentPanel(String contentPanelText) {
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new CompoundBorder());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(250, 0, 800, 400);
		contentPanel.setLayout(null);
		
		User loggedInUser = LoginController.getInstance().getLoggedInUser();
		JLabel contentPanelLabel = new JLabel("Welcome " + loggedInUser.getUserRole());
		contentPanelLabel.setBounds(250, 120, 400, 30);
		contentPanelLabel.setForeground(SystemColor.textHighlight);
		contentPanelLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPanelLabel.setBackground(new Color(4620980));
		

		contentPanel.add(contentPanelLabel);

		return contentPanel;

	}

	private void prepareNavigationBarItemMouseListener(JPanel navigationBarPanel, IView iview) {
		navigationBarPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				clickedNavigationBarItem(iview);

			}

		});

	}

	public void clickedNavigationBarItem(IView iview) {
		JPanel contentPanel = iview.prepareView();
		layeredPane.removeAll();
		layeredPane.add(contentPanel);
		layeredPane.repaint();
	}

	public static JLayeredPane getContentPane() {

		return layeredPane;

	}

}
