package model.map;

import controllerView.map.GroundType;
import controllerView.map.TerrainType;
import controllerView.map.Tile;

import java.util.HashMap;


public class Map {
    private HashMap<Position2D, Tile> map;


    public Map(){
        map = new HashMap<Position2D,Tile>();
        TerrainType type = new GroundType("Floor");
        //TerrainType type2 = new WallType("Wall");
        for(int i = 0; i< 20; i++) {
            for (int j = 0; j < 20; j++) {

                map.put(new Position2D(i,j),new Tile(true,false,type));
            }
        }
        for (int j = 0; j < 20; j++) {

            map.put(new Position2D(j,19),new Tile(false,false,type));
            map.put(new Position2D(j,0),new Tile(false,false,type));
            map.put(new Position2D(19,j),new Tile(false,false,type));
            map.put(new Position2D(0,j),new Tile(false,false,type));

        }
        map.put(new Position2D(0,1),new Tile(false,false,type));
        map.put(new Position2D(5,6),new Tile(false,false,type));
        map.put(new Position2D(10,10),new Tile(false,false,type));




    }

    public HashMap<Position2D, Tile> getMap(){
        return map;
    }
}
