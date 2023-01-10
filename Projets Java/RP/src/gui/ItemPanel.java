package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import characters.Character;
import items.Item;
import items.Outfit;
import items.Spell;
import items.Weapon;

public class ItemPanel extends JPanel {
	private String name;
	private Actioner actioner;

	public ItemPanel(Item value, Actioner actioner) {
		super();
		this.name = value.getName();
		this.actioner = actioner;
		add(new JLabel(name));
		if (value instanceof Weapon) {
			Weapon weapon = (Weapon) value;
			add(new JLabel(weapon.getDamage() + " dmg"));
		}
		if (value instanceof Outfit) {
			Outfit outfit = (Outfit) value;
			if (outfit.getArmorPoints() > 0) {
				add(new JLabel(String.valueOf(outfit.getArmorPoints()) + " AP"));
			}

		}
		JButton deleteButton = new JButton("X");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retour = JOptionPane.showConfirmDialog(null, "are you sure you want to delete " + name,
						"confirm deletion", JOptionPane.OK_CANCEL_OPTION);
				if (retour == 0) {
					actioner.removeItem(name);
				}
			}
		});
		if (value instanceof Spell) {
			JButton useSpellButton = new JButton("Use");
			useSpellButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					actioner.useSpell(name);
				}
			});
		}
		add(deleteButton);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
