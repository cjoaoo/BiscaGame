package biscagame.facade.facade;

import biscagame.domain.ComputerPlayer;
import biscagame.domain.HumanPlayer;
import biscagame.facade.handlers.GameHandler;

/**
 * @author catarinajoao
 * The system class.
 */
public class BiscaGame {

	// attributes
	private ComputerPlayer computer;
	private HumanPlayer human;
	
	// constructor
	/**
	 * @param playerName - the name of the user
	 */
	public BiscaGame(String playerName) {
		human = new HumanPlayer(playerName);
		computer = new ComputerPlayer("Computer");
	}
	
	// methods
	/**
	 * @return handler to interact with the user for one game of Bisca
	 */
	public GameHandler getGameHandler() {
		return new GameHandler(computer, human);
	}
	
}
