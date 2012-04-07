/* 
 * Demoiselle Sample
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 * 
 * Demoiselle Sample is a set of open source Java EE project samples using
 * the Demoiselle Framework
 *   
 * Demoiselle Sample is released under the terms of the GPL license 3
 * http://www.gnu.org/licenses/gpl.html  GPL License 3
 *   
 * This file is part of Demoiselle Sample.
 * 
 * Demoiselle Sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3.
 * 
 * Demoiselle Sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Demoiselle - Sample.  If not, see <http://www.gnu.org/licenses/>
 */
package br.gov.sample.demoiselle.escola.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 * Class to help to discover info about application paths.
 * 
 * @author SERPRO/CETEC/CTCTA
 */
public class PathUtil {
	
	private static String appName;

	public static void setAppName(String appName) {
		PathUtil.appName = appName;
	}

	/**
	 * Returns path where application is running
	 * 
	 * @return application path
	 */
	public static String getApplicationPath() {
		String path = PathUtil.class.getClassLoader().getResource(".").getFile();
		return path.substring(0, path.indexOf(appName) + appName.length());
	}

	/**
	 * As default, assumes that file is in web application directory
	 * 
	 * @param partialPath
	 * @return
	 */
	public static String getRealPath(String partialPath) {
		return getRealPath(partialPath, "/src/main/webapp");
	}

	/**
	 * Returns real path of partial path passed in
	 * 
	 * @param partialPath
	 * @return
	 */
	public static String getRealPath(String partialPath, String complement) {
		
		String completePath;

		FacesContext aFacesContext = FacesContext.getCurrentInstance();

		if (aFacesContext == null) {
			completePath = PathUtil.getApplicationPath() + complement
					+ partialPath;
		} else {
			ServletContext context = (ServletContext) aFacesContext
					.getExternalContext().getContext();

			completePath = context.getRealPath(partialPath);
		}
		
		return completePath;
	}

}
