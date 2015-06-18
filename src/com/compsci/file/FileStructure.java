package com.compsci.file;

public class FileStructure {

	/*
	 * REMEMBER TO INIT DIRS IN FILEMANAGER WHEN ADDING NEW ONES
	 */
	
	//FS parent
	protected static Directory appDir;
	
	//Sloverse main folder
	protected static Directory sloverseDir;
	
	//Sloverse sub folders
	protected static Directory optionsDir;
	protected static Directory logDir;
	
	public static Directory getAppDirectory() {
		return appDir;
	}
	
	public static Directory getSloverseDirectory() {
		return sloverseDir;
	}
	
	public static Directory getOptionsDirectory() {
		return optionsDir;
	}
	
	public static Directory getLogDirectory() {
		return logDir;
	}
}
