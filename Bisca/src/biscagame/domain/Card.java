package biscagame.domain;
import static utils.Utils.*;

import java.util.Comparator;

/**
 * @author catarinajoao
 * Represents a suit playing card.
 */
public class Card{
	
	// attributes
	private Suit suit;
	private RankBisca rank;
	private String name;
	
	// constructor
	/**
	 * @param s - suit of the card
	 * @param r - rank of the card
	 */
	public Card(Suit s, RankBisca r) {
		this.suit = s;
		this.rank = r;
		this.name = wordToTitleCase(rank.toString()) + " of " + wordToTitleCase(suit.toString());
	}

	// methods
	/**
	 * @return the how many points this card is worth
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
	 * @return the rank of this card
	 */
	public RankBisca getRank() {
		return rank;
	}
	
	/**
	 * @param s - a card suit
	 * @return true if this card is the same suit as the suit given
	 */
	public boolean isSameSuit(Card other) {
		return suit.equals(other.suit);
	}
	
	/**
	 * @param other - a card
	 * @return true if this card is the same suit as the card given
	 */
	public boolean isHigherRank(Card other) {
		return compareRank(other) > 0;
	}
	
	/**
	 * @param other
	 * @return true if this card is higher rank than the rank given
	 */
	public boolean isHigherRank(RankBisca other) {
		return this.rank.compareTo(other) > 0;
	}
	
	/**
	 * @return true if this card is worth more than zero points
	 */
	public boolean isValuable() {
		return rank.getValue() > 0;
	}
	
	/**
	 * @param trionfiSuit
	 * @return true if this card is trionfi
	 */
	public boolean isTrionfi(Suit trionfiSuit) {
		return suit.equals(trionfiSuit);
	}
	
	/**
	 * @param other - the card that is being compared to this one
	 * @return a positive number if this card has a higher rank, a negative number if this card has a lower rank, 0 if they are the same
	 */
	public int compareRank(Card other) {
		return rank.compareTo(other.rank);
	}
	
	@Override
	public String toString() {
		return name;
	}


}
