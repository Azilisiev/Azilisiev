package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import characters.Character;
import characters.Entity;
import effects.EffectsName;
import gui.Actioner;

public class GameState {
	private HashMap<String, Entity> entities = new HashMap<String, Entity>();
	private ArrayList<String> deadEntities = new ArrayList<String>();
	private Actioner actioner;

	public GameState(Actioner actioner) {
		super();
		this.actioner = actioner;
	}

	public void tick() {
		for (Map.Entry<String, Entity> entry : entities.entrySet()) {
			String key = entry.getKey();
			Entity value = entry.getValue();
			if (value.getHP() <= 0) {
				if(value instanceof Character) {
					Character character  = (Character) value;
					if(!character.getPlayer()) {
						deadEntities.add(key);
					}
				}
				else {
					deadEntities.add(key);
				}				
			}
			if (value instanceof Character) {
				Character character = (Character) value;
				int currentMana = character.getMana();
				int maxMana = character.getManaMax();
				int manaRegen = character.getManaRegen();
				if (currentMana + manaRegen > maxMana) {
					character.setMana(maxMana);
				} else if (currentMana + manaRegen <= 0) {
					character.setMana(0);
				} else {
					character.addMana(character.getManaRegen());
				}

				for (Map.Entry<EffectsName, Integer> characterSE : character.getStatusEffects().entrySet()) {
					EffectsName effectName = characterSE.getKey();
					int effectAmount = characterSE.getValue();
					EffectsName EN = effectName;
					if (effectAmount > 0) {
						currentMana = character.getMana();
						maxMana = character.getManaMax();
						switch (EN) {

						case Fire:

							value.addHP(-effectAmount);
							effectAmount--;

							break;

						case Heal:

							value.addHP(effectAmount);
							effectAmount = 0;

							break;

						case HealProgressive:

							value.addHP(effectAmount);
							effectAmount--;

							break;

						case Mana:

							character.setManaMax(effectAmount);

							break;
							
						case ManaRegen:
							
							if(currentMana + effectAmount > maxMana){
								character.setMana(maxMana);
							}
							else if(currentMana - effectAmount <= 0){
								character.setMana(0);
								
							}
							else {
								character.addMana(effectAmount);
							}
							

							break;
							
						case ManaRegenProgressive:

							character.addMana(effectAmount);
							effectAmount--;

							break;
							
						case Poison:

							character.addHP(-effectAmount);
							effectAmount --;

							break;
						case Charmed:
							character.addMana(-effectAmount);
							break;
						case Charming:
							character.addMana(effectAmount);

						default:
						}
						character.getStatusEffects().put(effectName, effectAmount);
					}
					else
					{
						removeEffect(character,effectName);
						
					}

					

				}
				entities.put(character.getName(), character);
			}

		}
		for (String name : deadEntities) {
			entities.remove(name);
		}
		actioner.refreshPanels();
	}

	private void removeEffect(Character character, EffectsName effectName) {
		if(effectName == EffectsName.Mana) {
			int amount  = character.getStatusEffects().get(effectName);
			character.setManaMax(character.getManaMax()-amount);
			
		}
		character.getStatusEffects().remove(effectName);
		
	}

	public void addEntity(Entity entity) {
		entities.put(entity.getName(), entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity.getName());
	}

	public void removeEntity(String entityName) {
		entities.remove(entityName);
	}

	public HashMap<String, Entity> getEntities() {
		return entities;
	}

	public void setEntities(HashMap<String, Entity> entities) {
		this.entities = entities;
	}

}
