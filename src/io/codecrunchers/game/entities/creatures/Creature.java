package io.codecrunchers.game.entities.creatures;

import io.codecrunchers.game.entities.Entity;

public abstract class Creature extends Entity {

    protected int health;
    protected boolean jumping = false, falling = true;
    protected float xVel, yVel;
    protected float gravity = 3.0f;


    public Creature(float x, float y, int width, int height) {
        super(x, y, width, height);
    }


    //Mutators and Accessors
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getxVel() {
        return xVel;
    }

    public void setxVel(float xVel) {
        this.xVel = xVel;
    }

    public float getyVel() {
        return yVel;
    }

    public void setyVel(float yVel) {
        this.yVel = yVel;
    }
}