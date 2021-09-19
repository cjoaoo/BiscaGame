package domain;
import static utils.Utils.*;

public class Card {
	
	// attributes
	private SuitEnum suit;
	private RankBiscaEnum rank;
	private String name;
	
	// constructor
	public Card(SuitEnum s, RankBiscaEnum r) {
		this.suit = s;
		this.rank = r;
		this.name = wordToTitleCase(rank.toString()) + " of " + wordToTitleCase(suit.toString());
	}

	// methods
	
	public int getValue() {
		return rank.getValue();
	}
	
	public SuitEnum getSuit() {
		return suit;
	}
	
	public String toString() {
		return name;
	}
	
	public boolean isTrumpCard(SuitEnum s) {
		return s.equals(suit);
	}

}
