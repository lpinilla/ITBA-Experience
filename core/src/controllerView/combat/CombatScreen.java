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
/*
    @author lpinilla, mheimann on 17/06/2017
*/
public class CombatScreen implements Screen {
    private SpriteBatch batch;
    private Texture background;
    private Texture buttonFleeInactive;
    private Texture buttonFleeActive;
    private Texture buttonHab1 = new Texture("habButton.png");
    private float timepassed;
    private static final int GAME_WIDTH = 1000;
   private static final int GAME_HEIGHT = 1000;

    public static final int WIDTH = GAME_WIDTH/20;
    private static final int HEIGHT = GAME_HEIGHT/20;
    public static final int WIDTH2 = 1000; //800
    public static final int HEIGHT2 = 1000; //600
    private static final int BUTTONWIDTH = 160;
    private static final int BUTTONHEIGHT = 60;
    private ControllerView cont;
    private Texture buttonFondo;
    private Texture buttonHab3;
    private Texture buttonHab2;
    private Texture buttonHab4;
    private Texture attack;
    private Texture hocSprite;
    private Texture prof1Sprite;
    private Texture prof2Sprite;
    private Texture mainCSprite;
    private Texture hero1Sprite;
    private Texture hero2Sprite;
    private float turntime;
    private int auxXConstant;
    private int auxYConstant;
    private float timer;
    private float delay;
    private GlyphLayout attackInfo;
    private BitmapFont attackLog;
    private boolean timerActive;
    private boolean drawAttackInfo;

    public CombatScreen (ControllerView cont){
        this.cont = cont;
        timepassed = 0;
        turntime = 0;
        timer = 0;
        delay = 0;
        attackInfo = null;
        timerActive =  drawAttackInfo = false;
        batch = new SpriteBatch();
        this.mainCSprite = new Texture(
                this.cont.getCurrentCombat().getMainCharacter().getName() + ".png");
        this.hero1Sprite = new Texture(
                this.cont.getCurrentCombat().getMainCharacter().getParty().get(1).getName() + ".png");
        this.hero2Sprite = new Texture(
                this.cont.getCurrentCombat().getMainCharacter().getParty().get(2).getName() + ".png");
        this.hocSprite = new Texture(
                this.cont.getCurrentCombat().getHeadOfChair().getName() + ".png");
        attack = new Texture("attack.png");
        background = new Texture("fightingBackground.png");
        buttonFleeInactive = new Texture("fleeInac.png");
        buttonFleeActive = new Texture("fleeActiv.png");
        buttonFondo = new Texture("recuadro.png");
        if(this.cont.getCurrentCombat().getMainCharacter().getAbilities().size() > 0){
            buttonHab1 = new Texture(
                    this.cont.getCurrentCombat().getMainCharacter().getAbilities().get(0).getName() + ".png");

        }if(this.cont.getCurrentCombat().getMainCharacter().getAbilities().size() > 1){
            buttonHab2 = new Texture(
                    this.cont.getCurrentCombat().getMainCharacter().getAbilities().get(1).getName() + ".png");
        }if(this.cont.getCurrentCombat().getMainCharacter().getAbilities().size() > 2){
            buttonHab3 = new Texture(
                    this.cont.getCurrentCombat().getMainCharacter().getAbilities().get(2).getName() + ".png");
        }
        if(this.cont.getCurrentCombat().getMainCharacter().getAbilities().size() > 3) {
            buttonHab4 = new Texture(
                    this.cont.getCurrentCombat().getMainCharacter().getAbilities().get(3).getName() + ".png");
        }
    }

    /*
        Draw the buttons of the abilities the character has
    */
    public void drawingHabButtons(){
        auxXConstant = 500;
        auxYConstant = 100;
        batch.draw(buttonFondo, 10 + auxXConstant, 10 + auxYConstant, 400, 150);

        if(cont.getCurrentCombat().getMainCharacter().getAbilities().size() > 0){
            batch.draw(buttonHab1, 15 + auxXConstant, 87.5f + auxYConstant, 192.5f, 67.5f);
        }
        if(cont.getCurrentCombat().getMainCharacter().getAbilities().size() > 1){
            batch.draw(buttonHab2, 212.5f + auxXConstant, 87.5f + auxYConstant, 192.5f, 67.5f);
        }
        if(cont.getCurrentCombat().getMainCharacter().getAbilities().size() >2){
            batch.draw(buttonHab3, 15 + auxXConstant, 15 + auxYConstant, 192.5f, 67.5f);
        }
        if(cont.getCurrentCombat().getMainCharacter().getAbilities().size() > 3){
            batch.draw(buttonHab4, 212.5f + auxXConstant, 15 + auxYConstant, 192.5f, 67.5f);
        }

        try{

            if(Gdx.input.getX() >= (15 + auxXConstant) && Gdx.input.getX() <= (auxXConstant +192.5f)
                    && GAME_HEIGHT - Gdx.input.getY() >= (15 + auxYConstant) && GAME_HEIGHT - Gdx.input.getY() <=  (auxYConstant +67.5f)){
                if(Gdx.input.justTouched()){
                    if (Gdx.input.isKeyPressed(Keys.NUM_1) &&
                            !this.cont.getCurrentCombat().getHeadOfChair().isKnockedOut()){
                        batch.draw(attack, 160, 430, 80, 80);
                        ActivateButton(0);

                    }
                    else if(Gdx.input.isKeyPressed(Keys.NUM_2) &&
                            !this.cont.getCurrentCombat().getHeadOfChair().getParty().get(1).isKnockedOut()){
                        batch.draw(attack, 270, 430, 80, 80);
                        ActivateButton(1);
                    }
                    else if(Gdx.input.isKeyPressed(Keys.NUM_3) &&
                            !this.cont.getCurrentCombat().getHeadOfChair().getParty().get(2).isKnockedOut()){
                        batch.draw(attack, 270, 430, 80, 80);
                        ActivateButton(2);
                    }
                }
            }

            //ButtonHab1 arriba izq
            if(Gdx.input.getX() >= (15 + auxXConstant) && Gdx.input.getX() <= (auxXConstant + 192.5f)
                    && GAME_HEIGHT - Gdx.input.getY() >= (auxYConstant + 87.5f) && GAME_HEIGHT - Gdx.input.getY() <= (auxYConstant + 87.5f + 67.5f)){
                if(Gdx.input.justTouched()){
                    if (Gdx.input.isKeyPressed(Keys.NUM_1) &&
                            !this.cont.getCurrentCombat().getHeadOfChair().isKnockedOut()){
                        batch.draw(attack, 160, 430, 80, 80);
                        ActivateButton(0);
                    }
                    else if(Gdx.input.isKeyPressed(Keys.NUM_2) &&
                            !this.cont.getCurrentCombat().getHeadOfChair().getParty().get(1).isKnockedOut()){
                        batch.draw(attack, 270, 430, 120, 80);
                        ActivateButton(1);

                    }
                    else if(Gdx.input.isKeyPressed(Keys.NUM_3) &&
                            !this.cont.getCurrentCombat().getHeadOfChair().getParty().get(2).isKnockedOut()){
                        batch.draw(attack, 270, 430, 80, 80);
                        ActivateButton(2);
                    }
                }
            }

            //ButtonHab4 abajo derecha
            if(Gdx.input.getX() >= (212.5f + auxXConstant) && Gdx.input.getX() <= (212.5f + auxXConstant + 192.5f)
                    && GAME_HEIGHT - Gdx.input.getY() >= (15 + auxYConstant) && GAME_HEIGHT - Gdx.input.getY() <=  (auxYConstant +67.5f)){
                if(Gdx.input.justTouched()){
                    if (Gdx.input.isKeyPressed(Keys.NUM_1) &&
                            !this.cont.getCurrentCombat().getHeadOfChair().isKnockedOut()){
                        batch.draw(attack, 160, 430, 80, 80);
                        ActivateButton(0);
                    }
                    else if(Gdx.input.isKeyPressed(Keys.NUM_2) &&
                            !this.cont.getCurrentCombat().getHeadOfChair().getParty().get(1).isKnockedOut()){
                        batch.draw(attack, 270, 430, 120, 80);
                        ActivateButton(1);
                    }
                    else if(Gdx.input.isKeyPressed(Keys.NUM_3)&&
                            !this.cont.getCurrentCombat().getHeadOfChair().getParty().get(2).isKnockedOut()){
                        ActivateButton(2);
                    }
                }
            }
            //ButtonHab2 arriba der
            if(Gdx.input.getX() >= (212.5f + auxXConstant) && Gdx.input.getX() <= (212.5f + auxXConstant + 192.5f)
                    && GAME_HEIGHT - Gdx.input.getY() >= (auxYConstant + 87.5f) && GAME_HEIGHT - Gdx.input.getY() <= (auxYConstant + 87.5f + 67.5f)){
                if(Gdx.input.justTouched()){
                    if (Gdx.input.isKeyPressed(Keys.NUM_1) &&
                            !this.cont.getCurrentCombat().getHeadOfChair().isKnockedOut()){
                        batch.draw(attack, 160, 430, 80, 80);
                        ActivateButton(0);
                    }
                    else if(Gdx.input.isKeyPressed(Keys.NUM_2) &&
                            !this.cont.getCurrentCombat().getHeadOfChair().getParty().get(1).isKnockedOut()){
                        batch.draw(attack, 270, 430, 120, 80);
                        ActivateButton(1);
                    }else if(Gdx.input.isKeyPressed(Keys.NUM_3) &&
                            !this.cont.getCurrentCombat().getHeadOfChair().getParty().get(2).isKnockedOut()){
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
        turntime += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0,0, GAME_WIDTH, GAME_WIDTH);
        BitmapFont font;
        if(cont.getCurrentCombat().isItFinished()){
            boolean win = cont.getCurrentCombat().winner();
                cont.endCombat(win);
        }

        BitmapFont characterTurn = new BitmapFont();
        GlyphLayout layoutCharTurn = null;
        int partyIndex;
        if(this.cont.getCurrentCombat().isPlayerTurn()){
            partyIndex = this.cont.getCurrentCombat().getMainCharacter().getPartyIndex();
            layoutCharTurn = new GlyphLayout(characterTurn, "Now Playing: " +
                    this.cont.getCurrentCombat().getMainCharacter().getParty().get(partyIndex).getName());
        }
        int i = 0;
        for (Hero h : cont.getCurrentCombat().getMainCharacter().getParty()){
            if(!h.isKnockedOut()){
                font = new BitmapFont();
                GlyphLayout layoutHp = new GlyphLayout(font, "HP:" + h.getCurrentHP() +  "/" + h.getHP());
                GlyphLayout layoutWp = new GlyphLayout(font, "WP:"+ h.getCurrentWillPower() +"/" + h.getWillPower());
                if(h instanceof MainCharacter){
                    font.draw(batch, layoutHp, 110+i, 585); // x 165 y 200
                    font.draw(batch, layoutWp, 110+i, 610); // x 165 y 225
                    batch.draw( mainCSprite, WIDTH - 30 + i, HEIGHT + 20, 200, 500);
                }else if( h == this.cont.getCurrentCombat().getMainCharacter().getParty().get(1)){
                    font.draw(batch, layoutHp, 110+i, 555); // x 165 y 200
                    font.draw(batch, layoutWp, 110+i, 580); // x 165 y 225
                    batch.draw( hero1Sprite,   WIDTH - 30 + i, HEIGHT -20 , 200, 500);
                }else{
                    font.draw(batch, layoutHp, 110+i, 495); // x 165 y 200
                    font.draw(batch, layoutWp, 110+i, 520); // x 165 y 225
                    batch.draw( hero2Sprite, WIDTH - 30 + i, HEIGHT -70  , 200, 500);
                }
                i +=135;
            }
        }
        /*
            It draws every enemy alive        
        */
        i=0;
        for (Enemy e : cont.getCurrentCombat().getHeadOfChair().getParty()){
            if(!e.isKnockedOut()){
                font = new BitmapFont();
                GlyphLayout layoutHp = new GlyphLayout(font, "HP: "+ e.getCurrentHP() +"/" + e.getHP());
                GlyphLayout layoutWp = new GlyphLayout(font, "WP: "+e.getCurrentWillPower()+"/" + e.getWillPower());
                //GlyphLayout layoutPlayer = new GlyphLayout(font, "Player:" + cont.getCurrentCombat().getHeadOfChair().getParty().indexOf(e));
                //font.draw(batch, layoutPlayer, 175 + i, 590);
                if(e instanceof HeadOfChair){
                    font.draw(batch, layoutHp, 450+i, 555); // x 165 y 575
                    font.draw(batch, layoutWp, 450+i, 530); // x 165 y 550
                    batch.draw( hocSprite, 8  * WIDTH + i, 7 * HEIGHT + 150 + 50, 200, 450);
                }else if (e == this.cont.getCurrentCombat().getHeadOfChair().getParty().get(1)){
                    font.draw(batch, layoutHp, 450+i, 505); // x 165 y 575
                    font.draw(batch, layoutWp, 450+i, 480); // x 165 y 550
                    batch.draw( hocSprite, 8  * WIDTH + i, 7 * HEIGHT + 150 , 200, 450);
                }else{
                    font.draw(batch, layoutHp, 450+i, 455); // x 165 y 575
                    font.draw(batch, layoutWp, 450+i, 430); // x 165 y 550
                    batch.draw( hocSprite, 8  * WIDTH + i, 7 * HEIGHT + 150 - 50, 200, 450);
                }
                i +=200;
            }
        }
        if(cont.getCurrentCombat().isPlayerTurn()){
            drawingHabButtons();
            font = new BitmapFont();
            font.getData().setScale(2.5f);
            GlyphLayout layoutTurns = new GlyphLayout(font, "Your Turn");
            font.draw(batch, layoutTurns, 170, 950);
            for(Abilities a : cont.getCurrentCombat().getMainCharacter().getAbilities()){
                font = new BitmapFont();
                //batch.draw(buttonHab1, 20, 12, buttonHab1.getWidth() * i, 30);
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
            }
        }
        else{
            batch.draw(buttonFleeInactive, (WIDTH2 / 2 - BUTTONWIDTH / 2) + 100, 10);
        }
        batch.end();
    }
    /*
      It increases the wp of every character in the in 10 points, after the Main Character
      use an ability to attack an enemy   
    */
    private void ActivateButton(int n){
        Hero h = cont.getCurrentCombat().getMainCharacter().getHero();
        h.modifyCurrentWillPower(10);
        Enemy e = cont.getCurrentCombat().getHeadOfChair().getParty().get(n);
        h.getAbility().use(h,e);
        if(cont.getCurrentCombat().getMainCharacter().getPartyIndex() >=
                cont.getCurrentCombat().getMainCharacter().getPartySize()) {
            turntime = 0;
            cont.getCurrentCombat().nextTurn();
            for(Enemy en: cont.getCurrentCombat().getHeadOfChair().getParty()){
                en.modifyCurrentWillPower(10);
            }
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
        background.dispose();
    }
}
