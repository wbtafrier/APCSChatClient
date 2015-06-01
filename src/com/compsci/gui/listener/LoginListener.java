package com.compsci.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.compsci.core.SloverseClient;
import com.compsci.gui.GuiOperations;
import com.compsci.gui.LoginPanel;

public class LoginListener extends KeyAdapter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(LoginPanel.getLoginButton())) {
			GuiOperations.switchToConsole();
			SloverseClient.connect();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
	    if ((c < '0' || c > '9') && (c < 'A' || c > 'Z') && (c < 'a' || c > 'z')
	    		&& c != '_' && c != KeyEvent.VK_BACK_SPACE) {
	    	e.consume();
	    }
	}

}
