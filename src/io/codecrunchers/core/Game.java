package io.codecrunchers.core;

import io.codecrunchers.gfx.Display;
import io.codecrunchers.input.MouseInputManager;
import io.codecrunchers.input.KeyboardInputManager;

import java.awt.*;

public class Game implements Runnable{

    //main graphics variable
    private Graphics g;

    //main integer width and height for the window
    private int width;
    private int height;
    private String title;

    //thread and running variable
    private Thread thread;
    private Boolean running = false;

    //main mouse and key listener
    private MouseInputManager mouseManager;
    private KeyboardInputManager keyManager;

    //main display class
    private Display display;

    //main game constructor
    public Game(String title, int width, int height){
        //set the class variables passed from the launcher
        this.width = width;
        this.height = height;
        this.title = title;
        
        //Creates a Display object so user can see window
        display = new Display(title, width, height);
    }

    //initialization method, is private and is called before the start method
    private void initialization(){

    }

    @Override // thread run method
    public void run() {

    }
}
