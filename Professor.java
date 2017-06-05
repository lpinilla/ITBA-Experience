package model;

public class Professor extends Enemy {
	private float rewardExp;
	private int hp;
    private int currentHp;
    private int willPower;
    private int currentWillPower;
    private int attack;
    private int defense;
    private int level;
    
    
    public Enemy(int hp, int willPower, int attack, int defense, float rewardExp)
    {
    	this.hp = hp;
        currentHp = hp;
        this.willPower = willPower;
        currentWillPower = willPower;
        this.attack = attack;
        this.defense = defense;
        this.rewardExp = rewardExp;
    }
    
    
}
