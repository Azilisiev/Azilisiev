package items;

public class Weapon extends Item {
	private String damage;
	private String stat;

	
	



	public Weapon(String name, int amount, String damage, String stat) {
		super(name, ItemClass.Weapon, amount);
		this.damage = damage;
		this.stat = stat;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
