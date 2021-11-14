package io.codecrunchers.game.entities.statics;

import io.codecrunchers.facades.App;

import java.awt.*;

public class Spawner extends StaticEntity{

    private final App app;
    public boolean canSpawn;

    public Spawner(float x, float y, App app) {
        super(x,y);
        this.texture = app.texture().allImages()[33];
        this.app = app;
        this.canSpawn = true;

        this.app.registerEntity(new PowerUp(this.x, this.y, this.app));

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(this.app.showDebug()){
            g.setColor(Color.pink);
            g.drawRect((int)this.x,(int)this.y,this.width,this.height);
        }
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public void die() {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
