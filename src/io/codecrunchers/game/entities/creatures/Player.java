package io.codecrunchers.game.entities.creatures;
import io.codecrunchers.core.ASCII;
import io.codecrunchers.facades.App;
import io.codecrunchers.game.entities.creatures.enemies.Enemy;
import io.codecrunchers.providers.EntityServiceProvider;

import java.awt.*;

public class Player extends Creature {

    EntityServiceProvider entityList;


    protected final int maxHealth = 100;
    private App app;



    public Player(float x, float y, int width, int height, App app) {
        super(x, y, width, height, app);
        this.app = app;
        setHealth(maxHealth);
        this.texture = this.app.texture().allImages()[26];
    }



    @Override
    public void tick() {
        this.x += xVel;
        this.y += yVel;

        if(!this.app.getTileAtLocation(((int)(this.x + width)/64),(int)this.y/64).solid()) {

            if(this.app.keyPressed().containsKey((int)'D') ){
                 this.xVel = 10;
            } else {
                this.xVel = 0;
            }
        } else {
            this.xVel = 0;

        }

        if(this.app.keyPressed().containsKey((int)'A')){
            if(!this.app.getTileAtLocation(((int)(this.x - width/6)/64),(int)this.y/64).solid()) {
                this.xVel = -10;
            }
        }

        if (this.app.keyPressed().containsKey(ASCII.space)){
            attack();
        }

        jumping = this.app.keyPressed().containsKey((int)'W');

        if (xVel > 0) facing = 1;
        if (xVel < 0 ) facing = -1;



        fall();
        jump();

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.yellow);
        g2d.draw(getBounds());
        g.setColor(Color.black);
        g2d.draw(range());


        g.setColor(Color.red);
        //g2d.draw(new Rectangle((int) ((int)this.x - this.app.getCamera().getxOffset()),(int) ((int)this.y - this.app.getCamera().getyOffset()) - 10,getMaxHealth(),10));
        //g2d.fillRect((int) ((int)this.x - this.app.getCamera().getxOffset()),(int) ((int)this.y - this.app.getCamera().getyOffset()) - 10,getHealth(),10);
        g.drawImage(this.texture, (int) ((int)this.x - this.app.getCamera().getxOffset()), (int) ((int)this.y - this.app.getCamera().getyOffset()),null);
    }



    public void jump(){
        if (!falling && jumping){
            if (!this.app.getTileAtLocation(((int)(this.x)/64),(int)(this.y - 128)/64).solid()){
                falling = true;
                this.yVel = -20;
            } else {
                falling = true;
                jumping = false;
            }
        }
    }

    public void attack() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }

        attackTimer = 0;

        for (int i = 0; i < this.app.getEntity().size(); i++) {
            Creature tempObject = (Creature) this.app.getEntity().get(i);
            if (tempObject.equals(this)) {
                continue;
            }
            if (tempObject.getBounds().intersects(super.range())) {
                Enemy target = (Enemy) tempObject;

                if (target.getBounds().intersects(super.range())) {
                    System.out.println("attacked");
                    tempObject.hurt(20);
                    return;
                }
            }
        }
    }

    @Override
    public void hurt(int dmg) {
        super.hurt(dmg);
    }

    @Override
    public void die() {
        setAlive(false);
    }

    @Override
    public Rectangle getBounds() {
       return super.getBounds();
    }

    @Override
    public boolean isAlive() {
        return alive;
    }


    public int getMaxHealth() {
        return maxHealth;
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