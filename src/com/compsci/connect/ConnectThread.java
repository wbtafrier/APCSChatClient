package com.compsci.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;

import com.compsci.chat.ChatManager;
import com.compsci.chat.ClientConsole;
import com.compsci.chat.Message;
import com.compsci.core.SloverseClient;
import com.compsci.user.Player;
import com.compsci.util.LoginHandler;
import com.compsci.util.SloverseLogger;

public class ConnectThread extends Thread {

	private boolean isConnected = false;
	
	private static final int TIME_OUT = 5000;
	private static String hostAddress;
	private static int portNumber;
	
	private ObjectOutputStream outStream;
	private ObjectInputStream inStream;
	
	private Player player;
	
	public ConnectThread() {
		super();
	}

	@Override
	public void run() {
		hostAddress = LoginHandler.getIpAddress();
		portNumber = LoginHandler.getPortNumber();
		
		try (Socket socket = new Socket();) {
			try {
				ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Attempting to connect to server... " + hostAddress + " at port : " + portNumber));
				socket.connect(new InetSocketAddress(hostAddress, portNumber), TIME_OUT);
			} catch (IOException e) {
				ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Timed out when trying to connect to the ip : " + hostAddress + " at port " + portNumber));
				isConnected = false;
				return;
			}
			isConnected = true;
			
			try {
				outStream = new ObjectOutputStream(socket.getOutputStream());
				inStream = new ObjectInputStream(socket.getInputStream());
				
				player = new Player(LoginHandler.getUsername());
				ConnectionManager.sendData(player);
				
				Object incoming;
				ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Connected successfully!"));
				
				while (isConnected && !socket.isClosed()) {
					incoming = inStream.readObject();
	
					if (incoming != null) {
						if (incoming instanceof Message) {
							ChatManager.printMessage((Message) incoming);
						}
					}
				}
			} catch (UnknownHostException e) {
				SloverseLogger.logErrorMessage(Level.SEVERE, "Unknown host name. :( Could not connect to: " + hostAddress);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SocketException e) {
				SloverseLogger.logErrorMessage(Level.WARNING, "Connection Lost!" + e.getStackTrace());
				ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Lost connection to the server."));
				setConnected(false);
				//Show message to bring the user back to home screen.
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setConnected(boolean b) {
		isConnected = b;
	}
	
	public ObjectOutputStream getOutStream() {
		return outStream;
	}
	
	public boolean isConnected() {
		return isConnected;
	}
}