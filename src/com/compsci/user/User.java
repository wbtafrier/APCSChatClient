package com.compsci.user;

import java.io.Serializable;

public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 7945909636295301742L;
	
	private String name;
	private EnumAuthorityLevel authority;
	private boolean isMuted;
	
	private boolean isModerator;
	private boolean isAdministrator;
	
	public User(EnumAuthorityLevel level, String userName) {
		authority = level;
		name = userName;
	}
	
	public EnumAuthorityLevel getAuthority() {
		return authority;
	}
	
	public void setAuthority(EnumAuthorityLevel level) {
		authority = level;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isMuted() {
		return isMuted;
	}
	
	public boolean isModerator() {
		return isModerator;
	}
	
	public void setModerator() {
		if (isAdministrator)  isAdministrator = false;
		isModerator = true;
		setAuthority(EnumAuthorityLevel.MODERATOR);
	}
	
	public void removeModerator() {
		isModerator = false;
		setAuthority(EnumAuthorityLevel.PLAYER);
	}
	
	public boolean isAdministrator() {
		return isAdministrator;
	}
	
	public void setAdministator() {
		if (isModerator) isModerator = false;
		isAdministrator = true;
		setAuthority(EnumAuthorityLevel.ADMINISTRATOR);
	}
	
	public void removeAdministrator() {
		
		isAdministrator = false;
		setAuthority(EnumAuthorityLevel.PLAYER);
	}
	
	public static void setupUser() {
		
		//Get username from the user.
		//Call ConnectionManager.sendData(Object o) etc to register username.
		
	}

	public void setMuted(boolean muted) {
		this.isMuted = muted;
	}
}
