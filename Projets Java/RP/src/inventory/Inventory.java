package inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import characters.Entity;
import items.Item;
import items.Outfit;

public class Inventory {
	private HashMap<String, Item> items = new HashMap<String, Item>();

	public HashMap<String, Item> getItems() {
		return items;
	}

	public void setItems(HashMap<String, Item> items) {
		this.items = items;
	}
	
	public Item getItem(String key) {
		return items.get(key);
	}
	
	public void addItem(Item item) {
		items.put(item.getName(), item);
	}
	
	public void removeItem(String key) {
		items.remove(key);
	}
	
	public int getAP() {
		int AP = 0;
		for (Entry<String, Item> entry : items.entrySet()) {
			String key = entry.getKey();
			Item value = entry.getValue();
			if (value instanceof Outfit) {
				Outfit outfit = (Outfit) value;
				AP+= outfit.getArmorPoints();
			}
		}
		return AP;
	}

	


}
