package utils;

public final class Utils {
	
	private Utils() {
		
	}

	/**
	 * @param s - a word
	 * @return word in title case (Ex: WORD -> Word)
	 */
	public static String wordToTitleCase(String s) {
		
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	
	/**
	 * 	Waits a second to simulate time passing.
	 */
	public static void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
