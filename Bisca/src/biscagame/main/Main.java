package biscagame.main;


import java.util.Scanner;
import biscagame.exceptions.InvalidInputException;
import biscagame.facade.dto.GameResults;
import biscagame.facade.dto.PlayInfo;
import biscagame.facade.dto.RoundInfo;
import biscagame.facade.facade.BiscaGame;
import biscagame.facade.handlers.GameHandler;
import utils.Utils;

public class Main {

	static final String RULES = "The goal of the game is to capture the most points.\nCards ranking is Two < Three < Four < Five < Six < Queen < Jack < King < Seven < Ace\n"
			+ "Each card is worth:\nAce: 11 points\nSeven: 10 points\nKing: 4 points\nJack: 3 points\nQueen: 2 points \nOther cards: 0 points\n"
			+ "\nEach player is dealt three cards. The next card will become the trionfi card and placed in the bottom of the deck.\n"
			+ "During each round, one player puts a card down, and the opponent places on of their cards. Who captures and who forfeits the card is determined by:\n"
			+ "- if the cards are the same suit, the highest ranking card wins\n"
			+ "- if the cards are of different suits and there is no trionfi card amongst them, the first player wins\n"
			+ "- if the cards are of different suits and there is a trionfi card amongst them, whoever played the trionfi card wins\n"
			+ "The player who wins the round, takes the two cards played to their won cards pile (they will not be played again).\n"
			+ "Each player takes a new card from the deck. The player who won this round takes the card first, and will also be the first to play the next round.\n"
			+ "When there are no cards left, each player counts their points. Whoever has most points wins.\n";


	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Hello. What is your name?");
		String playerName = sc.nextLine();

		BiscaGame bisca = new BiscaGame(playerName);

		System.out.println("Hello, " + playerName + ". Welcome to the Bisca game. You will be playing against me, the computer.\n"
				+ "Would you like to read the rules? [Y/n]");

		if(answerIsYes(sc)) {
			System.out.println(RULES);
		}
		sc.nextLine();

		
		System.out.println("Let's play the game.");
		Utils.sleep(); // to simulate a more natural flow of time
		
		do {
			GameHandler handler = bisca.getGameHandler();
			System.out.println("\nShuffling the deck...");
			Utils.sleep();
			String trionfi = handler.startGame();
			System.out.println("The trionfi card is: " + trionfi.toString() + ". It will go to the bottom of the deck. If you ever forget which is the trionfi suit, type 'trionfi'.");

			while(!handler.gameEnded()) {
				playRound(handler, sc);
			}
			GameResults res = handler.getGameResults();
			System.out.println("Points for " + res.getHuman() + ": " + res.getHumanPoints() + "\nPoints for " + res.getComputer() + ": " + res.getComputerPoints());
			System.out.println(res.getWinner() + " won the game.\nWould you like to play again? [Y/n]");
			//System.out.println("So far I have won " + computer.getGamesWon() + " game(s) and you have won " + player.getGamesWon() + " games(s).");
			
			}while(answerIsYes(sc));
		
		System.out.println("Thank you for playing Bisca with me. It was fun.");
		sc.close();
	

		}


		// private methods

		private static void validateAndSendInput(Scanner sc, GameHandler handler) {
			boolean invalidInput = true;
			do {
				try {
					handler.tellInput(sc.nextLine());
					invalidInput = false;
				}catch(InvalidInputException e) {
					System.out.println(e.getMessage());
				}
			}while(invalidInput);
		}

		// Reads input from the player. If the answer is yes, returns true
		private static boolean answerIsYes(Scanner sc) {	
			return sc.next().toLowerCase().startsWith("y");
		}
		
		private static void playRound(GameHandler handler, Scanner sc) {
			
			System.out.println("\n=== New Round ===\n");
			PlayInfo info = handler.startRound();		
			if(info != null) { // the computer has already played
				System.out.println(info.getPlayer() + " played the " + info.getCard() + ".");
			}
			System.out.println(handler.showCards());
			System.out.println("Which card would you like to play?");
			validateAndSendInput(sc, handler);

			RoundInfo round = handler.endRound();
			System.out.println("\n" + round.getFirstToPlay() + " played the " + round.getFirstCard() + ".");
			System.out.println(round.getSecondToPlay() + " played the " + round.getSecondCard() + ".");
			System.out.println("\n" + round.getWinner() + " won this round.");
			Utils.sleep();
			if(handler.deckStillHasCards()) {
				System.out.println("\n" + round.getWinner() + " takes a card from the deck...\n" + round.getLoser() + " takes a card from the deck..." );
			}
			
		}

	}
