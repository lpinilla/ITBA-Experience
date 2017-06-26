package tests.model;
import static org.junit.Assert.assertEquals;
/**
 * @author: ividaurreta
 */
import model.Model;
import model.abilities.Abilities;
import model.abilities.AbilityFactory;
import model.persons.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ModelTest{
	private Model model;
	private File mapsFile;
	private FileReader fr;


	@Before
	public void before() throws FileNotFoundException {
		this.mapsFile=new File("assets/maps2.txt");
		fr = new FileReader(this.mapsFile);
		model = new Model();
	}

	@Test
	public void createdPersonsTest(){
		model.activatePersonFactory();
		ArrayList<Person> persons = model.getPersons();
		int i =0;
		for (PersonFactory.People p :PersonFactory.People.values()){
			assertEquals(PersonFactory.createPerson(p), persons.get(i));
			i+=1;
		}
	}

	@Test
	public void setUpPartiesMainCharacterIncludedTest(){
		model.activatePersonFactory();
		model.setUpParties();
		//I know from the enum in personFactory that MC is on pos 0

		ArrayList<Person> persons = model.getPersons();
				assertEquals(true,((MainCharacter)persons.get(0)).getParty()
				.contains((MainCharacter)persons.get(0)));

	}

	@Test
	public void setUpPartiesHeadOfChairIncludedTest(){
		model.activatePersonFactory();
		model.setUpParties();
		//I know from the enum in personFactory that the first HoC is on pos 3

		ArrayList<Person> persons = model.getPersons();
		assertEquals(true,((HeadOfChair)persons.get(3)).getParty()
				.contains((HeadOfChair)persons.get(3)));

	}

	@Test
	public void createdAbilitiesTest(){
		model.activateAbilityFactory();
		ArrayList<Abilities> abilities = model.getAbilities();
		int i =0;
		for (AbilityFactory.Ability a : AbilityFactory.Ability.values()){
			assertEquals(AbilityFactory.createAbility(a), abilities.get(i));
			i+=1;
		}
	}

	@Test
	public void getPersonByClassMainCharacterTest(){
		model.activatePersonFactory();
		assertEquals(MainCharacter.class, model.getPersonByClass
				(MainCharacter.class.toString(),0).getClass());

	}

	@Test
	public void getPersonByClassHeroTest(){
		model.activatePersonFactory();
		assertEquals(Hero.class, model.getPersonByClass
				(Hero.class.toString(),0).getClass());

	}

	@Test
	public void getPersonByClassHeadOfChairTest(){
		model.activatePersonFactory();
		assertEquals(HeadOfChair.class, model.getPersonByClass
				(HeadOfChair.class.toString(),0).getClass());

	}

	@Test
	public void getPersonByClassProfessorTest(){
		model.activatePersonFactory();
		assertEquals(Professor.class, model.getPersonByClass
				(Professor.class.toString(),0).getClass());

	}

	@Test
	public void winCombatTest(){

	}

	@Test
	public void loadMapsTest(){

	}

	@Test
	public void generateMapsTest(){

	}

	@Test
	public void readMapTest(){
		//perdón al que tenga que hacer este test, gl m8
	}


 
}