package logic;

/**
 * Represents a space on the board.
 *
 * Keeps the name of the Space
 */
public abstract class Space {
	private String name;

	public Space(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
