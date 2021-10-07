package biscagame.facade.dto;

/**
 * @author catarinajoao
 * DTO with information about a round of bisca - names of the players in order, cards played, winner and loser
 */
public class RoundInfo {
	
	// attributes
	private String first;
	private String second;
	private String firstCard;
	private String secondCard;
	private String winner;
	private String loser;
	
	/**
	 * @param first - name of first player
	 * @param second - name of second player
	 * @param firstCard - name of first card played
	 * @param secondCard - name of second card played
	 * @param winner - name of the winner
	 * @param loser - name of the loser
	 */
	public RoundInfo(String first, String second, String firstCard, String secondCard, String winner, String loser) {
		this.first = first;
		this.second = second;
		this.firstCard = firstCard;
		this.secondCard = secondCard;
		this.winner = winner;
		this.loser = loser;
	}

	/**
	 * @return name of who played first
	 */
	public String getFirstToPlay() {
		return first;
	}
	/**
	 * @return name of who played second
	 */
	public String getSecondToPlay() {
		return second;
	}
	/**
	 * @return name of first card played
	 */
	public String getFirstCard() {
		return firstCard;
	}
	/**
	 * @return name of second card played
	 */
	public String getSecondCard() {
		return secondCard;
	}
	/**
	 * @return name of the winner
	 */
	public String getWinner() {
		return winner;
	}
	/**
	 * @return name of the loser
	 */
	public String getLoser() {
		return loser;
	}

	
}
