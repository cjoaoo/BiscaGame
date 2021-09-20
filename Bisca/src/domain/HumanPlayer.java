package domain;

import java.util.Scanner;

public class HumanPlayer extends Player{
	
	Scanner sc;

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
	
	private Card play() {
		
		showCards();
		int option = askForInput();
		Card chosen = playCard(option);
		System.out.println(name + " played the " + chosen.toString() + ".");
		return chosen;
	}
	
	private void showCards() {
		
		System.out.println("Your current cards are:");
		int i = 1;
		for(Card c : currentCards) {
			System.out.println(i + ". " + c.toString());
			i++;
		}
	}

	private int askForInput() {
		int option = -1;
		boolean valid = false;
		System.out.println("\nWhat card do you want to play? Please type the number of the option.");
		
		do {
			try {
				option = sc.nextInt();
				}catch(Exception e) {
					// do nothing
				}
			if(option >= 1 && option <= currentCards.size()) {
				valid = true;
			}else {
				System.out.println("Please type the number of the option."); // message for exception and for invalid input
			}
		}while(!valid);
		
		return option;
	}


	
	private Card playCard(int option) {
		return currentCards.remove(option-1);
	}


}
