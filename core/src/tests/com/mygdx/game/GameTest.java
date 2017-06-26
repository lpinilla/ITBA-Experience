package tests.com.mygdx.game;

import com.mygdx.game.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GameTest {
    Game ng;
    @Before
    public void Before() throws IOException{
       ng = new Game();
    }

    @Test
    public void SaveGameTest() throws IOException {
       ng.saveGame(); //saves in savedGame.txt
        FileReader fr = new FileReader("savedGame.txt");
        BufferedReader br = new BufferedReader(fr);
    //    assertEquals(ng.getModel().getPersons().get(0).toString(), br.readLine());

    }
}
