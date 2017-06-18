package model;
import map.*;
import person.*;


import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.ArrayList;

/**
* @autor: lpinilla
*/

public class Model{

	private ViewController vc;
	private MapHandler mh;
	private HashMap<String, Integer[][]> mapsRawData;
	private ArrayList<Person> persons;
	private ArrayList<Abilities> abilities;
	private boolean[] bossWon;
	private FileReader mapsFile;
	private BufferedReader br;

	public Model(){
		this.vc = null;
		this.mh = new MapHandler();
		this.mapsRawData = new HashMap<String, Integer[][]>();
		this.persons = new ArrayList<Person>();
		this.abilities = new ArrayList<Abilities>();
		this.mapsFile = null;
		this.br = null;
		this.bossWon = new boolean[] { false, false, false};
	}

	public Model(MainCharacter mc,  boolean[] bossWon, String currentMap){
		this.vc = null;
		this.mh = new MapHandler();
		this.mapsRawData = new HashMap<String, Integer[][]>();		
		this.persons = new ArrayList<Person>();
		replaceMainC(mc);
		this.abilities = new ArrayList<Abilities>();
		this.mapsFile = null;
		this.br = null;
		this.bossWon = bossWon;
		mh.setCurrentMap(currentMap);
	}

	private void replaceMainC(MainCharacter mc){
		for( Person p : this.persons){
			if(p.equals(mc)){
				p = mc;
			}
		}
	}

	
	public void setUpViewController(ViewController vc){
		this.vc = vc;
	}

	/**
	* Method to start the factory that creates all the People
	*/
/*	public void activatePersonFactory(){
		PersonFactory pFactory = new PersonFactory();
		pFactory.generatePeople(persons);
	}
*/
	/**
	* Method to start the factory that creates all the Abilities
	*/
/*	public void activateAbilityFactory(){
		AbilityFactory aFactory = new AbilityFactory();
		aFactory.generateAbilities(abilities);
	}
*/
	/**
	* This method was created so that the MainCharacter and
	* the HeadOfChair could be added to their own partys
	*/
	public void setUpParties(){
		Person mainC = getPersonByClass("MainCharacter", 0);
		((MainCharacter)mainC).addHeroToParty((Hero)mainC);
		for(int i = 0; i < 3; i++){
			Person hoC = getPersonByClass("HeadOfChair", i);	
			((HeadOfChair)mainC).addProfessorToParty((Enemy)hoC);
		}
	}

	/*
	* This method is an auxiliary method used by the method above
	* so that it can find a Person given its Class
	* @param objClass: class of the Person
	* @param ignoreNumber: The number of People it has to ignore if
	* there are multiple instances of a same class
	*/
	private Person getPersonByClass(String objClass, int ignoreNumber){
		int auxIgnore = 0;
		for(Person p : persons){
			if(p.getClass().toString() == objClass){
				if(auxIgnore == ignoreNumber){
					return p;				
				}else{
					auxIgnore++;
				}			
			}
		}
		throw new PersonNotFoundException("Person of given class was not found");
	}

	public boolean[] getBossWon(){
		return this.bossWon;
	}

	/*
	* Method that assigns the file where the Maps are stored
	* @param f, The FileReader given by the Game
	*/
	public void loadMaps(FileReader f){
		this.mapsFile = f;
		this.br = new BufferedReader(mapsFile);
	}

	/*
	* Method that reads how many maps are in the file and calls
	* that many times a method to read a Map from the file
	*/
	public void generateMaps() throws IOException {
		Integer mapCount;
		try{
			mapCount = Integer.valueOf(br.readLine());
			for(int i = 0; i < mapCount; i++){
				readMap(br);								
			}
		}catch(IOException e){
			throw new IOException("Game Data Corrupted");			
		}
	}

	/*
	* Method that reads the map from the file and creates
	* the rawData of them and calls the MapHandler to create
	* the maps, finally it stores the rawData in the Map
	* @param br: BufferredReader given by the method above
	*/
	private void readMap(BufferedReader br) throws IOException {
		String mapName = br.readLine();
		Integer width = Integer.valueOf(br.readLine());
		Integer height = Integer.valueOf(br.readLine());
		char c; // \n
		Integer[][] mapRawData = new Integer[width][height];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if(Character.isLetter(c = (char) br.read())){
					throw new IOException("Game Data Corrupted");
				}
				mapRawData[i][j] = Integer.valueOf((char) br.read()) - '0';
			}
			c = (char) br.read();
		}
		mh.createParticularMap(mapName, height, width, mapRawData);
		mapsRawData.put(mapName, mapRawData);
	}

	public MapHandler getMapHandler(){
		return this.mh;
	}



}
