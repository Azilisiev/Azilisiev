package items;

public class Pet extends Item {
	private int hp;
	public Pet(String name, int hp) {
		super(name, ItemClass.Pet, 1);
		this.hp = hp;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}

}
