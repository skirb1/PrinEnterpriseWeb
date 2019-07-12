package com.jhuep;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		
		// Destroyer constructor takes: numberMissiles, length, speed, name, type
		Destroyer destroyer1 = new Destroyer(-5, 50, 35, "Destroyer1", "Destroyer");
		Destroyer destroyer2 = new Destroyer(15, 30, 55, "Destroyer2", "Destroyer");
		destroyer2.setSpeed("50");
		destroyer2.setNumberMissiles("12");
		
		// Submarine constructor takes: numberTorpedos, length, speed, name, type
		Submarine sub1 = new Submarine(0, 30, -25, "Sub1", "Submarine");
		Submarine sub2 = new Submarine(22, 60, 30, "Sub2", "Submarine");
		sub2.setSpeed("33");
		sub2.setNumberTorpedos("Foo");
		
		// P3 constructor takes: numberEngines, altitude, length, speed, name, type
		P3 p31 = new P3(2, 200, 15, 150, "P3 1", "P3");
		P3 p32 = new P3(4, 250, 12, 120, "P3 2", "P3");
		p32.setSpeed("110");
		
		ArrayList<Destroyer> destroyers = new ArrayList<>();
		destroyers.add(destroyer1);
		destroyers.add(destroyer2);
		
		ArrayList<Submarine> submarines = new ArrayList<>();
		submarines.add(sub1);
		submarines.add(sub2);
		
		ArrayList<Ship> ships = new ArrayList<>();
		ships.addAll(destroyers);
		ships.addAll(submarines);
		
		ArrayList<Contact> contacts = new ArrayList<>();
		contacts.addAll(ships);
		contacts.add(p31);
		contacts.add(p32);
		
		
		for(Contact contact : contacts){
			System.out.println(contact);
		}

	}

}
