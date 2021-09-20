package domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ComputerPlayer extends Player{

	// attributes
	Random r = new Random();


	// constructor
	public ComputerPlayer(String name) {
		super(name);
	}


	// method

	/**
	 * @requires stillHasCards()
	 * @return
	 */
	public Card playFirst() {
		return currentCards.remove(r.nextInt(currentCards.size()));
	}

	/**
	 * @param c
	 * @param trionfiSuit
	 * @requires stillHasCards()
	 * @return
	 */
	public Card playSecond(Card c, Suit trionfiSuit) {

		Card chosen;

		// sort cards by rank so that the search is easier 
		Collections.sort(currentCards, Comparator.comparing(Card::getRank));

		// if the card is valuable, try to win it
		if(c.isValuable()) {
			if(hasHigherRankSameSuit(c)) { 
				chosen = playHigherRankSameSuit(c); // play higher rank of same suit to win it. can be trionfi or not
			}else {
				
				if(!c.isTrionfi(trionfiSuit) && hasTrionfi(trionfiSuit)){
					chosen =  playLowestRankTrionfi(trionfiSuit); // we play the lowest rank trionfi to win the non-trionfi valuable card
				}else {
					chosen = playLowestRankCard(); // this card may or may not be lost
				}
			}

		}else { // if the card is not valuable
			
			if(onlyHasSevensOrAces() && hasTrionfi(trionfiSuit)) {
				chosen = playLowestRankTrionfi(trionfiSuit); // play trionfi to save the other cards for later
			}else {
				chosen = playLowestRankCard(); // this card may or may not be lost
			}
		}
		System.out.println(name + " played the " + chosen.toString() + ".");
		return chosen;

	}

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
	 * @return
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
	 * @return
	 */
	private boolean onlyHasSevensOrAces() {
		return currentCards.get(0).isHigherRank(RankBisca.KING);
	}


	/**
	 * @requires hasTrionfi()
	 * @return the first trionfi it finds. because the list is ordered by rank, it will be the lowest rank
	 */
	private Card playLowestRankTrionfi(Suit trionfiSuit) {
		for(Card c: currentCards) {
			if(c.isTrionfi(trionfiSuit)) { 
				currentCards.remove(c);
				return c;
			}
		}
		return currentCards.remove(0);
	}

	
	private boolean hasTrionfi(Suit trionfiSuit) {
		for(Card c: currentCards) {
			if(c.isTrionfi(trionfiSuit)) {
				return true;
			}
		}
		return false;
	}


	private Card playLowestRankCard() {
		return currentCards.remove(0);
	}




}
