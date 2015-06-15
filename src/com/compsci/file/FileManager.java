package com.compsci.file;

import java.io.File;

public final class FileManager {

	private static final String s = File.separator;
	private static Directory sloverseDirectory;
	
	public static void initalizeStoragePath() {
		String operatingSystem = System.getProperty("os.name").toLowerCase();
		if (operatingSystem.contains("win")) {
			//Windows
			String appData = System.getenv("APPDATA");
			sloverseDirectory = new Directory(appData);
		}
		else if (operatingSystem.contains("os x")) {
			//Mac
			String appSupport = System.getProperty("user.home") + s + "Library" + s + "Application Support";
		}
		else {
			//Linux, etc.
			String home = System.getProperty("user.home");
		}
	}
	
	public static File retrieveFile(Directory d, String fileName) {
		return null;
	}
	
}
