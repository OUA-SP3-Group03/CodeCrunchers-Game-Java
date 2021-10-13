package io.codecrunchers.providers;


import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardServiceProvider extends Provider implements KeyListener {


    //Create an array which can store the ascii code of a key that is pressed
    private boolean[] keyCodes;

    //Public booleans so other classes can check their values
    //No need to create a getter for every single one
    //Jump
    public boolean up, w;
    //Move left
    public boolean left, a;
    //Move right
    public boolean right, d;
    //Attack
    public boolean space;
    //Switch attack modes
    //public boolean ...
    //Menu
    public boolean escape, enter;

    //**** BOOT METHOD ****\\
    @Override
    public void boot(App app) {
        keyCodes = new boolean[91];
    }

    //**** PERFORM TICK METHOD ****\\
    @Override
    public boolean performTick() {
        return true;
    }

    //**** PERFORM RENDER METHOD ****\\
    @Override
    public boolean performRender() {
        return false;
    }

    //**** RENDER METHOD ****\\
    @Override
    public void render(Graphics g) {}

    //**** TICK METHOD ****\\
    @Override
    public void tick() {
        //Set boolean variables if key is pressed/released
        //Jump
        up = keyCodes[KeyEvent.VK_UP];
        w = keyCodes[KeyEvent.VK_W];
        //Move left
        left = keyCodes[KeyEvent.VK_LEFT];
        a = keyCodes[KeyEvent.VK_A];
        //Move right
        right = keyCodes[KeyEvent.VK_RIGHT];
        d = keyCodes[KeyEvent.VK_D];
        //Attack
        space = keyCodes[KeyEvent.VK_SPACE];
        //Menu
        escape = keyCodes[KeyEvent.VK_ESCAPE];
        enter = keyCodes[KeyEvent.VK_ENTER];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCodes[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCodes[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
