package map;

/* Faltan ver detalles de esta clase,
 * Ver si tiene que ser clase abs o interfaz
 * ver que métodos tienen que heredar sus subclases
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