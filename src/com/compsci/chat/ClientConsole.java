package com.compsci.chat;

import java.util.logging.Level;

import com.compsci.gui.FrameHandle;
import com.compsci.util.SloverseLogger;

public class ClientConsole {

	public static void printMessage(Message m) {
		
		if (InputManager.isMessageAcceptable(m.getMessage())){
			String oldText = FrameHandle.getOutputPane().getText();
			if (oldText.isEmpty()) FrameHandle.getOutputPane().setText(oldText + m.getFormattedMessage());
			else FrameHandle.getOutputPane().setText(oldText + "\n" + m.getFormattedMessage());
		}
		else {
			SloverseLogger.logErrorMessage(Level.WARNING, "Message is not in the correct format! Will not print out message.");
		}
	}
}
