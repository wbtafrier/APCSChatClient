package com.compsci.chat;

import com.compsci.gui.FrameHandle;

public class ClientConsole {

	public static void printMessage(Message m) {
		
		String oldText = FrameHandle.getOutputPane().getText();
		if (oldText.isEmpty()) FrameHandle.getOutputPane().setText(oldText + m.getFormattedMessage());
		else FrameHandle.getOutputPane().setText(oldText + "\n" + m.getFormattedMessage());
	}
}
