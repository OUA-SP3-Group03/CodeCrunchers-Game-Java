package io.codecrunchers.classes.states;

import java.awt.*;

public abstract class State {

    protected StateManager gsm;

    //TODO add constructor arguments (GameStateManager gsm)
    public State() {
        //this.gsm = gsm;
        initialise();
    }

    public abstract void initialise();
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void keyPressed (int key);
    public abstract void keyReleased(int key);
}
