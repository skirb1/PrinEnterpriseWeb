package com.jhuep;

public abstract class Ship implements Contact {
	
	int length;
	int speed;
	String name;
	String type;
	
	public Ship(int length, int speed, String name, String type) {
		setLength(length);
		setSpeed(speed);
		setName(name);
		setType(type);
	}
	
	@Override
	public String toString() {
		return name + ": type=" + type + " length=" + length + " speed=" + speed;
	}

	@Override
	public int getLength() {
		return length;
	}

	@Override
	public void setLength(int length) {
		if(length >= 0) {
			this.length = length;
		}
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(int speed) {
		if(speed >= 0) {
			this.speed = speed;
		}
	}

	@Override
	public void setSpeed(String speed) {
		try {
			setSpeed(Integer.parseInt(speed));
		}
		catch (Exception ex) {
			System.out.println("Error setting speed.");
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

}
