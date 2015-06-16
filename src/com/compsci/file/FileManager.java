package com.compsci.file;

import java.io.File;

import com.compsci.util.EnumOS;
import com.compsci.util.UserUtilities;

public final class FileManager {

	private static final String s = File.separator;
	
	public static void initalizeStoragePath() {
		String operatingSystem = System.getProperty("os.name").toLowerCase();
		if (operatingSystem.contains("win")) {
			//Windows
			String appData = System.getenv("APPDATA");
			FileStructure.appDir = new Directory(appData);
			UserUtilities.setCurrentOS(EnumOS.WINDOWS);
		}
		else if (operatingSystem.contains("os x")) {
			//Mac
			String appSupport = System.getProperty("user.home") + s + "Library" + s + "Application Support";
			FileStructure.appDir = new Directory(appSupport);
			UserUtilities.setCurrentOS(EnumOS.OSX);
		}
		else {
			//Linux, etc.
			String home = System.getProperty("user.home");
			FileStructure.appDir = new Directory(home);
			if (operatingSystem.contains("lin")) {
				UserUtilities.setCurrentOS(EnumOS.LINUX);
			}
			else {
				UserUtilities.setCurrentOS(EnumOS.OTHER);
			}
		}
		initDirectories();		
	}
	
	private static void initDirectories() {
		(FileStructure.sloverseDir = FileStructure.appDir.down("Sloverse Chat")).makeDirectories();
		(FileStructure.loginDir = FileStructure.sloverseDir.down("login")).makeDirectories();
	}
	
	public static File retrieveFile(Directory d, String fileName) {
		return d.getFileFromDir(fileName);
	}
	
	public static File retrieveFile(String parentPath, String fileName) {
		return (new Directory(parentPath)).getFileFromDir(fileName);
	}
}
