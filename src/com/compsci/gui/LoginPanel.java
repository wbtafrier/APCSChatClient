package com.compsci.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.compsci.gui.listener.LoginListener;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = -6370808179302340905L;
	
	private static final Dimension DEFAULT_SIZE = new Dimension(300, 180);
	
	private static JPanel labelPanel = new JPanel();
	private static JLabel usernameLabel = new JLabel("Enter Username:", SwingConstants.RIGHT);
	private static JLabel ipLabel = new JLabel("Enter Server IP:", SwingConstants.RIGHT);
	
	private static JPanel fieldPanel = new JPanel();
	private static JTextField usernameField = new JTextField(15);
	
	private static JPanel ipPanel = new JPanel();
	private static JTextField ipField = new JTextField(10);
	private static JTextField portField = new JTextField(5);
	
	private static JPanel buttonPanel = new JPanel();
	private static JLabel errorLabel = new JLabel("");
	private static JButton loginButton = new JButton("Log-in");
	
	private static LoginListener loginListener = new LoginListener();
	private static boolean isDisplayed = false;
	
	public LoginPanel() {
		labelPanel.setLayout(new GridLayout(3, 0));
		
		labelPanel.add(usernameLabel);
		labelPanel.add(Box.createGlue());
		labelPanel.add(ipLabel);
		
		fieldPanel.setLayout(new GridLayout(3, 0));
		usernameField.setBorder(BorderFactory.createCompoundBorder(usernameField.getBorder(), BorderFactory.createEmptyBorder(5, 2, 5, 2)));
		usernameField.setToolTipText("Username");
		usernameField.addActionListener(loginListener);
		usernameField.addKeyListener(loginListener);
		usernameField.addFocusListener(loginListener);
		fieldPanel.add(usernameField);
		fieldPanel.add(Box.createGlue());
		
		ipPanel.setLayout(new BoxLayout(ipPanel, BoxLayout.X_AXIS));
		ipField.setBorder(BorderFactory.createCompoundBorder(ipField.getBorder(), BorderFactory.createEmptyBorder(5, 2, 5, 2)));
		ipField.setToolTipText("Server IP Address");
		ipField.addActionListener(loginListener);
		ipField.addKeyListener(loginListener);
		ipField.addFocusListener(loginListener);
		ipPanel.add(ipField);
		
		portField.setBorder(BorderFactory.createCompoundBorder(portField.getBorder(), BorderFactory.createEmptyBorder(5, 2, 5, 2)));
		portField.setToolTipText("Server Port Number");
		portField.addActionListener(loginListener);
		portField.addKeyListener(loginListener);
		portField.addFocusListener(loginListener);
		ipPanel.add(portField);
		fieldPanel.add(ipPanel);
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		errorLabel.setForeground(Color.RED);
		buttonPanel.add(errorLabel);
		
		loginButton.addActionListener(loginListener);
		loginButton.setToolTipText("Press this button to log-in!");
		buttonPanel.add(loginButton);
		
		this.add(labelPanel);
		this.add(fieldPanel);
		this.add(buttonPanel);
		this.addLoginGridBag();
		this.setPreferredSize(DEFAULT_SIZE);
	}
	
	private void addLoginGridBag() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.setLayout(gbl);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = gbc.weighty = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		
		this.add(labelPanel, gbc);
		
		gbc.gridx = 1;
		this.add(fieldPanel, gbc);
		
		gbc.gridy = 2;
		this.add(buttonPanel, gbc);
	}
	
	public static JPanel getLabelPanel() {
		return labelPanel;
	}
	
	public static JLabel getUsernameLabel() {
		return usernameLabel;
	}
	
	public static JTextField getUsernameField() {
		return usernameField;
	}
	
	public static JPanel getFieldPanel() {
		return fieldPanel;
	}
	
	public static JLabel getIpLabel() {
		return ipLabel;
	}
	
	public static JTextField getIpField() {
		return ipField;
	}
	
	public static JTextField getPortField() {
		return portField;
	}
	
	public static JPanel getButtonPanel() {
		return buttonPanel;
	}
	
	public static JLabel getErrorLabel() {
		return errorLabel;
	}
	
	public static JButton getLoginButton() {
		return loginButton;
	}
	
	public static boolean isDisplayed() {
		return isDisplayed;
	}
	
	public static void setDisplayed(boolean b) {
		isDisplayed = b;
	}
}
