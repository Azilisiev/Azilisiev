package characters;

public class Entity {
	private String name;
	private int HP;
	private int maxHp;
	
	public Entity(String name, int hP, int maxHP) {
		super();
		this.name = name;
		HP = hP;
		maxHp = maxHP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}
	
	public void addHP(int hP) {
		HP = HP+hP;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	
	
	
	

	
}
