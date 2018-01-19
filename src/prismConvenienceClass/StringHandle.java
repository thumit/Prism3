/*******************************************************************************
 * Copyright (C) 2016-2018 PRISM Development Team
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package prismConvenienceClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandle {
	public static boolean nameIsValid(String s) {
		boolean isNameValid = true;

		if (s == null || s.trim().isEmpty()) {
			isNameValid = false;
		}
		 
		if (s != null) {
//			String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
			String specialChars = "/*!@#$%^&*\"{}|\\?/<>";
			for (int i = 0; i < s.length() - 1; i++) {
				if (specialChars.contains(s.substring(i, i + 1))) {
					isNameValid = false;
				}
			}
		}

		return isNameValid;
	}
	
	public static String normalize(String s) {
		Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
		Matcher match = pt.matcher(s);
		while (match.find()) {
			String c = match.group();
			s = s.replaceAll("\\" + c, "_");
		}
		return s.toLowerCase();
	}
	
}
