package model.abilities;

public class AbilityFactory{

    public static enum Ability{
        bolaDeSabiduria, induccion, reglaDeLaCadena,
        aumentarVelocidadDeEscritura, notaDeCursada,
        teoremaDe3Pizarrones, cadaXHorasDeClase,
        hacerEjerciciosDeParcial, centrarElEspiritu,
        mateEnClase, laPapa, TDD, eureka;
    }

    public static Abilities createAbility(Ability a){
        switch(a){
            case bolaDeSabiduria:
                return new BolaDeSabiduria();
            case induccion:
                return new Induccion();
            case reglaDeLaCadena:
                return new ReglaDeLaCadena();
            case aumentarVelocidadDeEscritura:
                return new AumentarVelocidadDeEscritura();
            case laPapa:
                return new LaPapa();
            case notaDeCursada:
                return new NotaDeCursada();
            case teoremaDe3Pizarrones:
                return new TeoremaDe3Pizarrones();
            case cadaXHorasDeClase:
                return new CadaXHorasDeClase();
            case hacerEjerciciosDeParcial:
                return new HacerEjerciciosDeParcial();
            case centrarElEspiritu:
                return new CentrarElEspiritu();
            case mateEnClase:
                return new MateEnClase();
            case TDD:
                return new TDD();
            case eureka:
                return new Eureka();
        }
        return null; //No debería tirar una excepción?
    }

    private static class BolaDeSabiduria extends Abilities{

        public BolaDeSabiduria(){
            super("bolaDeSabiduria", 25, 15,2);
        }

    }

    private static class Induccion extends Abilities{
        public Induccion(){
            super("induccion", 15,12,2);
        }
    }

    private static class ReglaDeLaCadena extends Abilities{
        public ReglaDeLaCadena(){
            super("reglaDeLaCadena", 20, 16 , 2);
        }
    }

    private static class AumentarVelocidadDeEscritura extends Abilities{
        public AumentarVelocidadDeEscritura(){
            super("aumentarVelocidadDeEscritura", 30, 20, 2);
        }
    }

    private static class LaPapa extends Abilities{
        public LaPapa(){
            super("laPapa", 15, 12, 2);
        }
    }

    private static class NotaDeCursada extends Abilities{
        public NotaDeCursada(){
            super("ceroNotaDeCursada", 30, 20 ,2);
        }
    }

    private static class TeoremaDe3Pizarrones extends Abilities{
        public TeoremaDe3Pizarrones(){
            super ("teoremaDe3Pizarrones", 17, 14 ,2);
        }
    }

    private static class CadaXHorasDeClase extends Abilities{
        public CadaXHorasDeClase(){
            super ("cadaXHorasDeClase", 20 ,15, 2);
        }
    }

    private static class HacerEjerciciosDeParcial extends Abilities{ //default hero
        public HacerEjerciciosDeParcial(){
            super("hacerEjerciciosDeParcial", 30, 20, 2);
        }
    }

    private static class CentrarElEspiritu extends Abilities{ //mainChar
        public CentrarElEspiritu(){
            super("centrarElEspiritu", 40, 20, 2);
        }
    }

    private static class MateEnClase extends Abilities{
        public MateEnClase(){
            super("mateEnClase", 25, 15, 2);
        }
    }

    private static class TDD extends Abilities{
        public TDD(){
            super("TDD", 25, 14, 2);
        }
    }

    private static class Eureka extends Abilities{
        public Eureka(){
            super("eureka", 25, 15, 2);
        }
    }

}
