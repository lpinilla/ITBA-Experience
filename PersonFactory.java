//package person; modificar

import map.Position2D;
import person.*; //quitar wildcard import   
import person.AbilityFactory;

/**
 * @author lpinilla
 */
public class PersonFactory {
    public static enum People{
        mainC, h1,h2, hoC1, pF1, pF2, hoC2, pF3, pF4, hoC3, pF5, pF6;
    }

    //Cambiar todas las positions y las Abilities por las que corresponden
    public static Person createPerson(People p){
        switch(p){
            case mainC:
                return new MainCharacter("Main Character", 100, 100, 20, 20,
                        new Position2D(0, 0), new Type("Informatico"),
                        AbilityFactory.createAbility(AbilityFactory.Ability.bolaDeSabiduria));
            case h1:
                return new Hero("Hero 1", 100,100,10,10,
                        new Position2D(3,3), new Type("Informático"),
                        AbilityFactory.createAbility(AbilityFactory.Ability.bolaDeSabiduria));
            case h2:
                return new Hero("Hero 2", 100,100,10,10,
                        new Position2D(3,3), new Type("Informático"),
                        AbilityFactory.createAbility(AbilityFactory.Ability.bolaDeSabiduria));
            case hoC1:
                return new HeadOfChair("Alejandro Diaz", 200, 200, 30,30,
                        new Position2D(5,5), new Type("Físico"), 1, 100f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.bolaDeSabiduria));
            case pF1:
                new Professor("Roberto Dellelis", 200, 200, 30,30,
                        new Position2D(5,5), new Type("Físico"), 1, 100f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.bolaDeSabiduria));
            case pF2:
                new Professor("Marcelo Perotti", 200, 200, 30,30,
                        new Position2D(5,5), new Type("Físico"), 1, 100f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.bolaDeSabiduria));
            case hoC2:
                return new HeadOfChair("María Laura Noni", 400, 400, 50,50,
                        new Position2D(8,8), new Type("Informático"), 2, 300f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.induccion));
            case pF3:
                new Professor("Mechi", 200, 200, 30,40,
                        new Position2D(5,5), new Type("Matemático"), 2, 200f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.bolaDeSabiduria));
            case pF4:
                new Professor("Chelo", 200, 200, 40,30,
                        new Position2D(5,5), new Type("Informático"), 2, 200f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.bolaDeSabiduria));
            case hoC3:
                return new HeadOfChair("Santiago Bermudez", 800, 650, 70,50,
                        new Position2D(8,8), new Type("Informático"), 3, 1000f,
                        AbilityFactory.createAbility(AbilityFactory.Ability.induccion));
        }
        return null;
    }
}
