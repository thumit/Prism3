package spectrumConvenienceClasses;

public class NameHandle {
	 public static boolean nameIsValid(String s) {
		 boolean isNameValid = true;
		 
		 if (s == null || s.trim().isEmpty()) {
			 isNameValid = false;
	     }
		 
//		 String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
		 String specialChars = "/*!@#$%^&*\"{}|\\?/<>";
	     for (int i = 0; i < s.length() - 1; i++) {
	         if (specialChars.contains(s.substring(i, i+1))) {
	        	 isNameValid = false;
	         }
	     }
	     return isNameValid;
	 }	
}
