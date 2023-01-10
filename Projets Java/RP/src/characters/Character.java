package characters;

import java.util.HashMap;

import effects.EffectsName;
import inventory.Inventory;
import items.Item;

public class Character extends Entity {
	private int INT;
	private int AGI;
	private int STR;
	private int mana;
	private int manaMax;
	private int manaRegen;
	private int AP;
	private Inventory inventory = new Inventory();
	private HashMap<EffectsName,Integer> statusEffects = new HashMap<EffectsName,Integer>();
	private boolean player;
	
	public Character(String name, int hP, int maxHP, int iNT, int aGI, int sTR, int mana, int manaMax, int manaRegen, boolean player) {
		super(name, hP, maxHP);
		INT = iNT;
		AGI = aGI;
		STR = sTR;
		this.mana = mana;
		this.manaMax = manaMax;
		this.manaRegen = manaRegen;
		this.player = player;
	}
	
	public Character(String name, int hP, int iNT, int aGI, int sTR, int mana, int manaMax, int manaRegen) {
		super(name, hP, hP);
		INT = iNT;
		AGI = aGI;
		STR = sTR;
		this.mana = mana;
		this.manaMax = manaMax;
		this.manaRegen = manaRegen;
	}

	public int getINT() {
		return INT;
	}

	public void setINT(int iNT) {
		INT = iNT;
	}

	public int getAGI() {
		return AGI;
	}

	public void setAGI(int aGI) {
		AGI = aGI;
	}

	public int getSTR() {
		return STR;
	}

	public void setSTR(int sTR) {
		STR = sTR;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public void addMana(int quantity) {
		mana = mana + quantity;
		
	}

	public int getManaMax() {
		return manaMax;
	}

	public void setManaMax(int manaMax) {
		this.manaMax = manaMax;
	}

	public int getManaRegen() {
		return manaRegen;
	}

	public int getAP() {
		return AP;
	}

	public void setAP(int aP) {
		AP = aP;
	}
	
	public void addAP(int aP) {
		AP = AP+aP;
	}

	public void setManaRegen(int manaRegen) {
		this.manaRegen = manaRegen;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public HashMap<EffectsName, Integer> getStatusEffects() {
		return statusEffects;
	}

	public void setStatusEffects(HashMap<EffectsName, Integer> statusEffects) {
		this.statusEffects = statusEffects;
	}

	public void addEffect(EffectsName effectsName, int amount) {
		statusEffects.put(effectsName,amount);
	}
	
	public void removeEffect(String effect) {
		statusEffects.remove(effect);
	}
	
	public void addItem(Item item) {
		inventory.addItem(item);
		setAP(inventory.getAP());
	}
	
	public void removeItem(String key) {
		inventory.removeItem(key);
		setAP(inventory.getAP());
	}

	public Boolean getPlayer() {
		return player;
	}

	public void setPlayer(Boolean player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Character [INT=" + INT + ", AGI=" + AGI + ", STR=" + STR + ", mana=" + mana + ", manaMax=" + manaMax
				+ ", manaRegen=" + manaRegen + ", AP=" + AP + ", inventory=" + inventory + "]";
	}
	
	


	
	
	

}
