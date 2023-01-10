package iohandlers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import characters.Character;
import characters.Entity;
import effects.EffectsName;
import gui.CharacterPanel;
import items.Item;
import items.ItemClass;
import items.Outfit;
import items.Pet;
import items.Spell;
import items.SpellTypes;
import items.Usable;
import items.Weapon;

public class FileManager {
	private String path;
	private String content;

	public FileManager(String path) {
		super();
		this.path = path;
		try {
			content = Files.readString(Path.of(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("file not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String readFile() {
		try {
			content = Files.readString(Path.of(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;

	}

	public void writeFile(String text) {
		try {

			byte[] strToBytes = text.getBytes();

			Files.write(Path.of(path), strToBytes);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void writeFileAppend(String text) {
		try {

			byte[] strToBytes = (content + text).getBytes();

			Files.write(Path.of(path), strToBytes);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void displayContent() {
		System.out.println(content);
	}

	public ArrayList<Character> getCharacterLines() {
		ArrayList<Character> characters = new ArrayList<Character>();
		readFile();
		String name;
		int currentHealth;
		int maxHealth;
		int INT;
		int AGI;
		int STR;
		int mana;
		int maxMana;
		int manaRegen;
		boolean player = false;

		String[] lines = content.split("Items");
		String[] characterLines = lines[0].split("\n");
		String[] stuffLines = lines[1].split("Status Effects");
		String[] itemsLines = stuffLines[0].split("\n");
		String[] seLines = stuffLines[1].split("\n");
		for (String line : characterLines) {
			line = line.replace("\n", "").replace("\r", "");
			if (!line.isBlank() && !line.equals("Characters")) {
				String[] infos = line.split(";");
				name = infos[0];
				currentHealth = Integer.parseInt(infos[1]);
				maxHealth = Integer.parseInt(infos[2]);
				INT = Integer.parseInt(infos[3]);
				AGI = Integer.parseInt(infos[4]);
				STR = Integer.parseInt(infos[5]);
				mana = Integer.parseInt(infos[6]);
				maxMana = Integer.parseInt(infos[7]);
				manaRegen = Integer.parseInt(infos[8]);
				manaRegen = Integer.parseInt(infos[8]);
				if (Integer.parseInt(infos[9]) == 1) {
					player = true;
				}
				Character character = new Character(name, currentHealth, maxHealth, INT, AGI, STR, mana, maxMana,
						manaRegen, player);
				for (String items : itemsLines) {
					String[] itemInfos = items.split(";");
					if (itemInfos[0].equals(name)) {
						Item item;
						ItemClass IC = ItemClass.valueOf(itemInfos[2]);
						switch (IC) {
						case Outfit: {
							item = new Outfit(itemInfos[1], Integer.parseInt(itemInfos[3]),
									Integer.parseInt(itemInfos[4]));
							break;
						}
						case Weapon: {
							item = new Weapon(itemInfos[1], Integer.parseInt(itemInfos[3]), itemInfos[4], itemInfos[5]);
							break;
						}
						case Spell: {
							item = new Spell(itemInfos[1], Integer.parseInt(itemInfos[3]),
									Integer.parseInt(itemInfos[4]), SpellTypes.valueOf(itemInfos[5]), itemInfos[6]);
							break;
						}
						case Usable: {
							item = new Usable(itemInfos[1], Integer.parseInt(itemInfos[2]), itemInfos[3]);
							break;
						}

						case Pet: {
							item = new Pet(itemInfos[1], Integer.parseInt(itemInfos[3]));
							break;
						}
						default:
							throw new IllegalArgumentException("Unexpected value: " + IC);
						}
						character.addItem(item);
					}
				}

				for (String se : seLines) {

					String[] seInfos = se.split(";");
					if (seInfos[0].equals(name)) {

						EffectsName EN = EffectsName.valueOf(seInfos[1]);
						switch (EN) {
						case Fire: {
							character.addEffect(EffectsName.Fire, Integer.parseInt(seInfos[2]));
							break;
						}
						case Heal: {
							character.addEffect(EffectsName.Heal, Integer.parseInt(seInfos[2]));
							break;
						}
						case HealProgressive: {
							character.addEffect(EffectsName.HealProgressive, Integer.parseInt(seInfos[2]));
							break;
						}
						case Mana: {
							character.addEffect(EffectsName.Mana, Integer.parseInt(seInfos[2]));
							break;
						}

						case ManaRegen: {
							character.addEffect(EffectsName.ManaRegen, Integer.parseInt(seInfos[2]));
							break;
						}
						case ManaRegenAndMana: {
							character.addEffect(EffectsName.ManaRegen, Integer.parseInt(seInfos[2]));
							character.addEffect(EffectsName.Mana, Integer.parseInt(seInfos[3]));
							break;
						}
						case Poison: {
							character.addEffect(EffectsName.Poison, Integer.parseInt(seInfos[2]));
							break;
						}
						default:
							throw new IllegalArgumentException("Unexpected value: " + EN);
						}
					}
				}
				characters.add(character);
			}
		}

		return characters;
	}

	public void saveCharacter(Character character) {

		String[] lines = content.split("Items");
		String[] characterLines = lines[0].split("\n");
		String[] itemLines = lines[1].split("\n");
		String newContent = "";
		String itemContent = "";
		int index = 0;
		boolean found = false;
		for (String line : characterLines) {
			if (!line.isBlank()) {
				String[] infos = line.split(";");
				if (character.getName().equals(infos[0])) {
					line = stringifyCharacter(character);
					found = true;
				}
				newContent = newContent + line;
			}
			newContent = newContent + "\n";
			index++;

		}
		if (!found) {
			newContent = newContent + stringifyCharacter(character);

		}
		for (String line : itemLines) {
			if (!line.isBlank()) {
				String[] infos = line.split(";");
				if (!character.getName().equals(infos[0])) {
					itemContent = itemContent + line + "\n";
				}

			}
		}
		HashMap<String, Item> items = character.getInventory().getItems();
		HashMap<EffectsName, Integer> effects = character.getStatusEffects();

		for (Map.Entry<String, Item> entry : items.entrySet()) {
			String key = entry.getKey();
			Item value = entry.getValue();
			itemContent = stringifyItem(value, character.getName()) + itemContent;

		}
		for (Entry<EffectsName, Integer> entry : effects.entrySet()) {
			EffectsName key = entry.getKey();
			Integer value = entry.getValue();
			itemContent = itemContent + stringifyEffect(key.toString(), value, character.getName());

		}
		// System.out.println(newContent);
		// System.out.println(itemContent);
		writeFile(newContent + "Items\n" + itemContent);

	}

	public String stringifyCharacter(Character character) {

		return character.getName() + ";" + character.getHP() + ";" + character.getMaxHp() + ";" + character.getINT()
				+ ";" + character.getAGI() + ";" + character.getSTR() + ";" + character.getMana() + ";"
				+ character.getManaMax() + ";" + character.getManaRegen();
	}

	public String stringifyItem(Item item, String characterName) {
		ItemClass IC = item.getItemClass();
		switch (IC) {
		case Outfit: {
			Outfit outfit = (Outfit) item;
			return characterName + ";" + outfit.getName() + ";" + outfit.getItemClass().toString() + ";"
					+ outfit.getAmount() + ";" + outfit.getArmorPoints() + "\n";
		}
		case Weapon: {
			Weapon weapon = (Weapon) item;
			return characterName + ";" + weapon.getName() + ";" + weapon.getItemClass().toString() + ";"
					+ weapon.getAmount() + ";" + weapon.getDamage() + ";" + weapon.getStat() + "\n";
		}
		case Spell: {
			return characterName + ";" + item.getName() + ";" + item.getItemClass().toString() + ";" + item.getAmount();
		}
		case Usable: {
			return characterName + ";" + item.getName() + ";" + item.getItemClass().toString() + ";" + item.getAmount();
		}

		case Pet: {
			Pet pet = (Pet) item;
			return characterName + ";" + pet.getName() + ";" + pet.getItemClass().toString() + ";" + pet.getHp() + "\n";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + IC);
		}

	}

	public String stringifyEffect(String effectName, Integer value, String characterName) {
		return characterName + ";" + effectName + ";" + value + "\n";
	}

	public void removeCharacter(Character character) {
		String[] lines = content.split("Items");
		String[] characterLines = lines[0].split("\n");
		String newContent = "";
		int index = 0;
		boolean found = false;
		for (String line : characterLines) {
			if (!line.isBlank()) {
				String[] infos = line.split(";");
				if (character.getName().equals(infos[0])) {
					line = "";
					found = true;
				}
				newContent = newContent + line;
			}
			if (!found) {
				newContent = newContent + "\n";
			}

			index++;

		}
		if (!found) {
			newContent = newContent + stringifyCharacter(character);
		}
		writeFile(newContent + "Items" + lines[1]);

	}

}
