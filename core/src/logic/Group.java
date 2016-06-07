package logic;

import java.util.ArrayList;

/**
 * Represents an group of proprieties.
 * Keeps the list of proprieties and the house development cost if applicable.
 */
public class Group {
	private String name;
	private ArrayList<Propriety> proprieties;
	private int houseValue;

	/**
	 * Creates a new group (without houseValue) - for services and stations
	 * @param name - name of the group
     */
	public Group(String name) {
		this(name,0);
	}

	/**
	 * Creates a new group (with houseValue) - for building lots
	 * @param name - name of the group
	 * @param houseValue - value of each house
     */
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


	/**
	 * Adds a new propriety to group
	 * @param propriety - propriety to be added
	 */
	public void addPropriety(Propriety propriety){
		this.proprieties.add(propriety);
	}

	/**
	 * Compares two groups
	 * @param obj - object to be compared to this object
	 * @return true if name is equal, false if otherwise
     */
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Group &&
				this.name.equals(((Group)obj).name);
	}

}
