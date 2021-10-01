package biscagame.domain;



/**
 * This class represent the human player of BiscaGame.
 * @author catarinajoao
 *
 */
public class HumanPlayer extends Player{


	/**
	 * Creates a player with a name.
	 * @param name of the player
	 */
	public HumanPlayer(String name) {
		super(name);
	}
	
	/**
	 * Takes a card from the player's current cards.
	 * @param option - number on the list chosen by the player
	 * @requires 1 <= option <= number of cards available
	 * @return the card chosen
	 */
	public Card playCard(int option) {
		return currentCards.remove(option-1);
	}



}
