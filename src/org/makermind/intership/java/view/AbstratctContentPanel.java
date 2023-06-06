package org.makermind.intership.java.view;

import java.awt.Color;


/*
 * @author Anduela Nurshaba
 */

import javax.swing.JPanel;

public class AbstratctContentPanel {
	
	public JPanel createBasePanel() {
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBounds(260, 0, 700, 500);
		return contentPanel;
	}

}
