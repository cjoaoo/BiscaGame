package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author catarinajoao
 * Represents a player of Bisca.
 */
public abstract class Player {
	
	// attributes
	protected String name;
	private int gamesWon;
	protected ArrayList<Card> currentCards;
	private ArrayList<Card> cardsWon;

	// constructor
	/**
	 * Creates a player with a name.
	 * @param name of the player
	 */
	protected Player(String name) {
		this.name = name;
		gamesWon = 0;
	}
	
	// methods
	
	/**
	 * @return name of this player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return number of games won by this player
	 */
	public int getGamesWon() {
		return gamesWon;
	}

	
	/**
	 * Makes a player ready to start a game.
	 */
	public void startGame() {
		currentCards = new ArrayList<>();
		cardsWon = new ArrayList<>();
	}
	
	/**
	 * @return a list of the cards of the player
	 */
	public List<Card> showHand(){
		ArrayList<Card> copy = new ArrayList<>();
		for(Card c: currentCards) {
			copy.add(c);
		}
		return copy;
	}
	
	public abstract Card playFirst();
	public abstract Card playSecond(Card c, Suit trionfiSuit);
	
	/**
	 * The player adds this card to their current cards
	 * @param c - a card
	 */
	public void receiveCard(Card c) {
		currentCards.add(c);
	}
	
	/**
	 * The player adds these cards to their pile of cards won
	 * @param c1 - a card
	 * @param c2 - another card
	 */
	public void addToCardsWon(Card c1, Card c2) {
		cardsWon.add(c1);
		cardsWon.add(c2);
	}
	
	/**
	 * @return true if the player still has cards left to play
	 */
	public boolean stillHasCards() {
		return !currentCards.isEmpty();
	}
	
	
	/**
	 * @return sum of the points of the cards this player has won
	 */
	public int countPoints() {
		int points = 0;
		for(Card c: cardsWon) {
			points += c.getValue();
		}
		return points;
	}
	
	/**
	 * Adds a victory to the player's number of victories
	 */
	public void addVictory() {
		gamesWon++;
	}
	

	public String toString() {
		return name;
	}
	


}
