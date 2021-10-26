package io.codecrunchers.classes.states;

import java.awt.Graphics;
import java.util.Stack;

public class StateManager {
    private static State currentState = null;

    private Stack<State> states;

    public StateManager() {
        states = new Stack<State>();
        states.push(new GameState());
        states.push(new PauseMenuState());
        states.push(new SettingsState());
    }

    public static void setState(State state) {
        currentState = state;
    }
    public static State getCurrentState(){
        return currentState;
    }

    public void tick() {
        states.peek().tick();
    }

    public void draw(Graphics g) {
        states.peek().render(g);
    }

    public void keyPressed(int key) {
        states.peek().keyPressed(key);
    }

    public void keyReleased(int key) {
        states.peek().keyReleased(key);
    }

}
