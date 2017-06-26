package model.combat;

import model.persons.MainCharacter;
import model.persons.HeadOfChair;
import model.persons.Hero;
import model.persons.Enemy;
import model.persons.Fighter;
import model.abilities.Abilities;

/**
 *@author: lpinilla, ividaurreta
 */
public class Combat {
    private MainCharacter mc;
    private HeadOfChair hoc; //si hacemos alumnos, se cambia aca
    private boolean playerTurn, combatActive, stillAliveHero, stillAliveEnemy;

    /**
     * Creates a new Combat between mainC and HoC
     * @param mainC
     * @param hoc
     */
    public Combat(MainCharacter mainC, HeadOfChair hoc) {
        this.mc = mainC;
        this.hoc = hoc;
        this.playerTurn = combatActive = stillAliveHero = stillAliveEnemy = true;

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
//	public void fighterAttack(Fighter f1, CircularList<Fighter> f2, Abilities a) { //TODO
//		a.use(f1, f2);
//	}

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
        if(!this.playerTurn){
            this.machineAttack();
            nextTurn();
        }
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
  
    public Boolean isItFinished(){
        this.stillAliveHero = false;
        this.stillAliveEnemy = false;

        for(Hero h: mc.getParty()){
            if(h.getCurrentHP() > 0){
                this.stillAliveHero = true;
            }
        }
        stillAliveEnemy = hoc.isAlive();
        return (!stillAliveEnemy  || !stillAliveHero);
    }

    public void machineAttack(){

        Fighter f = null;

        if(!hoc.isKnockedOut()){
            for(Hero h : mc.getParty()){
                if(f == (null) || f.getCurrentHP() < h.getCurrentHP()){
                    f = h;
                }
            }
            hoc.getAbility().use(hoc, f);
        }
        for(int i = 1; i > Math.min(mc.getPartySize(), hoc.getPartySize()) &&
                this.isCombatActive(); i++){
            f = null;
            if(!(mc.getParty().get(i).isKnockedOut())){
                for(Enemy e : hoc.getParty()){
                    if(f == (null) || f.getCurrentHP() < e.getCurrentHP()){
                        f = e;
                    }
                }
                mc.getParty().get(i).getAbility().use(mc.getParty().get(i), f);
            }
            f = null;
            if(!(mc.getParty().get(i).isKnockedOut())){
                for(Hero h : mc.getParty()){
                    if(f == (null) || f.getCurrentHP() < h.getCurrentHP()){
                        f = h;
                    }
                }
                hoc.getParty().get(i).getAbility().use(hoc.getParty().get(i), f);
            }

        }
        for(int i = Math.min(mc.getPartySize(), hoc.getPartySize());
            i < Math.max(mc.getPartySize(), hoc.getPartySize())
                    && this.isCombatActive() ; i++){
            if(mc.getPartySize() > hoc.getPartySize()){
                if(!(mc.getParty().get(i).isKnockedOut())){
                    for(Hero h : mc.getParty()){
                        if(f == (null) || f.getCurrentHP() < h.getCurrentHP()){
                            f = h;
                        }
                    }
                    mc.getParty().get(i).getAbility().use(mc.getParty().get(i), f);
                }
            }else{
                if(!(hoc.getParty().get(i).isKnockedOut())){
                    for(Enemy e : hoc.getParty()){
                        if(f == (null) || f.getCurrentHP() < e.getCurrentHP()){
                            f = e;
                        }
                    }
                    hoc.getParty().get(i).getAbility().use(mc.getParty().get(i), f);
                }
            }
        }
    }

    public Boolean winner(){
        return !this.stillAliveEnemy;
    }

}
