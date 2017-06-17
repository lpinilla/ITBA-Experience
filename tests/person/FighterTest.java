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
                INITATTACK, INITDEFENSE, new Position2D(0, 0), new Type("CS"));
        expLeft = dummy.getExpToNextLevel() - dummy.getExp();
    }

    @Test
    public void getHpTest() {
        assertEquals("Debería devolver 20", INITHP, dummy.getHP());
    }

    @Test
    public void getHpLevelUpTest() {
        dummy.addExp(expLeft);
        assertEquals("Debería aumentar en " + Fighter.HP_PER_LEVEL+ " la HP por el level Up",
                INITHP + Fighter.HP_PER_LEVEL, dummy.getHP());
        // TODO ver cómo sacar el wildcard 100
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
    public void getCurrentHPAfterLevelUpTest(){
        dummy.addExp(expLeft);
        assertEquals("Debería devolver lo mismo que el hp", dummy.getHP(),dummy.getCurrentHP());
    }

    @Test
    public void getCurrentHpLevelUpTest(){
        dummy.addExp(expLeft+ Fighter.EXP_PERCENTAGE*dummy.getExpToNextLevel());
        assertEquals("Debería tener tanta vida como el maximo",dummy.getHP(), dummy.getCurrentHP());
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
    public void getWillPowerAfterLevelUpTest() {
        int willP = dummy.getWillPower();
        dummy.addExp(expLeft);
        assertEquals("El will Power deberia aumentar cuando se sube de nivel", true, dummy.getWillPower() > willP);
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
    public void getCurrentWillPowerAfterLevelUpTest(){
        dummy.addExp(expLeft);
        assertEquals("Deberia devolver lo mismo que el WillPower", dummy.getWillPower(),dummy.getCurrentWillPower());
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
    public void getAttackAfterLevelUpTest() {
        int attack = dummy.getAttack();
        dummy.addExp(expLeft);;
        assertEquals("Attack deberia ser mayor cuando se sube de nivel",true,dummy.getAttack() > attack);

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
    public void getDefenseAfterLevelUpTest() {
        int defense = dummy.getAttack();
        dummy.addExp(expLeft);;
        assertEquals("Defense deberia ser mayor cuando se sube de nivel",true,dummy.getDefense() > defense);
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
