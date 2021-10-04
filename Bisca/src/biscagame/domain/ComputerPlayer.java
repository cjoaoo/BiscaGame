package biscagame.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * @author catarinajoao
 * This class represent the computer player of the Bisca Game.
 */
public class ComputerPlayer extends Player{

	// attributes
	// Random r = new Random(1); // seed is only for tests
	Random r = new Random();

	// constructor
	/**
	 * Creates a ComputerPlayer with a name
	 * @param name
	 */
	public ComputerPlayer(String name) {
		super(name);
	}


	// methods

	/**
	 * @requires stillHasCards()
	 * @return true if this player still has cards left to play
	 */
	public Card playFirst() {
		// doesn't order the cards when it plays first
		return currentCards.remove(r.nextInt(currentCards.size()));
	}

	/**
	 * @param c - card the first player played
	 * @param trionfiSuit - trionfi suit of the game
	 * @requires stillHasCards()
	 * @return card chosen to play
	 */
	public Card playSecond(Card c) {

		Card chosen = null;

		// sort cards by rank so that the search is easier 
		Collections.sort(currentCards, Comparator.comparing(Card::getRank));

		// if the card is valuable, try to win it
		if(c.isValuable()) {
			if(hasHigherRankSameSuit(c)) { 
				chosen = playHigherRankSameSuit(c); // play higher rank of same suit to win it. can be trionfi or not
			}else {

				if(!c.isTrionfi(trionfi) && hasTrionfi()){
					chosen =  playLowestRankTrionfi(); // we play the lowest rank trionfi to win the non-trionfi valuable card
				}else {
					chosen = playLowestRankCard(); // this card may or may not be lost
				}
			}

		}else { // if the card is not valuable
			if(onlyHasValuables()) {
				if(hasHigherRankSameSuit(c)) { 
					chosen = playHigherRankSameSuit(c); // play higher rank of same suit to win it. can be trionfi or not
				}else {
					if(hasTrionfi()) {
						chosen = playLowestRankTrionfi(); // play trionfi to save the other cards for later
					}
				}
			}
		}	
		// if no conditions apply, play lowest rank card. this card may or may not be lost
		if(chosen == null) {
			chosen = playLowestRankCard();
		}


		return chosen;

	}

	/**
	 * @param cardPlayed
	 * @return true if the player has a card with a higher rank of the same suit as cardPlayer
	 */
	private boolean hasHigherRankSameSuit(Card cardPlayed) {
		for(Card c: currentCards) {
			if(c.isSameSuit(cardPlayed) && c.isHigherRank(cardPlayed)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param cardPlayed
	 * @requires stillHasCards()
	 * @return the card with a higher rank of the same suit as cardPlayer
	 */
	private Card playHigherRankSameSuit(Card cardPlayed) {
		for(Card c: currentCards) {
			if(c.isSameSuit(cardPlayed) && c.isHigherRank(cardPlayed)) {
				currentCards.remove(c);
				return c;
			}
		}
		return currentCards.remove(0);
	}


	/**
	 * @requires stillHasCards()
	 * @return true if the player only has cards to play that are worth > 0 points
	 */
	private boolean onlyHasValuables() {
		return currentCards.get(0).isHigherRank(RankBisca.SIX);
	}


	/**
	 * @requires hasTrionfi()
	 * @return the first trionfi it finds. because the list is ordered by rank, it will be the lowest rank
	 */
	private Card playLowestRankTrionfi() {
		for(Card c: currentCards) {
			if(c.isTrionfi(trionfi)) { 
				currentCards.remove(c);
				return c;
			}
		}
		return currentCards.remove(0);
	}


	/**
	 * @param trionfiSuit
	 * @return true if the player has any card of a trionfi suit
	 */
	private boolean hasTrionfi() {
		for(Card c: currentCards) {
			if(c.isTrionfi(trionfi)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * @return the lowest rank card this player has
	 */
	private Card playLowestRankCard() {
		return currentCards.remove(0);
	}




}
