package model;

/**
 * Created by Nacho on 17/06/17.
 */
public class PersonNotFoundException extends RuntimeException {
    PersonNotFoundException(String s){
        super(s);
    }
}
