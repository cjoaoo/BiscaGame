package domain;

import java.util.ArrayList;

public class Player {
	
	// attributes
	private String name;
	private int gamesWon;
	private ArrayList<Card> currentCards;
	private ArrayList<Card> cardsWon;

	// constructor
	/**
	 * @param name of the player
	 */
	public Player(String name) {
		this.name = name;
		gamesWon = 0;
	}
	
	// methods
	
	public void startGame() {
		currentCards = new ArrayList<>();
		cardsWon = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public void receiveCard(Card c) {
		currentCards.add(c);
	}
	
	public void addToCardsWon(Card c1, Card c2) {
		cardsWon.add(c1);
		cardsWon.add(c2);
	}
	
	/**
	 * @return number of games won by this player
	 */
	public int gamesWon() {
		return gamesWon;
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
