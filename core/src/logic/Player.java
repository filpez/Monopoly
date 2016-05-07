package logic;

import java.util.ArrayList;

public class Player {
	private String name;
	private int funds;
	private ArrayList<Propriety> proprieties;
	private int position;
	
	public Player(String name) {
		super();
		this.name = name;
		this.funds = 0;
		this.proprieties = new ArrayList<Propriety>();
		this.position = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFunds() {
		return funds;
	}

	public void setFunds(int funds) {
		this.funds = funds;
	}
	
	public void pay(int funds) {
		this.funds -= funds;
	}
	
	public void receive(int funds) {
		this.funds += funds;
	}

	public ArrayList<Propriety> getProprieties() {
		return proprieties;
	}

	public void setProprieties(ArrayList<Propriety> proprieties) {
		this.proprieties = proprieties;
	}
	
	public boolean canBuy(Propriety propriety){
		if (propriety.getPrice() <= funds)
			return true;
		return false;
	}
	
	public void buy(Propriety propriety, int price, Player player){
		propriety.setOwner(this);
		player.proprieties.remove(propriety);
		this.proprieties.add(propriety);
		
		this.pay(price);
		player.receive(price);
	}
	
	public void buy(Propriety propriety){
		int price = propriety.getPrice();
		propriety.setOwner(this);
		this.proprieties.add(propriety);
		this.pay(price);
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean hasMonopoly(Group group) {
		for (Propriety propriety: group.getProprieties())
			if (propriety.getOwner() != this)
				return false;
		return true;
	}

	public int getNumberOfProprieties(Group group) {
		int n = 0;
		for (Propriety propriety: group.getProprieties())
			if (propriety.getOwner() == this)
				n++;
		return n;
	}

	
	
}
