package person;

public class AbilityFactory{

    public static enum Ability{
        bolaDeSabiduria, induccion, reglaDeLaCadena,
        aumentarVelocidadDeEscritura, notaDeCursada,
        teoremaDe3Pizarrones, cadaXHorasDeClase,
        hacerEjerciciosDeParcial, centrarElEspiritu,
        mateEnClase, laPapa ,h1, h2, h3;
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
        }
        return null; //No debería tirar una excepción?
    }

    //Cambiar valores
    private static class BolaDeSabiduria extends Abilities{

        public BolaDeSabiduria(){
            super("Bola de Sabiduria", 25, 15,2);
        }

    }

    private static class Induccion extends Abilities{
        public Induccion(){
            super("Induccion", 15,12,2);
        }
    }

    private static class ReglaDeLaCadena extends Abilities{
        public ReglaDeLaCadena(){
            super("Regla de la cadena", 20, 16 , 2);
        }
    }

    private static class AumentarVelocidadDeEscritura extends Abilities{
        public AumentarVelocidadDeEscritura(){
            super("Aumentar velocidad de escritura", 30, 20, 2);
        }
    }

    private static class LaPapa extends Abilities{
        public LaPapa(){
            super("LA PAPA", 15, 12, 2);
        }
    }

    private static class NotaDeCursada extends Abilities{
        public NotaDeCursada(){
            super("Cero va a ser su nota de cursada", 30, 20 ,2);
        }
    }

    private static class TeoremaDe3Pizarrones extends Abilities{
        public TeoremaDe3Pizarrones(){
            super ("Teorema de 3 pizarrrones", 17, 14 ,2);
        }
    }

    private static class CadaXHorasDeClase extends Abilities{
        public CadaXHorasDeClase(){
            super ("Cada 24 horas de clase," +
                    " tienen que estudiar 24 en casa mínimo", 20 ,15, 2);
        }
    }

    private static class HacerEjerciciosDeParcial extends Abilities{ //default hero
        public HacerEjerciciosDeParcial(){
            super("Hacer ejercicios de parcial", 30, 20, 2);
        }
    }

    private static class CentrarElEspiritu extends Abilities{ //mainChar
        public CentrarElEspiritu(){
            super("Centrar el espiritu", 40, 20, 2);
        }
    }

    private static class MateEnClase extends Abilities{
        public MateEnClase(){
            super("Tomar mate en clase", 25, 15, 2);
        }
    }



}
