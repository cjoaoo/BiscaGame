package biscagame.domain;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * @author catarinajoao
 * Represents a deck of cards specifically for the game Bisca. 
 */
public class BiscaDeck extends Deck{

	// constructor
	/**
	 * Creates a deck of the 40 cards needed for a game of Bisca.
	 */
	public BiscaDeck() {
		super();
		ArrayList<Card> cards = new ArrayList<>();
		for(Suit s : Suit.values()) {
			EnumSet.allOf(RankBisca.class).forEach(r -> cards.add(new Card(s, r)));
		}
		setDeck(cards);
	}
}
