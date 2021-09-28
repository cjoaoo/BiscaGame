package bisca.tests;

import java.util.Scanner;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import domain.Card;
import domain.ComputerPlayer;
import domain.Game;
import domain.HumanPlayer;
import domain.RankBisca;
import domain.Suit;

public class PlayerGameTests {
	
	String humanInput = "\n1\nTEXTO\nmais texto que nao é a opção \n1\n1\n1\n1\n1\n1\n1\n";
	Scanner sc = new Scanner(humanInput);
	HumanPlayer human = new HumanPlayer("Fake Human", sc);
	ComputerPlayer computer = new ComputerPlayer("Computer");	
	Game game;
	
	Card twoOfHearts = new Card(Suit.HEARTS, RankBisca.TWO);
	Card queenOfHearts = new Card(Suit.HEARTS, RankBisca.QUEEN);
	Card threeOfSwords = new Card(Suit.SWORDS, RankBisca.THREE); // trionfi
	Card queenOfSwords = new Card(Suit.SWORDS, RankBisca.QUEEN); // trionfi
	Card aceOfDiamonds = new Card(Suit.DIAMONDS, RankBisca.ACE);
	Card queenOfDiamonds = new Card(Suit.DIAMONDS, RankBisca.QUEEN);
	
	
	// cards
	
	void setUp() {
		human.startGame();
		computer.startGame();
		game = new Game(human, computer);
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
		
		game.playRound(); // human plays queen of hearts, computer plays two of hearts. human takes from deck ace of clubs, computer takes seven of clubs
		assertTrue(human.countPoints() == 2);
		assertTrue(computer.countPoints() == 0);
		
		game.playRound(); // human plays three of swords, computer plays queen of diamonds. human takes from deck king of clubs, computer takes jack of clubs
		assertTrue(human.countPoints() == 4);
		assertTrue(computer.countPoints() == 0);	
		
		game.playRound(); // human plays queen of swords, computer plays jack of clubs. human takes from deck queen of clubs, computer takes six of clubs
		assertTrue(human.countPoints() == 9);
		assertTrue(computer.countPoints() == 0);
		
		game.playRound(); //human plays ace of clubs, computer plays six of clubs. human takes from deck five of clubs, computer takes four of clubs
		assertTrue(human.countPoints() == 20);
		assertTrue(computer.countPoints() == 0);
		
		String winner = game.endGame();
		assertTrue(winner.equals(human.getName()));

	}
	
	@Test
	public void balancedGame(){
		
		setUp();
		human.receiveCard(queenOfDiamonds);
		human.receiveCard(threeOfSwords);
		human.receiveCard(queenOfSwords);
				
		computer.receiveCard(twoOfHearts);
		computer.receiveCard(aceOfDiamonds);
		computer.receiveCard(queenOfHearts);
		
		int compPoints = 0;
		int humanPoints = 0;
		
		// human cards: queen diamons, 2 hearts, queen swords
		// computer cards: 3 swords, ace diamonds, queen hearts
		game.playRound(); 
		// human plays queen of diamonds, computer plays ace of diamonds and wins
		assertTrue((compPoints = computer.countPoints()) == 13);
		assertTrue(humanPoints == 0);
		
		// computer cards: 2 hearts, queen hearts, ace clubs
		// human cards: 3 swords, queen swords, 7 clubs
		game.playRound(); 
		// computer plays random: 2 hearts, queen hearts or ace clubs, human plays 3 swords and wins
		assertTrue((humanPoints = human.countPoints()) == 0 || humanPoints == 2 || humanPoints == 11 );
		assertTrue(compPoints == computer.countPoints());
			
		// human cards: queen swords, 7 clubs, king clubs
		// computer cards: 2 hearts, queen hearts, jack clubs OR queen hearts, ace clubs, jack clubs OR 2 hearts, ace clubs, jack clubs
		game.playRound(); 
		// human plays queen swords, computer plays less valuable card: 2 hearts OR queen hearts
		int prevHumanPoints = humanPoints;
		assertTrue((humanPoints = human.countPoints()) == prevHumanPoints || humanPoints == prevHumanPoints + 2);
		assertTrue(computer.countPoints() == compPoints);
		
		// human cards: 7 clubs, king clubs, queen clubs
		// comp cards: SET1: queen hearts, jack clubs, 6 clubs OR SET2:ace clubs, jack clubs, 6 clubs
		game.playRound(); 
		// human plays 7 clubs. computer plays 6 clubs if SET1 or ace clubs if SET2
		boolean compPlaysAceAndWins = (human.countPoints() == humanPoints) && (computer.countPoints() == compPoints + 21);
		boolean compPlaysSixAndLoses = (human.countPoints() == humanPoints + 10) && (computer.countPoints() == compPoints);
		assertTrue(compPlaysAceAndWins || compPlaysSixAndLoses);
		
		String winner = game.endGame();
		assertTrue((compPlaysAceAndWins && winner.equals(computer.getName())) || (compPlaysSixAndLoses && winner.equals(human.getName())));
	}
	
	@Test
	public void computerWinsEveryRound(){
		
		setUp();
		
		human.receiveCard(threeOfSwords);	
		human.receiveCard(queenOfHearts);
		human.receiveCard(twoOfHearts);
		
		computer.receiveCard(queenOfDiamonds);
		computer.receiveCard(aceOfDiamonds);
		computer.receiveCard(queenOfSwords);
		
		int computerPoints = 0;
		
		// human cards: 3 swords, 2 hearts, queen hearts
		// comp cards: queen diamonds, ace diamonds, queen swords
		game.playRound(); 
		// human plays 3 swords, computer plays queen swords and wins
		assertTrue(human.countPoints() == 0);
		assertTrue(computer.countPoints() == 2);
		
		// comp cards: queen diamonds, ace diamonds, ace clubs
		// human cards: queen hearts, two hearts, 7 clubs 
		game.playRound(); 
		// computer plays random and wins because it plays first
		assertTrue(human.countPoints() == 0);
		assertTrue((computerPoints=computer.countPoints()) == 6 || computerPoints == 15);	
		
		// comp cards: queen diamonds, ace diamonds, king clubs OR ace diamonds, ace clubs, king clubs OR queen diamonds, ace clubs, king clubs
		// human cards: two hearts, 7 clubs, queen clubs
		game.playRound(); // computer plays random and wins because it plays first
		assertTrue(human.countPoints() == 0);
		assertTrue(computer.countPoints() > computerPoints);
		
		game.endGame();
		String winner = game.endGame();
		assertTrue(winner.equals(computer.getName()));
	}
	
	
}
