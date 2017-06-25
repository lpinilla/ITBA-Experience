package tests.model.persons;

import model.abilities.Abilities;
import model.abilities.AbilityFactory;
import model.map.Position2D;
import model.persons.HeadOfChair;
import model.persons.Hero;
import model.persons.Professor;
import model.persons.Type;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HeadOfChairTest {
    HeadOfChair f;
    Abilities miscAbility;
    @Before
    public void before() {
        miscAbility =  AbilityFactory.createAbility(AbilityFactory.Ability.mateEnClase);
        f = new HeadOfChair("Santi",20, 10,
                10,1, new Position2D(2,0),
                new Type("CS"), 2, 300f,
                AbilityFactory.createAbility(AbilityFactory.Ability.mateEnClase));
    }


    @Test
    public void getRewardExperienceTest(){
        assertEquals("Reward experience should be 300",
                300f, f.getRewardExperience(), 0.0001);

    }

    @Test
    public void addProfessorToPartyValidTest(){
        Professor prof1 = new Professor("Santi", 200, 300,
                30,40, new Position2D(3,0), new Type("CS"),
                4, 5000, miscAbility );
        f.addProfessorToParty(prof1);
    }

    @Test
    public void isPartyFullFalseTest(){
        assertEquals(false, f.isPartyFull());
    }

    @Test
    public void isPartyFullTrueTest(){
        Professor prof1 = new Professor("Santi", 200, 300,
                30,40, new Position2D(3,0), new Type("CS"),
                4, 5000, miscAbility);
        Professor prof2 = new Professor("Laura", 200, 300,
                30,40, new Position2D(3,0), new Type("CS"),
                4, 5000, miscAbility);
        Professor prof3 = new Professor("Marcelo", 200, 300,
                30,40, new Position2D(3,0), new Type("CS"),
                4, 5000, miscAbility);

        f.addProfessorToParty(prof1);
        f.addProfessorToParty(prof2);
        f.addProfessorToParty(prof3);
        assertEquals(true, f.isPartyFull());

    }

    @Test
    public void getSpecialAbilityTest(){
        assertEquals("Tomar mate en clase",f.getAbility().getName());
    }

    @Test
    public void makeAttackTest(){
        Hero hr = new Hero("nacho",100,100,3,3,
                new Position2D(0,0), new Type("cs"), miscAbility);

    }
}