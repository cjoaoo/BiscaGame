package biscagame.facade.handlers;

import biscagame.domain.Card;
import biscagame.domain.ComputerPlayer;
import biscagame.domain.Game;
import biscagame.domain.HumanPlayer;
import biscagame.exceptions.InvalidInputException;
import biscagame.facade.dto.GameResults;
import biscagame.facade.dto.PlayInfo;
import biscagame.facade.dto.RoundInfo;

public class GameHandler {
	
	private HumanPlayer human;
	private Game game;
	private String trionfi;
	

	public GameHandler(ComputerPlayer computer, HumanPlayer human) {
		this.human = human;
		game = new Game(computer, human);
	}
	
	public String startGame() {
		trionfi = game.startGame();
		return trionfi;
	}

	public boolean gameEnded() {
		return game.hasEnded();
	}
	
	public PlayInfo startRound() {
		return game.startRound();
	}

	
	public String showCards() {
		StringBuilder s = new StringBuilder();
		s.append("\nYour current cards are:\n");
		int i = 1;
		for(Card c : human.showHand()) {
			s.append(i + ". " + c.toString() + "\n");
			i++;
		}
		return s.toString();
	}			
	

	public void tellInput(String input) throws InvalidInputException{
		int option = -1;
		try {
			option = Integer.parseInt(input);
		}catch(NumberFormatException e) {
			// do nothing because option will remain -1 and the InvalidInputException will be thrown
		}

		if(option < 1 || option > human.currentCardsSize()) {
			throw new InvalidInputException("Please type the number of the option.");
		}
		
		game.humanPlays(human.playCard(option));
	}

	public RoundInfo endRound() {
		return game.endRound();
		
	}
	
	public GameResults getGameResults() {
		return game.endGame();
	}

	public boolean deckStillHasCards() {
		return game.deckStillHasCards();
	}

	

}
