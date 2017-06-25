package com.mygdx.game;

import java.io.IOException;

/**
 * Created by agustin on 23/06/17.
 */
public class Prueba {
    public static void main(String args[])throws IOException{
        System.out.println("A ver si anda.");
        new Game();
        //ControllerView v = new ControllerView();
    }
    public Prueba()throws IOException{
        System.out.println("Esta inicia todo.");
        //ControllerView v = new ControllerView();
        new Game();
    }
}
