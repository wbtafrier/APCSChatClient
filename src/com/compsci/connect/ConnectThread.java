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
import com.compsci.gui.LoginPanel;
import com.compsci.user.Player;
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
		Socket clientSocket = new Socket();
		hostAddress = LoginPanel.getIpField().getText();
		portNumber = Integer.parseInt(LoginPanel.getPortField().getText());

		try {
			ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Attempting to connect to server... " + hostAddress + " at port : " + portNumber));
			clientSocket.connect(new InetSocketAddress(hostAddress, portNumber), TIME_OUT);
		} catch (IOException e) {
			ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Timed out when trying to connect to the ip : " + hostAddress + " at port " + portNumber));
			isConnected = false;
			return;
		}
		isConnected = true;
		ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Connected successfully!"));
		
		try {
			outStream = new ObjectOutputStream(clientSocket.getOutputStream());
			inStream = new ObjectInputStream(clientSocket.getInputStream());
			
			player = new Player(LoginPanel.getUsernameField().getText());
			ConnectionManager.sendData(player);
			Object incoming;

			while (isConnected) {
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
			e.printStackTrace();
			SloverseLogger.logErrorMessage(Level.WARNING, "Connection Lost. Internal exception: " + e.getClass());
			ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Connection Lost."));
			setConnected(false);
			//Show message to bring the user back to home screen.
		} catch (IOException e) {
			e.printStackTrace();
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
