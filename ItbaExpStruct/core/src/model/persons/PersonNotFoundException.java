package model.persons;

/**
 * Created by agustin on 23/06/17.
 */
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String s){
        super(s);
    }
}
