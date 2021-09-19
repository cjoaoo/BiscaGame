package domain;

public class Game {
	
	// constant
	final static int SIZE_HAND = 3;
	
	// attributes
	HumanPlayer user;
	ComputerPlayer comp;
	
	BiscaDeck deck;
	Card trionfi; // the trump card
	boolean computersTurn; // because the game is between two players, we can use a boolean
	
	// constructor
	public Game(ComputerPlayer comp, HumanPlayer user) {
		this.comp = comp;
		this.user = user;
		this.deck = new BiscaDeck();
		computersTurn = true; // the computer dealt the cards, so it starts first
	}
	
	// methods
	public void startGame() {
		
		// players have no cards
		comp.startGame();
		user.startGame();
		
		deck.shuffle();
		
		// players receive cards
		for(int i = 0; i < SIZE_HAND; i++) {
			user.receiveCard(deck.takeCard());
			comp.receiveCard(deck.takeCard());	
		}
		
		// trionfi is set and then put back in the deck
		trionfi = deck.takeCard();
		deck.putCardBottom(trionfi);
	}
	
	public void playRound() {
		
		Card compCard;
		Card userCard;
		
		// after the first round, whoever won the previous round plays first
		if(computersTurn) {
			compCard = comp.play();
			userCard = user.play();
		}else {
			userCard = user.play();
			compCard = comp.play();
		}
		
		// the player who won this round takes the cards played
		if(computerWins(compCard, userCard)) {	
			comp.addToCardsWon(compCard, userCard);
			computersTurn = true; // the computer plays first next time
			if(!deck.isEmpty()) {
				comp.receiveCard(deck.takeCard());	// the computer takes a card first
				user.receiveCard(deck.takeCard());
			}
		}else {
			user.addToCardsWon(compCard, userCard);
			computersTurn = false; // the user plays first next time
			if(!deck.isEmpty()) {
				user.receiveCard(deck.takeCard()); // the user takes a card first
				comp.receiveCard(deck.takeCard());		
			}
		}

	}
	
	public String endGame() {
		int result = comp.countPoints() -user.countPoints();

		if(result < 0) { // the human player won
			user.addVictory();
			return user.getName();
		}else if(result > 0) { // the computer won
			comp.addVictory();
			return comp.getName();
		}else { // it is a tie
			return "";
		}

	}
	
	// private methods
	
	private boolean computerWins(Card compCard, Card userCard) {
		
		// if the cards are the same suit, the highest rank wins
		if(compCard.isSameSuit(userCard)) {
			return compCard.compareRank(userCard) > 0; 
		
		// if cards are not same suit	
		}else {
			// if none of the cards has the trionfi suit, the winner is whoever played first
			if(!isTrionfi(compCard) && !isTrionfi(userCard)) {
				return computersTurn;
			}else {
				// one of them used a trionfi: if the computer's card is trionfi, the computer wins. if not, the user wins.
				return isTrionfi(compCard);
			}
			
		}
	}
	
	private boolean isTrionfi(Card c) {
		return c.isSameSuit(trionfi);
	}

}
