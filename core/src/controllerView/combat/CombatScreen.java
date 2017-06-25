package controllerView.combat;

import controllerView.ControllerView;
import controllerView.MainMenuScreen;
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
import model.abilities.Abilities;
import model.persons.Enemy;
import model.persons.Hero;
import java.util.ArrayList;
import java.util.IllegalFormatWidthException;

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
    private static final int GAME_WIDTH = 30*20;
    private static final int GAME_HEIGHT = 30*20;
   //private static final int GAME_WIDTH = 1000;
   //private static final int GAME_HEIGHT = 1000;

    public static final int WIDTH = GAME_WIDTH/20;
    private static final int HEIGHT = GAME_HEIGHT/20;
    public static final int WIDTH2 = 800;
    public static final int HEIGHT2 = 600;
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

    public CombatScreen (ControllerView cont){
        this.cont = cont;
        attack = new Texture("attack.png");
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

        /*Type t = new Type("asd");
        Position2D p = new Position2D(1,1);
        Abilities ab = new Abilities("dsf", 10, 10,0);
        Abilities rWp = new Abilities("Recover Will Power", 5, 100,0);
        MainCharacter mc = new MainCharacter("matigol", 100, 120, 10, 10, p, t, ab);
        HeadOfChair hod = new HeadOfChair("name", 100, 120, 10, 10, p, t, 1, 10.0f, ab);

        Combat combat = new Combat(mc, hod);
        cont.setCombat(combat);

        mc.addHeroToParty(mc);
        Hero asd = new Hero("dsaf", 80, 90, 10, 10, p, t, ab);
        mc.addHeroToParty(asd);
        Professor fd = new Professor("sdf", 100, 120, 10, 10, p, t, 2, 10.0f,ab);
        hod.addProfessorToParty(hod);
        hod.addProfessorToParty(fd);
        mc.addSpecialAbility(ab);
        mc.addSpecialAbility(rWp);
        */
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

        if(Gdx.input.getX() >= 18 && Gdx.input.getX() <= 125
                && GAME_HEIGHT - Gdx.input.getY() >= 13 && GAME_HEIGHT - Gdx.input.getY() <=  60){
            if(Gdx.input.isTouched()){
                if (Gdx.input.isKeyJustPressed(Keys.NUM_0)){
                    batch.draw(attack, 160, 430, 80, 80);
                    cont.getCurrentCombat().getMainCharacter().getAbilities().get(0).
                            use(cont.getCurrentCombat().getMainCharacter(), cont.getCurrentCombat().getHeadOfChair().getParty().get(0));
                }
                else if(Gdx.input.isKeyJustPressed(Keys.NUM_1)){
                    batch.draw(attack, 270, 430, 80, 80);
                    cont.getCurrentCombat().getMainCharacter().getAbilities().get(0).
                            use(cont.getCurrentCombat().getMainCharacter(), cont.getCurrentCombat().getHeadOfChair().getParty().get(1));

                }
                else if(Gdx.input.isKeyJustPressed(Keys.NUM_2)){
                    batch.draw(attack, 270, 430, 80, 80);
                    cont.getCurrentCombat().getMainCharacter().getAbilities().get(0).
                            use(cont.getCurrentCombat().getMainCharacter(), cont.getCurrentCombat().getHeadOfChair().getParty().get(2));
                }
            }
        }

        if(Gdx.input.getX() >= 18 && Gdx.input.getX() <= 125
                && GAME_HEIGHT - Gdx.input.getY() >= 53 && GAME_HEIGHT - Gdx.input.getY() <=  83){
            if(Gdx.input.isTouched()){
                if (Gdx.input.isKeyPressed(Keys.NUM_0)){
                    batch.draw(attack, 160, 430, 80, 80);
                    cont.getCurrentCombat().getMainCharacter().getAbilities().get(1).
                            use(cont.getCurrentCombat().getMainCharacter(), cont.getCurrentCombat().getHeadOfChair().getParty().get(1));
                }
                else if(Gdx.input.isKeyPressed(Keys.NUM_1)){
                    batch.draw(attack, 270, 430, 120, 80);
                    cont.getCurrentCombat().getMainCharacter().getAbilities().get(1).
                            use(cont.getCurrentCombat().getMainCharacter(), cont.getCurrentCombat().getHeadOfChair().getParty().get(1));
                }
                else if(Gdx.input.isKeyJustPressed(Keys.NUM_2)){
                    batch.draw(attack, 270, 430, 80, 80);
                    cont.getCurrentCombat().getMainCharacter().getAbilities().get(1).
                            use(cont.getCurrentCombat().getMainCharacter(), cont.getCurrentCombat().getHeadOfChair().getParty().get(2));
                }
            }
        }

        if(Gdx.input.getX() >= 200 && Gdx.input.getX() <= 307
                && GAME_HEIGHT - Gdx.input.getY() >= 13 && GAME_HEIGHT - Gdx.input.getY() <=  60){
            if(Gdx.input.isTouched()){
                if (Gdx.input.isKeyPressed(Keys.NUM_0)){
                    batch.draw(attack, 160, 430, 80, 80);
                    cont.getCurrentCombat().getMainCharacter().getAbilities().get(2).
                            use(cont.getCurrentCombat().getMainCharacter(), cont.getCurrentCombat().getHeadOfChair().getParty().get(2));

                }
                else if(Gdx.input.isKeyPressed(Keys.NUM_1)){
                    batch.draw(attack, 270, 430, 120, 80);
                    cont.getCurrentCombat().getMainCharacter().getAbilities().get(2).
                            use(cont.getCurrentCombat().getMainCharacter(), cont.getCurrentCombat().getHeadOfChair().getParty().get(2));

                }
                else if(Gdx.input.isKeyJustPressed(Keys.NUM_2)){
                    batch.draw(attack, 270, 430, 80, 80);
                    cont.getCurrentCombat().getMainCharacter().getAbilities().get(2).
                            use(cont.getCurrentCombat().getMainCharacter(), cont.getCurrentCombat().getHeadOfChair().getParty().get(2));
                }
            }
        }

        if(Gdx.input.getX() >= 200 && Gdx.input.getX() <= 307
                && GAME_HEIGHT - Gdx.input.getY() >= 53 && GAME_HEIGHT - Gdx.input.getY() <=  83){
            if(Gdx.input.isTouched()){
                if (Gdx.input.isKeyPressed(Keys.NUM_0)){
                    batch.draw(attack, 160, 430, 80, 80);
                    cont.getCurrentCombat().getMainCharacter().getAbilities().get(3).
                            use(cont.getCurrentCombat().getMainCharacter(), cont.getCurrentCombat().getHeadOfChair().getParty().get(3));
                }
                else if(Gdx.input.isKeyPressed(Keys.NUM_1)){
                    batch.draw(attack, 270, 430, 120, 80);
                    cont.getCurrentCombat().getMainCharacter().getAbilities().get(3).
                            use(cont.getCurrentCombat().getMainCharacter(), cont.getCurrentCombat().getHeadOfChair().getParty().get(3));
                }
            }
        }


        return;
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
            if(!h.isKnockedOut()){
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
            if(!e.isKnockedOut()){
                BitmapFont font = new BitmapFont();
                GlyphLayout layoutHp = new GlyphLayout(font, "HP: "+ e.getCurrentHP() +"/" + e.getHP());
                GlyphLayout layoutWp = new GlyphLayout(font, "WP: "+e.getCurrentWillPower()+"/" + e.getWillPower());
                GlyphLayout layoutPlayer = new GlyphLayout(font, "Player:" + cont.getCurrentCombat().getHeadOfChair().getParty().indexOf(e));
                font.draw(batch, layoutPlayer, 175 + i, 590);
                font.draw(batch, layoutHp, 165+i, 575);
                font.draw(batch, layoutWp, 165+i, 550);
                batch.draw((TextureRegion)(walks[1].getKeyFrame(timepassed, true)),x*WIDTH+ i ,y*HEIGHT+150,100,100);
                i +=100;
            }
        }
        if(cont.getCurrentCombat().isPlayerTurn()){
            for(Abilities a : cont.getCurrentCombat().getMainCharacter().getAbilities()){
                BitmapFont font = new BitmapFont();
                batch.draw(buttonHab1, 20, 12, buttonHab1.getWidth() * i, 30);
                font.draw(batch, a.getName(), i * buttonHab1.getWidth() - 60, 30);
            }
            cont.getCurrentCombat().nextTurn();
        }
        if(Gdx.input.getX() >= (WIDTH2 / 2 - BUTTONWIDTH / 2) + 100 && Gdx.input.getX() <= WIDTH2/2 - BUTTONWIDTH/2 + BUTTONWIDTH +100
                && HEIGHT2 - Gdx.input.getY() >= 10 &&   HEIGHT2 - Gdx.input.getY() <= 10 + BUTTONHEIGHT) {
            batch.draw(buttonFleeActive, (WIDTH2 / 2 - BUTTONWIDTH / 2) + 100, 10);
            if(Gdx.input.isTouched()){
                dispose();
                cont.setScreen(new MainMenuScreen(cont));
//               	cont.setScreen(new ExploreScreen(this,map,new MainCharacter("Agustin",100,100,10,10,new Position2D(5,10))));
//               cont.endCombat();
//               cont.setScreen(new ExploreScreen(cont));
            }
        }
        else{
            batch.draw(buttonFleeInactive, (WIDTH2 / 2 - BUTTONWIDTH / 2) + 100, 10);
        }

        drawingHabButtons();



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
