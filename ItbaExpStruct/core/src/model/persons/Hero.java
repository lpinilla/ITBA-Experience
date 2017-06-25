package model.persons;

import model.abilities.Abilities;
import model.map.Position2D;
import java.io.Serializable;

public class Hero extends Fighter implements Serializable {
    private int level;
    private float exp;
    private float expToNextLevel;
    public static final float EXP_PERCENTAGE = 0.25f;
    /**
     * Secondary characters, they will be part of the MainCharacter's party. Extends Fighter
     * @param name
     * @param HP
     * @param willPower
     * @param attack
     * @param defense
     * @param pos
     * @param type
     * @param ability
     */
    public Hero(String name, int HP, int willPower, int attack, int defense
            , Position2D pos, Type type, Abilities ability){
        super(name, HP, willPower, attack, defense, pos, type, ability);
        this.level = 1;
        this.exp = 0.0f;
        this.expToNextLevel = 1000;
    }

    public float getExp() {
        return exp;
    }

    /**
     * The character earns experience. If he gains enough, he levels up until
     * he doesn't have enough to level up.
     *
     * @param exp
     */
    public void addExp(float exp) {
        if(exp <= 0)
            throw new InvalidValueException("No se puede ganar experiencia negativa o cero");
        else {
            this.exp += exp;
            while (this.exp >= getExpToNextLevel()) {
                levelUp();
            }
        }
    }

    /**
     * Called by addExp: boosts Attack, Defense, willPower, HP and heals the character
     */
    private void levelUp() {
        level++;
        modifyAttack(10);
        modifyDefense(10);
        setWillPower(getWillPower() + WP_PER_LEVEL);
        modifyCurrentWillPower(getWillPower());//WillPower fulfills after level up
        setHP(getHP() + HP_PER_LEVEL);
        healCharacter(getHP());//currentHP fulfils after level up
        setExpToNextLevel(getExpToNextLevel() + getExpToNextLevel() * EXP_PERCENTAGE);

    }

    public float getExpToNextLevel() {
        return expToNextLevel;
    }

    private void setExpToNextLevel(float expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public int getLevel() {
        return level;
    }

}