package controllerView.map;

import model.map.Position2D;

/**
 * Created by agustin on 18/06/17.
 */
public class PassingType extends TerrainType{
    private String map;
    private Position2D nextPos;
    public PassingType(String name){
        super(name);
        map = null;
        nextPos = null;
    }

    public void setMap(String m){
        map = m;
    }

    public String getMap(){
        return map;
    }

    public void setPos(Position2D pos){
        nextPos = pos;
    }

    public Position2D getPos(){
        return nextPos;
    }
}
