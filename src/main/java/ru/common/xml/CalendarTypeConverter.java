package ru.common.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Converter the calendar to JaxB customer format.
 * 
 */

public final class CalendarTypeConverter extends XmlAdapter<String, XMLGregorianCalendar>{
	
    public final static String DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss"; //2015-09-03T05:49:45
	
    /**
     * Calendar to custom format print to XML.
     * 
     * @param val
     * @return
     */
    public static String printCalendar(XMLGregorianCalendar val) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CalendarTypeConverter.DATE_FORMAT);
        return simpleDateFormat.format(val.toGregorianCalendar().getTime());
    }

    
    public static GregorianCalendar parseCalendar(String value) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date = simpleDateFormat.parse(value);
        GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
        return calendar;
    }
    
    /**
     * Date to custom format print to XML.
     * 
     * @param val
     * @return
     */
    public static String printDate(java.util.Date val) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(val);
    }


	@Override
	public String marshal(XMLGregorianCalendar calendar) throws Exception {
		return printCalendar(calendar);
	}


	@Override
	public XMLGregorianCalendar unmarshal(String value) throws Exception {
		DatatypeFactory dataFactory = DatatypeFactory.newInstance();
		GregorianCalendar calendar = parseCalendar(value);
		return dataFactory.newXMLGregorianCalendar(calendar);
	}
}