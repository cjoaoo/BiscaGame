package domain;

/**
 * @author catarinajoao
 * This class represents a game of Bisca
 */
public class Game {

	// constant
	static final int SIZE_HAND = 3;

	// attributes
	Player playerOne;
	Player playerTwo;
	Player firstToPlay;
	Player secondToPlay;

	BiscaDeck deck;
	Suit trionfiSuit; // the trump suit
	//boolean computersTurn; // because the game is between two players, we can use a boolean to signal whose turn it is

	// constructor
	public Game(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.deck = new BiscaDeck();
		// computersTurn = true; // the computer dealt the cards, so it starts first
		this.firstToPlay = playerOne;
		this.secondToPlay = playerTwo;
		this.trionfiSuit = Suit.SWORDS; // default value
	}

	// methods

	/**
	 * Plays a game of Bisca
	 */
	public void playGame() {
		startGame();
		while(firstToPlay.stillHasCards()) {
			playRound();
		}
		endGame();
	}


	/**
	 * Starts the game of bisca. Deck is shuffled, players receive their cards and trionfi is chosen.
	 */
	public void startGame() {

		// players have no cards
		playerTwo.startGame();
		playerOne.startGame();

		deck.shuffle();

		// players receive cards
		for(int i = 0; i < SIZE_HAND; i++) {
			playerOne.receiveCard(deck.takeCard());
			playerTwo.receiveCard(deck.takeCard());	
		}

		// trionfi is set and then put back in the deck
		Card trionfi = deck.takeCard();
		deck.putCardBottom(trionfi);
		trionfiSuit = trionfi.getSuit();
	}

	/**
	 * Plays a round of bisca. Whoever wins is the first to play the next round.
	 */
	public void playRound() {

		// after the first round, whoever won the previous round plays first
		Card firstCard = firstToPlay.playFirst();
		System.out.println("\n" + firstToPlay.getName() + " played the " + firstCard.toString() + ".");
		Card secondCard = secondToPlay.playSecond(firstCard, trionfiSuit);
		System.out.println(secondToPlay.getName() + " played the " + secondCard.toString() + ".");

		// the player who won this round takes the cards played
		if(!firstPlayerWins(firstCard, secondCard)) {
			System.out.println("FirstToPlay: " + firstToPlay.getName());
			firstToPlay = swapPlayer(firstToPlay);
			secondToPlay = swapPlayer(secondToPlay);
			System.out.println("FirstToPlay: " + firstToPlay.getName());
		}

		firstToPlay.addToCardsWon(firstCard, secondCard);
		System.out.println(firstToPlay.getName() + " won this round.");

		if(!deck.isEmpty()) {
			firstToPlay.receiveCard(deck.takeCard());
			secondToPlay.receiveCard(deck.takeCard());
		}

	}

	/**
	 * Ends the game by countin points and adding a victory to the winner. In the event of a tie, no victories are added to the players.
	 * @return the name of the player who won. In case of a tie, returns ""
	 */
	public String endGame() {
		int result = playerTwo.countPoints() -playerOne.countPoints();

		if(result < 0) { // the human player won
			playerOne.addVictory();
			return playerOne.getName();
		}else if(result > 0) { // the computer won
			playerTwo.addVictory();
			return playerTwo.getName();
		}else { // it is a tie
			return "";
		}

	}

	// private methods

	/**
	 * Decides who wins a round of Bisca
	 * @param firstCard - card played by the first player
	 * @param secondCard - card played by the second player
	 * @return true if the first player wins, false if the second player wins (there are no ties during rounds)
	 */
	private boolean firstPlayerWins(Card firstCard, Card secondCard) {

		// if the cards are the same suit, the highest rank wins. includes case where both are trionfi
		if(firstCard.isSameSuit(secondCard)) {
			return firstCard.compareRank(secondCard) > 0; 

			// if cards are not same suit	
		}else {
			// if none of the cards has the trionfi suit, the winner is whoever played first
			if(!firstCard.isTrionfi(trionfiSuit) && !secondCard.isTrionfi(trionfiSuit)) {
				return true;
			}else {
				// only one of them used a trionfi: if the first player's card is trionfi, the first player wins. if not, the second player wins.
				return firstCard.isTrionfi(trionfiSuit);
			}

		}


	}


	/**
	 * @param p - a player
	 * @return the other player
	 */
	private Player swapPlayer(Player p) {
		if(p == playerOne) {
			return playerTwo;
		}else {
			return playerOne;
		}

	}
}



