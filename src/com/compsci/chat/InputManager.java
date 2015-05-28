package com.compsci.chat;

import com.compsci.connect.ConnectionManager;

public class InputManager {
	
	public static void sendMessage(Message m) {
		ConnectionManager.sendData(m);
	}
	
	public static boolean isMessageAcceptable(String input) {
		if ((input != null) && (!input.isEmpty())  && (!input.contains("\t")))
			return true;
		return false;
	}
}
