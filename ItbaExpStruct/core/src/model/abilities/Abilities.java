package model.abilities;

/**
 * Created by agustin on 20/06/17.
 */
import model.persons.Fighter;

import java.io.Serializable;
public class Abilities implements Serializable {

    private String name;
    private int damage;
    private int willCost;
    private int turnsActive;

    public Abilities(String name, int damage, int willCost, int turnsActive){
        this.name = name;
        this.damage = damage;
        this.willCost = willCost;
        this.turnsActive = turnsActive;
    }

    public String getName(){
        return name;
    }

    public int getDamage(){
        return damage;
    }

    public int getWillCost(){
        return willCost;
    }

    public void use(Fighter f1, Fighter f2) {
        f1.modifyCurrentWillPower(-this.getWillCost());
        f2.receiveDamage(this.getDamage());
    }

    public void use(Fighter f1) {
        //curarse o regenerar willcost?
    }
}
