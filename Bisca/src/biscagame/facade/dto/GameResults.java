package biscagame.facade.dto;

public class GameResults {
	
	String human;
	String computer;
	String winner;
	int humanPoints;
	int computerPoints;
	
	public GameResults (String human, String computer, int humanPoints, int computerPoints, String winner) {
		this.human = human;
		this.computer = computer;
		this.humanPoints = humanPoints;
		this.computerPoints = computerPoints;
		this.winner = winner;
	}

	public String getHuman() {
		return human;
	}

	public String getComputer() {
		return computer;
	}

	public String getWinner() {
		return winner;
	}

	public int getHumanPoints() {
		return humanPoints;
	}

	public int getComputerPoints() {
		return computerPoints;
	}


}
