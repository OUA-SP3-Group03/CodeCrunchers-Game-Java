package io.codecrunchers.core;

import io.codecrunchers.gfx.Display;
import io.codecrunchers.input.MouseInputManager;
import io.codecrunchers.input.KeyboardInputManager;

import java.awt.*;

public class Game implements Runnable{

    //Main graphics variable
    private Graphics g;

    //Main integer width and height for the window
    private int width;
    private int height;
    private String title;

    //Thread and running variable
    private Thread thread;
    private Boolean running = false;

    //Main mouse and key listener
    private MouseInputManager mouseManager;
    private KeyboardInputManager keyManager;

    //Main display class
    private Display display;

    //Main game constructor
    public Game(String title, int width, int height){
        //Set the class variables passed from the launcher
        this.width = width;
        this.height = height;
        this.title = title;
    }

    //Initialization method, is private and is called before the start method
    private void initialization(){

    }

    @Override //Thread run method
    public void run() {

    }
}
