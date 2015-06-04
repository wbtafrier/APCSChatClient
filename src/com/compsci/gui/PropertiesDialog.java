package com.compsci.gui;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.compsci.gui.listener.PropertiesListener;

public class PropertiesDialog extends JDialog {

	private static final long serialVersionUID = 4223266559039204055L;

	private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static String[] fontFamilyNames = ge.getAvailableFontFamilyNames();
	
	public JPanel propertiesPanel = new JPanel();
	public JPanel chatColorPanel = new JPanel();
	public JPanel textColorPanel = new JPanel();
	public JPanel fontPanel = new JPanel();
	public JPanel donePanel = new JPanel();
	public JLabel chatColorLabel = new JLabel("Chat Color: ");
	public JButton selectChatColor = new JButton("Select Chat Color...");
	public JLabel textColorLabel = new JLabel("Text Color: ");
	public JButton selectTextColor = new JButton("Select Text Color...");
	public JLabel fontLabel = new JLabel ("Font: ");
	public JComboBox<String> fontDropdown = new JComboBox<String>(fontFamilyNames);
	public JButton doneButton = new JButton("Done");
	
	public PropertiesListener propertiesListener = new PropertiesListener();
	private FontComboRenderer comboRenderer = new FontComboRenderer();
	
	public PropertiesDialog() {
		super(FrameHandle.getFrame(), "Sloverse Chat - Properties");
		propertiesPanel.setLayout(new GridLayout(4, 0));
		
		chatColorPanel.setLayout(new BoxLayout(chatColorPanel, BoxLayout.X_AXIS));
		chatColorPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		chatColorLabel.setBorder(new EmptyBorder(0, 0, 0, 5));
		chatColorPanel.add(chatColorLabel);
		selectChatColor.addActionListener(propertiesListener);
		selectChatColor.setToolTipText("Select the background color of your chat window.");
		chatColorPanel.add(selectChatColor);
		
		textColorPanel.setLayout(new BoxLayout(textColorPanel, BoxLayout.X_AXIS));
		textColorPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		textColorLabel.setBorder(new EmptyBorder(0, 0, 0, 5));
		textColorPanel.add(textColorLabel);
		selectTextColor.addActionListener(propertiesListener);
		selectTextColor.setToolTipText("Select the color of the text in your chat window.");
		textColorPanel.add(selectTextColor);
		
		fontPanel.setLayout(new BoxLayout(fontPanel, BoxLayout.X_AXIS));
		fontPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		fontLabel.setBorder(new EmptyBorder(0, 0, 0, 5));
		fontPanel.add(fontLabel);
		fontDropdown.setSelectedItem(FrameHandle.getOutputPane().getFont().getName());
		fontDropdown.setFont(new Font(fontDropdown.getSelectedItem().toString(), Font.PLAIN, 16));
		fontDropdown.setRenderer(comboRenderer);
		fontDropdown.addItemListener(propertiesListener);
		fontDropdown.setBorder(new EmptyBorder(0, 0, 0, 10));
		fontPanel.add(fontDropdown);
		
		doneButton.addActionListener(propertiesListener);
		donePanel.add(doneButton);
		
		propertiesPanel.add(chatColorPanel);
		propertiesPanel.add(textColorPanel);
		propertiesPanel.add(fontPanel);
		propertiesPanel.add(donePanel);
		this.add(propertiesPanel);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(FrameHandle.getFrame());
		this.addWindowListener(propertiesListener);
		this.setVisible(true);
	}
}
