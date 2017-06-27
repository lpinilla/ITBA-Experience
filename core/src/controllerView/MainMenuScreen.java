package controllerView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by agustin, tdallas on 31/05/17.
 */
public class MainMenuScreen implements Screen {
    private Texture exitActive;
    private Texture exitInactive;
    private Texture playActive;
    private Texture playInactive;
    private Texture infoGameInactive;
    private Texture infoGameActive;
    private Texture title;
    private Texture floor;
    private ControllerView game;
    private SpriteBatch batch;
    private static final int BUTTONWIDTH = 280;
    private static final int BUTTONHEIGHT = 140;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

    public MainMenuScreen(ControllerView game){
        this.game = game;
        infoGameActive = new Texture("gameInfoActive.png");
        infoGameInactive = new Texture("gameInfoInactive.png");
        exitActive = new Texture("exitGameActive.png");
        exitInactive = new Texture("exitGameInactive.png");
        playActive = new Texture("newGameActive.png");
        playInactive = new Texture("newGameInactive.png");
        batch = new SpriteBatch();
        title = new Texture("menuFondo.png");
        floor = new Texture("floor.jpg");
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        for(int i = 0; i<35 ; i++){
            for(int j = 0; j<35; j++){
                batch.draw(floor, i*30, j*30, 30, 30);
            }
        }
        batch.draw(title, 0, 0, WIDTH,HEIGHT);
        
        if(Gdx.input.getX() >= WIDTH/2 - BUTTONWIDTH/2 && Gdx.input.getX() <= WIDTH/3 - BUTTONWIDTH/3 + BUTTONWIDTH
                && HEIGHT - Gdx.input.getY() >= HEIGHT/30 &&   HEIGHT - Gdx.input.getY() <=  BUTTONHEIGHT) {
            batch.draw(exitActive, WIDTH / 2 - BUTTONWIDTH / 2, HEIGHT/20, BUTTONWIDTH, BUTTONHEIGHT);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }
        else{
            batch.draw(exitInactive, WIDTH / 2 - BUTTONWIDTH / 2, HEIGHT/20, BUTTONWIDTH, BUTTONHEIGHT);
        }

        
        if(Gdx.input.getX() >= WIDTH/2 - BUTTONWIDTH/2 && Gdx.input.getX() <= WIDTH/2 - BUTTONWIDTH/2 + BUTTONWIDTH
                && Gdx.input.getY() <=  HEIGHT - 5*(HEIGHT/20) && Gdx.input.getY() >=  HEIGHT - (5*(HEIGHT/20) + BUTTONHEIGHT-HEIGHT/30)){
            batch.draw(infoGameActive, WIDTH / 2 - BUTTONWIDTH / 2, 5*(HEIGHT/20), BUTTONWIDTH, BUTTONHEIGHT);
            if(Gdx.input.isTouched()){
                dispose();
                game.setInfoScreen();
            }
        }
        else{
            batch.draw(infoGameInactive, WIDTH / 2 - BUTTONWIDTH / 2, 5*(HEIGHT/20), BUTTONWIDTH, BUTTONHEIGHT);
        }

        
        if(Gdx.input.getX() >= WIDTH/2 - BUTTONWIDTH/2 && Gdx.input.getX() <= WIDTH/2 - BUTTONWIDTH/2 + BUTTONWIDTH
                && Gdx.input.getY() <=  HEIGHT - 9*(HEIGHT/20) && Gdx.input.getY() >= HEIGHT - (9*(HEIGHT/20) + BUTTONHEIGHT-(HEIGHT/30))){
            batch.draw(playActive, WIDTH / 2 - BUTTONWIDTH / 2, 9*(HEIGHT/20), BUTTONWIDTH, BUTTONHEIGHT);
            if(Gdx.input.isTouched()){
                dispose();
                game.setExplorerScreen();
              //  game.setCombatScreen();
            }
        }
        else{
            batch.draw(playInactive, WIDTH /2 - BUTTONWIDTH / 2, 9*(HEIGHT/20), BUTTONWIDTH, BUTTONHEIGHT);
        }
        batch.end();
    }



    @Override
    public void dispose() {
        playActive.dispose();
        playInactive.dispose();
        exitActive.dispose();
        exitInactive.dispose();
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
