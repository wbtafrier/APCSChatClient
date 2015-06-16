package com.compsci.util;

public enum EnumOS {

	WINDOWS(0), OSX(1), LINUX(2), OTHER(3);
	
	private final int OS;
	
	private EnumOS(int os) {
		OS = os;
	}
	
	public int getOS() {
		return OS;
	}
}
