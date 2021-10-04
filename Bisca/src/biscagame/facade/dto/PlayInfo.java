package biscagame.facade.dto;

/**
 * @author catarinajoao
 * DTO with information about one play (name of the player + card played).
 */
public class PlayInfo {
	
	// attributes
	private String player;
	private String card;
	
	// constructor
	/**
	 * @param player
	 * @param card
	 */
	public PlayInfo(String player, String card) {
		this.player = player;
		this.card = card;
	}
	
	/**
	 * @return name of the player
	 */
	public String getPlayer() {
		return player;
	}
	
	/**
	 * @return name of the card played
	 */
	public String getCard() {
		return card;
	}
}
