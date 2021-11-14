package io.codecrunchers.game.entities.statics;

import io.codecrunchers.facades.App;

import java.awt.*;

public class PowerUp extends StaticEntity {

    public PowerUp(float x, float y, App app) {
        super(x, y);
        this.app = app;
        this.texture = this.app.texture().allImages()[26];
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        if(this.app.showDebug()){
            g.setColor(Color.pink);
            g.drawRect((int) ((int)this.x- this.app.getCamera().getxOffset()),(int)this.y,this.width,this.height);
        }
        g.drawImage(this.texture, (int) ((int)x- this.app.getCamera().getxOffset()),(int)y,null);
    }


    @Override
    public void die() {
        this.app.resetAudioClip("powerup");
        this.setAlive(false);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.x,(int)this.y,this.width,this.height);
    }


    @Override
    public void collisionWithPlayer() {
        this.app.playAudioClip("powerup");
        this.die();
    }
}
