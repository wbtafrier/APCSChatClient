package com.compsci.gui.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;

import com.compsci.gui.FrameHandle;
import com.compsci.gui.GuiOperations;

public class PropertiesListener extends WindowAdapter implements ActionListener, ItemListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (FrameHandle.getPropertiesDialog() != null)
		{
			if (e.getSource().equals(FrameHandle.getPropertiesDialog().selectChatColor)) {
				Color c = JColorChooser.showDialog(FrameHandle.getPropertiesDialog(), "Sloverse Chat - Select Chat Color", FrameHandle.getOutputPane().getBackground());
				GuiOperations.changeChatColor(c);
			}
			else if (e.getSource().equals(FrameHandle.getPropertiesDialog().selectTextColor)) {
				Color c = JColorChooser.showDialog(FrameHandle.getPropertiesDialog(), "Sloverse Chat - Select Text Color", FrameHandle.getOutputPane().getForeground());
				GuiOperations.changeTextColor(c);
			}
			else if (e.getSource().equals(FrameHandle.getPropertiesDialog().doneButton)) {
				String fontName = FrameHandle.getPropertiesDialog().fontDropdown.getSelectedItem().toString();
				Font f = new Font(fontName, Font.PLAIN, 12);
				FrameHandle.getOutputPane().setFont(f);
				FrameHandle.getInputField().setFont(f);
				GuiOperations.closePropertiesDialog();
			}
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		GuiOperations.closePropertiesDialog();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(FrameHandle.getPropertiesDialog().fontDropdown)) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String fontName = FrameHandle.getPropertiesDialog().fontDropdown.getSelectedItem().toString();
				FrameHandle.getPropertiesDialog().fontDropdown.setFont(new Font(fontName, Font.PLAIN, 16));
				FrameHandle.getPropertiesDialog().pack();
				FrameHandle.getPropertiesDialog().setLocationRelativeTo(FrameHandle.getFrame());
			}
		}
	}

}
