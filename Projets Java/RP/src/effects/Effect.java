package effects;

public class Effect {
	private EffectsName name;
	private int amount;
	
	public Effect(EffectsName name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	public EffectsName getName() {
		return name;
	}
	public void setName(EffectsName name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

}
