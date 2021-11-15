package io.codecrunchers.game.entities.creatures.enemies;

import io.codecrunchers.facades.App;

import java.awt.*;

public class MeleeEnemy extends Enemy {

    private App app;

    public MeleeEnemy(float x, float y, App app) {
        super(x, y);
        this.app = app;
        this.texture = this.app.texture().allImages()[27];

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(this.app.showDebug()){
            g.setColor(Color.red);
            g.drawRect((int) ((int)this.x- this.app.getCamera().getxOffset()),(int)this.y,this.width,this.height);
        }
        g.drawImage(this.texture, (int) ((int)this.x- this.app.getCamera().getxOffset()),(int)this.y,null);
    }


    @Override
    public void die() {
        this.setAlive(false);
    }

    @Override
    public void collisionWithPlayer() {
    }


}
