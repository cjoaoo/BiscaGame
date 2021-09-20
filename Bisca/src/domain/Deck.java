package domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author catarinajoao
 * Represents a deck of playing cards.
 */
public class Deck {
	
	// attributes 
	private ArrayList<Card> cards;
	
	// constructors
	/**
	 * Creates an empty deck.
	 */
	public Deck() {
			cards = new ArrayList<>();
		}
	
	/**
	 * Creates a specific deck.
	 * @param list of cards to be put in the deck
	 */
	public void setDeck(List<Card> list) {
		cards = (ArrayList<Card>) list; 
	}
	
	// methods
	
	/**
	 * Shuffles the deck.
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * @return how many cards are in the deck
	 */
	public int size() {
		return cards.size();
	}
	
	/**
	 * @return true if there are no cards in the deck
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	/**
	 * Takes a card from the top of the deck.
	 * @requires !isEmpty()
	 * @return the card from on top of the deck
	 */
	public Card takeCard() {
		return cards.remove(cards.size()-1);
	}
	
	/**
	 * Inserts a card in the bottom of the deck.
	 * @param card to be put in the deck
	 */
	public void putCardBottom(Card card) {
		cards.add(0, card);
	}
	
	@Override
	public String toString() {
		
		if(cards.isEmpty()) {
			return "There are no cards in the deck.";
		}
		
		StringBuilder s = new StringBuilder();
		
		for(Card c: cards) {
			s.append(c.toString() + ", ");
		}
		
		return s.substring(0, s.length()-2);
	}
	
}
