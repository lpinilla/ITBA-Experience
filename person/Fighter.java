package person;

import map.Position2D;

/**
 * @author ividaurreta, aizaguirre
 */

public class Fighter extends Person {
    public static final int MIN_ATTACK = 0;
    public static final int MIN_DEFENSE = 0;
    public static final float EXP_PERCENTAGE = 0.25f;
    public static final int HP_PER_LEVEL = 200;
    public static final int WP_PER_LEVEL = 200;
    private int hp;
    private int currentHP;
    private int willPower;
    private int currentWillPower;
    private int attack;
    private int defense;
    private float exp;
    private int level;
    private float expToNextLevel;
    private Type type;
    private Position2D position;

    /**
     * Creates a new character
     *
     * @param name      Character's name
     * @param hp        Character's maximum hit points
     * @param willPower Character's maximun stamina, without stamina he can't attack
     * @param attack    Attack damage
     * @param defense   Defense damage
     * @param position  Determines character's position in the map
     * @param type      Character's type (Computer Science, Physics, Mathematics)
     */
    public Fighter(String name, int hp, int willPower, int attack, int defense
            , Position2D position, Type type) {
        super(name);
        this.hp = hp;
        this.currentHP = hp;
        this.willPower = willPower;
        this.currentWillPower = willPower;
        this.attack = attack;
        this.defense = defense;
        this.exp = 0.0f;
        this.level = 1;
        this.type = type;
        this.position = position;
        this.expToNextLevel = 1000;
    }


    public int getHP() {
        return hp;
    }

    /**
     * Changes the total HP of the character
     *
     * @return Total HP points of the character
     * @throws InvalidValueException If the HP entered is lower than 0
     */
    public void setHP(int hp) {
        if (hp <= 0) {
            throw new InvalidValueException("El HP debe ser mayor a cero.");
        }
        this.hp = hp;
    }

    public int getCurrentHP() {

        return currentHP;
    }

    /**
     * Private method. Recieves msg from healCharacter or recieveDamage
     * depending on what it's required to do.
     *
     * @param value Determines by what value the HP varies.
     *              - Value > 0 ==> called by healCharacter --> will heal the character
     *              - Value < 0 ==> called by recieveDamage --> will damage the character
     *              - Value = 0 ==> called by either, nothing happens
     */
    private void modifyCurrentHP(int value) {
        if (getCurrentHP() + value <= 0)
            currentHP = 0;
        else {
            if (getCurrentHP() + value >= getHP())
                currentHP = getHP();
            else
                currentHP += value;
        }
    }

    // TODO: Debería documentarlo?
    public void receiveDamage(int value) {
        if(value < 0)
            throw new InvalidValueException("El damage value no puede tener valor negativo.");
        modifyCurrentHP(-value);
    }

    // TODO: Debería documentarlo?
    public void healCharacter(int value) {
        if(value < 0)
            throw new InvalidValueException("El heal value no puede tener valor negativo.");
        modifyCurrentHP(value);
    }

    public int getWillPower() {
        return willPower;
    }

    /**
     * Modifies the character's stamina (named willPower)
     *
     * @param willPower
     * @throws InvalidValueException throws an exception if the willPower entered is lower than 0
     */
    public void setWillPower(int willPower) {
        if (willPower <= 0) {
            throw new InvalidValueException("La stamina debe ser mayor a cero"); //TODO
        }
        this.willPower = willPower;
    }

    public int getCurrentWillPower(){
        return currentWillPower;
    }

    public void modifyCurrentWillPower(int value){
        if(currentWillPower + value < 0)
            throw new NotEnoughWillPowerException("No hay suficiente Will Power");
        else if(currentWillPower + value >= willPower)
            currentWillPower = willPower;
        else
            currentWillPower += value;
    }


    public int getAttack() {
        return attack;
    }

    /**
     * Modifies attack value. If value < MIN_ATTACK changes attack to MIN_ATTACK
     *
     * @param value
     */
    public void modifyAttack(int value) {
        if (getAttack() + value <= MIN_ATTACK)
            attack = MIN_ATTACK;
        else
            attack += value;
    }

    public int getDefense() {
        return defense;
    }

    /**
     * Modifies defense value. If value < MIN_DEFENSE changes attack to MIN_DEFENSE
     *
     * @param value
     */
    public void modifyDefense(int value) {
        if (getDefense() + value <= MIN_DEFENSE)
            defense = MIN_DEFENSE;
        else
            defense += value;
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
        modifyCurrentWillPower(getWillPower());//WillPower fullfills after level up
        setHP(getHP() + HP_PER_LEVEL);
        modifyCurrentHP(getHP());//currentHP fullfils after level up
        healCharacter(getHP());
        setExpToNextLevel(getExpToNextLevel() + getExpToNextLevel() * EXP_PERCENTAGE);

    }

    public int getLevel() {
        return level;
    }

    public String getType() {
        return type.getName();
    }

    public float getExpToNextLevel() {
        return expToNextLevel;
    }

    private void setExpToNextLevel(float expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public boolean isKnockedOut() {
        return getCurrentHP() == 0;
    }

    public Position2D getPosition() {
        return position;
    }

    public void setPosition(Position2D pos){
        position=pos;
    }
}