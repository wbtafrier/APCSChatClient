package com.compsci.gui.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import com.compsci.core.SloverseClient;
import com.compsci.gui.GuiOperations;
import com.compsci.gui.LoginPanel;
import com.compsci.util.LoginHandler;

public class LoginListener extends KeyAdapter implements ActionListener, FocusListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(LoginPanel.getLoginButton())) {
			if (LoginHandler.attemptLogin(LoginPanel.getUsernameField().getText(), LoginPanel.getIpField().getText(), LoginPanel.getPortField().getText())) {
				GuiOperations.switchToConsole();
				SloverseClient.connect();
			}
		}
		else if (e.getSource().equals(LoginPanel.getUsernameField())) {
			LoginPanel.getIpField().requestFocusInWindow();
		}
		else if (e.getSource().equals(LoginPanel.getIpField())) {
			LoginPanel.getPortField().requestFocusInWindow();
		}
		else if (e.getSource().equals(LoginPanel.getPortField())) {
			LoginPanel.getLoginButton().doClick();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		
		JTextField field = null;
		if (e.getComponent() instanceof JTextField) {
			field = (JTextField)e.getComponent();
		}
		else {
			return;
		}
		
		if (field.getBackground().equals(LoginHandler.errorColor)) {
			field.setBackground(Color.WHITE);
		}
		
		if (field.equals(LoginPanel.getUsernameField())) {
	    	if (((c < '0' || c > '9') && (c < 'A' || c > 'Z') && (c < 'a' || c > 'z')
	    		&& c != '_' && c != KeyEvent.VK_BACK_SPACE)
	    		|| LoginPanel.getUsernameField().getText().length() > 19) {
	    		e.consume();
	    	}
	    }
	    else if (field.equals(LoginPanel.getIpField())) {
	    	if ((c < '0' || c > '9') && (c < 'A' || c > 'Z') && (c < 'a' || c > 'z')
	    		&& c != KeyEvent.VK_PERIOD && c != KeyEvent.VK_BACK_SPACE) {
    			e.consume();
    		}
	    }
	    else if (field.equals(LoginPanel.getPortField())) {
	    	if (((c < '0' || c > '9') && c != KeyEvent.VK_BACK_SPACE)
	    		|| LoginPanel.getPortField().getText().length() > 4) {
    			e.consume();
    		}
	    }
	}

	@Override
	public void focusGained(FocusEvent e) {
		JTextField field = null;
		
		if (e.getComponent() instanceof JTextField) {
			field = (JTextField)e.getComponent();
		}
		else {
			return;
		}
		
		if (field.getBackground().equals(LoginHandler.errorColor)) {
			field.setBackground(Color.WHITE);
			field.setText("");
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {}

}
