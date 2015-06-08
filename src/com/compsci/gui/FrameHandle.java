package com.compsci.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import javax.swing.text.StyledDocument;

import com.compsci.gui.listener.InputListener;
import com.compsci.gui.listener.MenuListener;
import com.compsci.gui.listener.PaneFocusListener;
import com.compsci.util.DisplayInfo;

/**
 * Provides setup information for the SloverseFrame and holds a handle to the frame.
 */
public class FrameHandle {
	
	private static int fontSize = 16;
	private static final Dimension DEFAULT_SIZE = new Dimension((int)(DisplayInfo.getScreenSize().width / 3), (int)(DisplayInfo.getScreenSize().height / 1.5));
	
	private static ClientFrame clientFrame;
	
	private static LoginPanel loginPanel = new LoginPanel();
	private static JPanel consolePanel = new JPanel();
	
	private static JMenuBar menuBar = new JMenuBar();
	private static JMenu fileMenu = new JMenu("File");
	private static JMenuItem customizeItem = new JMenuItem("Customize...", KeyEvent.VK_P);
	private static CustomizeDialog customizeDialog;
	private static JMenuItem logOutItem = new JMenuItem("Log Out");
	private static JMenuItem exitItem = new JMenuItem("Exit");
	private static MenuListener menuListener = new MenuListener();
	
	private static JTextPane outPane = new JTextPane();
	private static StyledDocument doc = outPane.getStyledDocument();
	private static JScrollPane scrollPane = new JScrollPane(outPane);
	
	private static JTextField inField = new JTextField();
	private static Border inBorder = inField.getBorder();
	private static InputListener inputListener = new InputListener();
	
	private static boolean isConsoleDisplayed = false;
	
	public static void setupFrame(ClientFrame frame) {
		if (frame != null) {		
			clientFrame = frame;
			
			customizeItem.addActionListener(menuListener);
			customizeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.CTRL_DOWN_MASK));
			customizeItem.setToolTipText("Customize the client chat window.");
			customizeItem.setEnabled(false);
			fileMenu.add(customizeItem);
			logOutItem.addActionListener(menuListener);
			logOutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
			logOutItem.setToolTipText("Log out of your current server and return to the log-in screen.");
			logOutItem.setEnabled(false);
			fileMenu.add(logOutItem);
			exitItem.addActionListener(menuListener);
			exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
			exitItem.setToolTipText("Stops the client and exits the chat window.");
			fileMenu.add(exitItem);
			fileMenu.setToolTipText("File functions enable the user to interact with the client window.");
			menuBar.add(fileMenu);
			clientFrame.setJMenuBar(menuBar);
			setupLogin();
		}
	}
	
	public static void setupLogin() {
		clientFrame.add(loginPanel);
		LoginPanel.setDisplayed(true);
	}
	
	public static void setupConsole() {
		addConsoleGridBag();
		clientFrame.add(consolePanel);
		clientFrame.pack();
		clientFrame.setLocationRelativeTo(null);
		setConsoleDisplayed(true);
		
		outPane.addFocusListener(new PaneFocusListener());
		outPane.setDragEnabled(true);
		outPane.setEditable(false);
		DefaultCaret caret = (DefaultCaret)outPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		GuiOperations.randomizeColor();
		
		String fontName = "Consolas";
		if (!DisplayInfo.isFontInstalled(fontName)) {
			fontName = "Courier New";
		}
		
		outPane.setFont(new Font(fontName, Font.PLAIN, fontSize));
		inField.setFont(new Font(fontName, Font.PLAIN, fontSize));
		
		inField.addActionListener(inputListener);
		inField.addKeyListener(inputListener);
		inField.requestFocusInWindow();
	}
	
	private static void addConsoleGridBag() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		consolePanel.setLayout(gbl);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(20, 20, 20, 20);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		consolePanel.add(scrollPane, gbc);
		
		gbc.insets = new Insets(0, 20, 20, 20);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = gbc.weighty = 0.0;
		gbc.anchor = GridBagConstraints.PAGE_END;
		
		inField.setBorder(BorderFactory.createCompoundBorder(inBorder, BorderFactory.createEmptyBorder(5, 2, 5, 2)));
		consolePanel.add(inField, gbc);
		consolePanel.setPreferredSize(DEFAULT_SIZE);
	}
	
	public static ClientFrame getFrame() {
		return clientFrame;
	}
	
	public static LoginPanel getLoginPanel() {
		return loginPanel;
	}
	
	public static JPanel getConsolePanel() {
		return consolePanel;
	}
	
	public static JMenuBar getMenuBar() {
		return menuBar;
	}
	
	public static JMenu getFileMenu() {
		return fileMenu;
	}
	
	public static JMenuItem getCustomizeItem() {
		return customizeItem;
	}
	
	public static JMenuItem getLogOutItem() {
		return logOutItem;
	}
	
	public static JMenuItem getExitItem() {
		return exitItem;
	}
	
	public static JTextPane getOutputPane() {
		return outPane;
	}
	
	public static StyledDocument getDocument() {
		return doc;
	}
	
	public static JScrollPane getScrollPane() {
		return scrollPane;
	}
	
	public static JTextField getInputField() {
		return inField;
	}
	
	public static CustomizeDialog getCustomizeDialog() {
		return customizeDialog;
	}
	
	public static void setCustomizeDialog(CustomizeDialog prop) {
		customizeDialog = prop;
	}
	
	public static boolean isConsoleDisplayed() {
		return isConsoleDisplayed;
	}
	
	public static void setConsoleDisplayed(boolean b) {
		isConsoleDisplayed = b;
	}
}
