package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import characters.Entity;
import inventory.Inventory;
import items.Item;

public class InventoryPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -214883628805529189L;
	private Inventory inventory;
	private String name;
	private Actioner actioner;

	public InventoryPanel(Inventory inventory, String name, Actioner actioner) {
		super();
		this.inventory = inventory;
		this.name = name;
		this.actioner = actioner;

		setBackground(Color.lightGray);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		for (Entry<String, Item> entry : inventory.getItems().entrySet()) {
			String key = entry.getKey();
			Item value = entry.getValue();

			add(new ItemPanel(value,actioner));

		}
	}

}
