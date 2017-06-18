package combat;

import person.*;
import java.util.ArrayList;

/**
*@author: lpinilla, ividaurreta
*/
public class Combat {
	private MainCharacter mc;
	private HeadOfChair hoc; //si hacemos alumnos, se cambia aca
	private boolean playerTurn, combatActive;

	/**
	 * Creates a new Combat between mainC and HoC
	 * @param mainC
	 * @param hoc
	 */
	public Combat(MainCharacter mainC, HeadOfChair hoc) {
		this.mc = mainC;
		this.hoc = hoc;
		this.playerTurn = combatActive = true;
	}

	/**
	 * Checks if it's the players turn
	 * @return true or false
	 */
	public boolean isPlayerTurn() {
		return this.playerTurn;
	}

	/**
	 * Changes to next hero
	 * @return
	 */
	public Hero getNextHero() {
		return mc.getHero();
	}

	/**
	 * Changes to next enemy
	 * @return
	 */
	public Enemy getNextSubEnemy() {
		return hoc.getEnemy();
	}

	/**
	 * Gets the MainCharacter
	 * @return MainCharacter
	 */
	public MainCharacter getPlayer() {
		return this.mc;
	}

	/**
	 * Gets te Head of Chair
	 * @return HeadOfChair
	 */
	public HeadOfChair getEnemy() {
		return this.hoc;
	}

	/**
	 * Method to link the attack from one Fighter to another
	 * @param f1 the one attacking
	 * @param f2 the one being attacked
	 * @param a ability being used
	 */	
	public void fighterAttack(Fighter f1, Fighter f2, Abilities a){
		f1.modifyCurrentWillPower(-a.getWillCost());
		f1.attackTo(f2, a.getDamage());
	}
	

	/**
	 * is called if an ally casts a spell to heal or drinks a potion
	 * @param f fighter being healed
	 * @param amount amount being healed
	 */
	public void fighterHeal(Fighter f, int amount) {
		f.healCharacter(amount);
	}

	// ??
	public void partyAbility(Fighter f, MultiTargetAbility a) { //TODO
		Fighter fig;
		if (playerTurn) { //medio imperativo, es para saber sobre que party se efectua
			for (int i = 0; i < this.mc.getPartySize(); i++) {
				fig = mc.getHero();
				//fig.useAbility(a); TODO Use ability?
			}
		} else {
			for (int i = 0; i < this.hoc.getPartySize(); i++) {
				fig = hoc.getEnemy();
				//fig.useAbility(a); TODO
			}
		}
	}

	/**
	 * Changes turn
	 */
	public void nextTurn() {
		this.playerTurn = !this.playerTurn;
	}

	/**
	 * Checks if the Combat has ended
	 * @return true or false
	 */
	public boolean isCombatActive() {
		return this.combatActive;
	}

	/**
	 * Ends combat
	 */
	public void endCombat() {
		this.combatActive = false;
	}


}
