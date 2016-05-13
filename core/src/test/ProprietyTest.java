package test;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.BuildingLot;
import logic.Group;
import logic.Player;
import logic.Propriety;

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
	}
	
	@Test
	public void testBuildingLotRents() {
		Group g1 = new Group("Red", 150);
		int[] p1_rents= {20,100,250,500,1000};
		Propriety p1 = new BuildingLot("Porto", g1, 300, p1_rents);
		Propriety p2 = new BuildingLot("Lisboa", g1, 300, p1_rents);
		
		Player player = new Player("Derp");
		player.buy(p1);
		assertEquals(p1.getRent(0), 20);
		assertFalse(p1.canAddHouse());
		
		player.buy(p2);
		assertEquals(p1.getRent(0), 40);
		//assertTrue(p1.canAddHouse());
		
		
		((BuildingLot)p1).addHouse();
		assertEquals(p1.getRent(0), 100);
	}
	
	//Other Rents

}
