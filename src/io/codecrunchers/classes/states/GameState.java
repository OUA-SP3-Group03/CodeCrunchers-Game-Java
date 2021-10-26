package io.codecrunchers.classes.states;

import io.codecrunchers.classes.ASCII;
import io.codecrunchers.facades.App;

import java.awt.*;

public class GameState extends State {

    private App app;

    @Override
    public void boot(App app) {
        this.app = app;
    }

    public void tick() {
        if(this.app.keyPressed().containsKey(ASCII.escape)){
            this.app.keyPressed().remove(ASCII.escape);
            this.app.setCurrentState("pause");
        }
    }
    public void render(Graphics g) {

    }
}
