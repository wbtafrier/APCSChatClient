package com.compsci.gui;

import java.awt.Color;
import java.util.Random;

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
	
	public static void openPropertiesDialog() {
		if (FrameHandle.getPropertiesDialog() == null) {
			FrameHandle.setPropertiesDialog(new PropertiesDialog());
		}
		else {
			FrameHandle.getPropertiesDialog().setVisible(true);
		}
	}
	
	public static void closePropertiesDialog() {
		if (FrameHandle.getPropertiesDialog() != null) {
			FrameHandle.getPropertiesDialog().setVisible(false);
			FrameHandle.getPropertiesDialog().dispose();
		}
	}
	
	public static void switchToConsole() {
		if (LoginPanel.isDisplayed()) {
			if (FrameHandle.getFrame() != null) {
				FrameHandle.getFrame().remove(FrameHandle.getLoginPanel());
				LoginPanel.setDisplayed(false);
				
				FrameHandle.setupConsole();
				FrameHandle.getPropertiesItem().setEnabled(true);
				FrameHandle.getFrame().revalidate();
				FrameHandle.getFrame().repaint();
				System.out.println("DONE");
			}
		}
	}
}
