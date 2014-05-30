package org.codingpedia.demo.rest.helpers;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateISO8601Adapter extends XmlAdapter<String, Date> {
	
	private static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSZZ";
	private SimpleDateFormat dateFormat;
	
	public DateISO8601Adapter() {
		super();

		dateFormat = new SimpleDateFormat(ISO_8601_DATE_FORMAT);
	}	

	@Override
	public Date unmarshal(String v) throws Exception {
		return dateFormat.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return dateFormat.format(v);
	}

}
