public class Game{

	private Model model;
	private ViewController vc;
	private File mapsFile; //falta el guardado de archivos
	
	public Game() throws IOException{
		this.model = new Model();
		this.vc = new ViewController();
		this.model.setUpViewController(vc);
		this.vc.setUpModel(model);
		mapsFile = new File("./testFile.txt");
		if(!mapsFile.exists()){
			throw new IOException("Game File Missing, Game Corrupted");
		}
		this.model.loadMaps(new FileReader(mapsFile));
		this.model.generateMaps();
	}




}