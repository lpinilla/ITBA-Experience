package model.map;
import controllerView.map.Tile;
import java.util.HashMap;

public class GameMap {

    private HashMap<Position2D, Tile> map;
    private int size;
    private int height, width; //how many tiles has in its witdh and height
    private String name;

    /**
     * Creates a new map. A map is a group of Tiles put together.
     * @param name name of the map
     * @param height number of tiles on a vertical line
     * @param width number of tiles on a horizontal line
     */
    public GameMap(String name, int height, int width){
        this.map = new HashMap<Position2D, Tile>();
        this.name = name;
        this.height = height;
        this.width = width;
        this.size = this.height * this.width;
    }


    public HashMap<Position2D, Tile> getMap(){
        return this.map;
    }

    public int getSize(){
        return this.size;
    }

    /**
     * Associates a tile to a specific location in the map
     * @param pos
     * @param tile
     */
    public void add(Position2D pos, Tile tile){
        this.map.put(pos, tile);
    }

    /**
     * Returns what Tile is present in key
     * @param key A position in the map
     * @return The tile at the key position
     */
    public Tile get(Position2D key){
        return this.map.get(key);
    }

    public String getName(){
        return this.name;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }
}