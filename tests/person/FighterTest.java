package tests.person;

import static org.junit.Assert.assertEquals;
import map.Position2D;
import org.junit.Before;
import org.junit.Test;
import person.*;

public class FighterTest {
    private Fighter dummy;
    private float expLeft;
    private static final int INITHP = 50;
    private static final int INITWP = 400;
    private static final int INITATTACK = 50;
    private static final int INITDEFENSE = 60;


    @Before
    public void before() {
        dummy = new Fighter("Fighter", INITHP, INITWP,
                INITATTACK, INITDEFENSE, new Position2D(0, 0),
                new Type("CS"), new SingleTargetAbility("Crunch",
                200,20,10));
    }

    @Test
    public void getHpTest() {
        assertEquals("Debería devolver 20", INITHP, dummy.getHP());
    }

    @Test
    public void setHpGoodValueTest() {
        dummy.setHP(10);
        assertEquals("Debería devolver 10", 10, dummy.getHP());
        dummy.setHP(200);
        assertEquals("Debería devolver 200", 200, dummy.getHP());
    }

    @Test(expected = InvalidValueException.class)
    public void setHPNegativeValueTest() {
        dummy.setHP(-1);
    }

    @Test
    public void getCurrentHpTest(){
        assertEquals("Como no recibió daño, debería recibir la HP inicial: "
                + INITHP, INITHP,dummy.getCurrentHP());
    }

    @Test
    public void recieveDamageInRangeValueTest(){
        dummy.receiveDamage(5);
        assertEquals("currentHp deberia ser " + (INITHP - 5), INITHP - 5,dummy.getCurrentHP());
        dummy.receiveDamage(14);
        assertEquals("currentHp deberia ser:" + (INITHP - 19), INITHP - 19,dummy.getCurrentHP());
    }

    @Test
    public void receiveZeroDamageTest(){
        dummy.receiveDamage(0);
        assertEquals("currentHp debería ser: " + INITHP,
                INITHP,dummy.getCurrentHP());
    }

    @Test
    public void recieveDamageGreaterThanHPTest(){
        dummy.receiveDamage(INITHP + 10);
        assertEquals("currentHp deberia ser 0", 0,dummy.getCurrentHP());
    }

    @Test(expected = InvalidValueException.class)
    public void receiveDamageWrongValueTest() {
        dummy.receiveDamage(-1);
    }

    @Test
    public void healCharacterHPGreaterThanHPTest(){
        dummy.healCharacter(INITHP + 10);
        assertEquals("Deberia estar en " + INITHP +  " HP",INITHP, dummy.getCurrentHP());
    }

    @Test
    public void healCharacterInRangeValueTest(){
        dummy.receiveDamage(10);
        dummy.healCharacter(5);
        assertEquals("Debería estar en " + (INITHP - 5)+  " HP",
                INITHP - 5, dummy.getCurrentHP() );
    }


    @Test
    public void healZeroValueTest(){
        dummy.healCharacter(0);
        assertEquals("currentHp deberia ser " + INITHP, INITHP,dummy.getCurrentHP());
    }

    @Test(expected = InvalidValueException.class)
    public void healWrongValueTest() {
        dummy.receiveDamage(-1);
    }

    @Test
    public void getWillPowerTest() {
        assertEquals("willPower deberia ser " + INITWP,
                INITWP, dummy.getWillPower());
    }



    @Test
    public void setWillPowerGoodValueTest(){
        dummy.setWillPower(100);
        assertEquals("willPower debería ser 100",100,dummy.getWillPower());
        dummy.setWillPower(400);
        assertEquals(400,dummy.getWillPower());
    }

    @Test (expected = InvalidValueException.class)
    public void setWillPowerInvalidNegativeValuesTest(){
        dummy.setWillPower(-1);
    }

    @Test
    public void getCurrentWillPowerTest(){
        assertEquals("Current willPower deberia ser" + INITWP, INITWP, dummy.getCurrentWillPower());
    }

    @Test
    public void modifyWillInRageValuesPowerTest(){
        dummy.modifyCurrentWillPower(-10);
        assertEquals("Current willPower deberia bajar en 10",
                INITWP-10,dummy.getCurrentWillPower());
        dummy.modifyCurrentWillPower(4);
        assertEquals("Current willPower deberia aumentar en 4",
                INITWP - 6,dummy.getCurrentWillPower()); //TODO VER MSG
    }

    @Test
    public void modifyWillPowerOverGreatestWillPowerTest() {
        dummy.modifyCurrentWillPower(INITWP - 10);
        assertEquals("Current willPower no debería cambiar pues ya es máximo",
                INITWP, dummy.getCurrentWillPower());
    }

    @Test
    public void modifyWillPowerUnderLowestWillPowerTest(){
        dummy.modifyCurrentWillPower(-400);
        assertEquals("Current willPower deberia ser 0", 0,dummy.getCurrentWillPower());

    }

    @Test
    public void getAttackTest() {
        assertEquals("Attack deberia ser " + INITATTACK,INITATTACK,dummy.getAttack());
    }


    @Test
    public  void modifyAttackAddNormalValueTest(){
        dummy.modifyAttack(40);
        assertEquals("El attack deberia ser" + (INITATTACK + 40),
                INITATTACK + 40,dummy.getAttack());
    }

    @Test
    public void modifyAttackDecreaseToMinValueTest(){
        dummy.modifyAttack(-100);
        assertEquals("El ataque no debe poder disminuir de un minimo fijado.",dummy.MIN_ATTACK, dummy.getAttack());
    }

    @Test
    public void getDefenseTest(){
        assertEquals("Defense deberia ser " + INITDEFENSE, INITDEFENSE , dummy.getDefense());
    }


    @Test
    public  void modifyDefenseAddNormalValueTest(){
        dummy.modifyDefense(50);
        assertEquals("Defense debería aumentar en 50",INITDEFENSE + 50,dummy.getDefense());
    }

    @Test
    public void modifyDefenseDecreaseToMinValueTest(){
        dummy.modifyDefense(-100);
        assertEquals("Defense no deberia poder disminuir de un mínimo prefijado" ,dummy.MIN_DEFENSE, dummy.getDefense());
    }

    @Test
    public void isKnockedOutFalseValueTest(){
        assertEquals("Deberia ser false", false, dummy.isKnockedOut());
    }

    @Test
    public void isKnockedOutTrueValueTest(){
        int hpLeft = dummy.getCurrentHP();
        dummy.receiveDamage(hpLeft);
        assertEquals("Deberia ser true", true, dummy.isKnockedOut());
    }

    @Test
    public void getTypeTest(){
        assertEquals("CS",dummy.getType());
    }

    @Test
    public void getPositionTest(){
        Position2D posAux = new Position2D(0,0);
        assertEquals(posAux, dummy.getPosition());
    }

}
