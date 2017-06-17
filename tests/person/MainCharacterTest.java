package tests.person;
import static org.junit.Assert.assertEquals;

import map.Position2D;
import org.junit.Before;
import org.junit.Test;
import person.*;


public class MainCharacterTest{

    private MainCharacter pj;
    private Abilities ab;

    @Before
    public void before(){
        pj = new MainCharacter("Tomas",200, 300,
                30, 40, new Position2D(0,0), new Type("CS"));
        ab = new SingleTargetAbility("Super smash",10, 200 ,1);
    }

    @Test //Que no tire excepcion
    public void addingHeroToPartyTest(){
        pj.addHeroToParty(new Hero("SuperHero", 200, 300,
                40, 60, new Position2D(10,10), new Type("Physics")));
    }

    @Test(expected=WrongHeroException.class)
    public void wrongHeroExceptionTest(){
        pj.addHeroToParty(null);
    }

    @Test
    public void isPartyFullFalseTest(){
        assertEquals(false, pj.isPartyFull());
    }

    @Test
    public void isPartyFullTrueTest(){
        Hero ch1 = new Hero("Ignacio", 200, 300,
                30, 40, new Position2D(1,0), new Type("CS"));
        Hero ch2 = new Hero("Lautaro", 200, 300,
                30, 40, new Position2D(1,0), new Type("CS"));
        Hero ch3 = new Hero("Matías", 200, 300,
                30, 40, new Position2D(1,0), new Type("CS"));
        Hero ch4 = new Hero("Agustín", 200, 300,
                30, 40, new Position2D(1,0), new Type("CS"));
        Hero ch5 = new Hero("RandomDude", 200, 300,
                30, 40, new Position2D(1,0), new Type("CS"));

        pj.addHeroToParty(ch1);
        pj.addHeroToParty(ch2);
        pj.addHeroToParty(ch3);
        pj.addHeroToParty(ch4);
        pj.addHeroToParty(ch5);


        assertEquals(true, pj.isPartyFull());
    }

    @Test(expected=WrongAbilityException.class)
    public void wrongAbilityExceptionTest(){
        pj.addSpecialAbility(null);
    }


    @Test
    public void addingSpecialAbilityTest(){
        pj.addSpecialAbility(ab);
    }

    @Test
    public void isAbilitesFullFalseTest(){ //No se cuan util es
        assertEquals(false, pj.isAbilitesFull());
    }

    @Test
    public void isAbilitiesFullTrueTest(){
        Abilities ab2 = new SingleTargetAbility("Super smash",10, 200 ,1);
        Abilities ab3 = new SingleTargetAbility("Super smash",10, 200 ,1);
        Abilities ab4 = new SingleTargetAbility("Super smash",10, 200 ,1);
        Abilities ab5 = new SingleTargetAbility("Super smash",10, 200 ,1);

        pj.addSpecialAbility(ab);
        pj.addSpecialAbility(ab2);
        pj.addSpecialAbility(ab3);
        pj.addSpecialAbility(ab4);
        pj.addSpecialAbility(ab5);

        assertEquals(true, pj.isAbilitesFull());

    }

    @Test
    public void removeHeroFromPartyValidTest(){
        Hero ch1 = new Hero("RandomDude", 200, 300,
                30, 40, new Position2D(1,0), new Type("CS"));

        pj.addHeroToParty(ch1);
    	assertEquals(true, pj.removeHeroFromParty(ch1));
    }
    @Test
    public void removeHeroFromPartyInvalidTest(){
        Hero ch1 = new Hero("RandomDude", 200, 300,
                30, 40, new Position2D(1,0), new Type("CS"));
        assertEquals(false, pj.removeHeroFromParty(ch1));

    }

}