package map;

public class Tile {

    private boolean walkable, sleepable;
    private TerrainType type;

    /**
     * Creates a tile
     * @param walkable checks if the MainCharacter can walk through de tile
     * @param sleepable checks if the mainCharacter can sleep here
     * @param type Checks what TerrainType the tile is
     */
    public Tile(boolean walkable,boolean sleepable,TerrainType type){
        this.walkable = walkable;
        this.sleepable = sleepable;
        this.type = type;
    }

    public boolean isWalkable(){
        return this.walkable;
    }

    public boolean isSleepable(){
        return this.sleepable;
    }

    public TerrainType getType(){
        return this.type;
    }
}