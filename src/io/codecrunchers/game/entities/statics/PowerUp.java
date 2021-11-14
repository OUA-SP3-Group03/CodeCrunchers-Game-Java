package io.codecrunchers.game.entities.statics;

import io.codecrunchers.facades.App;

import java.awt.*;

public class PowerUp extends StaticEntity {

    public PowerUp(float x, float y, App app) {
        super(x, y);
        this.app = app;
        this.texture = this.app.texture().allImages()[34];
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.texture, (int) ((int)x+ this.app.getCamera().getxOffset()),(int)y,null);
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
