package org.makermind.intership.java.view;
/*
 * @author Anduela Nurshaba
 */

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.makerminds.intership.java.restaurantpoint.controller.login.LoginController;
import org.makerminds.intership.java.restaurantpoint.login.model.User;

import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class LoginView {

	/*
	 * @author Anduela Nurshaba
	 */

	private JFrame frame;

	private final Font LABEL_FONT = (new Font("Segoe UI Semibold", Font.BOLD, 17));

	private JTextField usernameTextField = new JTextField();

	private JPasswordField passwordField;

	JLabel loginResultLabel;

	private JLabel tittleLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.prepareView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginView() {
	}
	
    public  void prepareView() {
		initialize();
		createLoginComponents();

	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setVisible(true);
	}

	private void createLoginComponents() {
		createUsernameComponents();
		createPasswordComponents();
		createResultLabel();
		createLoginButton();
		createTitleWelcome();
	}

	private void createUsernameComponents() {
		JLabel usernameLabel = createUsernameTextFieldLabel();
		createUsernameTextField();
		frame.getContentPane().add(usernameLabel);
		frame.getContentPane().add(usernameTextField);

	}

	private void createUsernameTextField() {
		usernameTextField.setBounds(111, 53, 191, 23);
		usernameTextField.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
	}

	private JLabel createUsernameTextFieldLabel() {
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 15));
		usernameLabel.setBounds(30, 45, 100, 40);
		return usernameLabel;
	}

	private void createPasswordComponents() {
		JLabel passwordLabel = createPasswordTextFieldLabel();
		frame.getContentPane().add(passwordLabel);
		createPasswordTextField();
		frame.getContentPane().add(passwordField);
	}

	private void createPasswordTextField() {
		passwordField = new JPasswordField();
		passwordField.setFont(LABEL_FONT);
		passwordField.setBounds(111, 113, 191, 23);
		passwordField.setEchoChar('*');
	}

	private JLabel createPasswordTextFieldLabel() {
		JLabel usernameLabel = new JLabel("Password:");
		usernameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 15));
		usernameLabel.setBounds(30, 108, 80, 35);
		return usernameLabel;
	}

	private void createResultLabel() {
		loginResultLabel = new JLabel();
		loginResultLabel.setBounds(30, 152, 300, 40);
		loginResultLabel.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 13));
		frame.add(loginResultLabel);
	}

	private void createTitleWelcome() {
		JLabel titleWelcome = new JLabel("Welcome!");
		titleWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		titleWelcome.setForeground(SystemColor.activeCaption);
		titleWelcome.setFont(new Font("High Tower Text", Font.BOLD | Font.ITALIC, 19));
		titleWelcome.setBounds(162, -1, 125, 40);
		frame.getContentPane().add(titleWelcome);

	}

	private void createLoginButton() {
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 14));
		loginButton.setBounds(162, 192, 100, 40);
		loginButton.setBackground(UIManager.getColor("Button.shadow"));
		loginButton.setForeground(Color.blue);

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				loginWithPorvidersUserCrecials();
			}

		});
		frame.getContentPane().add(loginButton);
		{
			tittleLabel = new JLabel("Welcome!");
			tittleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tittleLabel.setForeground(SystemColor.activeCaption);
			tittleLabel.setFont(new Font("High Tower Text", Font.BOLD | Font.ITALIC, 19));
			tittleLabel.setBounds(162, -1, 125, 40);
			frame.getContentPane().add(tittleLabel);
		}

		JLabel backroundLabel = new JLabel();
		ImageIcon img = new ImageIcon(this.getClass().getResource("/images.jpg"));
		backroundLabel.setIcon(img);
		backroundLabel.setBounds(0, -1, 436, 264);
		frame.getContentPane().add(backroundLabel);

	}

	private void loginWithPorvidersUserCrecials() {
		LoginController.getInstance();
		String username = usernameTextField.getText();
		String password = String.valueOf(passwordField.getPassword());

		Boolean creadicialProvided = isCredicialsProvided(username, password);
		if (creadicialProvided) {
			LoginController.getInstance().loginUser(username, password);

			User loogedInUser = LoginController.getInstance().getLoggedInUser();

			boolean isLoggedInSuccsessful = isLoggedInSuccsessful(loogedInUser);
			if (isLoggedInSuccsessful) {
				frame.dispose();
				new UserRoleView();
			}
		}
	}

	private boolean isLoggedInSuccsessful(User loggedInUser) {
		if (loggedInUser == null) {
			loginResultLabel.setText("Invalid Username or Password!");
			usernameTextField.setText("");
			passwordField.setText("");
			return false;
		}

		return true;
	}

	private Boolean isCredicialsProvided(String username, String password) {
		if (username.equals("") && username.isBlank()) {
			loginResultLabel.setText("Please provide your username!");
			return false;

		} else if (password.equals("") && password.isBlank()) {
			loginResultLabel.setText("Please provide your password!");
			return false;

		}
		return true;

	}

}
