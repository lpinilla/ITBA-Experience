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

    /*
        Use an ability to damage a rival, it affects an enemy's hp and the 
        character's will power  
    */
    public void use(Fighter f1, Fighter f2) {
        f1.modifyCurrentWillPower(-this.getWillCost());
        f2.receiveDamage(this.getDamage());
    }

    public void use(Fighter f1) {
        //curarse o regenerar willcost?
    }
    
    public boolean equals(Object o){
        if (o == null || o.getClass() != this.getClass()){
            return false;
        }
        if( o == this){
            return true;
        }
        Abilities a = (Abilities) o;
        return this.getName().equals(a.getName())
                && this.getDamage() == a.getDamage()
                && this.getWillCost() == a.getWillCost();
    }
}