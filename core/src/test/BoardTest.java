package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import logic.board.Board;
import logic.board.BuildingLot;
import logic.board.Card;
import logic.board.Deck;
import logic.board.GoToJailSpace;
import logic.board.Group;
import logic.board.Player;
import logic.board.Property;
import logic.board.Service;
import logic.board.Space;
import logic.board.Stations;
import logic.board.TransactionSpace;
import logic.effects.Effect;
import logic.effects.Move;

import static org.junit.Assert.assertEquals;


public class BoardTest {
	private Board getBoard(){
		ArrayList<Player> players = new ArrayList<Player>();
		Player p1 = new Player("yandere");
		Player p2 = new Player("tsundere");

		players.add(p1);
		players.add(p2);

		Space[] s = new Space[40];
		s[0] = new Service("Service1", new Group("Red"), 100);
		Arrays.fill(s, s[0]);

		int[] rents = {1,2,3,4,5};

		s[1] = new BuildingLot("Madrid", new Group("Blue"), 200, rents);
		s[2] = new Stations("Paris", new Group("Green"), 300);
		s[3] = new TransactionSpace("Tax1",-200);
		s[4] = new GoToJailSpace("Jail");
		s[5] = new TransactionSpace("Tax2",200);

		ArrayList<Card>cards_ch = new ArrayList<Card>();
		ArrayList<Card>cards_co = new ArrayList<Card>();
		Card move = new Card("Move 3 positions backwards", "Move", 3);
		Card goTo = new Card("Go to position 0", "GoTo", 0);
		cards_ch.add(move);
		cards_ch.add(goTo);
		cards_co.add(goTo);

		Deck chance = new Deck(cards_ch);
		Deck community = new Deck(cards_co);

		return new Board(players,s,community,chance);
	}

	@Test
	public void testBoardBuilder(){
		ArrayList<Player> players = new ArrayList<Player>();
		Space[] s = new Space[40];

		Board board = new Board(players,s);

		assertEquals(s[0], board.getSpaces()[0]);
		assertEquals(players, board.getPlayers());

		ArrayList<Player> p = new ArrayList<Player>();
		Player p1 = new Player("yandere");
		p.add(p1);

		board.setPlayers(p);
		assertEquals(p, board.getPlayers());

		ArrayList<Card>ch = new ArrayList<Card>();
		Deck chance = new Deck(ch);
		Card move = new Card("Move 3 positions backwards", "Move", 3);
		ch.add(move);
		chance.setCards(ch);

		board.setChance(chance);
		assertEquals(chance, board.getChance());

		ArrayList<Card>co = new ArrayList<Card>();
		Deck community = new Deck(co);
		Card goTo = new Card("Go to position 0", "GoTo", 0);
		co.add(goTo);

		board.setCommunity(community);
		assertEquals(community, board.getCommunity());

		Space[] spaces = new Space[40];
		spaces[0] = new Service("Service1", new Group("Red"), 100);

		board.setSpaces(spaces);
		assertEquals(spaces[0], board.getSpaces()[0]);
	}

	@Test
	public void testPlayerBuilder(){
		Player p1 = new Player("loli");

		assertEquals("loli", p1.getName());
		assertEquals(1500, p1.getFunds());
		assertEquals(0, p1.getPosition());

		p1.setName("tsundere");
		assertEquals("tsundere",p1.getName());

		p1.setFunds(2000);
		assertEquals(2000, p1.getFunds());

		p1.setPosition(10);
		assertEquals(10, p1.getPosition());

		ArrayList<Property> p= new ArrayList<Property>();
		p.add(new Service("Water works", new Group("Service"),100));

		p1.setProprieties(p);
		assertEquals(p, p1.getProprieties());
	}

	@Test
	public void testPlayerMovement() {
		//Test if the players move correctly.

		Board board = getBoard();
		board.setCurrentPlayer(board.getPlayers().get(0));

		board.move(-2,false);
		assertEquals(38, board.getCurrentPlayer().getPosition());	// current player = index 0
		board.endTurn();

		board.move(41,false);
		assertEquals(1, board.getCurrentPlayer().getPosition());	// current player = index 1
		board.endTurn();

		board.move(4,false);
		assertEquals(2, board.getCurrentPlayer().getPosition());	// current player = index 0
	}

	@Test
	public void testBuyingAndSellingAPropriety() {
		//Test if the players buys a property correctly.

		Board board = getBoard();
		board.setCurrentPlayer(board.getPlayers().get(0));
		Player p1 = board.getCurrentPlayer();

		int funds_p1 = p1.getFunds();
		Property property = (Property)board.getSpace(0);
		int price = property.getPrice();

		assertEquals(true, p1.canBuy(property));
		p1.buy(property);

		assertEquals(funds_p1-price,p1.getFunds());
		assertEquals(p1, property.getOwner());
		assertEquals(true,p1.getProprieties().contains(property));
		assertEquals(1, p1.getNumberOfProprieties(property.getGroup()));

		funds_p1 = funds_p1-price;
		p1.sell(property);

		assertEquals(funds_p1+price/2, p1.getFunds());
		assertEquals(null, property.getOwner());
		assertEquals(false,p1.getProprieties().contains(property));
	}

	@Test
	public void testEffect() {
		//Test various effects from cards or spaces, example: Go to Jail, Pay.

		Board board = getBoard();
		board.setCurrentPlayer(board.getPlayers().get(0));

		Effect effect = new Move(0);
		effect.setValue(2);

		assertEquals(2, effect.getValue());

		// GoToJail
		board.getCurrentPlayer().setPosition(4);			// Go To Jail Space
		board.applyCurrentSpaceEffect();

		assertEquals(10,board.getCurrentPlayer().getPosition());
		assertEquals(true, board.getCurrentPlayer().isArrested());

		// Move
		assertEquals(false, board.move(-10,false));				// player cannot move because he is arrested
		assertEquals(true, board.move(-10,true));				// doubles get player out of prison
		assertEquals(0, board.getCurrentPlayer().getPosition());// player is able to move now

		Card move = board.getChance().getCards().get(0);		// makes player move 3 positions
		move.getEffect().apply(board);

		assertEquals(3, board.getCurrentPlayer().getPosition());

		// PayTax
		board.getCurrentPlayer().setPosition(3);				// Transaction Space

		int funds = board.getCurrentPlayer().getFunds();		// current player funds
		int price = board.getSpace(3).getEffect().getValue();	// pay
		board.applyCurrentSpaceEffect();

		assertEquals(funds-price,board.getCurrentPlayer().getFunds());

		board.getCurrentPlayer().setPosition(6);

		funds = board.getCurrentPlayer().getFunds();			// current player funds
		price = board.getSpace(6).getEffect().getValue();		// receive
		board.applyCurrentSpaceEffect();

		assertEquals(funds-price,board.getCurrentPlayer().getFunds());

		// PayRent
		Property property = (Property)board.getSpace(1);

		assertEquals(0, ((Property) board.getSpace(1)).getRent(0));	// space has no owner

		board.getCurrentPlayer().buy(property);
		board.endTurn();

		funds = board.getCurrentPlayer().getFunds();
		int rent = ((Property) board.getSpace(1)).getRent(0);			// BuildingLot
		// rent = 2*rents[0]
		board.getCurrentPlayer().setPosition(1);
		board.applyCurrentSpaceEffect();

		assertEquals(funds-rent,board.getCurrentPlayer().getFunds());

		// GoTo
		Card goTo = board.getChance().getCards().get(1);
		goTo.getEffect().apply(board);

		assertEquals(0, board.getCurrentPlayer().getPosition());
	}

	@Test
	public void testLose() {
		//Test losing conditions.
		Board b = getBoard();
		b.setCurrentPlayer(b.getPlayers().get(0));

		b.getCurrentPlayer().pay(b.getCurrentPlayer().getFunds()+50);
		assertEquals(true, b.getCurrentPlayer().isBankrupt());

		assertEquals(2, b.getPlayers().size());
		b.endTurn();							// player that lost is removed from the game
		assertEquals(1, b.getPlayers().size());
	}
}