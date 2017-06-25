package model.persons;

public class WrongAbilityException extends RuntimeException{

    public WrongAbilityException(){
        super();
    }

    public WrongAbilityException(String msg){
        super(msg);
    }
}