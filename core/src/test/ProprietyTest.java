package test;

import org.junit.Test;

import logic.BuildingLot;
import logic.Group;
import logic.Player;
import logic.Propriety;
import logic.Service;
import logic.Stations;
import logic.effects.Effect;
import logic.effects.Move;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ProprietyTest {

	@Test
	public void testProprietyBuilder() {
		Group g1 = new Group("Red");
		int[] p1_rents= {20,100,250,500,1000};
		Propriety p1 = new BuildingLot("Porto", g1, 300, p1_rents);
		
		assertEquals(p1.getPrice(), 300);
		assertEquals(p1.getName(), "Porto");
		assertEquals(p1.isMortaged(), false);
		assertEquals(p1.getGroup(), new Group("Red"));

		p1.setPrice(200);
		assertEquals(200, p1.getPrice());

		p1.setMortaged(true);
		assertEquals(true, p1.isMortaged());

		p1.setName("Lisboa");
		assertEquals("Lisboa", p1.getName());

		Effect e = new Move(2);
		p1.setEffect(e);

		assertEquals(e, p1.getEffect());

		Group g2 = new Group("Blue");
		p1.setGroup(g2);
		assertEquals(new Group("Blue"), p1.getGroup());
	}

	@Test
	public void testGroupBuilder(){
		Group g1 = new Group("Red");

		g1.setName("Blue");
		assertEquals("Blue", g1.getName());

		g1.setHouseValue(100);
		assertEquals(100, g1.getHouseValue());
	}
	
	@Test
	public void testBuildingLotRents() {
		Group g1 = new Group("Red", 150);
		int[] p1_rents= {20,100,250,500,1000};
		BuildingLot p1 = new BuildingLot("Porto", g1, 300, p1_rents);
		BuildingLot p2 = new BuildingLot("Lisboa", g1, 300, p1_rents);
		
		Player player = new Player("Derp");
		player.buy(p1);
		assertEquals(p1.getRent(0), 20);			// rents[houses] with houses=0
		
		player.buy(p2);
		assertEquals(p1.getRent(0), 40);			// 2*rents[0]
		assertEquals(true, player.hasMonopoly(g1));

		assertEquals(true, p1.canAddHouse());
		p1.addHouse();
		assertEquals(1, p1.getHouses());
		assertEquals(p1.getRent(0), 100);			// rents[1]

		p1.setHouses(4);
		assertEquals(4, p1.getHouses());
		assertEquals(false, p1.canAddHouse());

		int[] rents = {1,2,3,4,5};
		p1.setRents(rents);
		assertEquals(rents, p1.getRents());
	}

	@Test
	public void testServiceRents(){
		Group g1 = new Group("Red", 150);
		Service p1 = new Service("Tax1",g1,-100);
		Service p2 = new Service("Tax2",g1, 200);

		assertEquals(0, p1.getRent(0));

		Player player = new Player("Derp");
		p1.setOwner(player);
		p1.setSingleMultiplier(1);

		assertEquals(1, p1.getSingleMultiplier());
		assertEquals(2, p1.getRent(2));

		p2.setOwner(player);
		p2.setMonopolyMultiplier(2);

		assertEquals(2, p1.getMonopolyMultiplier());
		assertEquals(4, p1.getRent(2));
	}
	@Test
	public void testStationsRents(){
		Group g1 = new Group("Red", 150);
		Stations p1 = new Stations("Station1",g1,100);
		Stations p2 = new Stations("Station2",g1,200);

		assertEquals(0, p1.getRent(0));

		p1.setBaseCost(10);
		assertEquals(10, p1.getBaseCost());

		Player player = new Player("Derp");
		p1.setOwner(player);
		p2.setOwner(player);

		assertEquals(20, p1.getRent(0));


	}


}
