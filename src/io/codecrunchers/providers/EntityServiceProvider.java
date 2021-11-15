package io.codecrunchers.providers;
import io.codecrunchers.core.Provider;
import io.codecrunchers.game.entities.Entity;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.util.ArrayList;
public class EntityServiceProvider extends Provider {

    private ArrayList<Entity> entities;
    private App app;

    @Override
    public void boot(App app) {
        entities = new ArrayList<>();
        this.app = app;
    }
    @Override
    public boolean performTick() {
        return true;
    }
    @Override
    public boolean performRender() {
        return true;
    }
    @Override
    public void tick() {

        if ( this.app.currentState().matches("game") && !this.entities.isEmpty()) {
            //Create an if statement to see which state is the game running
            //Ticking all the entity objects
            for (Entity tempEntity : entities) {
                if (tempEntity.isAlive()) {
                    tempEntity.tick();
                }
            }
        }
    }
    @Override
    public void render(Graphics g) {
        if ( this.app.currentState().matches("game") && !this.entities.isEmpty() ) {
            //addEntity(new Player(400,200,80, 100));
            //Rendering all the entity objects
            for (Entity tempEntity : entities) {
                if (tempEntity.isAlive()) {
                    tempEntity.render(g);
                }
            }
        }
    }
    public ArrayList<Entity> getEntities() {
        return entities;
    }
    public void registerEntity(Entity e){
        entities.add(e);
    }

    public boolean checkLocation(int x, int y){
        boolean outcome =  false;

        for (Entity e: this.entities) {
            if (e.getX() == x && e.getY() == y) {
                outcome = true;
                break;
            }
        }

        return outcome;
    }

    public void reset(){
       this.entities.clear();
    }
}