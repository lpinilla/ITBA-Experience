package controllerView;

//import build.tools.javazic.Main;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controllerView.map.ExploreScreen;
import model.abilities.Abilities;
import model.persons.Enemy;
import model.persons.HeadOfChair;
import model.persons.MainCharacter;
import model.combat.Combat;
import model.map.GameMap;
import model.map.MapHandler;
import model.map.Position2D;
import model.persons.Type;
import controllerView.combat.CombatScreen;

import java.util.Map;

/**
 * Created by agustin on 14/06/17.
 */
public class ControllerView extends Game{

        private com.mygdx.game.Game game;
        private SpriteBatch batch;
        //public static final int WIDTH = 450;
        //public static final int HEIGHT = 720;
        public static final int WIDTH = 1000;
        public static final int HEIGHT = 1000;
        private MainCharacter mc;
        private Combat currentCombat;
        private ExploreScreen explore;
        private Map map;
        //private static Game g;
        //

        public ControllerView(com.mygdx.game.Game game){
            this.game = game;
        }

        @Override
        public void create () {
            batch = new SpriteBatch();
            setScreen(new MainMenuScreen(this));
            game.setUpControllerView(this);
            Abilities ab = new Abilities("algo", 10, 10, 1);
            Type t1 = new Type("dePrueba");
            mc = new MainCharacter("Agustin", 100, 100, 10, 10, new Position2D(5, 10), t1, ab);
            /*map = new Map();
            TerrainType type = new PassingType("Pasadizo");
            TerrainType type2 = new CombatType("Pelea", new HeadOfChair("Agustin"));
            map.getMap().put(new Position2D(19,10),new Tile(true,false,type) );
            map.getMap().put(new Position2D(10,19), new Tile(true,false,type));
            map.getMap().put(new Position2D(10,0), new Tile(true,false,type));
            map.getMap().put(new Position2D(0,10), new Tile(true,false,type));
            map.getMap().put(new Position2D(19,18), new Tile(true,false,type2));

            setScreen(new ExploreScreen(this,map,new MainCharacter("Agustin",100,100,10,10,new Position2D(5,10))));
        */
        }

        public com.mygdx.game.Game getGame(){
            return game;
        }

    public void setExplorerScreen() {
      //  map = new Map();
        //TerrainType type = new PassingType("Pasadizo");
        Abilities ab = new Abilities("algo", 10, 10, 1);
        Type t1 = new Type("dePrueba");
        /*HeadOfChair hoc = new HeadOfChair("Agustin", 100, 200, 10, 10, new Position2D(10, 10), t1, 1, 50, ab);
        TerrainType type2 = new CombatType("Pelea");
        ((CombatType)type2).setHoc(hoc);

        map.getMap().put(new Position2D(19, 10), new Tile(true, false, type));
        map.getMap().put(new Position2D(10, 19), new Tile(true, false, type));
        map.getMap().put(new Position2D(10, 0), new Tile(true, false, type));
        map.getMap().put(new Position2D(0, 10), new Tile(true, false, type));
        map.getMap().put(new Position2D(19, 18), new Tile(true, false, type2));
        */
        //setScreen(new ExploreScreen(this, map, new MainCharacter("Agustin", 100, 100, 10, 10, new Position2D(5, 10), t1, ab)));
        MapHandler mh = game.getModel().getMapHandler();
        mh.setCurrentMap("bar");
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

    public void setCombat(Combat currentCombat){
           // System.out.println("Aca esta el combate.\n");
        this.currentCombat = currentCombat;
       // setScreen(new CombatScreen(this));
    }

    public Combat getCurrentCombat(){
        return currentCombat;
    }

    public void endCombat(){
        Boolean flag = false;
        this.getCurrentCombat().endCombat();
        HeadOfChair hoc = this.getCurrentCombat().getHeadOfChair();
        MainCharacter mc = this.getCurrentCombat().getMainCharacter();
        if(currentCombat.winner()){
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
        }
     //	setScreen(explore);
    }

    public void machineTurn(){
        this.getCurrentCombat().machineAttack();
        if(this.getCurrentCombat().getMainCharacter().isKnockedOut()){
            this.getCurrentCombat().nextTurn();
        }
        if(currentCombat.isItFinished()){
            this.endCombat();
        }
    }

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

