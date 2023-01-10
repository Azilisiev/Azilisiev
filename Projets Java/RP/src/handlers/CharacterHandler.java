package handlers;

import characters.Entity;
import inventory.Inventory;
import characters.Character;
import items.Item;
import items.ItemClass;
import items.Outfit;
import items.Weapon;

public class CharacterHandler {
	
	public void addItemToInventory(Character character,Item item) {
		if(item.getItemClass()==ItemClass.Outfit) {
			Outfit outfit = (Outfit) item;
			character.addAP(outfit.getArmorPoints());
		}
		Inventory inventory = character.getInventory();
		inventory.addItem(item);
	}
	
	public void removeItemFromInventory(Character character,Item item) {
		if(item.getItemClass()==ItemClass.Outfit) {
			Outfit outfit = (Outfit) item;
			character.addAP(-outfit.getArmorPoints());
		}
		Inventory inventory = character.getInventory();
		inventory.removeItem(item.getName());
	}
	
	public void removeItemFromInventory(Character character,String itemName) {
		Inventory inventory = character.getInventory();
		Item item = inventory.getItem(itemName);
		if(item.getItemClass()==ItemClass.Outfit) {
			Outfit outfit = (Outfit) item;
			character.addAP(-outfit.getArmorPoints());
		}
		inventory.removeItem(itemName);
	}
	
	
	
}
