package io.codecrunchers.providers;


import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyboardServiceProvider extends Provider implements KeyListener {

    public HashMap <Integer, Boolean> keyCodes;

    //**** BOOT METHOD ****\\
    @Override
    public void boot(App app) {
        app.canvas().addKeyListener(this);
        keyCodes = new HashMap<>();

        this.booted = true;
    }

    //**** PERFORM TICK METHOD ****\\
    @Override
    public boolean performTick() {
        return false;
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
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCodes.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCodes.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public boolean isCapsLocked(){
        return Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
    }
}
