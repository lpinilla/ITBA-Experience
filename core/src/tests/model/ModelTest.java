package tests.model;
import static org.junit.Assert.assertEquals;
/**
 * @author: ividaurreta
 */
import model.Model;
import model.persons.HeadOfChair;
import model.persons.MainCharacter;
import model.persons.Person;
import model.persons.PersonFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class ModelTest{


	private Model model;
	//private File mapsFile;


	@Before
	public void before(){
		//this.mapsFile(Assetspath);
		model = new Model();
	}

	@Test
	public void createdPersonsTest(){
		model.activatePersonFactory();
		ArrayList<Person> persons = model.getCreatedCharacters();
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

		ArrayList<Person> persons = model.getCreatedCharacters();
				assertEquals(true,((MainCharacter)persons.get(0)).getParty()
				.contains((MainCharacter)persons.get(0)));

	}

	@Test
	public void setUpPartiesHeadOfChairIncludedTest(){
		model.activatePersonFactory();
		model.setUpParties();
		//I know from the enum in personFactory that the first HoC is on pos 3

		ArrayList<Person> persons = model.getCreatedCharacters();
		assertEquals(true,((HeadOfChair)persons.get(3)).getParty()
				.contains((HeadOfChair)persons.get(3)));

	}

	@Test
	public void loadedMapsTests(){
		//see if the maps where correctly loaded from the file to the mapRawData multi-array
	}

	@Test
	public void createdAbilitiesTest(){
		//see if the Abilities were created correctly
	}

	@Test
	public void getPersonByNameTest(){

	}

	@Test
	public void getPersonByClassTest(){

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