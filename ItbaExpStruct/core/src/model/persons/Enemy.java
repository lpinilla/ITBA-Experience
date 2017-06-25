package model.persons;

import model.abilities.Abilities;
import model.map.Position2D;

public abstract class Enemy extends Fighter {
    private float rewardExp;
    private int level;

    public Enemy(String name, int hp, int willPower,
                 int attack, int defense, Position2D position,
                 Type type, int level, float rewardExp, Abilities ability)
    {
        super(name, hp, willPower, attack, defense, position, type, ability);
        this.rewardExp = rewardExp;
        this.level = level;
    }

    public int getLevel(){
        return level;
    }

    public float getRewardExperience(){
        return rewardExp;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null) return false;
        if (!(this.getClass().equals(o.getClass()))) return false;
        Enemy other = (Enemy) o;

        if (this.getName().equals(other.getName())
                && this.getLevel() == other.getLevel()){
            return true;
        }
        return false;
    }
}
