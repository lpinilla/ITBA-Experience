package com.mygdx.game;

import combat.*;
import person.*;
import person.Type;
import map.*;
import map.Position2D;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author mheimann on 16/06/17.
 */
public class ControllerView extends Game{

        private SpriteBatch batch;
        public static final int WIDTH = 450;
        public static final int HEIGHT = 720;
        public Combat currentCombat = null;
    
        @Override
        public void create() {
            batch = new SpriteBatch();
            setScreen(new CombatScreenBis(this));
            //setScreen(new MainGameScreen(this));   
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
        
        public void createCombat(Combat currentCombat){
        	this.currentCombat = currentCombat;
        }
        public Combat getCurrentCombat(){
        	return this.currentCombat;
        }
      
}
