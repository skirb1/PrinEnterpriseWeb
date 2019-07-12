package com.jhuep;

public abstract class Aircraft implements Contact {
	
	int altitude;
	int length;
	int speed;
	String name;
	String type;

	public Aircraft(int altitude, int length, int speed, String name, String type) {
		setAltitude(altitude);
		setLength(length);
		setSpeed(speed);
		setName(name);
		setType(type);
	}
	
	@Override
	public String toString() {
		return name + ": type=" + type + " length=" + length + " speed=" + speed + " altitude=" + altitude;
	}
	
	public int getAltitude() {
		return altitude;
	}
	
	public void setAltitude(int alt) {
		if(alt >= 0) {
			altitude = alt;
		}
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
