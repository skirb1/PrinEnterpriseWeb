package com.rbevans.bookingrate;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/** Booking is an object that provides a cost for a booked tour.
 * There is a base rate and a premium rate that are used to calculate 
 * the cost of the tour.  Weekdays use the base rate, weekends use the
 * premium rate.
 *
 * The premium rate is automatically generated as 1.5x the base rate
 *
 * @author evansrb1
 */
public class Booking {
    public static enum HIKE {
    	GARDINER("Gardiner", 0),
    	HELLROARING("Hellroaring", 1),
    	BEATEN("Beaten", 2);
    	
    	String name;
    	int id;
    	
    	private HIKE(String name, int id) {
    		this.id = id;
    	    this.name = name;
    	}

    	public int getId(){
    	    return this.id;
        }
    	
    	@Override
    	public String toString() {
    		return this.name;
    	}

    };

    private HIKE hike;

    // start of trip
    private BookingDay beginDate = null;

    private int[] validDurations = null;
    private int selectedDuration = -1;

    public Booking(){ }

    public void setHike(HIKE hike ) {
        this.hike = hike;
        switch (hike) {
            case GARDINER:
                validDurations = new int[2];
                validDurations[0] = 3;
                validDurations[1] = 5;
                break;
            case HELLROARING:
                validDurations = new int[3];
                validDurations[0] = 2;
                validDurations[1] = 3;
                validDurations[2] = 4;
                break;
            case BEATEN:
                validDurations = new int[2];
                validDurations[0] = 5;
                validDurations[1] = 7;
                break;
        }
    }

    public HIKE getHike(){
        return hike;
    }

    public boolean hasAllInfo(){
        return hasDuration() && getBeginBookingDay() != null && getHike() != null;
    }

    public String getRequestString(){
        if(hasAllInfo()){
            return hike.getId() + ":" + beginDate.toString() + ":" + selectedDuration;
        }
        else {
            return null;
        }
    }

    /** Get the beginning date of the reservation
     *
     * @return a BookingDay object of the beginning date
     */
    public BookingDay getBeginBookingDay() {
       return beginDate;
    }


    /** Set the beginning date of the reservation
     *
     * @param beginDate the beginning date
     */
    public void setBeginDate(BookingDay beginDate) {
        this.beginDate = beginDate;
    }

    // Get the valid durations for this hike.
    public int[] getDurations() {
        return validDurations;
    }
    
    /** Set the duration of the reservation.  One day hikes means no overnight,
     * a two day hike is two days, one night.
     *
     * @param days the duration of the hike
     */
    public boolean setDuration(int days) {
        boolean valid = false;
        for (int d : validDurations) {
            if (days == d) {
            	selectedDuration = days;
                valid = true;
                break;
            }
        }
        if (!valid) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean hasDuration() {
    	if(selectedDuration != -1) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

}

