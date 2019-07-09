package ru.common.utils.date;

import java.util.Calendar;
import java.util.Date;

/**
 * Utility of date. 
 * 
 */
public class DateUtil {

	/**
	 * Add days for selected date.
	 * 
	 * @param date selected date.
	 * @param days add days. Sign of parameter can be minus too.     
	 * @return new date.
	 */
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //add number would decrement the days
        return cal.getTime();
    }
}

