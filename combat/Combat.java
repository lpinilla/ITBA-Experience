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
	 * Gets the MainCharacter
	 * @return MainCharacter
	 */
	
	public MainCharacter getMainCharacter(){
		return this.mc;
	}
	
	/**
	 * Gets te Head of Chair
	 * @return HeadOfChair
	 */
	
	public HeadOfChair getHeadOfChair(){
		return this.hoc;
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
	 * Method to link the attack from one Fighter to another
	 * @param f1 the one attacking
	 * @param f2 the one being attacked
	 * @param a ability being used
	 */	
	public void fighterAttack(Fighter f1, Fighter f2, Abilities a){
		a.use(f1, f2);
	}
	public void fighterAttack(Fighter f1, CircularList<Fighter> f2, Abilities a) { //TODO
		a.use(f1, f2);
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
