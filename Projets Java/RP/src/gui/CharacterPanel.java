package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class CharacterPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Actioner actioner;
	
	
	
	public CharacterPanel(String name) {
		super();
		this.name = name;
		setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
	}
	
	public CharacterPanel(characters.Character character) {
		super();
		name = character.getName();
		setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
