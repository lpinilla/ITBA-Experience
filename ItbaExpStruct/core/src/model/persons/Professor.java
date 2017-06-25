package model.persons;

import model.abilities.Abilities;
import model.map.Position2D;


public class Professor extends Enemy {
    /**
     * Secondary enemies: they will be part of the HeadOfChair's party
     * @param name
     * @param hp
     * @param willPower
     * @param attack
     * @param defense
     * @param position What position will the professor appear in.
     * @param type same as enemy
     * @param level
     * @param rewardExp same as enemy
     */
    public Professor(String name, int hp, int willPower,
                     int attack, int defense, Position2D position,
                     Type type, int level, float rewardExp, Abilities ability){
        super(name, hp, willPower, attack, defense, position, type, level, rewardExp, ability);

    }
}
