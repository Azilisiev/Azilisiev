package items;

public class Usable extends Item {
	private String action;

	public Usable(String name, int amount, String action) {
		super(name, ItemClass.Usable, amount);
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	

}
