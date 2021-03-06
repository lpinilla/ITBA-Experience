package tests.com.mygdx.game;

/**
 * @author: ividaurreta
 */

import com.mygdx.game.Game;
import model.persons.MainCharacter;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

public class GameTest {
    Game ng;
    @Before
    public void Before() throws IOException{
       ng = new Game();
    }

    @Test
    public void SaveGameTest() throws IOException, ClassNotFoundException {
       ng.saveGame(); //saves in savedGame.txt
       ObjectInputStream in = new ObjectInputStream(
               new BufferedInputStream(new FileInputStream(
                       "savedGame.txt")));
       assertEquals(ng.getModel().getPersons().get(0).getClass(),
               ((MainCharacter)in.readObject()).getClass());

    }

    @Test
    public void loadGameTest() throws IOException, ClassNotFoundException {
        ng.saveGame();
        System.out.println(ng.getModel().getBossWon()[0]);
        ng.getModel().setBossWin("María Laura Noni");
        boolean[] oldBossWon = ng.getModel().getBossWon();
        Game.loadGame();
        boolean[] newBossWon =ng.getModel().getBossWon();
        //assertNotEquals(oldBossWon[0],newBossWon[0]);
    }
}
