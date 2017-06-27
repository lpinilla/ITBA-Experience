package controllerView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by tdallas on 26/6/2017.
 */
public class InfoScreen implements Screen {
	
	private Texture info;
	private Texture backActive;
	private Texture backInactive;
	private ControllerView game;
	private SpriteBatch batch;
    private static final int BUTTONWIDTH = 280;
    private static final int BUTTONHEIGHT = 140;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

	public InfoScreen(ControllerView game){
		info = new Texture("info.png");
		batch = new SpriteBatch();
		backActive = new Texture("backActive.png");
		backInactive = new Texture("backInactive.png");
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		 Gdx.gl.glClearColor(1, 0,0, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        batch.begin();
	        batch.draw(info, 0, 0, WIDTH, HEIGHT);
	        
	        int auxX = 50;
	        int auxY = 50;
	        if(Gdx.input.getX() >= auxX && Gdx.input.getX() <= 75 + BUTTONWIDTH
	                && HEIGHT - Gdx.input.getY() >= HEIGHT/30 &&   HEIGHT - Gdx.input.getY() <=  BUTTONHEIGHT) {
	            batch.draw(backActive, auxX, auxY, BUTTONWIDTH, BUTTONHEIGHT);
	            if(Gdx.input.isTouched()){
	                game.setMenuScreen();
	            }
	        }
	        else{
	            batch.draw(backInactive, auxX, auxY, BUTTONWIDTH, BUTTONHEIGHT);
	        }
	        
	        batch.end();
	}

	@Override
	public void dispose() {
		info.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
