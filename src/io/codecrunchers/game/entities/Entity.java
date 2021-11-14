package io.codecrunchers.game.entities;

import io.codecrunchers.facades.App;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected float x,y;
    protected int width, height;
    protected boolean alive = true;
    protected App app;

    protected BufferedImage texture;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void die();
    public abstract void collisionWithPlayer();


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)this.x,(int)this.y,this.width,this.height);
    }

    public boolean isAlive(){
        return this.alive;
    }
}
