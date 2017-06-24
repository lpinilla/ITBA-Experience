package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ControllerView;
import com.mygdx.game.Map;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1000;
		config.height =  1000;
		config.resizable = false;
		LwjglApplication lwjglApplication = new LwjglApplication(new ControllerView(), config);
	}
}
