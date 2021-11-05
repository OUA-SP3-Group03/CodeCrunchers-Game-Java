package io.codecrunchers.game.entities.creatures;
import io.codecrunchers.core.ASCII;
import io.codecrunchers.facades.App;
import io.codecrunchers.providers.EntityServiceProvider;

import java.awt.*;

public class Player extends Creature {

    EntityServiceProvider entityList;

    //Records the player's direction
    //right = 1
    //left = -1
    private int facing = 1;
    private App app;
    private boolean jumping = false;

    public Player(float x, float y, int width, int height, App app) {
        super(x, y, width, height);
        this.app = app;
        this.texture = this.app.texture().allImages()[26];
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void tick() {
        this.xVel = 0;
        this.yVel = 0;

        if(this.app.keyPressed().containsKey((int)'D') ){
            if(!this.app.getTileAtLocation(((int)(this.x+32)/64),(int)this.y/64).solid()) {
                this.x += 32;
            }
        }
        if(this.app.keyPressed().containsKey((int)'A') && !this.app.getTileAtLocation(((int)this.x/64),((int)this.y/64)).solid()){
            if(!this.app.getTileAtLocation(((int)(this.x-32)/64),(int)this.y/64).solid()) {
                this.x -= 32;
            }
        }

        if(this.app.keyPressed().containsKey(ASCII.space)){
            this.jumping = true;
        }else{
            this.jumping = false;
        }

        x += xVel;
        y += yVel;

        if (xVel > 0) facing = 1;
        if (xVel < 0 ) facing = -1;


        fall();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.texture, (int) ((int)this.x - this.app.getCamera().getxOffset()), (int) ((int)this.y - this.app.getCamera().getyOffset()),null);
    }

    //Gravity method (numbers might need tweaking, waiting on collision)
    public void fall() {
        if(!this.app.getTileAtLocation(((int)(this.x)/64),(int)(this.y+64)/64).solid() && !this.jumping) {
            this.y += 32;
       }else if(this.jumping){
            this.jumping = false;
            this.y -= 64;
        }
    }

    @Override
    public void die() {
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y,width,height);
    }


    //These bounds are called to specify which side the player is colliding
    public Rectangle getBottom() {
        return new Rectangle((int)(x + (width/4)), (int)(y + (height)-(height/3)) - 5, width / 2,  height/3);
    }
    public Rectangle getTop(){
        return new Rectangle((int)(x + (width/4)), (int) y + 5,  width / 2,  height/3);
    }
    public Rectangle getLeft(){
        return new Rectangle((int) x + 5, (int) y + 10,  5,  height - 20);
    }
    public Rectangle getRight(){
        return new Rectangle((int)( x + width - 10), (int) y + 10,  5,  height - 20);
    }
}