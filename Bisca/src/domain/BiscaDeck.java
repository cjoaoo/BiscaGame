package domain;

import java.util.ArrayList;
import java.util.EnumSet;

public class BiscaDeck extends Deck{

	// constructor
	public BiscaDeck() {
		super();
		ArrayList<Card> cards = new ArrayList<>();
		for(SuitEnum s : SuitEnum.values()) {
			EnumSet.allOf(RankBiscaEnum.class).forEach(r -> cards.add(new Card(s, r)));
		}
		setDeck(cards);
	}
}
