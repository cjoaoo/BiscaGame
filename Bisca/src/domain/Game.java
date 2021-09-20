package domain;

public class Game {
	
	// constant
	static final int SIZE_HAND = 3;
	
	// attributes
	Player user;
	Player comp;
	
	BiscaDeck deck;
	Suit trionfiSuit; // the trump suit
	boolean computersTurn; // because the game is between two players, we can use a boolean to signal whose turn it is
	
	// constructor
	public Game(Player comp, Player user) {
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
		Card trionfi = deck.takeCard();
		deck.putCardBottom(trionfi);
		trionfiSuit = trionfi.getSuit();
	}
	
	public void playRound() {
		
		Card compCard;
		Card userCard;
		
		// after the first round, whoever won the previous round plays first
		if(computersTurn) {
			compCard = comp.playFirst();
			userCard = user.playSecond(compCard, trionfiSuit);
		}else {
			userCard = user.playFirst();
			compCard = comp.playSecond(userCard, trionfiSuit);
		}
		
		// the player who won this round takes the cards played
		Player winner = computerWins(compCard, userCard) ? comp : user;
		Player loser = winner == comp ? user : comp;
		computersTurn = winner == comp? true : false; // the winner plays first next time
		winner.addToCardsWon(compCard, userCard);
		
		System.out.println(winner.getName() + " won this round.");
		
		if(!deck.isEmpty()) {
			winner.receiveCard(deck.takeCard());	// the computer takes a card first
			loser.receiveCard(deck.takeCard());
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
			if(!compCard.isTrionfi(trionfiSuit) && !userCard.isTrionfi(trionfiSuit)) {
				return computersTurn;
			}else {
				// one of them used a trionfi: if the computer's card is trionfi, the computer wins. if not, the user wins.
				return compCard.isTrionfi(trionfiSuit);
			}
			
		}
	}

}
