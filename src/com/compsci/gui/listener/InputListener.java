package com.compsci.gui.listener;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.compsci.chat.ClientConsole;
import com.compsci.chat.InputManager;
import com.compsci.chat.Message;
import com.compsci.core.SloverseClient;
import com.compsci.gui.FrameHandle;
import com.compsci.util.SloverseLogger;

public class InputListener extends KeyAdapter implements ActionListener {

	private List<String> inputHistory = new ArrayList<>();
	private String formerText = "";
	private int currentHistoryLocation = -1;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(FrameHandle.getInputField())) {
			String rawInput = FrameHandle.getInputField().getText();
			if (!InputManager.isMessageAcceptable(rawInput)) {
				SloverseLogger.logErrorMessage(Level.WARNING, "Message is not in the correct format! Will not print out message.");
				return;
			}
			else if (!SloverseClient.isConnected()) {
				ClientConsole.printMessage(new Message(SloverseClient.SERVER, rawInput));
				ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Not connected to any server!"));
				resetInputField();
				return;
			}
			else {
				Message input = new Message(SloverseClient.SERVER, rawInput);
				InputManager.sendMessage(input);
				
				inputHistory.add(0, rawInput);
				currentHistoryLocation = -1;
				resetInputField();
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource().equals(FrameHandle.getInputField())) {
			if (currentHistoryLocation == -2) {
				currentHistoryLocation = -1;
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource().equals(FrameHandle.getInputField())) {
			if (e.getKeyCode() == KeyEvent.VK_UP || (!Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK) && e.getKeyCode() == KeyEvent.VK_NUMPAD8)) {
				
				if (currentHistoryLocation == -1) {
					formerText = FrameHandle.getInputField().getText();
				}
				
				if (inputHistory.size() <= 0 || (currentHistoryLocation + 1) >= inputHistory.size()) {
					return;
				}
				
				if (currentHistoryLocation == -2 && (formerText == null || formerText.isEmpty())) {
					currentHistoryLocation += 2;
				}
				else {
					currentHistoryLocation++;
				}
				
				if (currentHistoryLocation == -1) {
					FrameHandle.getInputField().setText(formerText);
				}
				else {
					FrameHandle.getInputField().setText(inputHistory.get(currentHistoryLocation));
				}
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN || (!Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK) && e.getKeyCode() == KeyEvent.VK_NUMPAD2)) {
				if (currentHistoryLocation == -1) {
					formerText = FrameHandle.getInputField().getText();
				}
				
				if (inputHistory.size() <= 0 || (currentHistoryLocation - 1) < -2) {
					return;
				}
				
				currentHistoryLocation--;
				if (currentHistoryLocation == -2) {
					resetInputField();
				}
				else if (currentHistoryLocation == -1) {
					FrameHandle.getInputField().setText(formerText);
				}
				else {
					FrameHandle.getInputField().setText(inputHistory.get(currentHistoryLocation));
				}
			}
		}
	}
	
	private static void resetInputField() {
		FrameHandle.getInputField().setText("");
	}
}
