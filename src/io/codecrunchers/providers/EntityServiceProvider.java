package io.codecrunchers.providers;
import io.codecrunchers.core.Provider;
import io.codecrunchers.classes.entities.Entity;
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

        if ( this.app.currentState().matches("game") ) {
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
        if ( this.app.currentState().matches("game") ) {
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
    //What if there are more than one entity
    public void removeEntity(Entity e){
        this.entities.remove(e);
    }
}