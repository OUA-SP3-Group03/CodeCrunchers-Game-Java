package io.codecrunchers.game.states;

import io.codecrunchers.core.ASCII;
import io.codecrunchers.game.entities.Entity;
import io.codecrunchers.game.entities.creatures.Player;
import io.codecrunchers.facades.App;

import java.awt.*;

public class GameState extends State {

    private App app;
    Entity player;

    @Override
    public void boot(App app) {
        this.app = app;

        this.app.level().generate();
        this.app.setWorldWidth(this.app.level().width());
        this.app.setWorldHeight(this.app.level().height());
        this.app.setWorld(this.app.level().tiles());

        player = new Player(64, 0, 64, 64, this.app);
        this.app.registerEntity(player);
    }

    public void tick() {
        if(this.app.keyPressed().containsKey(ASCII.escape)){
            this.app.keyPressed().remove(ASCII.escape);
            this.app.level().generate();
            this.app.setWorldWidth(this.app.level().width());
            this.app.setWorldHeight(this.app.level().height());
            this.app.setWorld(this.app.level().tiles());
        }

        this.app.getCamera().centerOnEntity(player);
    }
    public void render(Graphics g) {

    }
}
