package map;

public class TileFactory{

	public static enum Tiles {
		ground, wall, furniture, passenger, combat, library;
	}
	
	public static Tile createTile(Tiles tTile){
		switch(tTile){
			case ground:
				return new GroundTile();
			case wall:
				return new WallTile();
			case furniture:
				return new FurnitureTile();
			case passenger:
				return new PassengerTile();
			case combat:
				return new CombatTile();
			case library:
				return new LibraryTile();
		}
	}

	private static class GroundTile extends Tile{
		public GroundTile(){
			super(true, false, new Ground	Type());
		}
	}

	private static class WallTile extends Tile{
		public WallTile(){
			super(false, false, new Wall());
		}
	}

	private static class FurnitureTile extends Tile{
		public FurnitureTile(){
			super(false, false, new FurnitureType());
		}
	}
	//TODO me gustaría cambiarle el nombre a passingTile, puedo?
	private static class PassengerTile extends Tile{
		public PassengerTile(){
			super(true, false, new PassingType());
		}
	}

	private static class CombatTile extends Tile{
		public CombatTile(){
			super(true, false, new CombatType());
		}
	}

	private static class LibraryTile extends Tile{
		public LibraryTile(){
			super(true, true, new LibraryType());
		}
	}


}
