package domain;

import java.util.ArrayList;
import java.util.List;

abstract public class Player {
	
	// attributes
	protected String name;
	private int gamesWon;
	protected ArrayList<Card> currentCards;
	private ArrayList<Card> cardsWon;

	// constructor
	/**
	 * @param name of the player
	 */
	public Player(String name) {
		this.name = name;
		gamesWon = 0;
	}
	
	// getters
	
	public String getName() {
		return name;
	}
	
	/**
	 * @return number of games won by this player
	 */
	public int getGamesWon() {
		return gamesWon;
	}
	
	// methods
	
	public void startGame() {
		currentCards = new ArrayList<>();
		cardsWon = new ArrayList<>();
	}
	
	public List<Card> showHand(){
		ArrayList<Card> copy = new ArrayList<>();
		for(Card c: currentCards) {
			copy.add(c);
		}
		return copy;
	}
	
	abstract public Card playFirst();
	abstract public Card playSecond(Card c, Suit trionfiSuit);
	
	public void receiveCard(Card c) {
		currentCards.add(c);
	}
	
	public void addToCardsWon(Card c1, Card c2) {
		cardsWon.add(c1);
		cardsWon.add(c2);
	}
	
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
	
	public void addVictory() {
		gamesWon++;
	}
	

	public String toString() {
		return name;
	}
	


}
