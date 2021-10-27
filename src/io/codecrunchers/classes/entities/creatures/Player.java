package io.codecrunchers.classes.entities.creatures;
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
        if(this.app.keyPressed().containsKey((int)'D')){
            this.xVel = 20;
        }else if(this.app.keyPressed().containsKey((int)'A')){
            this.xVel = -20;
        }else{
            this.xVel = 0;
        }


        x += xVel;
        y += yVel;
        if (xVel > 0) facing = 1;
        if (xVel < 0 ) facing = -1;
        //TODO Implement gravity


        collision();
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(this.app.texture().animation("powerUp"),(int)this.x,(int)this.y,null);
    }
    @Override
    public void die() {
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y,width,height);
    }
    private void collision(){
        //for (int i = 0; i < entityList.getEntities().size(); i++){
        //    Entity temp = entityList.getEntities().get(i);
        //    if (getBottom().intersects(temp.getBounds())){
        //        y = temp.getY() - height;
        //        yVel = 0;
        //    }
        //    if (getTop().intersects(temp.getBounds())){
        //        y = temp.getHeight() + 10;
        //        yVel = 0;
        //    } if (getLeft().intersects(temp.getBounds())){
        //        x = temp.getX() + temp.getWidth() + 5;
        //        xVel = 0;
        //    } if (getRight().intersects(temp.getBounds())){
        //        x = temp.getX() - 5;
        //        xVel = 0;
        //    }
//
        //      }
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