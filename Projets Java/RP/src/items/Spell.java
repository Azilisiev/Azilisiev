package items;

public class Spell extends Item {
	private int manaCastCost;
	private int manaSustainCost;
	private SpellTypes type;
	private String actionAmount;
	
	public Spell(String name, int manaCastCost, int manaSustainCost, SpellTypes type, String actionAmount) {
		super(name, ItemClass.Spell, 1);
		this.manaCastCost = manaCastCost;
		this.manaSustainCost = manaSustainCost;
		this.type = type;
		this.setActionAmount(actionAmount);
	}

	public int getManaCastCost() {
		return manaCastCost;
	}

	public void setManaCastCost(int manaCastCost) {
		this.manaCastCost = manaCastCost;
	}

	public int getManaSustainCost() {
		return manaSustainCost;
	}

	public void setManaSustainCost(int manaSustainCost) {
		this.manaSustainCost = manaSustainCost;
	}

	public SpellTypes getSpellType() {
		return type;
	}

	public void setSpellType(SpellTypes type) {
		this.type = type;
	}

	public String getActionAmount() {
		return actionAmount;
	}

	public void setActionAmount(String actionAmount) {
		this.actionAmount = actionAmount;
	}

	
	

}
