package map;


import java.util.HashMap;
import java.util.ArrayList;


/*Objeto que ser encarga de crear, cambiar y
 * modificar (o indicar que tienen que modificar) -> se pueden modificar?
 * los mapas
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
        this.currentMap = new GameMap("initial",0,0); //debería arrancar en el mainmap
        this.size = 0;
    }

    /*Multiple maps creator, podríamos crear una clase interna "MapInfo" que tenga
    *todo lo que necesita este método para funcionar
    */
    //TODO:reparar, el mapa necesita que le pasen dimensiones, data y nombre
    public void createMaps(ArrayList<int[][]> mapsdata, ArrayList<String> names){
        for(int t = 0; t < mapsdata.size(); t++){
            //createParticularMap(names.get(t),)
        }
        this.size += mapsdata.size();
    }

    //Create a particular map
    public void createParticularMap(String name, int height,
                                    int width, int[][] mapdata){
        GameMap map = new GameMap(name, height, width);
        for(int i = 0; i < map.getHeight(); i++){ //era al revez height y width?
            for(int j = 0; j < map.getWidth(); j++){
                if(mapdata[i][j] == 1){ //XXX:ACA esta lo imperativo
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
    public void removeMap(String mapName){
        if(!isEmpty()){
            maps.remove(mapName);
        }else{
            throw new MapsCollectionEmptyException("There is no map loaded to remove!");
        }
        this.size--;
    }

    //Podría utilizar este metodo con un array y reemplazar el metodo de arriba

    /**
     * Same as removeMap only that removes an ArrayList of maps
     * @param mapNames
     */
    public void removeMaps(ArrayList<String> mapNames){
        if(!isEmpty()){
            for(int i = 0; i < mapNames.size(); i++){
                maps.remove(mapNames.get(i));
            }
        }else{
            throw new MapsCollectionEmptyException("There is no map loaded to remove!");
        }
        this.size -= mapNames.size();
    }

    /**
     * Changes current working map
     * @param mapName
     */
    //TODO Diferencia con setCurrentMap?
    public void changeMap(String mapName){
        this.currentMap = maps.get(mapName);
    }

    public GameMap getCurrentMap(){
        return this.currentMap;
    }

    public void setCurrentMap(String name){
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