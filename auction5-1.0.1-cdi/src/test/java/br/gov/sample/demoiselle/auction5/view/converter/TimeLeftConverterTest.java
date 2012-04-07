/*
 * Demoiselle Framework
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 *
 * Demoiselle Framework is an open source Java EE library designed to accelerate
 * the development of transactional database Web applications.
 *
 * Demoiselle Framework is released under the terms of the LGPL license 3
 * http://www.gnu.org/licenses/lgpl.html  LGPL License 3
 *
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License 3 as published by
 * the Free Software Foundation.
 *
 * Demoiselle Framework is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Demoiselle Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.gov.sample.demoiselle.auction5.view.converter;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

import br.gov.sample.demoiselle.auction5.view.converter.TimeLeftConverter;

/**
 * Unitary tests for TimeLeftConverter JSF converter.
 * 
 * @author CETEC/CTJEE
 */
public class TimeLeftConverterTest {

	private static Logger log = Logger.getLogger(TimeLeftConverterTest.class);
	private static TimeLeftConverter converter = new TimeLeftConverter();

	@Test
	public void testGetAsString() {

		Date testDate = null;
		String result = null;

		debug(Calendar.getInstance().getTime(), "Current Timestamp");

		testDate = null;
		result = getTimeLeft(null);
		assertEquals("", result);
		debug(testDate, result);

		testDate = futureTimestamp(30);
		result = getTimeLeft(testDate);
		//assertEquals("30s", result);
		debug(testDate, result);

		testDate = futureTimestamp(15, 0);
		result = getTimeLeft(testDate);
		//assertEquals("15m", result);
		debug(testDate, result);

		testDate = futureTimestamp(2, 10, 0);
		result = getTimeLeft(testDate);
		//assertEquals("2h 10m", result);
		debug(testDate, result);

		testDate = futureTimestamp(15, 0, 25);
		result = getTimeLeft(testDate);
		//assertEquals("15h 25s", result);
		debug(testDate, result);

		testDate = futureTimestamp(3, 20, 45, 0);
		result = getTimeLeft(testDate);
		//assertEquals("3d 20h 45m", result);
		debug(testDate, result);
		
		testDate = futureTimestamp(15, 23, 7, 55);
		result = getTimeLeft(testDate);
		//assertEquals("15d 23h 7m", result);
		debug(testDate, result);
	}

	/**
	 * Debugging support method.
	 * 
	 * @param date
	 * @param result
	 */
	private void debug(Date date, String result) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = (date != null ? df.format(date) : "null");
		log.debug(str + " => '" + result + "'");
	}

	/**
	 * @param date
	 * @return
	 */
	private String getTimeLeft(Date date) {
		return converter.getAsString(null, null, date);
	}

	/**
	 * @param seconds
	 * @return
	 */
	private Date futureTimestamp(int seconds) {
		return futureTimestamp(0, 0, 0, seconds);
	}

	/**
	 * @param minutes
	 * @param seconds
	 * @return
	 */
	private Date futureTimestamp(int minutes, int seconds) {
		return futureTimestamp(0, 0, minutes, seconds);
	}

	/**
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return
	 */
	private Date futureTimestamp(int hours, int minutes, int seconds) {
		return futureTimestamp(0, hours, minutes, seconds);
	}

	/**
	 * Creates a new date/time based on the specified parameters.
	 * 
	 * @param days
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return a date/time instance
	 */
	private Date futureTimestamp(int days, int hours, int minutes, int seconds) {
		
		Date now = Calendar.getInstance().getTime();
		long offset = (long) (seconds * 1E3 + minutes * 6E4 + hours * 36E5 + days * 864E5);
		Date newDate = new Date(now.getTime() + offset);
		
		return newDate;
	}

}
