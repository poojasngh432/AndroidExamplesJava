package com.example.tutorialsproject.util;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtilsClass {
    private static final String TAG = DateUtilsClass.class.getSimpleName();

    public static String getCurrentDateTime(String format){
        return getTimeByFormat(format);
    }

    public static String getCurrentTime(String format){
        return getTimeByFormat(format);
    }

    public static String getCurrentDateTime() {
        return  getTimeByFormat("dd-MMM-yyyy hh:mm:ss");
    }

    public static  String getCurrentTime(){
        return  getTimeByFormat("hh:mm");
    }

    private static String getTimeByFormat(String format){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getID()));
        String formattedDate = df.format(c.getTime());
        return formattedDate ;
    }

    /**
     * Transforms Calendar to ISO 8601 string.
     **/
    public static String fromCalendar(final Calendar calendar) {
        Date date = calendar.getTime();
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }

    /**
     * Gets current date and time formatted as ISO 8601 string.
     **/
    public static String now() {
        return fromCalendar(GregorianCalendar.getInstance());
    }

    /**
     * Transforms ISO 8601 string to Calendar.
     **/
    public static Calendar toCalendar(final String iso8601string) throws ParseException {
        Calendar calendar = GregorianCalendar.getInstance();
        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23);
        } catch (IndexOutOfBoundsException e) {
            // throw new org.apache.http.ParseException();
            e.printStackTrace();
        }

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar;
    }

    /***
     * Converts ISO date string to UTC timezone equivalent.
     *
     * @param dateAndTime ISO formatted time string.
     */
    public static String getUtcTime(String dateAndTime) {
        Date d = parseDate(dateAndTime);

        String format = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());

        // Convert Local Time to UTC
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(d);
    }

    /****
     * Parses date string and return a {@link java.util.Date} object
     *
     * @return The ISO formatted date object
     */

    public static Date parseDate(String date) {

        if (date == null) {
            return null;
        }

        StringBuffer sbDate = new StringBuffer();
        sbDate.append(date);
        String newDate = null;
        Date dateDT = null;

        try {
            newDate = sbDate.substring(0, 19).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String rDate = newDate.replace("T", " ");
        String nDate = rDate.replaceAll("-", "/");

        try {
            dateDT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).parse(nDate);
            // Log.v( TAG, "#parseDate dateDT: " + dateDT );
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateDT;
    }

    /**
     * Returns abbreviated (3 letters) day of the week.
     *
     * @param date ISO format date
     * @return The name of the day of the week
     */
    public static String getDayOfWeekAbbreviated(String date) {
        Date dateDT = parseDate(date);

        if (dateDT == null) {
            return null;
        }

        // Get current date
        Calendar c = Calendar.getInstance();
        // it is very important to
        // set the date of
        // the calendar.
        c.setTime(dateDT);
        int day = c.get(Calendar.DAY_OF_WEEK);
        String dayStr = null;

        switch (day) {

            case Calendar.SUNDAY:
                dayStr = "Sun";
                break;

            case Calendar.MONDAY:
                dayStr = "Mon";
                break;

            case Calendar.TUESDAY:
                dayStr = "Tue";
                break;

            case Calendar.WEDNESDAY:
                dayStr = "Wed";
                break;

            case Calendar.THURSDAY:
                dayStr = "Thu";
                break;

            case Calendar.FRIDAY:
                dayStr = "Fri";
                break;

            case Calendar.SATURDAY:
                dayStr = "Sat";
                break;
        }

        return dayStr;
    }

    /**
     * Gets the name of the day of the week.
     **/
    public static String getDayOfWeek(String date) {
        Date dateDT = DateUtilsClass.parseDate(date);

        if (dateDT == null) {
            return null;
        }

        // Get current date
        Calendar c = Calendar.getInstance();
        // it is very important to
        // set the date of
        // the calendar.
        c.setTime(dateDT);
        int day = c.get(Calendar.DAY_OF_WEEK);

        String dayStr = null;

        switch (day) {

            case Calendar.SUNDAY:
                dayStr = "Sunday";
                break;

            case Calendar.MONDAY:
                dayStr = "Monday";
                break;

            case Calendar.TUESDAY:
                dayStr = "Tuesday";
                break;

            case Calendar.WEDNESDAY:
                dayStr = "Wednesday";
                break;

            case Calendar.THURSDAY:
                dayStr = "Thursday";
                break;

            case Calendar.FRIDAY:
                dayStr = "Friday";
                break;

            case Calendar.SATURDAY:
                dayStr = "Saturday";
                break;
        }

        return dayStr;
    }

    /***
     * Gets the name of the month from the given date.
     *
     * @param date ISO format date
     * @return Returns the name of the month
     */
    public static String getMonth(String date) {
        Date dateDT = parseDate(date);

        if (dateDT == null) {
            return null;
        }

        // Get current date
        Calendar c = Calendar.getInstance();
        // it is very important to
        // set the date of
        // the calendar.
        c.setTime(dateDT);
        int day = c.get(Calendar.MONTH);

        String dayStr = null;

        switch (day) {

            case Calendar.JANUARY:
                dayStr = "January";
                break;

            case Calendar.FEBRUARY:
                dayStr = "February";
                break;

            case Calendar.MARCH:
                dayStr = "March";
                break;

            case Calendar.APRIL:
                dayStr = "April";
                break;

            case Calendar.MAY:
                dayStr = "May";
                break;

            case Calendar.JUNE:
                dayStr = "June";
                break;

            case Calendar.JULY:
                dayStr = "July";
                break;

            case Calendar.AUGUST:
                dayStr = "August";
                break;

            case Calendar.SEPTEMBER:
                dayStr = "September";
                break;

            case Calendar.OCTOBER:
                dayStr = "October";
                break;

            case Calendar.NOVEMBER:
                dayStr = "November";
                break;

            case Calendar.DECEMBER:
                dayStr = "December";
                break;
        }

        return dayStr;
    }

    /**
     * Gets abbreviated name of the month from the given date.
     *
     * @param date ISO format date
     * @return Returns the name of the month
     */
    public static String getMonthAbbreviated(String date) {
        Date dateDT = parseDate(date);

        if (dateDT == null) {
            return null;
        }

        // Get current date
        Calendar c = Calendar.getInstance();
        // it is very important to
        // set the date of
        // the calendar.
        c.setTime(dateDT);
        int day = c.get(Calendar.MONTH);

        String dayStr = null;

        switch (day) {

            case Calendar.JANUARY:
                dayStr = "Jan";
                break;

            case Calendar.FEBRUARY:
                dayStr = "Feb";
                break;

            case Calendar.MARCH:
                dayStr = "Mar";
                break;

            case Calendar.APRIL:
                dayStr = "Apr";
                break;

            case Calendar.MAY:
                dayStr = "May";
                break;

            case Calendar.JUNE:
                dayStr = "Jun";
                break;

            case Calendar.JULY:
                dayStr = "Jul";
                break;

            case Calendar.AUGUST:
                dayStr = "Aug";
                break;

            case Calendar.SEPTEMBER:
                dayStr = "Sep";
                break;

            case Calendar.OCTOBER:
                dayStr = "Oct";
                break;

            case Calendar.NOVEMBER:
                dayStr = "Nov";
                break;

            case Calendar.DECEMBER:
                dayStr = "Dec";
                break;
        }

        return dayStr;
    }

    /**
     * Gets a string TimeStamp phrase like 5 mins ago
     * yesterday, 3 days ago.
     * {
     * 	String timeStamp = getTimeStamp(originalDate);
     * }
     * @param originalDate
     * @return convertedDate String
     *
     */
    public static String getTimeStamp(Date originalDate){

        String convertedDate = DateUtils.getRelativeTimeSpanString(originalDate.getTime(),
                new Date().getTime(),
                DateUtils.SECOND_IN_MILLIS).toString();

        return convertedDate;
    }


    /**
     * Gets a string TimeStamp phrase like 5 mins ago
     * yesterday, 3 days ago.
     * {
     * 	String timeStamp = getTimeStamp(originalDate);
     * }
     * @param originalDateTime time in miliseconds since epoch
     * @return convertedDate String
     *
     */
    public static String getTimeStamp(Long originalDateTime) {

        String convertedDate = DateUtils.getRelativeTimeSpanString(originalDateTime, new Date().getTime(),
                DateUtils.SECOND_IN_MILLIS).toString();

        return convertedDate;

    }

}
