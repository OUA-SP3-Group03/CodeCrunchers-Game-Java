package io.codecrunchers.game.entities.creatures;
import io.codecrunchers.core.ASCII;
import io.codecrunchers.facades.App;
import io.codecrunchers.game.entities.Entity;
import io.codecrunchers.providers.EntityServiceProvider;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Creature {

    private final App app;
    private boolean jumping = false;
    private boolean attacking = false;
    private int fallSpeed = 0;
    private int jumpSpeed = 30;

    public Player(float x, float y, App app) {
        super(x, y);
        this.app = app;
        this.texture = this.app.texture().allImages()[26];
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void tick() {
        this.texture = this.app.texture().animation("playerIdol");

        if((this.app.keyPressed().containsKey((int)'D') || this.app.keyPressed().containsKey(KeyEvent.VK_RIGHT))
                && !this.app.getTileAtLocation(((int)(this.x+46)/64),(int)(this.y+32)/64).solid() ){
                this.x += 6;
        }
        if((this.app.keyPressed().containsKey((int)'A') || this.app.keyPressed().containsKey(KeyEvent.VK_LEFT))
                && !this.app.getTileAtLocation(((int)(this.x+12)/64),(int)(this.y+32)/64).solid()
                && this.x >= this.app.getCamera().getxOffset() - 12 ) {
                this.x -= 6;
        }

        if(this.app.keyPressed().containsKey((int)'W') || this.app.keyPressed().containsKey(KeyEvent.VK_UP)) {
            jumping = true;
        }

        //Temp attack statement
        if(this.app.keyPressed().containsKey(ASCII.space)) {
            attacking = true;
        }
        else {
            attacking = false;
        }

        attack();
        jump();
        fall();
        die();

        checkCollison();
    }

    @Override
    public void render(Graphics g) {
        if(this.app.showDebug()){
            g.setColor(Color.green);
            g.drawRect((int) ((int)this.x- this.app.getCamera().getxOffset()),(int)this.y,this.width,this.height);
        }
        g.drawImage(this.texture, (int) ((int)this.x - this.app.getCamera().getxOffset()), (int) ((int)this.y - this.app.getCamera().getyOffset()),null);
    }

    public void fall() {
        if(!(this.app.getTileAtLocation( ((int)(this.x+32)/64),(int)(this.y+64)/64).solid() ||
                this.app.getTileAtLocation( ((int)(this.x)/64),(int)(this.y+64)/64).solid())
                        && !jumping) {
            y += fallSpeed;
            falling = true;

            if (fallSpeed != gravity) {
                fallSpeed = fallSpeed + 2;
            }

            //Player doesn't fall through tiles
            if (this.app.getTileAtLocation( ((int)(this.x+32)/64),(int)(this.y+64)/64).solid() && !jumping) {
                float tempY = this.y;
                tempY = tempY / 64;

                this.y = (int)tempY * 64;
            }
       } else {
            falling = false;
            fallSpeed = 0;
        }
    }

    public void jump() {
        if(!this.app.getTileAtLocation( ((int)(this.x+32)/64),(int)(this.y)/64).solid() && jumping && !falling) {

            //Play jump sound
            this.app.playAudioClip("jump");

            y -= jumpSpeed;
            jumpSpeed -= 2;

            if (jumpSpeed <= 0) {
                jumpSpeed = 30;
                jumping = false;
                falling = true;
            }
        }
        else {
            //Reload jump sound for next use
            if (this.app.getTileAtLocation( ((int)(this.x+32)/64),(int)(this.y+64)/64).solid())
                this.app.resetAudioClip("jump");
            jumping = false;
            falling = true;
            jumpSpeed = 30;
        }
    }

    //Temp attack method, to test sound
    public void attack() {
        if (attacking) {
            this.app.playAudioClip("attack");
        }
        else {
             this.app.resetAudioClip("attack");
        }
    }

    @Override
    public void die() {
        if(this.y >= this.app.config().interfaceHeight()){
            this.app.playAudioClip("hurt");
            this.setAlive(false);
        }
    }

    @Override
    public void collisionWithPlayer() {
        //DISREGARD ON PLAYER
    }

    public void checkCollison(){

        Rectangle bounds =  new Rectangle((int)this.x,(int)this.y,this.width,this.height);

        for (Entity entity: ((EntityServiceProvider)this.app.getProvider("entity")).getEntities()) {
            if(entity != this && entity.isAlive()) {
                if (bounds.intersects(entity.getBounds())) {
                        entity.collisionWithPlayer();
                }
            }

        }
    }

}