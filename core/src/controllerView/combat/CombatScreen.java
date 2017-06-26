package controllerView.combat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
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
import controllerView.ControllerView;
import model.abilities.Abilities;
import model.combat.Combat;
import model.map.Position2D;
import model.persons.*;

import java.util.ArrayList;
import java.util.IllegalFormatWidthException;
//public class CombatScreen{
public class CombatScreen implements Screen {
    private SpriteBatch batch;
    private Texture floor;
    private Animation walks [];
    private TextureRegion spriteSheet[][];
    private Texture buttonFleeInactive;
    private Texture buttonFleeActive;
    private Texture buttonHab1 = new Texture("habButton.png");
    private TextureRegion walkSheet[][];
    private float timepassed;
    //private static final int GAME_WIDTH = 30*20;
    //private static final int GAME_HEIGHT = 30*20;
    private static final int GAME_WIDTH = 800;
   // private static final int GAME_HEIGHT = 1000;
   private static final int GAME_HEIGHT = 800;

    public static final int WIDTH = GAME_WIDTH/20;
    private static final int HEIGHT = GAME_HEIGHT/20;
    public static final int WIDTH2 = 800; //800
    public static final int HEIGHT2 = 800; //600
    private static final int BUTTONWIDTH = 160;
    private static final int BUTTONHEIGHT = 60;
    private float x;
    private float y;
    private ControllerView cont;
    private Texture buttonFondo;
    private Texture buttonHab3;
    private Texture buttonHab2;
    private Texture buttonHab4;
    private Texture attack;
    private Texture Hoc;

    public CombatScreen (ControllerView cont){
        this.cont = cont;
        this.Hoc = new Texture("diaz.png"); //harcoded
        attack = new Texture("attack.png");
        timepassed = 0;
        x = 5.f;
        y = 10.f;
        batch = new SpriteBatch();
        floor = new Texture("fightingBackground.png");
        spriteSheet = TextureRegion.split(new Texture("character.png"),64,64);
        walkSheet = new TextureRegion[4][9];
        walks = new Animation[5];
        for(int i=0;i<4;i++){
            for(int j=0;j<9;j++){
                walkSheet[i][j] = spriteSheet[8+i][j];

            }
        }


        //arriba abajo izquierda derecha
        walks[0] = new Animation(0.1f,walkSheet[0]);
        walks[1] = new Animation(0.1f,walkSheet[2]);
        walks[2] = new Animation(0.1f,walkSheet[1]);
        walks[3] = new Animation(0.1f,walkSheet[3]);

        buttonFleeInactive = new Texture("fleeInac.png");
        buttonFleeActive = new Texture("fleeActiv.png");
        buttonFondo = new Texture("recuadro.png");
        buttonHab1 = new Texture("habButton.png");
        buttonHab2 = new Texture("habButton.png");
        buttonHab3 = new Texture("habButton.png");
        buttonHab4 = new Texture("habButton.png");


    }


    public void drawingHabButtons(){
        batch.draw(buttonFondo, 10, 10, 300, 80);
        //if(cont.getCurrentCombat().getMainCharacter().getAbilities().contains()){
        batch.draw(buttonHab1, 18, 13, 105, 30);
        //}
        //if(cont.getCurrentCombat().getMainCharacter().getAbilities().contains()){
        batch.draw(buttonHab2, 200, 13, 105, 30);
        //}
        //if(cont.getCurrentCombat().getMainCharacter().getAbilities().contains()){
        batch.draw(buttonHab3, 18, 53, 105, 30);
        //}
        //if(cont.getCurrentCombat().getMainCharacter().getAbilities().contains()){
        batch.draw(buttonHab4, 200, 53, 105, 30);
        //}*/


        try{
            //System.out.println("Aca entro al if3: index= " + cont.getCurrentCombat().getMainCharacter().getPartyIndex());

            //ButtonHab1
            if(Gdx.input.getX() >= 18 && Gdx.input.getX() <= 125
                    && GAME_HEIGHT - Gdx.input.getY() >= 13 && GAME_HEIGHT - Gdx.input.getY() <=  60){
                if(Gdx.input.justTouched()){
                    if (Gdx.input.isKeyJustPressed(Keys.NUM_0)){
                        batch.draw(attack, 160, 430, 80, 80);
                        ActivateButton(0);
                    }
                    else if(Gdx.input.isKeyJustPressed(Keys.NUM_1)){
                        batch.draw(attack, 270, 430, 80, 80);
                        ActivateButton(1);
                    }
                    else if(Gdx.input.isKeyJustPressed(Keys.NUM_2)){
                        batch.draw(attack, 270, 430, 80, 80);
                        ActivateButton(2);
                    }
                }
            }

            //ButtonHab3
            if(Gdx.input.getX() >= 18 && Gdx.input.getX() <= 125
                    && GAME_HEIGHT - Gdx.input.getY() >= 53 && GAME_HEIGHT - Gdx.input.getY() <=  83){
                if(Gdx.input.justTouched()){
                    if (Gdx.input.isKeyPressed(Keys.NUM_0)){
                        batch.draw(attack, 160, 430, 80, 80);
                        ActivateButton(0);
                    }
                    else if(Gdx.input.isKeyPressed(Keys.NUM_1)){
                        batch.draw(attack, 270, 430, 120, 80);
                        ActivateButton(1);

                    }
                    else if(Gdx.input.isKeyJustPressed(Keys.NUM_2)){
                        batch.draw(attack, 270, 430, 80, 80);
                        ActivateButton(2);
                    }
                }
            }

            //ButtonHab2
            if(Gdx.input.getX() >= 200 && Gdx.input.getX() <= 307
                    && GAME_HEIGHT - Gdx.input.getY() >= 13 && GAME_HEIGHT - Gdx.input.getY() <=  60){
                if(Gdx.input.justTouched()){
                    if (Gdx.input.isKeyPressed(Keys.NUM_0)){
                        batch.draw(attack, 160, 430, 80, 80);
                        ActivateButton(0);
                    }
                    else if(Gdx.input.isKeyPressed(Keys.NUM_1)){
                        batch.draw(attack, 270, 430, 120, 80);
                        ActivateButton(1);
                    }
                    else if(Gdx.input.isKeyJustPressed(Keys.NUM_2)){
                        ActivateButton(2);
                    }
                }
            }
            //ButtonHab4
            if(Gdx.input.getX() >= 200 && Gdx.input.getX() <= 307
                    && GAME_HEIGHT - Gdx.input.getY() >= 53 && GAME_HEIGHT - Gdx.input.getY() <=  83){
                if(Gdx.input.justTouched()){
                    if (Gdx.input.isKeyPressed(Keys.NUM_0)){
                        batch.draw(attack, 160, 430, 80, 80);
                        ActivateButton(0);
                    }
                    else if(Gdx.input.isKeyPressed(Keys.NUM_1)){
                        batch.draw(attack, 270, 430, 120, 80);
                        ActivateButton(1);
                    }else if(Gdx.input.isKeyJustPressed(Keys.NUM_2)){
                        batch.draw(attack, 270, 430, 80, 80);
                        ActivateButton(2);
                    }
                }
            }
        }catch(NotEnoughWillPowerException e){ //cambiar de lugar el texto
            BitmapFont font = new BitmapFont();
            GlyphLayout layoutHp = new GlyphLayout(font, e.getMessage());
            font.draw(batch, layoutHp, 500 , 500); // x 165 y 200
        }
    }


    @Override
    public void render (float delta) {
        timepassed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(floor, 0,0, GAME_WIDTH, GAME_WIDTH);
        BitmapFont font;
        if(cont.getCurrentCombat().isItFinished()){
            if(cont.getCurrentCombat().winner()){
                cont.endCombat();
            }
        }

        int i = 0;
        for (Hero h : cont.getCurrentCombat().getMainCharacter().getParty()){
            if(!h.isKnockedOut()){
                font = new BitmapFont();
                GlyphLayout layoutHp = new GlyphLayout(font, "HP:" + h.getCurrentHP() +  "/" + h.getHP());
                GlyphLayout layoutWp = new GlyphLayout(font, "WP:"+ h.getCurrentWillPower() +"/" + h.getWillPower());
                font.draw(batch, layoutHp, 170+i, 220); // x 165 y 200
                font.draw(batch, layoutWp, 170+i, 250); // x 165 y 225
                batch.draw((TextureRegion)(walks[0].getKeyFrame(timepassed, true)),3*WIDTH + i ,8*HEIGHT-200,100,100);
                i +=100;
            }
        }
        i=0;
        for (Enemy e : cont.getCurrentCombat().getHeadOfChair().getParty()){
            if(!e.isKnockedOut()){
                font = new BitmapFont();
                GlyphLayout layoutHp = new GlyphLayout(font, "HP: "+ e.getCurrentHP() +"/" + e.getHP());
                GlyphLayout layoutWp = new GlyphLayout(font, "WP: "+e.getCurrentWillPower()+"/" + e.getWillPower());
                //esto es necesario?
                //GlyphLayout layoutPlayer = new GlyphLayout(font, "Player:" + cont.getCurrentCombat().getHeadOfChair().getParty().indexOf(e));
                //font.draw(batch, layoutPlayer, 175 + i, 590);
                font.draw(batch, layoutHp, 650+i, 475); // x 165 y 575
                font.draw(batch, layoutWp, 650+i, 450); // x 165 y 550
                if(e instanceof HeadOfChair){
                    batch.draw( Hoc, 12  * WIDTH + i, 7 * HEIGHT + 150, 200, 450);
                }else {
                    batch.draw((TextureRegion) (walks[1].getKeyFrame(timepassed, true)), 12 * WIDTH + i, 11 * HEIGHT + 150, 100, 100);
                }i +=200;
            }
        }
        if(cont.getCurrentCombat().isPlayerTurn()){
            drawingHabButtons();
            font = new BitmapFont();
            GlyphLayout layoutTurns = new GlyphLayout(font, "Your Turn");
            font.draw(batch, layoutTurns, 170, 950);
            for(Abilities a : cont.getCurrentCombat().getMainCharacter().getAbilities()){
                font = new BitmapFont();
                batch.draw(buttonHab1, 20, 12, buttonHab1.getWidth() * i, 30);
                font.draw(batch, a.getName(), i * buttonHab1.getWidth() - 60, 30);
            }
        }


        //FleeButton
        if(Gdx.input.getX() >= (WIDTH2 / 2 - BUTTONWIDTH / 2) + 100 && Gdx.input.getX() <= WIDTH2/2 - BUTTONWIDTH/2 + BUTTONWIDTH +100
                && HEIGHT2 - Gdx.input.getY() >= 10 &&   HEIGHT2 - Gdx.input.getY() <= 10 + BUTTONHEIGHT) {
            batch.draw(buttonFleeActive, (WIDTH2 / 2 - BUTTONWIDTH / 2) + 100, 10);
            if(Gdx.input.isTouched()){
                dispose();
                cont.setExplorerScreen();
//               	cont.setScreen(new ExploreScreen(this,map,new MainCharacter("Agustin",100,100,10,10,new Position2D(5,10))));
//               cont.endCombat();
//               cont.setScreen(new ExploreScreen(cont));
            }
        }
        else{
            batch.draw(buttonFleeInactive, (WIDTH2 / 2 - BUTTONWIDTH / 2) + 100, 10);
        }
        batch.end();
    }

    private void ActivateButton(int n){
        Hero h = cont.getCurrentCombat().getMainCharacter().getHero();
        Enemy e = cont.getCurrentCombat().getHeadOfChair().getParty().get(n);
        h.getAbility().use(h,e);
        if(cont.getCurrentCombat().getMainCharacter().getPartyIndex() >=
                cont.getCurrentCombat().getMainCharacter().getPartySize()) {
            cont.getCurrentCombat().nextTurn();

        }
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
