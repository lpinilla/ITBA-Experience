package model.persons;

/**
 * Created by agustin on 20/06/17.
 */
public class WrongHeroException extends RuntimeException{

        public WrongHeroException(){
            super();
        }

        public WrongHeroException(String msg){
            super(msg);
        }
}

