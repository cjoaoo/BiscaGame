package domain;

public enum RankBiscaEnum {
	TWO (0), THREE(0), FOUR(0), FIVE(0), SIX(0), QUEEN(2), JACK(3), KING(4), SEVEN(10), ACE(11);
	
	private int value;
	
	private RankBiscaEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
