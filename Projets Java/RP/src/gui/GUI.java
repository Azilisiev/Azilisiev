package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.*;

import characters.Character;
import characters.Entity;
import effects.Effect;
import effects.EffectsName;
import game.GameState;
import inventory.Inventory;
import iohandlers.FileManager;
import items.Item;
import items.ItemClass;
import items.Outfit;
import items.Pet;
import items.Spell;
import items.SpellTypes;
import items.Weapon;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameState game = new GameState(new Actioner(this, ""));
	private JPanel content = new JPanel();
	private ArrayList<String> charaList = new ArrayList<String>();
	private FileManager filemanager;

	public GUI(String path) {
		super("RP du bled 0.1");
		filemanager = new FileManager(path);
		ArrayList<Character> characters = filemanager.getCharacterLines();
		content.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};

		addWindowListener(l);
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenDimension.width, screenDimension.height);
		setContentPane(content);
		// JButton entityBouton = new JButton("add Entity");
		JButton characterBouton = new JButton("add Character");
		characterBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addEntity(promptCharacter());
				refresh();

			}
		});

		JButton tickButton = new JButton("Tick");
		tickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				game.tick();

			}
		});

		JPanel panneau = new JPanel();
		panneau.add(characterBouton);
		panneau.add(tickButton);
		content.add(panneau);
		for (Character character : characters) {
			addEntity(character);
			refresh();
		}
		setVisible(true);
	}

	public Item promptItem() {

		String s = (String) JOptionPane.showInputDialog(null, "Item Data : ", "Customized Dialog",
				JOptionPane.PLAIN_MESSAGE, null, null, null);

//If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			String[] itemData = s.split(";");
			ItemClass IC = ItemClass.valueOf(itemData[2]);

			switch (IC) {
			case Outfit: {

				return new Outfit(itemData[1], 1, Integer.parseInt(itemData[4]));
			}
			case Weapon: {

				return new Weapon(itemData[1], 1, itemData[4], itemData[5]);
			}
			case Pet: {

				return new Pet(itemData[1], Integer.parseInt(itemData[3]));
			}
			case Spell: {

				return new Spell(itemData[1], Integer.parseInt(itemData[3]),
						Integer.parseInt(itemData[4]), SpellTypes.valueOf(itemData[5]), itemData[6]);
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + IC);
			}

		}

		return new Item("ah", null, 0);

	}

	public Effect promptSE() {

		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("Enter effect name: \n");
		String name = reader.next();

		System.out.println("Enter effect amount: \n");
		int amount = reader.nextInt();

		reader.close();

		return new Effect(EffectsName.valueOf(name), amount);
	}

	public Character promptCharacter() {
		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("Full List ? : y/n \n");
		String full = reader.next();
		String stats = "";
		String name = "";
		int hp = 0;
		int maxHp = 0;
		int INT = 0;
		int AGI = 0;
		int STR = 0;
		int mana = 0;
		int manaMax = 0;
		int manaRegen = 0;
		boolean player = false;

		if (full.equals("y")) {

			System.out.println("Enter character name: \n");
			name = reader.next();

			System.out.println("Enter character hp: \n");
			hp = reader.nextInt();

			System.out.println("Enter character maxHp: \n");
			maxHp = reader.nextInt();

			System.out.println("Enter character int: \n");
			INT = reader.nextInt();

			System.out.println("Enter character agi: \n");
			AGI = reader.nextInt();

			System.out.println("Enter character str: \n");
			STR = reader.nextInt();

			System.out.println("Enter character mana: \n");
			mana = reader.nextInt();

			System.out.println("Enter character manamax: \n");
			manaMax = reader.nextInt();

			System.out.println("Enter character mana regen: \n");
			manaRegen = reader.nextInt();
		} else {
			System.out.println("Enter character stats: \n");
			stats = reader.next();
			String statList[] = stats.split(";");
			name = statList[0];
			hp = Integer.parseInt(statList[1]);
			maxHp = Integer.parseInt(statList[2]);
			INT = Integer.parseInt(statList[3]);
			AGI = Integer.parseInt(statList[4]);
			STR = Integer.parseInt(statList[5]);
			mana = Integer.parseInt(statList[6]);
			manaMax = Integer.parseInt(statList[7]);
			manaRegen = Integer.parseInt(statList[8]);
			if (Integer.parseInt(statList[9]) == 1) {
				player = true;
			}
		}
		characters.Character newCharacter = new characters.Character(name, hp, maxHp, INT, AGI, STR, mana, manaMax,
				manaRegen, player);

		reader.close();
		return newCharacter;
	}

	public void paint() {
		HashMap<String, Entity> entities = game.getEntities();
		ArrayList<String> presentEntities = new ArrayList<String>();

		for (Map.Entry<String, Entity> entry : entities.entrySet()) {
			String key = entry.getKey();
			Entity value = entry.getValue();
			presentEntities.add(key);
			if (!charaList.contains(key)) {
				addNewCharacterTab(key, value);
				charaList.add(key);
			}
		}
		for (String name : charaList) {
			if (!presentEntities.contains(name)) {
				removeCharacterTab(name);
				charaList.remove(name);
			}
		}

	}

	public void refresh() {
		paint();
		invalidate();
		validate();
		repaint();
	}

	private void removeCharacterTab(String name) {
		Entity entity = game.getEntities().get(name);
		if (entity instanceof Character) {
			Character character = (Character) entity;
			if (character.getPlayer()) {
				JOptionPane.showConfirmDialog(this, "You can't delete players","info message",
						JOptionPane.DEFAULT_OPTION);
			} else {
				int retour = JOptionPane.showConfirmDialog(this, "are you sure you want to delete " + name, "confirm deletion",
						JOptionPane.OK_CANCEL_OPTION);
				if (retour == 0) {
					Component[] componentList = content.getComponents();
					for (Component c : componentList) {
						if (c instanceof CharacterPanel) {
							CharacterPanel panel = (CharacterPanel) c;
							if (panel.getName() == name) {
								content.remove(c);
								filemanager.removeCharacter((Character) game.getEntities().get(name));
							}
						}
					}
					refresh();
				}

			}
		}


	}

	public void refreshCharacterTab() {
		Component[] componentList = content.getComponents();
		for (Component c : componentList) {
			if (c instanceof CharacterPanel) {
				CharacterPanel CP = (CharacterPanel) c;
				Character value = (Character) game.getEntities().get(CP.getName());

				addNewCharacterTab(value.getName(), value);
				content.remove(c);
			} else if (c instanceof InventoryPanel || c instanceof SEPanel) {
				content.remove(c);
			}

		}
		refresh();

	}

	private void addNewCharacterTab(String key, Entity value) {

		JButton removeButton = new JButton("X");
		JButton saveButton = new JButton("save");
		JButton addSE = new JButton("Add Status Effect");
		JButton addItem = new JButton("Add Item");
		StatPanel statPanel = new StatPanel("HP", value.getHP(), value.getMaxHp(), new Actioner(this, value.getName()));
		JLabel label = new JLabel(key);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCharacterTab(key);
				paint();
			}

		});
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveCharacter(key);
				paint();
			}

		});
		addSE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Effect effect = promptSE();
				Entity entity = game.getEntities().get(key);
				Character character = (Character) entity;
				character.addEffect(effect.getName(), effect.getAmount());
				game.addEntity(character);
				refreshCharacterTab();
			}

		});

		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = promptItem();
				Entity entity = game.getEntities().get(key);
				Character character = (Character) entity;
				character.addItem(item);
				game.addEntity(character);
				refreshCharacterTab();
			}

		});
		CharacterPanel panneau = new CharacterPanel(key);
		// panneau.setLayout(new BorderLayout());
		panneau.add(label);
		panneau.add(removeButton);
		panneau.add(saveButton);
		panneau.add(addSE);
		panneau.add(addItem);
		panneau.add(statPanel);

		if (value instanceof Character) {
			Character character = (Character) value;
			StatPanel manaStatPanel = new StatPanel("mana", character.getMana(), character.getManaMax(),
					new Actioner(this, character.getName()));
			JLabel INT = new JLabel("Inteligence : " + character.getINT());
			JLabel AGI = new JLabel("Agillity : " + character.getAGI());
			JLabel STR = new JLabel("Strength : " + character.getSTR());
			JLabel AP = new JLabel("ArmorPoints : " + character.getAP());

			panneau.add(manaStatPanel);
			panneau.add(INT);
			panneau.add(AGI);
			panneau.add(STR);
			panneau.add(AP);

		}

		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		panneau.setPreferredSize(new Dimension(screenDimension.width - 20, 73));
		panneau.setBackground(Color.lightGray);
		content.add(panneau);
		content.add(newInventoryPanel(key));
		content.add(newSEPanel(key));
	}

	private Component newSEPanel(String key) {
		Entity entity = game.getEntities().get(key);
		Character character = (Character) entity;

		return new SEPanel(character.getStatusEffects(), character.getName(), new Actioner(this, character.getName()));
	}

	private Component newInventoryPanel(String key) {
		Entity entity = game.getEntities().get(key);
		Character character = (Character) entity;

		return new InventoryPanel(character.getInventory(), character.getName(),
				new Actioner(this, character.getName()));
	}

	private void saveCharacter(String key) {
		Character character = (Character) game.getEntities().get(key);
		filemanager.saveCharacter(character);

	}

	public void addEntity(Entity entity) {
		game.addEntity(entity);

	}

	public void removeEntity(Entity entity) {
		if (entity instanceof Character) {
			Character charcater = (Character) entity;
			if (!charcater.getPlayer()) {
				getGame().removeEntity(entity);
				charaList.remove(entity.getName());
			} else {
				JOptionPane.showInputDialog(null, "You can't delete a player", "info message",
						JOptionPane.PLAIN_MESSAGE, null, null, null);
			}
		}

	}

	public void modifyStat(String characterName, String name, int quantity) {
		Character character = (Character) game.getEntities().get(characterName);
		if (name.equals("HP")) {
			int hp = character.getHP();
			int maxHP = character.getMaxHp();
			if (hp + quantity > maxHP) {
				character.setHP(maxHP);
			} else if (hp + quantity < 0) {
				character.setHP(0);
			} else {
				character.addHP(quantity);
			}

		} else if (name.equals("mana")) {
			int mana = character.getMana();
			int maxMana = character.getManaMax();
			if (mana + quantity > maxMana) {
				character.setMana(maxMana);
			} else if (mana + quantity < 0) {
				character.setMana(0);
			} else {
				character.addMana(quantity);
			}
		}
		game.getEntities().put(characterName, character);

	}

	public void setStat(String characterName, String name, int quantity) {
		Character character = (Character) game.getEntities().get(characterName);
		if (name.equals("HP")) {
			character.setHP(quantity);
		} else if (name.equals("mana")) {
			character.setMana(quantity);
		}
		game.getEntities().put(characterName, character);

	}

	public GameState getGame() {
		return game;
	}

	public JPanel getContent() {
		return content;
	}

	public void removeItem(String characterName, String name) {
		HashMap<String, Entity> entities = game.getEntities();
		for (Map.Entry<String, Entity> entry : entities.entrySet()) {
			String key = entry.getKey();
			Entity value = entry.getValue();
			if (key.equals(characterName)) {
				Character character = (Character) value;
				character.removeItem(name);
				game.addEntity(character);
			}
		}
		refreshCharacterTab();
	}

	public void removeEffect(String characterName, String name) {
		HashMap<String, Entity> entities = game.getEntities();
		for (Map.Entry<String, Entity> entry : entities.entrySet()) {
			String key = entry.getKey();
			Entity value = entry.getValue();
			if (key.equals(characterName)) {
				Character character = (Character) value;
				HashMap<EffectsName, Integer> effects = character.getStatusEffects();
				effects.remove(EffectsName.valueOf(name));
				character.setStatusEffects(effects);
				game.addEntity(character);
			}
		}
		refreshCharacterTab();
	}

	public void useSpell(String characterName, String name) {
		HashMap<String, Entity> entities = game.getEntities();
		for (Map.Entry<String, Entity> entry : entities.entrySet()) {
			String key = entry.getKey();
			Entity value = entry.getValue();
			if (key.equals(characterName)) {
				Character character = (Character) value;
				Spell spell = (Spell) character.getInventory().getItem(name);
				int currentMana = character.getMana();
				int castCost = spell.getManaCastCost();
				SpellTypes SP = spell.getSpellType();
				if(currentMana - castCost >= 0) {
					character.addMana(-castCost);
				}
				
				switch (SP) {
				case Attack: {
					String s = (String) JOptionPane.showInputDialog(null, "target name : ", "Customized Dialog",
							JOptionPane.PLAIN_MESSAGE, null, null, null);
					String damageData[] = spell.getActionAmount().split("[");
					
					break;
				}
				case Fire: {
					
					break;
				}
				case ManaBuff: {
					
					break;
				}
				case ManaDebuff: {
					
					break;
				}
				case ManaSteal: {
					
					break;
				}
				case Misc: {
					
					break;
				}
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + SP);
				}
				
				game.addEntity(character);
			}
		}
		refreshCharacterTab();
	}

}
