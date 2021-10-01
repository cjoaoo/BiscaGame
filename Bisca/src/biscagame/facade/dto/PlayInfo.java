package biscagame.facade.dto;

public class PlayInfo {
	
	private String player;
	private String card;
	
	public PlayInfo(String player, String card) {
		this.player = player;
		this.card = card;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public String getCard() {
		return card;
	}
}
