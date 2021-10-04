package biscagame.facade.dto;

/**
 * DTO with information about the game - points and names of each player, name of the winner.
 * @author catarinajoao
 *
 */
public class GameResults {
	
	// attributes
	String human;
	String computer;
	String winner;
	int humanPoints;
	int computerPoints;
	
	// constructor
	/**
	 * @param human
	 * @param computer
	 * @param humanPoints
	 * @param computerPoints
	 * @param winner
	 */
	public GameResults (String human, String computer, int humanPoints, int computerPoints, String winner) {
		this.human = human;
		this.computer = computer;
		this.humanPoints = humanPoints;
		this.computerPoints = computerPoints;
		this.winner = winner;
	}

	/**
	 * @return name of the human player
	 */
	public String getHuman() {
		return human;
	}

	/**
	 * @return name of the computer player
	 */
	public String getComputer() {
		return computer;
	}

	/**
	 * @return name of the winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * @return points of the human player
	 */
	public int getHumanPoints() {
		return humanPoints;
	}

	/**
	 * @return points of the computer player
	 */
	public int getComputerPoints() {
		return computerPoints;
	}


}
