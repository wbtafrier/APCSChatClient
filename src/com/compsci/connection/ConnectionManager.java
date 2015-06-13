package com.compsci.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

import com.compsci.chat.ClientConsole;
import com.compsci.chat.Message;
import com.compsci.core.SloverseClient;
import com.compsci.user.Player;
import com.compsci.util.SloverseLogger;

public class ConnectionManager {

	public synchronized static void sendData(Object o) {
		
		try {
			SloverseClient.getConnectThread().getOutStream().writeObject(o);
		} catch (IOException e) {
			SloverseLogger.logErrorMessage(Level.SEVERE, "Error transmitting object over the network. :(");
			e.printStackTrace();
		}
	}
	
	public synchronized static boolean validatePlayer(Socket s, Player user) throws IOException, ClassNotFoundException {
		ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Validating username..."));
		sendData(user);
		
		Object incoming;
		while (SloverseClient.getConnectThread().isConnected() && !s.isClosed()) {
			incoming = SloverseClient.getConnectThread().getInStream().readObject();
			
			if (incoming instanceof Boolean) {
				return ((Boolean) incoming).booleanValue();
			}
			else {
				SloverseLogger.logErrorMessage(Level.WARNING, "Object recieved was not a boolean!");
				break;
			}
		}
		return false;
	}
	
}
