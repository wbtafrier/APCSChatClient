package com.compsci.core;

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
import com.compsci.gui.ClientFrame;
import com.compsci.user.ServerUser;
import com.compsci.user.User;
import com.compsci.util.SloverseLogger;

public class SloverseClient {

	public static final User SERVER = new ServerUser();
	
	private static boolean isConnected = false;
	
	private static String hostAddress = "100.1.255.24";
	private static final int TIME_OUT = 10000;
	private static final int PORT_NUMBER = 609;
	
	private static ObjectOutputStream outStream;
	private static ObjectInputStream inStream;
	
	public static void main(String[] args) {
		
		new ClientFrame("Sloverse Client");
		
		try (Socket clientSocket = new Socket();) {
			connect(clientSocket);
			outStream = new ObjectOutputStream(clientSocket.getOutputStream());
			inStream = new ObjectInputStream(clientSocket.getInputStream());
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
			SloverseLogger.logErrorMessage(Level.WARNING, "Connection Lost. Internal exception: " + e.getClass());
			ClientConsole.printMessage(new Message(SERVER, "Connection Lost."));
			setConnected(false);
			//Show message to bring the user back to home screen.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void shutdownClient() {
		setConnected(false);
	}
	
	public static boolean isConnected() {
		return isConnected;
	}
	
	private static void connect(Socket s) {
		try {
			ClientConsole.printMessage(new Message(SERVER, "Attempting to connect to server..."));
			s.connect(new InetSocketAddress(hostAddress, PORT_NUMBER), TIME_OUT);
		} catch (IOException e) {
			ClientConsole.printMessage(new Message(SERVER, "Timed out when trying to connect to the ip : " + hostAddress + " at port " + PORT_NUMBER));
			isConnected = false;
			return;
		}
		isConnected = true;
		ClientConsole.printMessage(new Message(SERVER, "Connected successfully!"));
	}
	
	private static void setConnected(boolean b) {
		isConnected = b;
	}
	
	public static ObjectOutputStream getOutStream() {
		return outStream;
	}
}
