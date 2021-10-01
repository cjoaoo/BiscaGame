package biscagame.facade.facade;



import biscagame.domain.ComputerPlayer;
import biscagame.domain.HumanPlayer;
import biscagame.facade.handlers.GameHandler;

public class BiscaGame {

	private ComputerPlayer computer;
	private HumanPlayer human;
	
	public BiscaGame(String playerName) {
		human = new HumanPlayer(playerName);
		computer = new ComputerPlayer("Computer");
	}
	
	public GameHandler getGameHandler() {
		return new GameHandler(computer, human);
	}
	
	
}
