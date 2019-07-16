
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


/** Rates is an object that provides a cost for a booked tour.  
 * There is a base rate and a premium rate that are used to calculate 
 * the cost of the tour.  Weekdays use the base rate, weekends use the
 * premium rate.
 *
 * The premium rate is automatically generated as 1.5x the base rate
 *
 * @author evansrb1
 */
public class Rates {
    public static enum HIKE {
    	GARDINER("Gardiner Lake"), 
    	HELLROARING("Hellroaring Plateau"), 
    	BEATEN("Beaten Path");
    	
    	String name;
    	
    	private HIKE(String name) {
    		this.name = name;
    	}
    	
    	@Override
    	public String toString() {
    		return this.name;
    	}
    	
    
    };
    private HIKE hike;
    // flag to indicate total rate and day types need to be recalculated
    private boolean synched=false;
    // weekday rate
    private int baseRate = -1;
    private int premiumRate = -1;
    // cached calculated rate value
    private int rate = 0;
    // start of trip
    private BookingDay beginDate = null;
    // end of trip
    private BookingDay endDate = null;    
    // Format the date to something readable
    private final static DateFormat FORMAT = DateFormat.getDateInstance();
    // cached number of weekend days
    int premiumDays = 0;
    // cached number of weekday days
    int normalDays = 0;

    // we open June 1st (Jan = 0)
    private int seasonStartMonth = 5;
    private int seasonStartDay = 1;    

    // we close Oct 1st. (Jan = 0)
    private int seasonEndMonth = 9;
    private int seasonEndDay = 1;    
    
    private String details = "none";
    private int[] validDurations = null;
    private int selectedDuration = -1;
    
    private int participantCount = 1;

    public Rates(HIKE hike ) {
        this.hike = hike;
        switch (hike) {
            case GARDINER:
                baseRate = 4000;
                validDurations = new int[2];
                validDurations[0] = 3;
                validDurations[1] = 5;
                break;
            case HELLROARING:
                baseRate = 3500;
                validDurations = new int[3];
                validDurations[0] = 2;
                validDurations[1] = 3;
                validDurations[2] = 4;
                break;
            case BEATEN:
                baseRate = 4500;
                validDurations = new int[2];
                validDurations[0] = 5;
                validDurations[1] = 7;
                break;
        }
        premiumRate = baseRate + (baseRate / 2);
    }
    
    public HIKE getHike() {
    	return hike;
    }
    
    public void setParticipantCount(int count) {
    	participantCount = count;
    }
    
    public int getParticipantCount() {
    	return participantCount;
    }

    /** Get the total cost for the trip.  Returns -0.01 is something is amiss.
     * 
     * @return the cost of the trip
     */
    public double getCost() {
        if (synched) {
            return (rate / 100.0) * participantCount;
        } else {
            return (calculateCost() / 100.0) * participantCount;            
        }
    }


    /** Get the base rate for a weekday
     @return the weekend rate
     */
    public double getBaseRate() {
       return  baseRate / 100.0;
    }

    /** Get the base rate for a weekend
     @return the weekend rate
     */
    public double getPremiumRate() {
       return  premiumRate / 100.0;
    }

    public Calendar getSeasonStart() {
    	return new GregorianCalendar(2007, seasonStartMonth, seasonStartDay);
    }
    
    public Calendar getSeasonEnd() {
    	return new GregorianCalendar(2020, seasonEndMonth, seasonEndDay);
    }


    /** Determine if the entered dates are valid
     * 
     * @return true if both days exist and the begin date is before the end date
     */
     public boolean isValidDates() {
        if (beginDate == null) {
            details = "Start date is not defined";
            return false;
        }
        else if (endDate == null) {
        	calculateEndDate();
        }
        
        if(endDate != null) {
	        if (beginDate.getYear() != endDate.getYear()) {
	                details = "The begin and end date must be within the same year";
	                return false;
	        } else if (beginDate.equals(endDate)) {
	                details = "The begin and end date must not be the same date";
	                return false;
	        } else if (beginDate.isValidDate() && endDate.isValidDate()) {
	            if (!beginDate.after(endDate)) {
	                if ((!beginDate.before(seasonStartMonth, seasonStartDay)) &&
	                        (!endDate.after(seasonEndMonth, seasonEndDay))) {
	                    details = "Valid dates";
	                    return true;
	                } else {
	                    details = "Begin or end date is out of season";
	                    return false;
	                }
	            } else {
	                details = "End date is before begin date";
	                return false;
	            }
	        } else {
	            details = "One of the dates is not a valid day";
	            return false;
	        }
        }
        else {
        	details = "Could not calculate end date";
        	return false;
        }
        
    }

    /** Status of validation of data.  This is filled in once isValidDates() is called
     * 
     * @return A String indicating the status of the input data validity
     */
    public String getDetails() {
        return details;
    }
    
    // calculate the cost, returns -1 if dates are bad
    // bug fix courtesy of Zachary Schmook! 8/7/2010
    private int calculateCost() {
        if (! isValidDates() ) {
            return -1;
        }
        premiumDays = 0;
        normalDays=0;

        // Okay, if we got to here, the dates are somewhat sane...
        GregorianCalendar day = getBeginDate();
        GregorianCalendar endDay = getEndDate();
        while (day.before(endDay)) {
            int dayOfWeek = day.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SATURDAY ||
                    dayOfWeek == Calendar.SUNDAY ) {
                premiumDays++;
            } else {
                normalDays++;
            }
            day.add(Calendar.DAY_OF_WEEK, 1);
        }
        if (endDay.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                endDay.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            premiumDays++;
        } else {
            normalDays++;
        }
        rate = normalDays * baseRate + premiumDays * premiumRate;
        synched = true;
        return rate;
    }

    /** Get the number of weekdays in the reservation
     * 
     * @return number of weekdays in the reservation
     */
    public int getNormalDays() {
        if (!synched) {
            calculateCost();
        }
        return normalDays;
    }

    /** Get the number of weekend days in the reservation
     * 
     * @return number of weekend days in the reservation
     */
    public int getPremiumDays() {
        if (!synched) {
            calculateCost();
        }
        return premiumDays;
    }

    /** Get the beginning date of the reservation
     *
     * @return a GregorianCalendar object of the beginning date
     */
    public GregorianCalendar getBeginDate() {
        if (beginDate == null) {
            return null;
        } else {
            return beginDate.getDate();
        }
    }

    /** Get the end date of the reservation
     *
     * @return a GregorianCalendar object of the end date
     */
    public GregorianCalendar getEndDate() {
    	calculateEndDate();
        if (endDate == null) {
            return null;
        } else {
            return endDate.getDate();
        }
    }

    /** Get the beginning date of the reservation
     *
     * @return a BookingDay object of the beginning date
     */
    public BookingDay getBeginBookingDay() {
       return beginDate;
    }

    /** Get the end date of the reservation
     *
     * @return a GregorianCalendar object of the end date
     */
    public BookingDay getEndBookingDay() {
    	calculateEndDate();
       return endDate;
    }

    /** Set the beginning date of the reservation
     *
     * @param beginDate the beginning date
     */
    public void setBeginDate(BookingDay beginDate) {
        this.beginDate = beginDate;
        synched = false;
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
        synched = false;
        return valid;
    }

    public int getSelectedDuration(){
        return selectedDuration;
    }
    
    public boolean hasDuration() {
    	if(selectedDuration != -1) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public void calculateEndDate() {
    	if(hasDuration() && getBeginDate() != null) {
            GregorianCalendar day = getBeginDate();
            day.add(Calendar.DAY_OF_YEAR, getSelectedDuration());
            endDate = new BookingDay(day.get(Calendar.YEAR),
                    day.get(Calendar.MONTH),
                    day.get(Calendar.DAY_OF_MONTH));
    	}
    }

    /** Quick test of the class */
    public static void main(String[] argv) {
        BookingDay startDay = new BookingDay(2008,7,1);
        BookingDay endDay = new BookingDay(2008,7, 7);
        System.out.println("start Day of " + startDay + " " + (startDay.isValidDate()?"is valid":"is not valid"));
        System.out.println("end Day " + endDay + " " + (endDay.isValidDate()?"is valid":"is not valid"));        

        Rates rates = new Rates(HIKE.BEATEN);

        rates.setBeginDate(startDay);
        boolean success = rates.setDuration(7);
        System.out.println("duration was " + (success ? "good" : "bad"));
        System.out.println("valid Dates = " + rates.isValidDates());
        if (rates.isValidDates()) {
            System.out.println("Cost of trip = " + rates.getCost());
            System.out.println("Weekdays: " + rates.getNormalDays());
            System.out.println("Weekends: " + rates.getPremiumDays());        
        } else {
            System.out.println("Sorry, but " + rates.getDetails());
        }
    }
}

