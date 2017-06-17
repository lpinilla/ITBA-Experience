package person;

import map.Position2D;
import java.util.LinkedList;

public class HeadOfChair extends Enemy {

    public static int PARTY_MAX = 3;
    private CircularList<Enemy> party;
    Abilities specialAbility;
    private int partyIndex;

    /**
     * Creates a new HeadOfChair (main enemy)
     * @param name
     * @param hp
     * @param willPower
     * @param attack
     * @param defense
     * @param position
     * @param type  Specifies what type the enemy is
     * @param level Specifies enemy's level
     * @param rewardExp How much experience beating this enemy will it reward
     * @param specialAbility What special ability this enemy has
     */
    public HeadOfChair(String name, int hp, int willPower,
                 int attack, int defense, Position2D position,
                 Type type, int level, float rewardExp, Abilities specialAbility){

        super(name, hp, willPower, attack, defense, position, type, level, rewardExp);
        this.specialAbility = specialAbility;
        party = new CircularList<>();
        partyIndex=0;
    }

    public Abilities getSpecialAbility(){
        return specialAbility;
    }

    /**
     * Checks if the enemy party is full or not
     * @return boolean, true or false
     */
    public boolean isPartyFull(){
        return party.size() == PARTY_MAX;
    }

    /**
     * Add Professor prof to the party
     * @param prof
     * @throws FullPartyException if isPartyFull() return true
     */
    public void addProfessorToParty(Enemy prof){
        if (isPartyFull()) throw new FullPartyException("Party full, can't add");
        party.add(prof);
    }


    public CircularList<Enemy> getParty() {
        return party;
    }

    public int getPartySize() {
        return party.size();
    }

    public Enemy getEnemy() {
        if (partyIndex == party.size())
            partyIndex = 0;
        return party.get(partyIndex++);
    }
}