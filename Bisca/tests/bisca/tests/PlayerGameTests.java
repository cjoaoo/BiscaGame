package bisca.tests;

import java.util.Scanner;

import org.junit.Test;

import biscagame.domain.Card;
import biscagame.domain.ComputerPlayer;
import biscagame.domain.Game;
import biscagame.domain.HumanPlayer;
import biscagame.domain.RankBisca;
import biscagame.domain.Suit;
import biscagame.facade.dto.PlayInfo;
import biscagame.facade.dto.RoundInfo;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerGameTests {
	
	String humanInput = "\n1\n\n\n1\n1\n1\n1\n1\n1\n1\n";
	Scanner sc = new Scanner(humanInput);
	HumanPlayer human = new HumanPlayer("Fake Human");
	ComputerPlayer computer;	
	Game game;
	
	// cards
	Card twoOfHearts = new Card(Suit.HEARTS, RankBisca.TWO);
	Card queenOfHearts = new Card(Suit.HEARTS, RankBisca.QUEEN);
	Card threeOfSwords = new Card(Suit.SWORDS, RankBisca.THREE); // trionfi
	Card queenOfSwords = new Card(Suit.SWORDS, RankBisca.QUEEN); // trionfi
	Card aceOfDiamonds = new Card(Suit.DIAMONDS, RankBisca.ACE);
	Card queenOfDiamonds = new Card(Suit.DIAMONDS, RankBisca.QUEEN);
	
	
	// players with empty hands and no cards wond
	void setUp() {
		// Computer player has Random, with seed == 1 (for tests only). the pseudo random sequence starts with {0, 1, 1, 0, 2, 1}
		computer = new ComputerPlayer("Computer"); 
		human.startGame();
		computer.startGame();
		human.setTrionfi(Suit.SWORDS);
		computer.setTrionfi(Suit.SWORDS);
		game = new Game(computer, human);
	}
	
	// simulates user interaction
	void playRound() {
		game.startRound();
		game.humanPlays(human.playCard(sc.nextInt()));
		RoundInfo round = game.endRound();
		System.out.println("\n" + round.getFirstToPlay() + " played the " + round.getFirstCard() + ".");
		System.out.println(round.getSecondToPlay() + " played the " + round.getSecondCard() + ".");
		System.out.println(round.getWinner() + " won this round.");
	}
	
	
	// human wins every round
	@Test
	public void humanWinsEveryRound(){
		
		setUp();
		human.receiveCard(queenOfHearts);
		human.receiveCard(threeOfSwords);
		human.receiveCard(queenOfSwords);
				
		computer.receiveCard(twoOfHearts);
		computer.receiveCard(aceOfDiamonds);
		computer.receiveCard(queenOfDiamonds);
		
		// human cards: queen hearts, 3 swords, queen swords
		// comp cards: 2 hearts, queen diamonds, ace diamonds
		// computer plays 2 hearts, human plays queen hearts and wins.
		playRound();
		assertTrue(human.countPoints() == 2);
		assertTrue(computer.countPoints() == 0);
		
		// human cards: 3 swords, queen swords, ace clubs
		// comp cards: queen diamonds, 7 clubs, ace diamonds
		// human plays 3 swords, comp plays queen diamonds. human wins.
		playRound(); 
		assertTrue(human.countPoints() == 4);
		assertTrue(computer.countPoints() == 0);	
		
		// human cards: queen swords, ace clubs, king clubs
		// comp cards: jack clubs, 7 clubs, ace diamonds
		// human plays queen swords, comp plays jack clubs. human wins.
		playRound(); 
		assertTrue(human.countPoints() == 9);
		assertTrue(computer.countPoints() == 0);
		
		// human cards: queen swords, ace clubs, queen clubs
		// comp cards: 6 clubs, 7 clubs, ace diamonds
		//human plays ace clubs, computer plays 6 clubs. human wins
		playRound();
		assertTrue(human.countPoints() == 20);
		assertTrue(computer.countPoints() == 0);
		
		String winner = game.endGame().getWinner();
		assertTrue(winner.equals(human.getName()));

	}
	
	@Test
	public void humanWinsHalfRoundsButLoses(){
		
		setUp();
		human.receiveCard(queenOfDiamonds);
		human.receiveCard(threeOfSwords);
		human.receiveCard(queenOfSwords);
		
		computer.receiveCard(aceOfDiamonds);
		computer.receiveCard(queenOfHearts);
		computer.receiveCard(twoOfHearts);
		
		// human cards: queen diamonds, 3 swords, queen swords
		// comp cards: ace diamonds, queen hearts,  2 hearts
		// comp plays ace diamonds (random with seed 1 == index 0), human plays queen diamonds. comp wins
		playRound(); 
		assertTrue(computer.countPoints() == 13);
		assertTrue(human.countPoints() == 0);
		
		// human cards: 3 swords, queen swords, 7 clubs
		// comp cards: queen hearts, 2 hearts, ace clubs
		// comp plays 2 hearts (random with seed 1 == index 1), human plays 3 swords and wins
		playRound(); 
		assertTrue(human.countPoints() == 0);
		assertTrue(computer.countPoints() == 13);
			
		// human cards: queen swords, 7 clubs, king clubs
		// comp cards: queen hearts, jack clubs, ace clubs (ordered)
		// human plays queen swords, comp plays queen hearts. human wins
		playRound(); 
		assertTrue(human.countPoints() == 4);
		assertTrue(computer.countPoints() == 13);
		
		// human cards: 7 clubs, king clubs, queen clubs
		// comp cards: 6 clubs, jack clubs, ace clubs
		// human plays 7 clubs, comp plays ace clubs. comp wins
		playRound(); 
		assertTrue(human.countPoints() == 4);
		assertTrue(computer.countPoints() == 34);

		String winner = game.endGame().getWinner();
		assertTrue(winner.equals(computer.getName()));
	}
	
	@Test
	public void computerWinsEveryRound(){
		
		setUp();
		
		human.receiveCard(threeOfSwords);	
		human.receiveCard(queenOfHearts);
		human.receiveCard(twoOfHearts);
		
		computer.receiveCard(queenOfSwords);
		computer.receiveCard(queenOfDiamonds);
		computer.receiveCard(aceOfDiamonds);
		
		// comp cards: queen swords, queen diamonds, ace diamonds
		// human cards: 3 swords, queen hearts, 2 hearts
		// computer plays queen of swords (random with seed 1 == index 0), human plays 3 swords, computer wins.
		playRound(); 
		assertTrue(human.countPoints() == 0);
		assertTrue(computer.countPoints() == 2);
		
		// comp cards: queen diamonds, ace diamonds, ace clubs
		// human cards: queen hearts, two hearts, 7 clubs 
		// computer plays ace diamonds (random with seed 1 == index 1) and wins because it plays first
		playRound(); 
		assertTrue(human.countPoints() == 0);
		assertTrue(computer.countPoints() == 15);	
		
		// comp cards: queen diamonds,  ace clubs, king clubs (it doesnt order the cards when it plays first)
		// human cards: 2 hearts, 7 clubs, queen clubs
		playRound(); // computer plays ace clubs (random with seed 1 == index 1), human plays 2 hearts. computer wins because it plays first
		assertTrue(human.countPoints() == 0);
		assertTrue(computer.countPoints() == 26);
		
		// comp cards: queen diamonds, king clubs, 6 clubs
		// human cards: 7 clubs, queen clubs, 5 clubs
		playRound(); // computer plays queen diamonds (random with seed 1 == index 0), human plays 7 clubs. computer wins because it plays first
		assertTrue(human.countPoints() == 0);
		assertTrue(computer.countPoints() == 38);
		
		String winner = game.endGame().getWinner();
		assertTrue(winner.equals(computer.getName()));
	}
	
	
}
