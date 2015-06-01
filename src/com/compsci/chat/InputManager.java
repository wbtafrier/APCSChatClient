package com.compsci.chat;

import java.util.logging.Level;

import com.compsci.connect.ConnectionManager;
import com.compsci.util.SloverseLogger;

public class InputManager {
	
	private static final int MAXIMUM_LENGTH = 300;
	
	public static void sendMessage(Message m) {
		if (isMessageAcceptable(m.getMessage())){
			ConnectionManager.sendData(m);
		}
		else {
			SloverseLogger.logErrorMessage(Level.WARNING, "Message is not in the correct format! Will not print out message.");
		}
	}
	
	public static boolean isMessageAcceptable(String input) {
		if ((input != null) && (!input.isEmpty())  && (!input.contains("\t") && input.length() < MAXIMUM_LENGTH))
			return true;
		return false;
	}
}
