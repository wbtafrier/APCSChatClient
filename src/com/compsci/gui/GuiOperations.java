package com.compsci.gui;

import java.awt.Color;
import java.util.Random;

import com.compsci.user.User;
import com.compsci.util.UserUtilities;

public class GuiOperations {

	private static Random rand = new Random();
	
	public static void changeChatColor(Color newColor) {
		if (!FrameHandle.getOutputPane().getBackground().equals(newColor) && newColor != null) {
			FrameHandle.getOutputPane().setBackground(newColor);
		}
	}
	
	public static void changeTextColor(Color newColor) {
		if (!FrameHandle.getOutputPane().getForeground().equals(newColor) && newColor != null) {
			FrameHandle.getOutputPane().setForeground(newColor);
		}
	}
	
	public static void randomizeColor() {
		FrameHandle.getOutputPane().setBackground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		FrameHandle.getOutputPane().setForeground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
	}
	
	public static void openCustomizeDialog() {
		if (FrameHandle.getCustomizeDialog() == null) {
			FrameHandle.setCustomizeDialog(new CustomizeDialog());
		}
		else {
			FrameHandle.getCustomizeDialog().setVisible(true);
		}
	}
	
	public static void closeCustomizeDialog() {
		if (FrameHandle.getCustomizeDialog() != null) {
			FrameHandle.getCustomizeDialog().setVisible(false);
			FrameHandle.getCustomizeDialog().dispose();
		}
	}
	
	public static void switchToConsole() {
		if (LoginPanel.isDisplayed()) {
			if (FrameHandle.getFrame() != null) {
				FrameHandle.getFrame().remove(FrameHandle.getLoginPanel());
				LoginPanel.setDisplayed(false);
				
				FrameHandle.setupConsole();
				FrameHandle.getOutputPane().setText("");
				FrameHandle.getInputField().setText("");
				clearUserList();
				FrameHandle.getCustomizeItem().setEnabled(true);
				FrameHandle.getLogOutItem().setEnabled(true);
				FrameHandle.getFrame().revalidate();
				FrameHandle.getFrame().repaint();
			}
		}
	}
	
	public static void switchToLogin() {
		if (FrameHandle.isConsoleDisplayed()) {
			if (FrameHandle.getFrame() != null) {
				FrameHandle.getFrame().remove(FrameHandle.getConsolePanel());
				FrameHandle.setConsoleDisplayed(false);
				
				FrameHandle.setupLogin();
				LoginPanel.getErrorLabel().setText("");
				FrameHandle.getCustomizeItem().setEnabled(false);
				FrameHandle.getLogOutItem().setEnabled(false);
				FrameHandle.getFrame().revalidate();
				FrameHandle.getFrame().repaint();
			}
		}
	}
	
	public static void addUserToList(User user) {
		if (!UserUtilities.getUserList().contains(user)) {
			UserUtilities.getUserList().add(user);
		}
		
		if (FrameHandle.isConsoleDisplayed()) {
			if (FrameHandle.getFrame() != null && !FrameHandle.getPlayerListModel().contains(user.getName())) {
				FrameHandle.getPlayerListModel().addElement(user.getName());
			}
		}
	}
	
	public static void removeUserFromList(String username) {
		for (User user : UserUtilities.getUserList()) {
			if (user.getName().equals(username)) {
				UserUtilities.getUserList().remove(user);
				break;
			}
		}
		
		if (FrameHandle.isConsoleDisplayed()) {
			if (FrameHandle.getFrame() != null && FrameHandle.getPlayerListModel().contains(username)) {
				FrameHandle.getPlayerListModel().removeElement(username);
			}
		}
	}
	
	public static void clearUserList() {
		UserUtilities.getUserList().clear();
		if (FrameHandle.getFrame() != null && !FrameHandle.getPlayerListModel().isEmpty()) {
			FrameHandle.getPlayerListModel().clear();
		}
	}
	
	public static void refreshList() {
		if (FrameHandle.getFrame() != null && !FrameHandle.getPlayerListModel().isEmpty()) {
			FrameHandle.getPlayerListModel().clear();
		}
		
		for (User user : UserUtilities.getUserList()) {
			addUserToList(user);
		}
	}
}
