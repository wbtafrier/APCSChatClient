package com.compsci.util;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

import com.compsci.gui.LoginPanel;

public class LoginHandler {
	
	public static final Color errorColor = new Color(225, 132, 132);
	private static final String errorMessage = "<html>Incorrect format<br>or empty field(s)</html>";
	
	public static boolean attemptLogin(String username, String ip, String port) {
		boolean passed = true;
		if (!verifyUsername(username)) {
			LoginPanel.getUsernameField().setBackground(errorColor);
			passed = false;
		}
		if (!verifyIpAddress(ip)) {
			LoginPanel.getIpField().setBackground(errorColor);
			passed = false;
		}
		if (!verifyPort(port)) {
			LoginPanel.getPortField().setBackground(errorColor);
			passed = false;
		}
		
		if (!passed) {
			LoginPanel.getErrorLabel().setText(errorMessage);
		}
		
		return passed;
	}
	
	public static boolean verifyUsername(String username) {
		if (username != null && !username.isEmpty() && username.length() <= 20) {
			for (int i = 0; i < username.length(); i++) {
				char c = username.charAt(i);
				if ((c < '0' || c > '9') && (c < 'A' || c > 'Z') && (c < 'a' || c > 'z')
					&& c != '_') {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean verifyIpAddress(String ipAddress) {
		if (ipAddress != null && !ipAddress.isEmpty()) {
			for (int i = 0; i < ipAddress.length(); i++) {
				char c = ipAddress.charAt(i);
				if ((c < '0' || c > '9') && (c < 'A' || c > 'Z') && (c < 'a' || c > 'z')
			    	&& c != KeyEvent.VK_PERIOD) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean verifyPort(String port) {
		if (port != null && !port.isEmpty() && port.length() <= 5) {
			for (int i =0; i < port.length(); i++) {
				char c = port.charAt(i);
				if (c < '0' || c > '9') {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static String getUsername() {
		String username = LoginPanel.getUsernameField().getText();
		if (verifyUsername(username)) {
			return username;
		}
		SloverseLogger.logErrorMessage(Level.WARNING, "Username is NOT in acceptable format or too long! Letters, numbers, underscores only! Returning null!");
		return null;
	}
	
	public static String getIpAddress() {
		String ipAddress = LoginPanel.getIpField().getText();
		if (verifyIpAddress(ipAddress)) {
			return ipAddress;
		}
		SloverseLogger.logErrorMessage(Level.WARNING, "IP Address is NOT in acceptable format! Returning null!");
		return null;
	}
	
	public static int getPortNumber() {
		String port = LoginPanel.getPortField().getText();
		int portNum;
		
		if (verifyPort(port)) {
			try {
				portNum = Integer.parseInt(port);
			}
			catch (NumberFormatException nfe) {
				SloverseLogger.logErrorMessage(Level.WARNING, "Port number is NOT in acceptable format! Numbers only! Returning default 609!");
				return 609;
			}
			return portNum;
		}
		SloverseLogger.logErrorMessage(Level.WARNING, "Port number is NOT in acceptable format or too long! Numbers only! Returning default 609!");
		return 609;
	}
}
