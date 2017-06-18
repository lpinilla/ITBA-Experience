
package person;

import map.Position2D;
import java.util.LinkedList;
import java.io.Serializable:

public class MainCharacter extends Hero implements Serializable{
    
    public static final int PARTY_MAX = 3;

    private CircularList<Hero> party;
    private LinkedList<Abilities> abilities;
    private int partyIndex;

    /**
     * Creates a new MainCharacter. Starts no party member nor specialAbilities
     * @param name
     * @param HP
     * @param willPower
     * @param attack
     * @param defense
     * @param position
     * @param type
     */
    public MainCharacter(String name, int HP, int willPower, int attack, int defense
            , Position2D position, Type type, Abilities specialAbility){
        super(name, HP, willPower, attack, defense, position, type, specialAbility);
        party = new CircularList<Hero>();
        abilities = new LinkedList<Abilities>(); //Cuantos lugares? -> va a ser una constante
        partyIndex=0;
    }

    /**
     * Checks if MainCharacter's party is full or not
     * @return boolean, true or false.
     */
    public Boolean isPartyFull() {
        return party.size() == PARTY_MAX;
    }

    /**
     * Checks whether MainCharacter can learn any new ability or not
     * @return boolean, true or false
     */
    public Boolean isAbilitesFull() {
        //Supongo que abilities funciona como party, tiene maximo 5 abilities
        return abilities.size() == 5;
    }

    /**
     * Adds Hero h to the party if possible
     * @param h
     * @throws WrongHeroException if h is not of type Hero //TODO: ESTO ESTA BIEN? NO HABRÍA QUE SACARLO?
      * @throws FullPartyException if isPartyFull() == true
     */
    public void addHeroToParty(Hero h) {

        if ( h == null ) throw new WrongHeroException("Hero is null, can't add");
        if (isPartyFull()) throw new FullPartyException("Your party is full, can't add");
        party.add(h);
    }
    
    public void addMeToParty(){
    	party.add(this);
    }

    /**
     * Adds a new special ability for the MainCharacter to use
     * @param ability
     * @throws WrongAbilityException if the ability is not of type Abilities TODO: VER ESTO
     */
    public void addSpecialAbility(Abilities ability) {
        if ( ability == null ) throw new WrongAbilityException("Ability is null");
        abilities.add(ability);
    }

    /**
     * Removes the first Occurrance of ch1 it founds from the party
     * @param ch1
     * @return boolean: true if its able to remove ch1 false if ch1 isn't in the party
     */
    public boolean removeHeroFromParty(Hero ch1) {
        return party.remove(ch1);
    }


    public CircularList<Hero> getParty() {
        return party;
    }

    public int getPartySize() {
        return party.size();
    }

    public Hero getHero() {
    if (partyIndex == party.size())
            partyIndex=0;
        return party.get(partyIndex++);
    }
}
