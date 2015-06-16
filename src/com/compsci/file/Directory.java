package com.compsci.file;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;

import com.compsci.util.SloverseLogger;

public class Directory {

	private String filePath = null;
	
	public Directory(String path) {
		filePath = path;
		if (path == null || path.isEmpty()) {
			SloverseLogger.logErrorMessage(Level.WARNING, "A new directory was instantiated with a null path! Something's not right!");
		}
	}
	
	public File toFile() {
		return new File(filePath);
	}
	
	public Directory up() {
		File f = this.toFile();
		return new Directory(f.getParent());
	}
	
	public Directory down(String folderName) {
		return new Directory(this.filePath + File.separator + folderName);
	}
	
	/**
	 * NOTE: This ONLY retrieves FILE names, not directories.
	 * @return
	 */
	public ArrayList<String> getFileNames() {
		File[] allFiles = this.toFile().listFiles();
		ArrayList<String> onlyFiles = new ArrayList<String>();
		for (File f : allFiles) {
			if (f.isFile()) {
				onlyFiles.add(f.getName());
			}
		}
		return onlyFiles;
	}
	
	public ArrayList<Directory> getSubDirectories() {
		File[] allFiles = this.toFile().listFiles();
		ArrayList<Directory> directories = new ArrayList<Directory>();
		for (File f : allFiles) {
			if (f.isDirectory()) {
				directories.add(new Directory(f.getAbsolutePath()));
			}
		}
		return directories;
	}
	
	public File getFileFromDir(String fileName) {
		File f = new File(this.getFilePath() + File.separator + fileName);
		return f;
	}
	
	public boolean contains(String fileOrDirName) {
		File f = new File(this.getFilePath() + File.separator + fileOrDirName);
		return f.exists();
	}
	
	public boolean contains(File f) {
		return this.contains(f.getName());
	}
	
	public boolean contains(Directory d) {
		return this.contains(d.toFile());
	}
	
	public boolean exists() {
		return this.toFile().exists();
	}
	
	public boolean makeDirectories() {
		boolean mkdirs = false;
		try {
			mkdirs = this.toFile().mkdirs();
		}
		catch (SecurityException se) {
			SloverseLogger.logErrorMessage(Level.SEVERE, "Denied access to directory: " + this.getFilePath());
			se.printStackTrace();
		}
		return mkdirs;
	}
	
	public String getFilePath() {
		return filePath;
	}
}
