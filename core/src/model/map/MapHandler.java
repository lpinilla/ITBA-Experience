package model.map;

import controllerView.combat.CombatType;
import controllerView.map.Chair;
import controllerView.map.Empty;
import controllerView.map.GroundType;
import controllerView.map.PassingType;
import controllerView.map.Shelf;
import controllerView.map.Table;
import controllerView.map.Tile;
import controllerView.map.Wall;
import model.abilities.Abilities;
import model.persons.HeadOfChair;
import model.persons.Type;
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
     * @throws IllegalArgumentException if mapData is null or height or
     * width are 0
     */
    public void createParticularMap(String name, int height,
                                    int width, Integer[][] mapData) throws IllegalArgumentException{
        if(mapData == null || name == null || height == 0 || width == 0){
            throw new IllegalArgumentException();
        }
        GameMap map = new GameMap(name, height, width);
        for(int i = 0; i < map.getHeight(); i++){ //era al revez height y width?
            for(int j = 0; j < map.getWidth(); j++){
                switch (mapData[i][j]) {
                    case 0:
                        map.add(new Position2D(j,i),new Tile(true,false,new GroundType("Floor")));
                        break;
                    case 1:
                        map.add(new Position2D(j,i),new Tile(false,false,new Wall()));
                        break;
                    case 2:
                        map.add(new Position2D(j,i),new Tile(true,false,new PassingType("Pasadizo")));
                        break;
                    case 3:
                        map.add(new Position2D(j,i),new Tile(false,false,new Table()));
                        break;
                    case 4:
                        map.add(new Position2D(j,i),new Tile(false,false,new Chair()));
                        break;
                    case 5:
                        //aca deberia ser combate y setearse el hoc
                        map.add(new Position2D(j,i),new Tile(true,false,new CombatType("Pelea")));
                        break;
                    case 7:
                        map.add(new Position2D(j,i),new Tile(false,false,new Shelf()));
                        break;
                    case 8:
                        map.add(new Position2D(j,i),new Tile(true,true,new GroundType("Floor")));
                        break;
                    case 9:
                        map.add(new Position2D(j,i),new Tile(false,false,new Empty()));
                        break;
                }
            }
        }
        this.size++;
        maps.put(name, map);
    }

    public void setUpDoors(){
        GameMap m = maps.get("bar");
        setUpBarDoors(m);
        m = maps.get("biblio");
        setUpLibDoors(m);
        m = maps.get("hall");
        setUpHallDoors(m);
        m = maps.get("SUM");
        setUpSUMDoors(m);
    }

    private void setUpBarDoors(GameMap m){
        /*Tile t = m.get(new Position2D(19,1));
        System.out.println(t.getType());
        */Tile t = m.get(new Position2D(19,1));
        ((PassingType)(t.getType())).setMap("biblio");
        ((PassingType)(t.getType())).setPos(new Position2D(1,1));
        t = m.get(new Position2D(19,2));
        ((PassingType)(t.getType())).setMap("biblio");
        ((PassingType)(t.getType())).setPos(new Position2D(1,2));

    }

    private void setUpLibDoors(GameMap m){
        //left doors
        Tile t = m.get(new Position2D(0,1));
        ((PassingType)(t.getType())).setMap("bar");
        ((PassingType)(t.getType())).setPos(new Position2D(18,1));
        t = m.get(new Position2D(0,2));
        ((PassingType)(t.getType())).setMap("bar");
        ((PassingType)(t.getType())).setPos(new Position2D(18,2));
        //right doors
        t = m.get(new Position2D(19,1));
        ((PassingType)(t.getType())).setMap("hall");

        ((PassingType)(t.getType())).setPos(new Position2D(1,1));
        t = m.get(new Position2D(19,2));
        ((PassingType)(t.getType())).setMap("hall");
        ((PassingType)(t.getType())).setPos(new Position2D(1,2));
    }


    private void setUpHallDoors(GameMap m){
        //left doors
        Tile t = m.get(new Position2D(0,1));
        ((PassingType)(t.getType())).setMap("biblio");
        ((PassingType)(t.getType())).setPos(new Position2D(18,1));
        t = m.get(new Position2D(0,2));
        ((PassingType)(t.getType())).setMap("biblio");
        ((PassingType)(t.getType())).setPos(new Position2D(18,2));
        //right doors
        t = m.get(new Position2D(19,1));
        ((PassingType)(t.getType())).setMap("SUM");

        ((PassingType)(t.getType())).setPos(new Position2D(1,1));
        t = m.get(new Position2D(19,2));
        ((PassingType)(t.getType())).setMap("SUM");
        ((PassingType)(t.getType())).setPos(new Position2D(1,2));
    }


    private void setUpSUMDoors(GameMap m){
        Tile t = m.get(new Position2D(0,1));
        ((PassingType)(t.getType())).setMap("hall");
        ((PassingType)(t.getType())).setPos(new Position2D(18,1));
        t = m.get(new Position2D(0,2));
        ((PassingType)(t.getType())).setPos(new Position2D(18,2));
        ((PassingType)(t.getType())).setMap("hall");
    }

    public void setUpHocs(){
        GameMap m = maps.get("SUM");
        Abilities ab = new Abilities("algo", 10, 10, 1);
        Type t1 = new Type("dePrueba");
        HeadOfChair hoc = new HeadOfChair("Agustin", 100, 200, 10, 10, new Position2D(10, 10),
                t1, 1, 50, ab);
        Tile t = m.get(new Position2D(18,18));
        ((CombatType)(t.getType())).setHoc(hoc);
        t = m.get(new Position2D(18,1));
        ((CombatType)(t.getType())).setHoc(hoc);
        t = m.get(new Position2D(1,12));
        ((CombatType)(t.getType())).setHoc(hoc);

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
            throw new MapNotFoundException("No such map in collection");
        }else{
            maps.remove(mapName);
        }
        this.size--;
    }

    /**
     * Same as removeMap only that removes an ArrayList of maps
     * @param mapNames
     */
    public void removeMaps(ArrayList<String> mapNames){
        if(!isEmpty()){
            for(int i = 0; i < mapNames.size(); i++){
                if(!maps.containsKey(mapNames.get(i))){
                    throw new MapNotFoundException("No such map in the Collection");
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
    public void setCurrentMap(String mapName) throws IllegalArgumentException{
        if(mapName == null || !maps.containsKey(mapName)){
            throw new IllegalArgumentException();
        }
        this.currentMap = this.maps.get(mapName);
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