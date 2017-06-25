package model.map;

/**
 * Created by agustin on 23/06/17.
 */
public class MapNotFoundException extends RuntimeException{
    public MapNotFoundException(String s) {
        super(s);
    }
}