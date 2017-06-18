package map;

/**
 * Created by Nacho on 18/06/17.
 */
public class MapNotFoundException extends RuntimeException{
    public MapNotFoundException(String s) {
        super(s);
    }
}
