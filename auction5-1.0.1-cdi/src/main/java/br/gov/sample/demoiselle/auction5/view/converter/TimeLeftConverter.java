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

import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * This converter will be used in fields to display time left based on a date.
 * 
 * @author CETEC/CTJEE
 * @see javax.faces.convert.Converter
 */
public class TimeLeftConverter implements Converter {

	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String string) {
		try {
			return null;
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}

	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		try {
			
			if (object == null || !(object instanceof Date)) {
				return "";
			}
			
			Date date = (Date) object;
			
			return calculateTimeLeft(date);
			
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}

	/**
	 * Support method used to calculate time left based on a date.
	 * 
	 * @param date	the given date/time
	 * @return	the time left
	 */
	private String calculateTimeLeft(Date date) {
		
		long remainder = date.getTime() - Calendar.getInstance().getTimeInMillis();
		
		if (remainder <= 0)
			return "";
		
		final long ONE_SEC  = 1000;
		final long ONE_MIN  = 60 * ONE_SEC;
		final long ONE_HOUR = 60 * ONE_MIN;
		final long ONE_DAY  = 24 * ONE_HOUR;
		
		long days = remainder / ONE_DAY;
		remainder %= ONE_DAY;
		
		long hours = remainder / ONE_HOUR;
		remainder %= ONE_HOUR;
		
		long mins = remainder / ONE_MIN;
		remainder %= ONE_MIN;
		
		long secs = remainder / ONE_SEC;
		remainder %= ONE_SEC;
		
		StringBuffer sb = new StringBuffer(15);
		
		if (days > 0) {
			sb.append(days);
			sb.append("d ");
		}
		if (hours > 0) {
			sb.append(hours);
			sb.append("h ");
		}
		if (mins > 0) {
			sb.append(mins);
			sb.append("m ");
		}
		if (days == 0 && secs > 0) {
			sb.append(secs);
			sb.append("s ");
		}
		
		return sb.toString().trim();
	}

}
