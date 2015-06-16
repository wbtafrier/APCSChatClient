package com.compsci.file;

import java.io.File;

public class DataFile extends File {

	private static final long serialVersionUID = -1964495058536968172L;
	
	public DataFile(File parent, String child) {
		super(parent, child);
	}
	
	public void setValue(String field, String value) {
		
	}
	
	public String getValue(String field) {
		return null;
	}
	
	public String[] listFields() {
		return null;
	}

}
