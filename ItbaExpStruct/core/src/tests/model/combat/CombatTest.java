package tests.model.combat;
/**
*@author: lpinilla, ividaurreta
*/

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.abilities.Abilities;
import model.combat.Combat;
import model.map.Position2D;
import model.persons.Enemy;
import model.persons.HeadOfChair;
import model.persons.Hero;
import model.persons.MainCharacter;
import model.persons.Professor;
import model.persons.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



import java.util.ArrayList;

public class CombatTest {

	private MainCharacter mc;
	private HeadOfChair hoc;
	private Hero hr, hr2;
	private Enemy pf, pf2;
	private static final int INITHP=100;
	private static final int INITWP=200;
	private Combat combat;

	@Before
	public void before() {
		Abilities miscAbility = new SingleTargetAbility("Testing",50,50,2);
		mc = new MainCharacter("lpinilla", INITHP, INITWP, 20, 20,
				new Position2D(0, 0), new Type("CS"), miscAbility);

		hoc = new HeadOfChair("sbermudez", INITHP, INITWP, 30, 30,
				new Position2D(1, 0), new Type("CS"), 3, 500, miscAbility);
		hr = new Hero("nacho",INITHP,INITWP,50,
				3,
				new Position2D(0,0), new Type("cs"), miscAbility);

		hr2 = new Hero("agus",INITHP,INITWP,3,3,
				new Position2D(0,0), new Type("cs"), miscAbility);

		pf = new Professor("Santi",INITHP,INITWP,3,3,
				new Position2D(0,0), new Type("cs"),3,200, miscAbility);

		pf2 = new Professor("Augusto",INITHP,INITWP,3,3,
				new Position2D(0,0), new Type("cs"),3,200, miscAbility);

		mc.addHeroToParty(mc); //Se agrega a sí mismo
		mc.addHeroToParty(hr); //agrega a la party del MainCharacter
		mc.addHeroToParty(hr2);

		hoc.addProfessorToParty(hoc);
		hoc.addProfessorToParty((Professor) pf); //TODO HOC EXTENDS PROFESSOR??
		hoc.addProfessorToParty((Professor) pf2);
		combat = new Combat(mc, hoc);
	}

	@Test
	//ver si el primer parametro de combate es el Main Character -> siempre va a ser el MainCharacter, test necesario?
	public void isMainCharacterTest() {
		assertEquals(this.combat.getMainCharacter().getClass(),
				mc.getClass());
	}

	@Test //por ahora solo se pelean con HOC
	public void isHeadOfChairTest() {
		assertEquals(combat.getHeadOfChair().getClass(),
				hoc.getClass());
	}

	@Test
	public void enemyNumberOfPartyTest() {
		assertEquals(3, hoc.getParty().size());
	}

	@Test
	public void mainCharacterNumberOfParty() {
		assertEquals(3, mc.getParty().size());
	}

	@Test
	public void notNullHero() {
		hr = combat.getNextHero();
		assertTrue(hr != null);
	}

	@Test
	public void notNullEnemy() {
		pf = combat.getNextSubEnemy();
		assertTrue(pf != null);
	}



	@Test //copy del test de arriba
	public void willPowerCostTest() { //player tiene que heredar de heroe
		hr = this.combat.getMainCharacter().getHero();
		pf = this.combat.getHeadOfChair().getEnemy();
		combat.fighterAttack(hr, pf);
		assertEquals(50, hr.getWillPower() - hr.getCurrentWillPower());
	}

	@Test
	public void getNextHero() {
		hr = combat.getNextHero();
		hr2 = combat.getNextHero();
		Assert.assertFalse(hr.equals(hr2));
	}

	@Test
	public void getNextEnemy() {
		pf = this.combat.getNextSubEnemy();
		pf2 = this.combat.getNextSubEnemy();
		Assert.assertFalse(pf.equals(pf2));
	}

	@Test
	public void circularPlayerListTest() {
		hr = this.combat.getNextHero(); //agarrar al player
		hr = this.combat.getNextHero(); //agarrar al primer heroe
		hr = this.combat.getNextHero(); //agarrar al segundo heroe
		hr = this.combat.getNextHero(); //agarrar al player de nuevo
		assertTrue(hr.equals(combat.getMainCharacter()));
	}

	@Test
	public void circularEnemyListTest() {
		pf = this.combat.getNextSubEnemy(); //agarrar al enemy
		pf = this.combat.getNextSubEnemy(); //agarrar al primer subEnemy(Professor)
		pf = this.combat.getNextSubEnemy(); //agarrar al segundo subEnemy
		pf = this.combat.getNextSubEnemy(); //agarrar al enemy de nuevo
		assertTrue(pf.equals(combat.getHeadOfChair()));
	}

	@Test
	public void playerTurnTest() {
		assertTrue(this.combat.isPlayerTurn());
	}

	@Test
	public void attackDoesntKnockOutTest() {
		hr = this.combat.getNextHero();
		pf = this.combat.getHeadOfChair().getEnemy();
		combat.fighterAttack(hr, pf);
		assertEquals(50, pf.getHP() - pf.getCurrentHP());
	}

	@Test
	public void attackKnocksOutTest(){
		pf = this.combat.getHeadOfChair().getEnemy();
		Abilities miscAbility2 = new SingleTargetAbility("Testing",pf.getHP(),50,2);
		mc = new MainCharacter("lpinilla", INITHP, INITWP, 20, 20,
				new Position2D(0, 0), new Type("CS"), miscAbility2);

		combat.fighterAttack(mc, pf);
		assertEquals(0, pf.getCurrentHP());

	}

	@Test
	public void DamagedFighterHealTest(){
		hr = this.combat.getMainCharacter().getHero();
		pf = this.combat.getHeadOfChair().getEnemy();
		combat.fighterAttack(hr, pf);

		combat.fighterHeal(hr, hr.getAbility().getDamage());
		assertEquals(hr.getHP(),hr.getCurrentHP());

	}

	@Test
	public void FullHPFighterHealTest(){
		combat.fighterHeal(hr, 50);
		assertEquals(hr.getHP(),hr.getCurrentHP());
	}

	@Test
	public void NextTurnEnemyTest(){
		combat.nextTurn();
		assertEquals(combat.isPlayerTurn(), false);
	}
	@Test
	public void NextTurnPlayerTest(){
		combat.nextTurn();
		combat.nextTurn();
		assertEquals(combat.isPlayerTurn(), true);
	}
	@Test
	public void CombatEndedTest(){
		combat.endCombat();
		assertEquals(combat.isCombatActive(), false);
	}
}

