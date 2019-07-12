package com.jhuep;

public class P3 extends Aircraft {
	
	private int numberEngines;

	public P3(int engines, int altitude, int length, int speed, String name, String type) {
		super(altitude, length, speed, name, type);
		setNumberEngines(engines);
	}
	
	@Override
	public String toString() {
		return super.toString() + " numberOfEngines=" + numberEngines;
	}

	public int getNumberEngines() {
		return numberEngines;
	}
	
	public void setNumberEngines(int engines) {
		if(engines >= 0) {
			numberEngines = engines;
		}
	}
}
