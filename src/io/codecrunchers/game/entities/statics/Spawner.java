package io.codecrunchers.game.entities.statics;

import io.codecrunchers.facades.App;
import io.codecrunchers.game.entities.creatures.enemies.MeleeEnemy;

import java.awt.*;
import java.util.Random;

public class Spawner extends StaticEntity{

    private final App app;

    public Spawner(float x, float y, App app) {
        super(x,y);
        this.texture = app.texture().allImages()[33];
        this.app = app;

        Random rng = new Random();
        int selection;
        selection = rng.nextInt(2);

        if(selection == 1){
            this.app.registerEntity(new PowerUp(this.x,this.y,this.app));
        }else {
            this.app.registerEntity(new MeleeEnemy(this.x,this.y,this.app));
        }

        this.die();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(this.app.showDebug()){
            g.setColor(Color.pink);
           // g.drawRect((int) ((int)this.x- this.app.getCamera().getxOffset()),(int)this.y,this.width,this.height);
        }
    }

    @Override
    public void die() {
        this.setAlive(false);
    }

    @Override
    public void collisionWithPlayer() {
    //DISREGARD ON THIS ENTITY
    }
}
