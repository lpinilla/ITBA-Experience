package controllerView.map;

/**
 * Created by agustin on 18/06/17.
 */
public abstract class TerrainType {
    private String name;

    public TerrainType(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
