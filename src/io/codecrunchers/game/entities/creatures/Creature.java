package io.codecrunchers.game.entities.creatures;

import io.codecrunchers.core.ASCII;
import io.codecrunchers.facades.App;
import io.codecrunchers.game.entities.Entity;
import io.codecrunchers.game.tiles.Tile;

import java.awt.*;

public abstract class Creature extends Entity {


    protected int health;
    protected boolean jumping = false, falling = false;
    protected float xVel, yVel;

    //Records the player's direction
    //right = 1
    //left = -1
    protected int facing;
    protected float gravity = 1.3f;

    protected long lastAttackTimer, attackCooldown = 300, attackTimer = attackCooldown;


    public Creature(float x, float y, int width, int height, App app) {
        super(x, y, width, height, app);

    }



    @Override
    public void tick() {
        this.x += xVel;
        this.y += yVel;
    }

    //Gravity method (numbers might need tweaking, waiting on collision)
    public void fall() {
        if (!this.app.getTileAtLocation(((int) (this.x) / 64), (int) (this.y + height) / 64).solid()) {

            this.yVel += gravity;
        } else {
            this.yVel = 0;
            this.falling = false;
        }
    }


    public Rectangle range() {
        Rectangle bounds = getBounds();
        Rectangle range = new Rectangle();

        range.width = 30;
        range.height = bounds.height;

        if (facing == 1) {
            range.x = bounds.x + bounds.width - bounds.width/4;
            range.y = bounds.y;
        }
        if (facing == -1) {
            range.x = bounds.x - bounds.width/4;
            range.y = bounds.y;
        }


        return range;
    }



    @Override
    public void render(Graphics g) {


    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void die() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(((int) ((int) this.x - this.app.getCamera().getxOffset())), (int) ((int) this.y - this.app.getCamera().getyOffset()), 64, 64);

    }

    public void hurt(int dmg) {

        health -= dmg;
        if (health <= 0) {
            die();
        }
        System.out.println("Remaining Health: " + health);
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