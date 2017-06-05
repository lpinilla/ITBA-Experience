package model;

public abstract class Enemy extends Fighter{
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
    public int getHp(){
        return hp;
    }

    public void setHp(int value){
        if(value < getHp())
            throw new InvalidHpValueException("El valor deberia ser mayor al hp."); 
            //Podríamos hacer InvalidValueException para que sea más gral y podamos reutilizarla (*)
        else {
            hp = value;
            setCurrentHp(value);
        }
    }

    public int getCurrentHp(){
        return currentHp;
    }

    private void setCurrentHp(int value){
        if(currentHp + value <= 0)
            currentHp = 0;
        else if(currentHp + value >= hp)
            currentHp = hp;
        else
            currentHp += value;
    }

    public void receiveDamage(int value){
        if(value < 0)
            throw new InvalidDamageValueException("No puede pasarse un valor negativo.");
        else
            setCurrentHp(-value);
    }

    public int getWillPower(){
        return willPower;
    }

    public void setWillPower(int value){
        if(value < getWillPower())
            throw new InvalidWillPowerValueException("El valor deberia ser mayor al willPower.");
            //(*)Acá lo podríamos reutilizar por ejemplo
        else {
            willPower = value;
            setCurrentWillPower(value);
        }
    }

    public int getCurrentWillPower(){
        return currentWillPower;
    }

    public void setCurrentWillPower(int value){
        if(currentWillPower + value <= 0)
            currentWillPower = 0;
        else if(currentWillPower + value >= willPower)
            currentWillPower = willPower;
        else
            currentWillPower += value;
    }

    public int getAttack(){
        return attack;
    }

    public int getDefense(){
        return defense;
    }

    public void setDefense(int value){
        if(value < getDefense())
            throw new InvalidDefenseValueException("El valor deberia ser mayor al defense.");
        else
            defense = value;
    }

    //falta testear cuando se implemente en el levelUp
    public boolean isKnockedDown(){
        return getCurrentHp() == 0;
    }
    
    public float getRewardExpirience(){
    	return rewardExp;
    }
}
