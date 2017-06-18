/*
 @author: mheimann on 17/06/2017
 */

package com.mygdx.game;

import combat.*;
import person.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import map.Position2D;
import person.Abilities;
import person.HeadOfChair;
import person.MainCharacter;
import person.Type;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.IllegalFormatWidthException;

public class CombatScreenBis implements Screen {
	private SpriteBatch batch;
	private Texture floor;
	private Animation walks [];
	private TextureRegion spriteSheet[][];
	private Texture buttonFleeInactive;
	private Texture buttonFleeActive;
	private Texture buttonHab1 = new Texture("habButton.png");
	private TextureRegion walkSheet[][];
	private float timepassed;
	private static final int GAME_WIDTH = 30*20;
	private static final int GAME_HEIGHT = 30*20;
	public static final int WIDTH = GAME_WIDTH/20;
	private static final int HEIGHT = GAME_HEIGHT/20;
	public static final int WIDTH2 = 800;
	public static final int HEIGHT2 = 600;
	private static final int BUTTONWIDTH = 103;
    private static final int BUTTONHEIGHT = 46;
	private float x;
	private float y;
	private ControllerView cont;

	public CombatScreenBis (ControllerView cont){
		this.cont = cont;
		timepassed = 0;
		x = 5.f;
		y = 10.f;
		batch = new SpriteBatch();
		floor = new Texture("floor.jpg");
		spriteSheet = TextureRegion.split(new Texture("character.png"),64,64);
		walkSheet = new TextureRegion[4][9];
		walks = new Animation[5];
		for(int i=0;i<4;i++){
			for(int j=0;j<9;j++){
				walkSheet[i][j] = spriteSheet[8+i][j];

			}
		}
		
        Type t = new Type("asd");
        Position2D p = new Position2D(1,1);
        Abilities ab = new Abilities("dsf", 10, 10);
        Abilities rWp = new Abilities("Recover Will Power", 0, 100);
        MainCharacter mc = new MainCharacter("matigol", 100, 120, 10, 10, p, t);
        HeadOfChair hod = new HeadOfChair("name", 100, 120, 10, 10, p, t, 1, 10.0f, ab);
        
        Combat combat = new Combat(mc, hod);
        cont.createCombat(combat);
        
        mc.addHeroToParty(mc);
        Hero asd = new Hero("dsaf", 80, 90, 10, 10, p, t);
        mc.addHeroToParty(asd);
        Professor fd = new Professor("sdf", 100, 120, 10, 10, p, t, 2, 10.0f);
        hod.addProfessorToParty(hod);
        hod.addProfessorToParty(fd);
        mc.addSpecialAbility(ab);
        mc.addSpecialAbility(rWp);
  
		//arriba abajo izquierda derecha
		walks[0] = new Animation(0.1f,walkSheet[0]);
		walks[1] = new Animation(0.1f,walkSheet[2]);
		walks[2] = new Animation(0.1f,walkSheet[1]);
		walks[3] = new Animation(0.1f,walkSheet[3]);
		
		buttonFleeInactive = new Texture("button_flee.png");
		buttonFleeActive = new Texture("button_flee (1).png");
		
		
	}
	@Override
	public void render (float delta) {
		timepassed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for(int i = 0; i<20 ; i++){
			for(int j = 0; j<20; j++){
				batch.draw(floor, i*WIDTH, j*HEIGHT,WIDTH,HEIGHT);
			}
		}

		int i = 0;
		for (Hero h : cont.getCurrentCombat().getMainCharacter().getParty()){
			if(h.getCurrentHP() > 0){
				BitmapFont font = new BitmapFont();
				GlyphLayout layoutHp = new GlyphLayout(font, "HP:" + h.getCurrentHP() +  "/" + h.getHP());
				GlyphLayout layoutWp = new GlyphLayout(font, "WP:"+ h.getCurrentWillPower() +"/" + h.getWillPower());
				font.draw(batch, layoutHp, 165+i, 200);
				font.draw(batch, layoutWp, 165+i, 225);
				batch.draw((TextureRegion)(walks[0].getKeyFrame(timepassed, true)),x*WIDTH + i ,y*HEIGHT-200,100,100);
				i +=100;
			}
		}
		i=0;
		for (Enemy e : cont.getCurrentCombat().getHeadOfChair().getParty()){
			if(e.getCurrentHP() > 0){
				BitmapFont font = new BitmapFont();
				GlyphLayout layoutHp = new GlyphLayout(font, "HP: "+ e.getCurrentHP() +"/" + e.getHP());
				GlyphLayout layoutWp = new GlyphLayout(font, "WP: "+e.getCurrentWillPower()+"/" + e.getWillPower());
				font.draw(batch, layoutHp, 165+i, 575);
				font.draw(batch, layoutWp, 165+i, 550);
				batch.draw((TextureRegion)(walks[1].getKeyFrame(timepassed, true)),x*WIDTH+ i ,y*HEIGHT+150,100,100);
				i +=100;
			}
		}
		i=1;
		for(Abilities a : cont.getCurrentCombat().getMainCharacter().getAbilities()){
			BitmapFont font = new BitmapFont();
			batch.draw(buttonHab1, 20, 12, buttonHab1.getWidth() * i, 30);
			font.draw(batch, a.getName(), i * buttonHab1.getWidth() - 60, 30);
			i+=1;
		}
		if(Gdx.input.getX() >= (WIDTH2 / 2 - BUTTONWIDTH / 2) + 100 && Gdx.input.getX() <= WIDTH2/2 - BUTTONWIDTH/2 + BUTTONWIDTH +100
                && HEIGHT2 - Gdx.input.getY() >= 10 &&   HEIGHT2 - Gdx.input.getY() <= 10 + BUTTONHEIGHT) {
            batch.draw(buttonFleeActive, (WIDTH2 / 2 - BUTTONWIDTH / 2) + 100, 10);
            if(Gdx.input.isTouched()){
               dispose();
               cont.setScreen(new ExploreScreen());
            }
        }
        else{
            batch.draw(buttonFleeInactive, (WIDTH2 / 2 - BUTTONWIDTH / 2) + 100, 10);
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

