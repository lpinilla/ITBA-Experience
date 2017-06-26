package model.persons;

import model.abilities.AbilityFactory;
import model.map.Position2D;

/**
 * @author lpinilla
 *
 */
public class PersonFactory {
    public static enum People{
        mainC, h1,h2, hoC1, pF1, pF2, hoC2, pF3, pF4, hoC3, pF5, pF6
        //Important!, changing this order will probably break ModelTest
    }

    public static Person createPerson(People p){
        switch(p){
            case mainC:
                return new MainCharacter("Main Character", 100, 100, 20, 20,
                        new Position2D(0, 2), new Type("Informatico"),
                        AbilityFactory.createAbility(AbilityFactory.Ability.centrarElEspiritu));
            case h1:
                return new Hero("Hero Boy", 100,100,10,10,
                        new Position2D(0,0), new Type("Informático"),
                        AbilityFactory.createAbility(AbilityFactory.Ability.hacerEjerciciosDeParcial));
            case h2:
                return new Hero("Hero Girl", 100,100,10,10,
                        new Position2D(0,0), new Type("Informático"),
                        AbilityFactory.createAbility(AbilityFactory.Ability.mateEnClase));
            case hoC1:
                return new HeadOfChair("Alejandro Diaz", 200, 200, 30,30,
                        new Position2D(1,19), new Type("Físico"), 1, 100f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.bolaDeSabiduria));
            case pF1:
                return new Professor("Roberto Dellelis", 200, 200, 30,30,
                        new Position2D(0,0), new Type("Físico"), 1, 100f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.reglaDeLaCadena));
            case pF2:
                return new Professor("Marcelo Perotti", 200, 200, 30,30,
                        new Position2D(0,0), new Type("Físico"), 1, 100f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.reglaDeLaCadena));
            case hoC2:
                return new HeadOfChair("Maria Laura Noni", 400, 400, 50,50,
                        new Position2D(1,12), new Type("Informático"), 2, 300f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.aumentarVelocidadDeEscritura));
            case pF3:
                new Professor("Mechi", 200, 200, 30,40,
                        new Position2D(0,0), new Type("Matemático"), 2, 200f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.centrarElEspiritu));
            case pF4:
                new Professor("Chelo", 200, 200, 40,30,
                        new Position2D(0,0), new Type("Informático"), 2, 200f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.induccion));
            case hoC3:
                return new HeadOfChair("Santiago Bermudez", 800, 650, 70,50,
                        new Position2D(19,19), new Type("Informático"), 3, 1000f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.cadaXHorasDeClase));
            case pF5:
                return new Professor("Augusto MacIntosh", 200, 200, 30,30,
                        new Position2D(0,0), new Type("Informático"), 2, 200f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.eureka));
            case pF6:
                return new Professor("Agustin Pagnoni", 200, 200, 30,30,
                        new Position2D(0,0), new Type("Informático"), 2, 200f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.TDD));
        }
        return null;
    }
}
