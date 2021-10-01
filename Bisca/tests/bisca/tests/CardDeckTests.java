package bisca.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import biscagame.domain.BiscaDeck;
import biscagame.domain.Card;
import biscagame.domain.Deck;
import biscagame.domain.RankBisca;
import biscagame.domain.Suit;

class CardDeckTests {
	
	Card twoOfHearts = new Card(Suit.HEARTS, RankBisca.TWO);
	Card queenOfSwords = new Card(Suit.SWORDS, RankBisca.QUEEN);
	Card queenOfHearts = new Card(Suit.HEARTS, RankBisca.QUEEN);
	Card aceOfDiamonds = new Card(Suit.DIAMONDS, RankBisca.ACE);
	ArrayList<Card> listCards = new ArrayList<>();
	
	String listaCartas = "Two of Hearts, Queen of Swords, Queen of Hearts, Ace of Diamonds";
	
	String biscaCards = "Two of Swords, Three of Swords, Four of Swords, Five of Swords, Six of Swords, "
			+ "Queen of Swords, Jack of Swords, King of Swords, Seven of Swords, Ace of Swords, "
			+ "Two of Hearts, Three of Hearts, Four of Hearts, Five of Hearts, Six of Hearts, "
			+ "Queen of Hearts, Jack of Hearts, King of Hearts, Seven of Hearts, Ace of Hearts, "
			+ "Two of Diamonds, Three of Diamonds, Four of Diamonds, Five of Diamonds, Six of Diamonds, "
			+ "Queen of Diamonds, Jack of Diamonds, King of Diamonds, Seven of Diamonds, Ace of Diamonds, "
			+ "Two of Clubs, Three of Clubs, Four of Clubs, Five of Clubs, Six of Clubs, "
			+ "Queen of Clubs, Jack of Clubs, King of Clubs, Seven of Clubs, Ace of Clubs";


	private void setUp() {
		
		listCards.add(twoOfHearts);
		listCards.add(queenOfSwords);
		listCards.add(queenOfHearts);
		listCards.add(aceOfDiamonds);
	}
	
	@Test
	void cardCreationAndToStringTest() {
		
		assertEquals(twoOfHearts.toString(), "Two of Hearts");
		assertEquals(queenOfSwords.toString(), "Queen of Swords");
		assertEquals(aceOfDiamonds.toString(), "Ace of Diamonds");
	}
	
	@Test
	void rankComparisonTest() {
		
		assertTrue(twoOfHearts.compareRank(queenOfSwords) < 0);
		assertTrue(queenOfHearts.compareRank(queenOfSwords) == 0);
		assertTrue(aceOfDiamonds.compareRank(queenOfSwords) > 0);
		
		assertTrue(aceOfDiamonds.isHigherRank(queenOfSwords));
		assertFalse(queenOfHearts.isHigherRank(queenOfSwords));
		
		assertTrue(aceOfDiamonds.isHigherRank(RankBisca.KING));
		assertFalse(queenOfHearts.isHigherRank(RankBisca.KING));
	}
	
	@Test
	void suitComparisonTest() {	
		
		assertTrue(twoOfHearts.isSameSuit(queenOfHearts));
		assertFalse(queenOfHearts.isSameSuit(queenOfSwords));
		
		Suit trionfi = Suit.HEARTS;
		assertTrue(twoOfHearts.isTrionfi(trionfi));
		assertFalse(queenOfSwords.isTrionfi(trionfi));
	}
	
	@Test
	void valuableTest() {
		
		assertTrue(queenOfHearts.isValuable());
		assertFalse(twoOfHearts.isValuable());
		
	}
	
	@Test
	void deckCreationTest() {
		
		setUp();

		Deck d = new Deck();
		assertTrue(d.isEmpty());
		
		d.setDeck(listCards);
		assertTrue(d.size() == 4);
		
		BiscaDeck bd = new BiscaDeck();
		assertTrue(bd.size() == 40);		
		assertTrue(biscaCards.equals(bd.toString()));
	}
	
	@Test
	void shuffleTest() {
		
		BiscaDeck bd = new BiscaDeck();	
		assertTrue(biscaCards.equals(bd.toString()));
		
		bd.shuffle();
		assertTrue(!biscaCards.equals(bd.toString()));
	}
	
	@Test
	void TakePutCardsTest() {
		
		setUp();
		Deck d = new Deck();
		d.setDeck(listCards);	
		assertTrue(listaCartas.equals(d.toString()));
		
		Card trunfo = d.takeCard();
		assertTrue(trunfo.equals(aceOfDiamonds));
		assertTrue(d.size() == 3);
		
		d.putCardBottom(trunfo);
		assertTrue(d.toString().startsWith(trunfo.toString()));
		assertTrue(d.size() == 4);
		
	}
	

}
