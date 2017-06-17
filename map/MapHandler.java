package map;

import java.util.HashMap;
import java.util.ArrayList;

/*Objeto que ser encarga de crear, cambiar
 *y devolver los mapas para que puedan ser
 *utilzados por el juego
 */

/**
 *@author: lpinilla
 */

public class MapHandler {

    private HashMap<String,GameMap> maps; //map of maps o.O
    private GameMap currentMap; //user's map
    private int size;

    public MapHandler(){
        this.maps = new HashMap<String, GameMap>();
        this.currentMap = new GameMap("initial",0,0); //should be main map or start in null
        this.size = 0;
    }

    /**
    * Creates a GameMap from an array of ints and adds it to the collection
    * of Maps so they can be later used.
    * @param name: name of the map, which will be the key in the map
    * @param height
    * @param width
    * @param mapData: array of ints that indicate what object has to be
    * created in that specific position.
    * @throws InvalidArgumentException if mapData is null or height or 
    * width are 0
    */
    public void createParticularMap(String name, int height,
                                    int width, int[][] mapData) throws IllegalArgumentException{
        if(mapData == null || name == null || height == 0 || width == 0){
            throws new IllegalArgumentException();   
        }
        GameMap map = new GameMap(name, height, width);
        for(int i = 0; i < map.getHeight(); i++){ //era al revez height y width?
            for(int j = 0; j < map.getWidth(); j++){
                if(mapData[i][j] == 1){ //XXX:ACA esta lo imperativo
                    map.add(new Position2D(i,j),
                            new Tile(false, false, new Wall()));
                }else{
                    map.add(new Position2D(i,j),
                            new Tile(true, true, new CommonGround()));
                }
            }
        }
        this.size++;
        maps.put(name, map);
    }

    /**
     * Removes mapName from the collection.
     * @param mapName
     * @throws MapsCollectionEmptyException if mapName isn't in the collection.
     */
    public void removeMap(String mapName) throws MapsCollectionEmptyException{
        if(isEmpty()){
            throw new MapsCollectionEmptyException("There is no map loaded to remove!");
        }
        if(!maps.containsKey(mapName)){
            throw new RuntimeException("No such map in collection"); // nueva exception?             
        }else{
              maps.remove(mapName);
        }
        this.size--;
    }

    /**
     * Same as removeMap only that removes an ArrayList of maps
     * @param mapNames
     */
    public void removeMaps(ArrayList<String> mapNames) throws RuntimeException{ //cambiar
        if(!isEmpty()){
            for(int i = 0; i < mapNames.size(); i++){
                if(!maps.containsKey(mapNames[i])){
                    throw new RuntimeException("No such map in the Collection"); //crear nueva excepcion?
                }else{
                    maps.remove(mapNames.get(i));
                }
            }
        }else{
            throw new MapsCollectionEmptyException("There is no map loaded to remove!");
        }
        this.size -= mapNames.size();
    }

    public GameMap getCurrentMap(){
        return this.currentMap;
    }

    /**
     * Changes current working map
     * @param mapName
     */
    public void setCurrentMap(String name) throws IllegalArgumentException{
        if(name == null || !maps.containsKey(name)){
            throw new IllegalArgumentException();   
        }
        this.currentMap = this.maps.get(name);
    }

    public int getSize(){
        return this.size;
    }

    /**
     * Checks if there is any map loaded in the collection
     * @return true if there is no map loaded, false if there is.
     */
    public boolean isEmpty(){
        return this.size == 0;
    }

}
