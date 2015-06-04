package com.compsci.core;

import com.compsci.connect.ConnectThread;
import com.compsci.gui.ClientFrame;
import com.compsci.user.ServerUser;
import com.compsci.user.User;

public class SloverseClient {

	public static final User SERVER = new ServerUser();
	private static ConnectThread userThread;
	
	public static void main(String[] args) {
		new ClientFrame("Sloverse Chat");
		userThread = new ConnectThread();
	}
	
	public static void shutdownClient() {
		userThread.setConnected(false);
		System.exit(0);
	}
	
	public static void connect() {
		userThread.start();
	}
	
	public static ConnectThread getConnectThread() {
		return userThread;
	}
}
