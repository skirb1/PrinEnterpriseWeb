package com.jhuep;

public class Destroyer extends Ship {
	
	private int numberMissiles;

	public Destroyer(int numMissiles, int length, int speed, String name, String type) {
		super(length, speed, name, type);
		setNumberMissiles(numMissiles);
	}
	
	@Override
	public String toString() {
		return super.toString() + " numberOfMissiles=" + numberMissiles;
	}
	
	public int getNumberMissiles() {
		return numberMissiles;
	}
	
	public void setNumberMissiles(int num) {
		if(num >= 0) {
			numberMissiles = num;
		}
	}
	
	public void setNumberMissiles(String numStr) {
		try {
			setNumberMissiles(Integer.parseInt(numStr));
		}
		catch (Exception ex) {
			numberMissiles = 2;
		}
	}

}
