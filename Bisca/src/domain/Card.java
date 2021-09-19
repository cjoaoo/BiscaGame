package domain;
import static utils.Utils.*;

/**
 * @author catarinajoao
 * Represents a suit playing card.
 */
public class Card {
	
	// attributes
	private Suit suit;
	private RankBisca rank;
	private String name;
	
	// constructor
	/**
	 * @param s suit of the card
	 * @param r rank of the card
	 */
	public Card(Suit s, RankBisca r) {
		this.suit = s;
		this.rank = r;
		this.name = wordToTitleCase(rank.toString()) + " of " + wordToTitleCase(suit.toString());
	}

	// methods
	/**
	 * @return the value/point of this card
	 */
	public int getValue() {
		return rank.getValue();
	}
	
	/**
	 * @return the suit of this card
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * @param s a card suit
	 * @return true if this card is the same suit as the suit given
	 */
	public boolean isSameSuit(Card other) {
		return suit.equals(other.suit);
	}
	
	/**
	 * @param other the card that is being compared to this one
	 * @return a positive number if this card has a higher rank, a negative number if this card has a lower rank, 0 if they are the same
	 */
	public int compareRank(Card other) {
		return rank.compareTo(other.rank);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public boolean equals(Card other) {
		return rank.equals(other.rank) && suit.equals(other.suit);
	}

}
