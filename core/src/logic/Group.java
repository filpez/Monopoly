package logic;

import java.util.ArrayList;

public class Group {
	private String name;
	private ArrayList<Propriety> proprieties;
	private int houseValue;
		
	public Group(String name) {
		this(name,0);
	}
	
	public Group(String name, int houseValue) {
		super();
		this.name = name;
		this.houseValue = houseValue;
		this.proprieties = new ArrayList<Propriety>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Propriety> getProprieties() {
		return proprieties;
	}
	public void setProprieties(ArrayList<Propriety> proprieties) {
		this.proprieties = proprieties;
	}
	public int getHouseValue() {
		return houseValue;
	}
	public void setHouseValue(int houseValue) {
		this.houseValue = houseValue;
	}
	public void addPropriety(Propriety propriety){
		this.proprieties.add(propriety);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Group &&
				this.name.equals(((Group)obj).name);
	}

}
