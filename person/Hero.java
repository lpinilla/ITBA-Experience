package person;

import map.Position2D;

public class Hero extends Fighter{
    /**
     * Secondary characters, they will be part of the MainCharacter's party. Extends Fighter
     * @param name
     * @param HP
     * @param willPower
     * @param attack
     * @param defense
     * @param pos
     * @param type
     */
    public Hero(String name, int HP, int willPower, int attack, int defense
            , Position2D pos, Type type){
        super(name, HP, willPower, attack, defense, pos, type);
    }

}