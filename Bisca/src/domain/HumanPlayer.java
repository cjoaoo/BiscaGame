package domain;

import java.util.Scanner;

/**
 * This class represent the human player of BiscaGame.
 * @author catarinajoao
 *
 */
public class HumanPlayer extends Player{
	
	Scanner sc;

	/**
	 * Creates a player with a name.
	 * @param name of the player
	 * @param sc scanner that reads player input
	 */
	public HumanPlayer(String name, Scanner sc) {
		super(name);
		this.sc = sc;
	}

	@Override
	public Card playFirst() {
		return play();
	}

	@Override
	public Card playSecond(Card c, Suit trionfiSuit) {
		return play(); 
	}

	// private methods
	
	/**
	 * @return the card chosen to play
	 */
	private Card play() {
		
		showCards();
		int option = askForInput();
		Card chosen = playCard(option);
		return chosen;
	}
	
	/**
	 * Prints the current hand so that the player can chose
	 */
	private void showCards() {
		
		System.out.println("\nYour current cards are:");
		int i = 1;
		for(Card c : currentCards) {
			System.out.println(i + ". " + c.toString());
			i++;
		}
	}

	/**
	 * @return number of the option chosen
	 * @ensures 1 <= number <= number of cards available
	 */
	private int askForInput() {
		int option = -1;
		boolean valid = false;
		System.out.println("What card do you want to play? Please type the number of the option.");
		
		do {
			try {
				option = sc.nextInt();
				}catch(Exception e) {
					sc.nextLine();
				}
			if(option >= 1 && option <= currentCards.size()) {
				valid = true;
			}else {
				System.out.println("Please type the number of the option."); // message for exception and for invalid input
			}
		}while(!valid);
		
		return option;
	}

	
	/**
	 * Takes a card from the player's current cards.
	 * @param option - number on the list chosen by the player
	 * @requires 1 <= option <= number of cards available
	 * @return the card chosen
	 */
	private Card playCard(int option) {
		return currentCards.remove(option-1);
	}


}
