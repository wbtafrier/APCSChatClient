package com.compsci.util;

import java.util.ArrayList;
import java.util.logging.Level;

import com.compsci.gui.FrameHandle;
import com.compsci.gui.GuiOperations;
import com.compsci.user.EnumAction;
import com.compsci.user.EnumAuthorityLevel;
import com.compsci.user.Player;
import com.compsci.user.User;
import com.compsci.user.UserAction;

public class UserUtilities {

	private static ArrayList<User> userList = new ArrayList<User>();
	
	public static ArrayList<User> getUserList() {
		return userList;
	}
	
	public static void handleUserAction(UserAction ua) {
		if (ua != null && ua.getUsername() != null && ua.getAction() != null) {
			String username = ua.getUsername();
			EnumAction action = ua.getAction();
			
			if (action.equals(EnumAction.DISCONNECT)) {
				GuiOperations.removeUserFromList(username);
				return;
			}
			
			User user = null;
			for (User u : getUserList()) {
				if (u.getName().equals(username)) {
					user = (User) u;
					break;
				}
			}
			
			if (action.equals(EnumAction.MUTE)) {
				user.setMuted(true);
			}
			else if (action.equals(EnumAction.UNMUTE)) {
				user.setMuted(false);
			}
			else if (action.equals(EnumAction.MOD)) {
				user.setModerator();
			}
			else if (action.equals(EnumAction.DEMOD)) {
				user.removeModerator();
			}
			else if (action.equals(EnumAction.ADMIN)) {
				user.setAdministator();
			}
			else if (action.equals(EnumAction.DEADMIN)) {
				user.removeAdministrator();
			}
			GuiOperations.refreshList();
		}
		else {
			SloverseLogger.logErrorMessage(Level.WARNING, "Error in handling UserAction: found null class or fields");
		}
	}
}
