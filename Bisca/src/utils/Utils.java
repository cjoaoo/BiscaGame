package utils;

public final class Utils {
	
	private Utils() {
		
	}

	public static String wordToTitleCase(String s) {
		
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
}
