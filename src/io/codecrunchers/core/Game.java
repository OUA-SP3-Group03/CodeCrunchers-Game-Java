package io.codecrunchers.core;

import io.codecrunchers.gfx.Display;
import io.codecrunchers.input.MouseManager;
import io.codecrunchers.input.KeyboardManager;

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
    private MouseManager mouseManager;
    private KeyboardManager keyManager;

    //main display class
    private Display display;

    //main game constructor
    public Game(int width, int height, String title){
        //set the class variables passed from the launcher
        this.width = width;
        this.height = height;
        this.title = title;
    }

    //initialization method, is private and is called before the start method

    private void initialization(){

    }

    @Override // thread run method
    public void run() {

    }
}
