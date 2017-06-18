import model.Model;
import model.ViewController;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Game{

	private Model model;
	private ViewController vc;
	private File mapsFile;
	private FileWriter fw;
	private FileReader fr;
	
	public Game() throws IOException {
		this.fw = null;
		this.fr = null;
		this.model = new Model();
		this.vc = new ViewController();
		this.model.setUpViewController(vc);
		//this.vc.setUpModel(model);
		mapsFile = new File("./testFile.txt");
		if(!mapsFile.exists()){
			throw new IOException("Game File Missing, Game Corrupted");
		}
		this.model.loadMaps(new FileReader(mapsFile));
		this.model.generateMaps();		
	}

	private Game(MainCharacter mc, boolean[] bossWon, String currentMap){ //from Load game
		this.fw = null;
		this.fr = null;
		this.model = new Model(mc, bossWon, currentMap);
		this.vc = new ViewController();
		this.model.setUpViewController(vc);
		//this.vc.setUpModel(model);
		mapsFile = new File("./testFile.txt");
		if(!mapsFile.exists()){
			throw new IOException("Game File Missing, Game Corrupted");
		}
		this.model.loadMaps(new FileReader(mapsFile));
		this.model.generateMaps();		
	}

	public void saveGame() throws IOException{
		try{
		ObjectOutputStream file = new ObjectOutputStream(
			new BufferedOutputStream(new FileOutputStream(
				"./savedGame.txt")));
		MainCharacter mc = this.model.getPersonByClass("MainCharacter",0);
		file.writeObject(mc);		
		}catch(IOException e){
			e.getMessage(); //cambiar
		}
		file.flush(); //necesario?
		file.close();
	}

	private void writeObject(ObjectOutputStream out) throws IOException{
		out.defaultWriteObject();		
		boolean[] boosWon = this.model.getBossWon();
		String currentMap = 
		this.model.getMapHandler().getCurrentMap().getName();
		out.writeObject(levelsPassed);
		out.writeObject(bossWon[0]);
		out.writeObject(bossWon[1]);
		out.writeObject(bossWon[2]);
	}

	public static void loadGame()throws IOException{
		ObjectInputStream in = new ObjectInputStream(
			new BufferedInputStream(new FileInputStream(
				"./savedGame.txt")));
		boolean[] bossWon = new boolean[3];
		String currentMap;
		MainCharacter mc;
		try{
			mc = in.readObject(in, bossWon, currentMap);
		}catch(IOException e){
			e.getMessage();
		}
		in.close();
		this.Game(mc, bossWon, currentMap);
	}

	private MainCharacter readObject(ObjectInputStream in, boolean[] bossWon,String currentMap) throws IOException{
		MainCharacter mc = in.defaultReadObject();
		bossWon[0] = in.readBoolean();
		bossWon[1] = in.readBoolean();
		bossWon[2] = in.readBoolean();
		currentMap = (String) in.readObject();
		return mc;
	}




}
