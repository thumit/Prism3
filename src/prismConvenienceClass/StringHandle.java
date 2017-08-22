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
