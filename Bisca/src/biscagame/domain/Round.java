package biscagame.domain;

import biscagame.facade.dto.RoundInfo;

public class Round {

	// attributes
	Player first;
	Player second;
	Card firstCard;
	Card secondCard;
	Player winner;
	Player loser;
	BiscaDeck deck;
	boolean computerPlaysFirst;
	
	// constructor
	/**
	 * @param computer player
	 * @param human player
	 * @param computerPlaysFirst - true if the computer is the first player
	 * @param deck - the current deck of the game
	 */
	public Round(ComputerPlayer computer, HumanPlayer human, boolean computerPlaysFirst, BiscaDeck deck) {
		this.computerPlaysFirst = computerPlaysFirst;
		this.deck = deck;
		this.first = computerPlaysFirst ? computer : human;
		this.second = computerPlaysFirst ? human : computer;
	}
	
	// getters
	/**
	 * @return the first card played in this round
	 */
	public Card getFirstCard() {
		return firstCard;
	}
	
	/**
	 * @return the second card played in this round
	 */
	public Card getSecondCard() {
		return secondCard;
	}
	
	//methods
	
	/**
	 * Starts a round.
	 */
	public void startRound() {
		if(computerPlaysFirst) {
			firstCard = ((ComputerPlayer)first).playFirst();
		}
	}
	
	/**
	 * @param c - card chosen by the human player
	 */
	public void humanPlays(Card c) {
		if(computerPlaysFirst) {
			secondCard = c;
		}else {
			firstCard = c;
			secondCard = ((ComputerPlayer) second).playSecond(c);
		}
	}
	
	/**
	 * @param trionfi - the trionfi of this game
	 * @return true if the computer wins this round
	 */
	public boolean computerWins(Suit trionfi) {
		
		boolean firstPlayerWins = firstPlayerWins(trionfi);
		winner = firstPlayerWins ? first : second;
		loser = firstPlayerWins ? second : first;	
		return (firstPlayerWins && computerPlaysFirst) || (!firstPlayerWins && !computerPlaysFirst);
	
	}
	
	/**
	 * @return DTO with the names of the players, cards player, winner and loser of this round
	 */
	public RoundInfo endRound() {
		
		winner.addToCardsWon(firstCard, secondCard);
		if(!deck.isEmpty()) {
			winner.receiveCard(deck.takeCard());
			loser.receiveCard(deck.takeCard());
		}
		return new RoundInfo(first.getName(), second.getName(), firstCard.toString(), secondCard.toString(), winner.getName(), loser.getName());
	}


	
	/**
	 * Decides who wins a round of Bisca
	 * @param trionfi - the trionfi suit of the game
	 * @return true if the first player wins, false if the second player wins (there are no ties during rounds)
	 */
	public boolean firstPlayerWins(Suit trionfi) {

		// if the cards are the same suit, the highest rank wins. includes case where both are trionfi
		if(firstCard.isSameSuit(secondCard)) {
			return firstCard.compareRank(secondCard) > 0; 

			// if cards are not same suit	
		}else {
			// if none of the cards has the trionfi suit, the winner is whoever played first
			if(!firstCard.isTrionfi(trionfi) && !secondCard.isTrionfi(trionfi)) {
				return true;
			}else {
				// only one of them used a trionfi: if the first player's card is trionfi, the first player wins. if not, the second player wins.
				return firstCard.isTrionfi(trionfi);
			}

		}
	}


	
}
