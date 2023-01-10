package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;

import effects.EffectsName;

public class SEPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -269862250297918749L;
	private String name;
	private Actioner actioner;

	public SEPanel(HashMap<EffectsName,Integer> SElist, String name, Actioner actioner) {
		super();
		this.name  = name;
		this.actioner = actioner;

		setBackground(Color.lightGray);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		for (Entry<EffectsName, Integer> entry : SElist.entrySet()) {
			EffectsName key = entry.getKey();
			Integer value = entry.getValue();

			add(new EffectPanel(key, value, actioner));

		}
	}

}
