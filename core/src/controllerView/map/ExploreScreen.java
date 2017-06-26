package controllerView.map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Game;
import controllerView.ControllerView;
import controllerView.combat.CombatType;
import model.abilities.Abilities;
import model.combat.Combat;
import model.map.GameMap;
import model.map.Position2D;
import model.persons.HeadOfChair;
import model.persons.Hero;
import model.persons.MainCharacter;
import model.persons.Professor;
import model.persons.Type;

public class ExploreScreen implements Screen {
	private SpriteBatch batch;
	private Texture floor;
	private Texture woodenfloor;
	private Texture empty;
	private Texture chair;
	private Texture table;
	private Texture passingTile;
	private Texture combatTile;
	private Texture wall;
	private Animation walks [];
	private TextureRegion spriteSheet[][];
	private TextureRegion walkSheet[][];
	private float timepassed;
	private Game game;
	private MainCharacter player;
	private GameMap m;
	private static final int GAME_WIDTH = 1000;
	private static final int GAME_HEIGHT = 1000;
	public static final int WIDTH = GAME_WIDTH/20;
	public static final int CHARACTER_WIDTH = 52;
	private static final int HEIGHT = GAME_HEIGHT/20;
	private static final float SPEED = 205;
	private float x;
	private float y;
	private float sleeptime;
	private boolean sleep;
	private int walk;
	private ControllerView controller;

//	public ExploreScreen (ControllerView controller, Map map) {
public ExploreScreen (ControllerView controller, GameMap map, MainCharacter player) {
		timepassed = 0;
		sleeptime = 0;
		sleep = false;
		game = controller.getGame();
		this.player = player;
		x=(float)(player.getPosition().getX()*WIDTH);
		y = (float)(player.getPosition().getY()*HEIGHT);
		walk = 0;
		this.controller = controller;
		m = map;
		batch = new SpriteBatch();
		floor = new Texture("floor.jpg");
		woodenfloor = new Texture("woodenFloor.png");
		wall = new Texture("wall.png");
		empty = new Texture("empty.jpg");
		chair = new Texture("silla.png");
		table = new Texture("mesa.png");
		passingTile = new Texture("floor.jpg");
		combatTile = new Texture("combatTile.png");
		spriteSheet = TextureRegion.split(new Texture("CharacterSpriteSheet.png"),32,32);
		walkSheet = new TextureRegion[4][3];
		walks = new Animation[4];
		for(int i=0;i<4;i++){
			for(int j=0;j<3;j++){
				walkSheet[i][j] = spriteSheet[i][j];

			}
		}
		//arriba izquierda abajo derecha
		walks[0] = new Animation(0.1f,walkSheet[0]);
		walks[1] = new Animation(0.1f,walkSheet[1]);
		walks[2] = new Animation(0.1f,walkSheet[2]);
		walks[3] = new Animation(0.1f,walkSheet[3]);

	}

	@Override
	public void render (float delta){
		timepassed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(sleep) {
			sleeptime += Gdx.graphics.getDeltaTime();
			if (sleeptime > 1.3) {
				sleep = false;
				sleeptime = 0;
			}
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			BitmapFont font = new BitmapFont();
			font.getData().setScale(3.5f,3.5f);
			font.draw(batch, "I am sleeping...", GAME_WIDTH/2 - 160, GAME_HEIGHT/2+ 100);
		}
		else {
			if(m.getMap().get(new Position2D((int) ((x +WIDTH/2) / WIDTH), (int) ((y+HEIGHT/4)/HEIGHT) )).getType().getName().equals("Pelea")){
				/*Aca es donde deberian llamar a setCombat y esta tile tiene un Hoc asi
				*	hoc = m.getMap().get(new Position2D((int) ((x +WIDTH/2) / WIDTH), (int) ((y+HEIGHT/4)/HEIGHT) )).getHoc();
				*	setCombat(new Combat(player,hoc));
				*/
				HeadOfChair hoc = ((CombatType)(m.getMap().get(new Position2D((int) ((x +WIDTH/2) / WIDTH), (int) ((y+HEIGHT/4)/HEIGHT) )).getType())).getHoc();
				if(hoc.isAlive()) {
					player.setPosition(new Position2D((int) ((x + WIDTH / 2) / WIDTH), (int) ((y + HEIGHT / 4) / HEIGHT)));
					controller.setCombat(new Combat(player, hoc));
					controller.setCombatScreen();
				}
				else{
					m.getMap().put(new Position2D((int) ((x +WIDTH/2) / WIDTH), (int) ((y+HEIGHT/4)/HEIGHT) ),
							new Tile(true,false,new GroundType("Floor")));
				}
			}


			else if(m.getMap().get(new Position2D((int) ((x +WIDTH/2) / WIDTH), (int) ((y+HEIGHT/4)/HEIGHT) )).getType().getName().equals("Pasadizo")
					|| m.getMap().get(new Position2D((int) ((x +WIDTH/2) / WIDTH), (int) ((y+HEIGHT/2)/HEIGHT) )).getType().getName().equals("Pasadizo")){

				Tile t1 = m.getMap().get(new Position2D((int) ((x +WIDTH/2) / WIDTH), (int) ((y+HEIGHT/4)/HEIGHT) ));
				Tile t2 = m.getMap().get(new Position2D((int) ((x +WIDTH/2) / WIDTH), (int) ((y+HEIGHT/2)/HEIGHT) ));
				x= 5.0f*WIDTH;
				y = 5.0f*HEIGHT;
				String mapName = null;
				Position2D pos = null;
				if(t1.getType().getName().equals("Pasadizo")) {
					mapName = ((PassingType) t1.getType()).getMap();
					pos = ((PassingType) t1.getType()).getPos();
				}
				else if(t2.getType().getName().equals("Pasadizo")) {
					mapName = ((PassingType) t2.getType()).getMap();
					pos = ((PassingType) t2.getType()).getPos();
				}
				controller.changeMap(mapName,pos);
			}

			//m2.draw(batch);
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					//Integer value = m.getMap().get(new Position2D(i, j));
					Tile t = m.getMap().get(new Position2D(i, j));
					if ( t.isWalkable()) {
						if(t.isSleepable())
							batch.draw(woodenfloor, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
						else if(t.getType().getName().equals("Pasadizo"))
							batch.draw(passingTile, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
						else if(t.getType().getName().equals("Pelea"))
							batch.draw(combatTile, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
						else
							batch.draw(floor, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
					}
					else if(t.getType().getName().equals("Wall")){
						batch.draw(wall, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
					}
					else if(t.getType().getName().equals("Empty")){
						batch.draw(empty, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
					}
					else if(t.getType().getName().equals("Chair")){
						batch.draw(chair, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
					}
					else if(t.getType().getName().equals("Table")){
						batch.draw(table, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
					}
					else
						batch.draw(wall, i * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
				}
			}

			if(Gdx.input.isKeyPressed(Input.Keys.Q)){
				Gdx.app.exit();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				walk = 0;
				//System.out.println("x = " + (int)(x+CHARACTER_WIDTH/2)/WIDTH + "y = " + (int)((y+HEIGHT/2)/HEIGHT) +"value " + m.getMap().get(new Position2D((int) ((x +WIDTH/2) / WIDTH), (int) ((y/2)/ HEIGHT) + 1)) );
				//System.out.println("map[10][19]= " + m.getMap().get(new Position2D(19, 10 )));
				if(y<GAME_HEIGHT) {
					if (m.getMap().get(new Position2D((int) ((x + CHARACTER_WIDTH / 2) / WIDTH), (int) (y / HEIGHT) + 1)).isWalkable())
						y += SPEED * Gdx.graphics.getDeltaTime();
				}
			} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				walk = 2;
				if(y>0) {
					if (m.getMap().get(new Position2D((int) ((x + CHARACTER_WIDTH / 2) / WIDTH), (int) (y / HEIGHT))).isWalkable())
						y -= SPEED * Gdx.graphics.getDeltaTime();
				}
			} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				walk = 3;
				if (x < GAME_WIDTH -  CHARACTER_WIDTH / 2){
					if (m.getMap().get(new Position2D((int) (x / WIDTH) + 1, (int) (y / HEIGHT))).isWalkable())
						x += SPEED * Gdx.graphics.getDeltaTime();
				}
			} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				walk = 1;
				if(x > 0) {
					if (m.getMap().get(new Position2D((int) ((x + CHARACTER_WIDTH / 2) / WIDTH), (int) (y / HEIGHT))).isWalkable())
						x -= SPEED * Gdx.graphics.getDeltaTime();
				}
			}
			else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
				if(m.getMap().get(new Position2D((int) ((x + CHARACTER_WIDTH / 2) / WIDTH), (int) (y / HEIGHT))).isSleepable()) {
					sleep = true;
					controller.healRecover();
					controller.addAbilities();
					//controller.save();
				}
			}
			if(Gdx.input.isKeyPressed(Input.Keys.UP)||Gdx.input.isKeyPressed(Input.Keys.DOWN)||Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.RIGHT))
				batch.draw((TextureRegion) (walks[walk].getKeyFrame(timepassed, true)), x, y, 52, 52);
			else
				batch.draw(walkSheet[walk][0], x, y, 52, 52);
		}
		//batch.draw(floor, 0, 0);
		batch.end();
	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		floor.dispose();
	}
}

