package gui;

public class Actioner {
	private GUI gui;
	private String characterName;

	public Actioner(GUI gui, String characterName) {
		super();
		this.gui = gui;
		this.characterName = characterName;
	}
	
	void refreshGUI () {
		gui.refresh();
	}

	public void modifyCharacterStat(String name, int quantity) {

			gui.modifyStat(characterName,name,quantity);
			gui.refresh();
		
	}
	
	public void setCharacterStat(String name, int quantity) {

		gui.setStat(characterName,name,quantity);
		gui.refresh();
	
}
	
	
	
	public void refreshPanels() {
		gui.refreshCharacterTab();
	}

	public void removeItem(String name) {
		gui.removeItem(characterName,name);
		gui.refresh();
		
	}

	public void removeEffect(String name) {
		gui.removeEffect(characterName,name);
		gui.refresh();
		
	}

	public void useSpell(String name) {
		gui.useSpell(characterName,name);
		gui.refresh();
	}
	 

}
