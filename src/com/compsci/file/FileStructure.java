package com.compsci.file;

public class FileStructure {

	//FS parent
	protected static Directory appDir;
	
	//Sloverse main folder
	protected static Directory sloverseDir;
	
	//Sloverse sub folders
	protected static Directory loginDir;
	
	public static Directory getAppDirectory() {
		return appDir;
	}
	
	public static Directory getSloverseDirectory() {
		return sloverseDir;
	}
	
	public static Directory getLoginDirectory() {
		return loginDir;
	}
}
