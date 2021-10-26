package io.codecrunchers.entities.creatures;

import io.codecrunchers.entities.Entity;
import io.codecrunchers.providers.EntityServiceProvider;
import org.w3c.dom.css.Rect;

import java.awt.*;

public class Player extends Creature {

    EntityServiceProvider entityList;


    //Records the player's direction
    //right = 1
    //left = -1
    private int facing = 1;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void tick() {

        x += xVel;
        y += yVel;

        if (xVel > 0) facing = 1;
        if (xVel < 0 ) facing = -1;

        //TODO Implement gravity




        collision();
    }

    @Override
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;


        g2d.setColor(Color.red);
        g2d.draw(getBottom());
        g2d.setColor(Color.blue);
        g2d.draw(getTop());
        g2d.draw(getLeft());
        g2d.draw(getRight());
        g2d.setColor(Color.green);
        g2d.draw(getBounds());
    }

    @Override
    public void die() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y,width,height);
    }

    private void collision(){
        for (int i = 0; i < entityList.getEntities().size(); i++){
            Entity temp = entityList.getEntities().get(i);
            if (getBottom().intersects(temp.getBounds())){
                y = temp.getY() - height;
                yVel = 0;
            }
            if (getTop().intersects(temp.getBounds())){
                y = temp.getHeight() + 10;
                yVel = 0;
            } if (getLeft().intersects(temp.getBounds())){
                x = temp.getX() + temp.getWidth() + 5;
                xVel = 0;
            } if (getRight().intersects(temp.getBounds())){
                x = temp.getX() - 5;
                xVel = 0;
            }

        }
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
