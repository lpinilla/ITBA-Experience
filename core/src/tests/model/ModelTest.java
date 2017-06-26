package tests.model;
/**
 * @author: ividaurreta
 */

import static org.junit.Assert.assertEquals;
import model.Model;
import model.abilities.Abilities;
import model.abilities.AbilityFactory;
import model.persons.HeadOfChair;
import model.persons.Hero;
import model.persons.MainCharacter;
import model.persons.Person;
import model.persons.PersonFactory;
import model.persons.Professor;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
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
	public void readMapTest() throws IOException {
		model.loadMaps(fr);
		model.generateMaps();

		FileReader fr2 = new FileReader("assets/maps2.txt");
		BufferedReader bf = new BufferedReader(fr2);
		int mapqnty = Integer.valueOf(bf.readLine());
		while (mapqnty > 0){
			String name = bf.readLine();
			Integer[][] rawData=model.getRawData().get(name);
			int width = Integer.valueOf(bf.readLine());
			int height = Integer.valueOf(bf.readLine());
			for (int i = height-1 ; i>=0 ; i--) {
				for (int j = 0; j < width; j++) {
					assertEquals(rawData[i][j], Integer.valueOf((bf.read()) - '0'));
				}
				bf.read();
			}
			mapqnty--;
			}
	}

	@Test (expected= IOException.class)
	public void readBrokenMapTest() throws IOException {
		FileReader fr2 = new FileReader(new File("assets/brokenMap.txt"));
		model.loadMaps(fr2);
		model.generateMaps();

	}

	@Test
	public void generateMapsTest() throws IOException{
		model.loadMaps(fr);
		int oldqnt=model.getMapHandler().getSize();
		model.generateMaps();

		//How many maps should've been added
		FileReader fr2 = new FileReader("assets/maps2.txt");
		BufferedReader bf = new BufferedReader(fr2);
		int mapsAdded =Integer.valueOf(bf.readLine());

		assertEquals(oldqnt + mapsAdded,model.getMapHandler().getSize());
	}


 
}