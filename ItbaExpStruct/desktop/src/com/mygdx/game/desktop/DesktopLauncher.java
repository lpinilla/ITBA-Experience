package com.mygdx.game.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ControllerView;
import com.mygdx.game.Map;
import com.mygdx.game.Prueba;

import java.io.IOException;

public class DesktopLauncher{
	public static void main (String[] arg)  throws IOException{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 30*20;
		config.height =  30*20;
		//config.width = 1000;
		//config.height =  1000;

		config.resizable = false;
		config.title = "ITBA Experience, Hell of a Week";
		com.mygdx.game.Game game = new com.mygdx.game.Game();
		new LwjglApplication(new ControllerView(game), config);
		//new LwjglApplication(new (), config);
	}
}
