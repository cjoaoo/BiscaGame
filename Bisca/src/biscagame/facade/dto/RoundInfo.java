package biscagame.facade.dto;

public class RoundInfo {
	private String first;
	private String second;
	private String firstCard;
	private String secondCard;
	private String winner;
	private String loser;
	
	public RoundInfo(String first, String second, String firstCard, String secondCard, String winner, String loser) {
		this.first = first;
		this.second = second;
		this.firstCard = firstCard;
		this.secondCard = secondCard;
		this.winner = winner;
	}

	public String getFirstToPlay() {
		return first;
	}
	public String getSecondToPlay() {
		return second;
	}
	public String getFirstCard() {
		return firstCard;
	}
	public String getSecondCard() {
		return secondCard;
	}
	public String getWinner() {
		return winner;
	}
	public String getLoser() {
		return loser;
	}

	
}
