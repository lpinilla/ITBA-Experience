package person;

public class InvalidValueException extends RuntimeException {
    InvalidValueException(String s){
        super(s);
    }
}
