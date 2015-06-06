package com.compsci.core;

import com.compsci.connection.ConnectThread;
import com.compsci.gui.ClientFrame;
import com.compsci.user.ServerUser;
import com.compsci.user.User;

public class SloverseClient {

	public static final User SERVER = new ServerUser();
	private static ConnectThread userThread;
	
	public static void main(String[] args) {
		new ClientFrame("Sloverse Chat");
	}
	
	public static void shutdownClient() {
		System.exit(0);
	}
	
	public static void connect() {
		userThread = new ConnectThread();
		userThread.start();
	}
	
	public static ConnectThread getConnectThread() {
		return userThread;
	}
}
