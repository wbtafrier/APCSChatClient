package com.compsci.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

import com.compsci.util.SloverseLogger;

public class WritingHelper {

	//Success or failure
	public static boolean writeFile(Directory d, String fileName, String contents) {
		File f = FileManager.retrieveFile(d, fileName);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(contents);
			writer.close();
		}
		catch (IOException ioe) {
			SloverseLogger.logErrorMessage(Level.SEVERE, "Writing to file failed!");
			SloverseLogger.logErrorMessage(Level.SEVERE, "Failed file: " + d.getFilePath() + File.separator + fileName);
			ioe.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean appendToFile(Directory d, String fileName, String contents) {
		File f = FileManager.retrieveFile(d, fileName);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.append(contents);
			writer.close();
		}
		catch (IOException ioe) {
			SloverseLogger.logErrorMessage(Level.SEVERE, "Appending to file failed!");
			SloverseLogger.logErrorMessage(Level.SEVERE, "Failed file: " + d.getFilePath() + File.separator + fileName);
			ioe.printStackTrace();
			return false;
		}
		return true;
	}
}
