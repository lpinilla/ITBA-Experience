package person;

/**
 * Created by lautaro on 18/06/17.
 */
public class AbilityFactory{

    public static enum Ability{
        bolaDeSabiduria, induccion, h1, h2, h3;
    }

    public static Abilities createAbility(Ability a){
        switch(a){
            case bolaDeSabiduria:
                return new BolaDeSabiduria();
            case induccion:
                return new Induccion();
        }
        return null;
    }

    //Cambiar valores
    private static class BolaDeSabiduria extends Abilities{

        public BolaDeSabiduria(){
            super("Bola de Sabiduria", 10, 10);
        }

    }

    private static class Induccion extends Abilities{
        public Induccion(){
            super("Induccion", 10,10);
        }
    }



}
