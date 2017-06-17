package person;
//Cuando Lauti suba el update de combat del método que falta testear lo vuelvo a ver - Nacho
public abstract class Abilities {

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

    //Esto para qué lo vamos a usar?
    public void use(Fighter f1, Fighter f2) {
        // f1 vs f1

    }

    public void use(Fighter f1) {
        //curarse o regenerar willcost?
    }
}
