package model;

import controllerView.ControllerView;
import model.abilities.Abilities;
import model.map.MapHandler;
import model.persons.Enemy;
import model.persons.HeadOfChair;
import model.persons.Hero;
import model.persons.MainCharacter;
import model.persons.Person;
import model.persons.PersonFactory;
import model.persons.PersonNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * @autor: lpinilla
 */

public class Model{

    private ControllerView vc;
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

    public Model(MainCharacter mc, boolean[] bossWon, String currentMap){
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


    public void setUpViewController(ControllerView vc){
        this.vc = vc;
    }

    /**
     * Method to start the factory that creates all the People
     */
    public void activatePersonFactory(){
        PersonFactory pFactory = new PersonFactory();
        int i=0;
        for(PersonFactory.People p: PersonFactory.People.values()) {
            persons.add(i, PersonFactory.createPerson(p));
            i += 1;
        }
    }

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
        ((MainCharacter)mainC).addHeroToParty((Hero) mainC);
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
    public Person getPersonByClass(String objClass, int ignoreNumber){
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
            mh.setUpDoors();
            mh.setUpHocs();
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
       // System.out.println(mapName + "\n");
        for(int i =  height - 1; i >= 0 ; i--){
            for(int j = 0; j < width; j++){
                if(Character.isLetter(c = (char) br.read())){
                    throw new IOException("Game Data Corrupted");
                }
                mapRawData[i][j] = Integer.valueOf((char)c - '0');
            }
            c = (char) br.read();
        }
        //printMap(mapRawData,width,height);
       mh.createParticularMap(mapName, height, width, mapRawData);
        mapsRawData.put(mapName, mapRawData);
    }
    void printMap(Integer[][]map, int w, int h) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(map[i][j]);
            }
            System.out.print("\n");
        }
    }

    public MapHandler getMapHandler(){
        return this.mh;
    }



}
