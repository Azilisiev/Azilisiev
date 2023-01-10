package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import effects.EffectsName;

public class EffectPanel extends JPanel {
	
	private String name;
	private Actioner actioner;

	public EffectPanel(EffectsName key, Integer value, Actioner actioner) {
		super();
		name = key.toString();
		this.actioner = actioner;
		add(new JLabel(key.toString() + " : " + value));
		JButton deleteButton = new JButton("X");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retour = JOptionPane.showConfirmDialog(null, "are you sure you want to delete " + name, "confirm deletion",
						JOptionPane.OK_CANCEL_OPTION);
				if (retour == 0) {
					actioner.removeEffect(name);
				}
			}
		});
		add(deleteButton);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
