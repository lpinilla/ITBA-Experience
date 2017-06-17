public class Model{

	private ViewController vc;
	private MapHandler mh;
	private Map<String, Integer[][]> mapsRawData;
	private ArrayList<Person> persons;
	private ArrayList<Ability> abilities;
	private int combatsWon;
	private FileReader mapsFile;
	BufferedReader br;

	public class Model(){
		this.vc = null;
		this.mh = new MapHandler();
		this.mapsRawData = new HashMap<String, int[][]>();
		this.combatsWon = 0;
		this.persons = new ArrayList<Person>();
		this.abilities = new ArrayList<Ability>();
		this.mapsFile = null;
		this.br = null;
	}

	public void setUpViewController(ViewController vc){
		this.vc = vc;
	}

	public void activatePersonFactory(){
		PersonFactory pFactory = new PersonFactory();
		pFactory.generatePeople(persons);
	}

	public void activateAbilityFactory(){
		AbilityFactory aFactory = new AbilityFactory();
		aFactory.generateAbilities(abilities);
	}

	public void setUpParties(){
		Person mainC = getPersonByClass(MainCharacter, 0);
		mainC.addHeroToParty(mainC);
		for(int i = 0; i < 3; i++){
			Person hoC = getPersonByClass(HeadOfChair, i);	
			hoC.addProfessorToParty(hoC);
		}
	}

	private Person getPersonByClass(Class class, int ignoreNumber){
		int auxIgnore = 0;
		for(Person p : persons){
			if(p.getClass() == class){
				if(auxIgnore == ignoreNumber){
					return p;				
				}else{
					auxIgnore++;
				}			
			}
		}
	}

	public void winCombat(){
		this.combatsWon++;
	}

	public int getCombatsWon(){
		return this.combatsWon;
	}

	public void loadMaps(FileReader f){
		this.mapsFile(f);
		this.br = new BufferedReader(f);
	}

	public void generateMaps(){
		Integer mapCount;
		try{
			mapCount = Integer.valueOf(br.readLine()); //cantidad de mapas en el archivo
			for(int i = 0; i < mapCount; i++){
				readMap(br);								
			}
		}catch(IOException e){
			throw new IOException("Game Data Corrupted");			
		}
	}

	private void readMap(BufferedReader br){
		String mapName = br.readLine();
		int width = br.readLine();		
		int height = br.readLine();
		char c; // \n
		Integer[][] mapRawData = new Integer[width][height];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){ //se podria verificar si hay algun caracter no permitido
				mapRawData[i][j] = Integer.valueOf((char) br.read()) - '0';
			}
			c = (char) br.read();
		}
		mh.createParticularMap(mapName, height, width, mapRawData);
		mapsRawData.put(mapName, mapRawData);
	}



}