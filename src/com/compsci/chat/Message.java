package com.compsci.chat;

import java.io.Serializable;

import com.compsci.user.User;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 3900037731396757918L;
	
	private String message;
	private EnumMessageType type;
	private User sender, receiver;

	public Message(User s, String input) {
		initMessage(s, input);
		type = EnumMessageType.PUBLIC;
		receiver = null;
	}

	public Message(User s, User r, String input) {
		initMessage(s, r, input);
		type = EnumMessageType.PRIVATE;
	}

	private void initMessage(User s, String input) {
		sender = s;
		message = input;
	}

	private void initMessage(User s, User r, String input) {
		receiver = r;
		initMessage(s, input);
	}

	public String getFormattedMessage() {
		String formattedMessage;
		System.out.println(type);

		if (type.equals(EnumMessageType.PUBLIC)) {
			formattedMessage = "[" + sender.getName() + "] " + message;
		}
		else {
			formattedMessage = "[" + sender.getName() + " --> " + receiver.getName() + "] " + message;
		}
		return formattedMessage;
	}

	public User getSender() {
		return sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public EnumMessageType getType() {
		return type;
	}

	/**
	 * Do not get the string for outputting! Only use when comparing the actual message. Use the toString method when printing out messages.
	 * @return : the unformatted message.
	 */
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return getFormattedMessage();
	}
}
