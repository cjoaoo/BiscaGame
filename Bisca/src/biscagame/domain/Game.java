package biscagame.domain;

import biscagame.facade.dto.GameResults;
import biscagame.facade.dto.PlayInfo;
import biscagame.facade.dto.RoundInfo;

/**
 * @author catarinajoao
 * This class represents a game of Bisca
 */
/**
 * @author catarinajoao
 *
 */
public class Game {

	// constant
	static final int SIZE_HAND = 3;

	// attributes
	HumanPlayer human;
	ComputerPlayer computer;
	BiscaDeck deck;
	Suit trionfi; // the trump suit
	Round currentRound;
	boolean computerPlaysFirst;


	// constructor
	/**
	 * @param computer 
	 * @param human 
	 */
	public Game(ComputerPlayer computer, HumanPlayer human) {
		this.computer = computer;
		this.human = human;
		this.deck = new BiscaDeck();
		this.trionfi = Suit.SWORDS; // default value
		this.computerPlaysFirst = true;
	}

	// methods

	/**
	 * Starts the game of bisca. Deck is shuffled, players receive their cards and trionfi is chosen.
	 */
	public String startGame() {

		// players have no cards
		human.startGame();
		computer.startGame();

		deck.shuffle();

		// players receive cards
		for(int i = 0; i < SIZE_HAND; i++) {
			human.receiveCard(deck.takeCard());
			computer.receiveCard(deck.takeCard());	
		}

		// trionfi is set and then put back in the deck
		Card trionfiCard = deck.takeCard();
		deck.putCardBottom(trionfiCard);

		trionfi = trionfiCard.getSuit();
		human.setTrionfi(trionfi);
		computer.setTrionfi(trionfi);

		return trionfiCard.toString();
	}

	/**
	 * Starts a round of Bisca.
	 * @return if the computer plays first, returns a DTO with the computer's name and card played. if the human plays first, returns null
	 */
	public PlayInfo startRound() {
		currentRound = new Round(computer, human, computerPlaysFirst, deck);
		currentRound.startRound();
		if(computerPlaysFirst) {
			return new PlayInfo(computer.getName(), currentRound.getFirstCard().toString());
		}
		return null;
	}
	
	/**
	 * Plays the card chosen during this round.
	 * @param c - the card chosen by the human player
	 * @requires have used startRound() before
	 */
	public void humanPlays(Card c) {
		currentRound.humanPlays(c);
	}

	/**
	 * Ends a round of bisca.
	 * @return DTO with information of the names of the players, the cards they player, and the winner and loser of the round
	 */
	public RoundInfo endRound() {
		// whoever wins this round will play first in the next round
		computerPlaysFirst = currentRound.computerWins(trionfi);
		return currentRound.endRound();
	}

	
	/**
	 * @return true if the game has ended (the players have no more cards left)
	 */
	public boolean hasEnded() {
		return !computer.stillHasCards();
	}

	/**
	 * @return true if the deck still has cards
	 */
	public boolean deckStillHasCards() {
		return !deck.isEmpty();
	}


	/**
	 * Ends the game by counting points and adding a victory to the winner. In the event of a tie, no victories are added to the players.
	 * @return DTO with the points of each player and the winner.
	 */
	public GameResults endGame() {

		int pointsHuman = human.countPoints();
		int pointsComputer = computer.countPoints();
		int result = pointsComputer - pointsHuman;
		String winner = "";

		if(result < 0) { // the human player won
			human.addVictory();
			winner = human.getName();
		}else if(result > 0) { // the computer won
			computer.addVictory();
			winner = computer.getName();
		}else { // it is a tie
			winner = "It was a tie.";
		}
		
		return new GameResults(human.getName(), computer.getName(), pointsHuman, pointsComputer, winner);

	}






}



