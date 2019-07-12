/*
 * A simple object to keep represent a Day.  It has getter and setters for day,
 * month and year so that it can be used as a bean.
 *
 * getDate() returns a GregorianCalendar object that can be used for date
 * calcualtions
 *
 * isValidDate() is used to see if the day/month/year is a valid date
 * getValidationStatus() returns a string message for problems
 *
 * Internally, months are from 0-11 (like the GregorianCalendar) but externally
 * they run 1-12
 */

package com.rbevans.bookingrate;

/**
 *
 * @author evansrb1
 */
public class BookingDay {

    private int year = 0;
    private int month = 0;
    private int dayOfMonth = 0;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookingDay other = (BookingDay) obj;
        if (this.year != other.year) {
            return false;
        }
        if (this.month != other.month) {
            return false;
        }
        if (this.dayOfMonth != other.dayOfMonth) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.year;
        hash = 53 * hash + this.month;
        hash = 53 * hash + this.dayOfMonth;
        return hash;
    }

    /** Constructor to use for Bean purposes.  Must set the three fields manually
     * 
     */
    public BookingDay() {
    }
    
    /** Constructor for testing purposes or use within the bookingrate package.
     @param year the year, from 2007 to 2020
     @param month, the month Jan = 1, Dec = 12
     @param dayOfMonth, the numerical day of the month
     */
    public BookingDay(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }


    @Override
    public String toString() {
        return year + ":" + month + ":" + dayOfMonth;
    }


    /** Get the year of this date
     *
     * @return the year
     */
    int getYear() {
        return year;
    }

    /** Set the year of this date.  This invalidates the BookingDay object,
     * and isValidDate() must be called again
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /** Get the month of this date
     *
     * @return the month (Jan = 1, Dec = 12)
     */
    public int getMonth() {
        return month + 1;

    }

    /** Set the month of this date
     *
     * @param month (Jan = 1, Dec = 12)
     */
    public void setMonth(int month) {
        this.month = month - 1;
    }

    /** Get the day of the month
     *
     * @return the day of the month
     */
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    /** Set the day of the month
     *
     * @param dayOfMonth
     */
    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

}