package com.compsci.chat;

import com.compsci.connect.ConnectionManager;

public class InputManager {
	
	private static final int MAXIMUM_LENGTH = 500;
	
	public static void sendMessage(Message m) {
		ConnectionManager.sendData(m);
	}
	
	public static boolean isMessageAcceptable(String input) {
		if ((input != null) && (!input.isEmpty())  && (!input.contains("\t") && input.length() < MAXIMUM_LENGTH))
			return true;
		return false;
	}
}
