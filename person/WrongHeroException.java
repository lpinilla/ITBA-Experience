package person;
public class WrongHeroException extends RuntimeException{

    public WrongHeroException(){
        super();
    }

    public WrongHeroException(String msg){
        super(msg);
    }

}