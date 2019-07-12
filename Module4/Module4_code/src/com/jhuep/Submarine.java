package com.jhuep;

public class Submarine extends Ship {
	
	private int numberTorpedos;

	public Submarine(int numTorpedos, int length, int speed, String name, String type) {
		super(length, speed, name, type);
		setNumberTorpedos(numTorpedos);
	}
	
	@Override
	public String toString() {
		return super.toString() + " numberOfTorpedos=" + numberTorpedos;
	}

	public int getNumberTorpedos() {
		return numberTorpedos;
	}
	
	public void setNumberTorpedos(int num) {
		if(num >= 0) {
			numberTorpedos = num;
		}
	}
	
	public void setNumberTorpedos(String numStr) {
		try {
			setNumberTorpedos(Integer.parseInt(numStr));
		}
		catch (Exception ex) {
			numberTorpedos = 2;
		}
	}
	
}
