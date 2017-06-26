package controllerView;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controllerView.map.ExploreScreen;
import model.abilities.Abilities;
import model.abilities.AbilityFactory;
import model.persons.Enemy;
import model.persons.HeadOfChair;
import model.persons.MainCharacter;
import model.combat.Combat;
import model.map.GameMap;
import model.map.MapHandler;
import model.map.Position2D;
import model.persons.Type;
import controllerView.combat.CombatScreen;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by agustin on 14/06/17.
 */
public class ControllerView extends Game{

        private com.mygdx.game.Game game;
        private SpriteBatch batch;
        //public static final int WIDTH = 450;
        //public static final int HEIGHT = 720;
        public static final int WIDTH = 800;
        public static final int HEIGHT = 800;
        private MainCharacter mc;
        private Combat currentCombat;
        private ExploreScreen explore;
        private Map map;
        private List<Abilities> listAbilities;

        public ControllerView(com.mygdx.game.Game game){
            this.game = game;
        }

    @Override
    public void create () {
        batch = new SpriteBatch();
        game.setUpControllerView(this);
        Abilities ab = new Abilities("algo", 10, 10,1);
        Type t1 = new Type("dePrueba");
        mc = (MainCharacter)(game.getModel().getPersons().get(0));
        listAbilities = new LinkedList<Abilities>();
        listAbilities.add(AbilityFactory.createAbility(AbilityFactory.Ability.bolaDeSabiduria));
        listAbilities.add(AbilityFactory.createAbility(AbilityFactory.Ability.aumentarVelocidadDeEscritura));
        listAbilities.add(AbilityFactory.createAbility(AbilityFactory.Ability.cadaXHorasDeClase));
        setScreen(new MainMenuScreen(this));

    }

        public com.mygdx.game.Game getGame(){
            return game;
        }

    public void setExplorerScreen() {
        MapHandler mh = game.getModel().getMapHandler();
        if(mh.getCurrentMap().getName() == "initial") {
            mh.setCurrentMap("bar");
            mc.setPosition(new Position2D(2,1));
        }
        else
            mc.setPosition(new Position2D(mc.getPosition().getX(),mc.getPosition().getY()-1));
        GameMap map = mh.getCurrentMap();
        explore = new ExploreScreen(this,map,mc);
        setScreen(explore);
    }

        public void changeMap(String mapName, Position2D pos){
            MapHandler mh = game.getModel().getMapHandler();
            mh.setCurrentMap(mapName);
            GameMap map = mh.getCurrentMap();
            mc.setPosition(pos);
            explore = new ExploreScreen(this,map,mc);
            setScreen(explore);
        }

    public void setCombatScreen(){
        setScreen(new CombatScreen(this));
    }

    public void save(){
        try {
            game.saveGame();
        } catch(IOException e){
            e.getMessage(); //cambiar
        }
    }
       /*public void load(){
        try {

            game = game.loadGame();
            //game.setUpControllerView(this);
            System.out.println("Aca llego");
            MapHandler mh = game.getModel().getMapHandler();
            GameMap map = mh.getCurrentMap();
            System.out.println("map = " + map.getName());
            explore = new ExploreScreen(this,map,mc);
           setScreen(explore);
        } catch(Exception e){
            e.getMessage(); //cambiar
        }

    }*/
    public void setCombat(Combat currentCombat){
           // System.out.println("Aca esta el combate.\n");
        this.currentCombat = currentCombat;
       // setScreen(new CombatScreen(this));
    }

    public Combat getCurrentCombat(){
        return currentCombat;
    }

   public void healRecover(){
        for(Hero h : mc.getParty()){
            h.healCharacter(h.getHP());
            h.modifyCurrentWillPower(h.getWillPower());
        }
    }
    public void endCombat(boolean win){
        if(win == true){
            float exp = 0;
            for(Enemy e: currentCombat.getHeadOfChair().getParty()){
                exp += e.getRewardExperience();
            }
            for(Hero h: mc.getParty()){
                System.out.println("Exp = " + exp);
                h.addExp(exp);
                System.out.println("Experience to next level = "+ h.getExpToNextLevel() + "level = " + h.getLevel());
            }
        }

        getCurrentCombat().endCombat();
        // HeadOfChair hoc = this.getCurrentCombat().getHeadOfChair();
        /*if(currentCombat.winner()){
            for(Enemy e : hoc.getParty()){
                mc.addExp(e.getRewardExperience());
            }
//        		for(Abilities a : mc.getAbilities()){
//        			if(a.getName().equals(hoc.getSpecialAbility())){
//        				flag=true;
//        			}
//        		}
//        		if(!flag){
//        			mc.addSpecialAbility(hoc.getSpecialAbility());
//        		}
        }*/
        setExplorerScreen();
    }


  /*  public void machineTurn(){
        this.getCurrentCombat().machineAttack();
        if(this.getCurrentCombat().getMainCharacter().isKnockedOut()){
            this.getCurrentCombat().nextTurn();
        }
        if(currentCombat.isItFinished()){
            this.endCombat();
        }
    }*/

    @Override
        public void render () {
            super.render();

        }

        @Override
        public void dispose () {
        }

        public SpriteBatch getBatch(){
            return batch;
        }
}

