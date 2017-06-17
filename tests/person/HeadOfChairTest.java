package tests.person;

import map.Position2D;
import org.junit.Before;
import org.junit.Test;
import person.*;

import static org.junit.Assert.assertEquals;
 /*
    Remember: Sin wildcards, para testing los imports que me sirvieron fueron:
    import static org.junit.Assert.assertEquals;
    import org.junit.Before;
    import org.junit.Test;
 */


public class HeadOfChairTest {
    HeadOfChair f;
    @Before
    public void before() {
        f = new HeadOfChair("Santi",20, 10,
                10,1, new Position2D(2,0),
                new Type("CS"), 2, 300f,
                new SingleTargetAbility("Ejer1", 1000, 400, 1));
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
                4, 5000);
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
                4, 5000);
        Professor prof2 = new Professor("Laura", 200, 300,
                30,40, new Position2D(3,0), new Type("CS"),
                4, 5000);
        Professor prof3 = new Professor("Marcelo", 200, 300,
                30,40, new Position2D(3,0), new Type("CS"),
                4, 5000);

        f.addProfessorToParty(prof1);
        f.addProfessorToParty(prof2);
        f.addProfessorToParty(prof3);
        assertEquals(true, f.isPartyFull());

    }

    @Test
    public void getSpecialAbilityTest(){
        assertEquals("Ejer1",f.getSpecialAbility().getName());
    }

    @Test
    public void makeAttackTest(){
        Hero hr = new Hero("nacho",100,100,3,3,
                new Position2D(0,0), new Type("cs"));

    }
}