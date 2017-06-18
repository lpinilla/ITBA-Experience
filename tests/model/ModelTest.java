package tests.model;
import static org.junit.Assert.assertEquals;

import map.Position2D;
import model.Model;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

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
		//see if the persons are in the ArrayList
	}

	@Test
	public void setUpPartiesTest(){
		//see if MainC and HoC are on their collection
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