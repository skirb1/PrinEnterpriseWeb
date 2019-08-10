package com.kirby;

import java.sql.Date;

public class RecordBean {
	
	private int reservationId;
	
	private String firstName;
	
	private String lastName;
	
	private Date startDate;
	
	private int numberOfDays;
	
	private String guideFirst;
	
	private String guideLast;
	
	private String location;

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public String getGuideFirst() {
		return guideFirst;
	}

	public void setGuideFirst(String guideFirst) {
		this.guideFirst = guideFirst;
	}

	public String getGuideLast() {
		return guideLast;
	}

	public void setGuideLast(String guideLast) {
		this.guideLast = guideLast;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
