package com.compsci.connection;

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
import com.compsci.gui.FrameHandle;
import com.compsci.gui.GuiOperations;
import com.compsci.user.Player;
import com.compsci.user.User;
import com.compsci.util.LoginHandler;
import com.compsci.util.SloverseLogger;

public class ConnectThread extends Thread {

	private boolean isConnected = false;
	
	private static final int TIME_OUT = 5000;
	private static String hostAddress;
	private static int portNumber;
	
	private ObjectOutputStream outStream;
	private ObjectInputStream inStream;
	private Socket socket;
	
	private Player player;
	
	public ConnectThread() {
		super();
	}

	@Override
	public void run() {
		hostAddress = LoginHandler.getIpAddress();
		portNumber = LoginHandler.getPortNumber();
		
		try {
			socket = new Socket();
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
			if (!ConnectionManager.validatePlayer(socket, player)) {
				ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Server blocked connection because this username is already in use on the server: " + LoginHandler.getUsername()));
				isConnected = false;
				return;
			}
			
			Object incoming;
			ClientConsole.printMessage(new Message(SloverseClient.SERVER, "Connected successfully!"));
			
			while (isConnected && !socket.isClosed()) {
				incoming = inStream.readObject();

				if (incoming != null) {
					if (incoming instanceof Message) {
						ChatManager.printMessage((Message) incoming);
					}
					else if (incoming instanceof User) {
						System.out.println("BEEP!");
						User u = (User)incoming;
						GuiOperations.addUserToList(u);
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
			FrameHandle.getPlayerListModel().clear();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	
	public ObjectInputStream getInStream() {
		return inStream;
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public Socket getSocket() {
		return socket;
	}
}