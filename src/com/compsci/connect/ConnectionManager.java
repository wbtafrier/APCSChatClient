package com.compsci.connect;

import java.io.IOException;
import java.util.logging.Level;

import com.compsci.core.SloverseClient;
import com.compsci.util.SloverseLogger;

public class ConnectionManager {

	public static void sendData(Object o) {
		
		try {
			SloverseClient.getConnectThread().getOutStream().writeObject(o);
		} catch (IOException e) {
			SloverseLogger.logErrorMessage(Level.SEVERE, "Error transmitting object over the network. :(");
			e.printStackTrace();
		}
	}
}
