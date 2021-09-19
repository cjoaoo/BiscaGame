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
	
	// empty deck
	public Deck() {
			cards = new ArrayList<>();
		}
	
	// custom deck of cards
	public void setDeck(List<Card> list) {
		cards = (ArrayList<Card>) list; 
	}
	
	// methods
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public int size() {
		return cards.size();
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	/**
	 * @requires !isEmpty()
	 * @return the card from on top of the deck
	 */
	public Card takeCard() {
		return cards.remove(cards.size()-1);
	}
	
	public void putCardBeginning(Card card) {
		cards.add(0, card);
	}
	
	public void addCards(List<Card> playerCards) {
		cards.addAll(playerCards);
	}
	
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
