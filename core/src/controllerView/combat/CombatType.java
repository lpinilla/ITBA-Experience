package controllerView.combat;

import controllerView.map.TerrainType;
import model.persons.HeadOfChair;

/**
 * Created by agustin on 18/06/17.
 */
public class CombatType extends TerrainType {
    private HeadOfChair hoc;
    public CombatType(String name){
        super(name);
        this.hoc = null;
    }

    public void setHoc(HeadOfChair hoc){
        this.hoc = hoc;
    }

    public HeadOfChair getHoc(){
        return hoc;
    }
}
