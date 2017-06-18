package tests.person;

import map.Position2D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import person.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nacho on 17/06/17.
 */
public class HeroTest {
    private Hero dummy;
    private float expLeft;
    private static final int INITHP = 50;
    private static final int INITWP = 400;
    private static final int INITATTACK = 50;
    private static final int INITDEFENSE = 60;


    @Before
    public void before() {
        dummy = new Hero("DummyHero", INITHP, INITWP,
                INITATTACK, INITDEFENSE, new Position2D(0, 0),
                new Type("CS"), new SingleTargetAbility("Crunch",
                200,20,10));
        expLeft = dummy.getExpToNextLevel() - dummy.getExp();
    }

    @Test
    public void getHpLevelUpTest() {
        dummy.addExp(expLeft);
        Assert.assertEquals("Debería aumentar en " + Fighter.HP_PER_LEVEL+ " la HP por el level Up",
                INITHP + Fighter.HP_PER_LEVEL, dummy.getHP());
    }

    @Test
    public void getCurrentHPAfterLevelUpTest(){
        dummy.addExp(expLeft);
        assertEquals("Debería devolver lo mismo que el hp", dummy.getHP(),dummy.getCurrentHP());
    }

    @Test
    public void getCurrentHpLevelUpTest(){
        dummy.addExp(expLeft+ Hero.EXP_PERCENTAGE*dummy.getExpToNextLevel());
        assertEquals("Debería tener tanta vida como el maximo",dummy.getHP(), dummy.getCurrentHP());
    }

    @Test
    public void getWillPowerAfterLevelUpTest() {
        int willP = dummy.getWillPower();
        dummy.addExp(expLeft);
        assertEquals("El will Power deberia aumentar cuando se sube de nivel", true, dummy.getWillPower() > willP);
    }

    @Test
    public void getCurrentWillPowerAfterLevelUpTest(){
        dummy.addExp(expLeft);
        assertEquals("Deberia devolver lo mismo que el WillPower", dummy.getWillPower(),dummy.getCurrentWillPower());
    }

    @Test
    public void getAttackAfterLevelUpTest() {
        int attack = dummy.getAttack();
        dummy.addExp(expLeft);;
        assertEquals("Attack deberia ser mayor cuando se sube de nivel",true,dummy.getAttack() > attack);

    }

    @Test
    public void getDefenseAfterLevelUpTest() {
        int defense = dummy.getAttack();
        dummy.addExp(expLeft);;
        assertEquals("Defense deberia ser mayor cuando se sube de nivel",true,dummy.getDefense() > defense);
    }

    @Test
    public void getLevelTest(){
        assertEquals("Level debería ser 1", 1 , dummy.getLevel());
    }

    @Test
    public void getLevelAfterLevelUpTest() {
        dummy.addExp(expLeft);
        assertEquals("Level deberia ser 2", 2 , dummy.getLevel());
    }


    @Test
    public void getExperienceTest(){
        assertEquals("La experiencia deberia ser 0",0,dummy.getExp(),0.001);
    }

    @Test
    public void addExperienceLessThanNextLevelTest() {
        dummy.addExp(10);
        assertEquals("Deberia ser 10 la experiencia", 10, dummy.getExp(), 0.0001);
    }

    @Test
    public void addExperienceLevelUpTest() {
        dummy.addExp(expLeft);
        assertEquals("Deberia haber subido de nivel", 2, dummy.getLevel());
    }

    @Test
    public void earnExperienceTwoLevelUpTest() {
        dummy.addExp(expLeft + dummy.EXP_PERCENTAGE * dummy.getExpToNextLevel());
        assertEquals("Deberia haber subido de nivel", 3,dummy.getLevel());
    }


    @Test(expected = InvalidValueException.class)
    public void addExperienceZeroValueTest(){
        dummy.addExp(0);
    }

    @Test(expected = InvalidValueException.class)
    public void earnExperienceNegativeValueTest(){
        dummy.addExp(-10);
    }

    @Test
    public void getExperienceToNextLevelTest(){
        assertEquals("La experiencia para el proximo level deberia ser 1000",1000,dummy.getExpToNextLevel(),0.0001);
    }

    @Test
    public void getExperienceToNextLevelAfterLevelUpTest(){
        float exp = dummy.getExpToNextLevel();
        dummy.addExp(expLeft);
        assertEquals("La experiencia para el proximo nivel deberia aumentar cuando se sube de nivel",true,dummy.getExpToNextLevel()>exp);
    }


}
