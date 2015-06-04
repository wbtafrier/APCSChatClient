package com.compsci.gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.compsci.core.SloverseClient;
import com.compsci.util.DisplayInfo;

/**
 * The JFrame for the Server control panel.
 */
public class ClientFrame extends JFrame {

	private static final long serialVersionUID = 1707966321497413110L;
	private static final Dimension MINIMUM_SIZE = new Dimension((int)(DisplayInfo.getScreenSize().getWidth() / 5), (int)(DisplayInfo.getScreenSize().getHeight() / 4.5));
	private static final Dimension MAXIMUM_SIZE = new Dimension(DisplayInfo.getScreenSize().width, DisplayInfo.getScreenSize().height);

	public ClientFrame(String name) {
		super(name);
		FrameHandle.setupFrame(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListeners();
		
		this.setMinimumSize(MINIMUM_SIZE);
		this.setMaximumSize(MAXIMUM_SIZE);
		this.setLocationRelativeTo(null);
		
		this.pack();
		this.setVisible(true);
	}
	
	private void addWindowListeners() {
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent we) {
				SloverseClient.shutdownClient();
			}
		});
	}
}
