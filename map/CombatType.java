package map;

import person.HeadOfChair;

/**
 * Created by agustin on 18/06/17.
 */
public class CombatType extends TerrainType {
    private HeadOfChair hoc;
    public CombatType(String name, HeadOfChair hoc){
        super(name);
        this.hoc = hoc;
    }

    public HeadOfChair getHoc(){
        return hoc;
    }
}
