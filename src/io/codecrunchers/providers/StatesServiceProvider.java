package io.codecrunchers.providers;

import io.codecrunchers.game.states.State;
import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.util.HashMap;

public class StatesServiceProvider extends Provider {

    private HashMap<String,State> states;

    private String currentState;
    private App app;

    //**** BOOT METHOD ****\\
    @Override
    public void boot(App app) {
        this.states = new HashMap<>();
        this.app = app;
    }

    //**** PERFORM TICK METHOD ****\\
    @Override
    public boolean performTick() {
        //this method tells the kernel if this service provider needs to be ticked or not, return true for yes or false for no
        return true;
    }

    //**** PERFORM RENDER METHOD ****\\
    @Override
    public boolean performRender() {
        //this method tells the kernel if this service provider needs to render code in its render function, return true for yes or no for false.
        return true;
    }

    //**** RENDER METHOD ****\\
    @Override
    public void render(Graphics g) {
        if(this.currentState != null) {
            this.states.get(this.currentState).render(g);
        }
    }

    //**** TICK METHOD ****\\
    @Override
    public void tick() {
        if(this.currentState != null) {
            this.states.get(this.currentState).tick();
        }
    }

    public void registerState(String key, State state){
            this.states.put(key, state);
    }

    public void setCurrentState(String key){
        this.currentState = key;
    }

    public String getCurrentState(){
        return this.currentState;
    }

    public void initialize(){
        for (State state: this.states.values()) {
                state.boot(this.app);
        }
    }

    public State getState(String state){
        return this.states.get(state);
    }





}
