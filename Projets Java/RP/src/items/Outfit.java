package items;

public class Outfit extends Item {
	private int armorPoints;

	public Outfit(String name, int amount, int armorPoints) {
		super(name, ItemClass.Outfit, amount);
		this.armorPoints = armorPoints;
	}

	public int getArmorPoints() {
		return armorPoints;
	}

	public void setArmorPoints(int armorPoints) {
		this.armorPoints = armorPoints;
	}
	

	
	
}
