package handlers;

import items.Weapon;

public class ItemHandler {
	
	public int rollWeaponDamage(Weapon weapon) {

		return Randomizer.weaponRoll(weapon.getDamage());
	}

}
