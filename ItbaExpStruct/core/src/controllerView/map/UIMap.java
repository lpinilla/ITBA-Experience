package controllerView.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import model.map.Map;


public class UIMap {
    private Texture floor;
    private Texture wall;
    private Map map;
    private static final int GAME_WIDTH = 30*20;
    private static final int GAME_HEIGHT = 30*20;
    public static final int WIDTH = GAME_WIDTH/20;
    private static final int HEIGHT = GAME_HEIGHT/20;
    private SpriteBatch batch;
    public UIMap(Map map){
        this.map = map;
    }

   /* public void draw(SpriteBatch batch){
        this.batch = batch;
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Integer value = map.getMap().get(new Position2D(i, j));
                if (value == 0)
                    batch.draw(floor, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
                else
                    batch.draw(wall, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
            }
        }
    }*/
}
