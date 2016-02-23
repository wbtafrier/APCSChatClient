package com.compsci.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.logging.Level;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.compsci.core.SloverseClient;
import com.compsci.user.User;
import com.compsci.util.SloverseLogger;
import com.compsci.util.UserUtilities;

@SuppressWarnings("rawtypes")
public class PlayerListCellRenderer implements ListCellRenderer {

	private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	
	@Override
	@SuppressWarnings("unchecked")
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		JLabel cell = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Font font = cell.getFont();
		int fontStyle = font.getStyle();
		Color fontColor = cell.getForeground();
		
		User user = null;
		for (User u : UserUtilities.getUserList()) {
			if (u.getName().equals(value)) {
				user = u;
				break;
			}
		}
		
		if (user == null) {
			SloverseLogger.logErrorMessage(Level.SEVERE, value + " cannot be not found in user registry!");
			return null;
		}
		
		if (SloverseClient.getConnectThread().getPlayer().getName().equals(user.getName())) {
			fontStyle = Font.BOLD + Font.ITALIC;
		}
		
		if (user.isMuted()) {
			Map attributes = font.getAttributes();
			fontColor = Color.DARK_GRAY;
			attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
			font = new Font(attributes);
		}
		
		if (user.isAFK()) {
			fontColor = Color.LIGHT_GRAY;
		}
		else {
			fontColor = Color.BLACK;
		}
		
		if (user.isModerator()) {
			fontColor = Color.BLUE;
		}
		else if (user.isAdministrator()) {
			fontColor = Color.RED;
		}
		
		cell.setFont(font.deriveFont(fontStyle));
		cell.setForeground(fontColor);
		return cell;
	}
}
