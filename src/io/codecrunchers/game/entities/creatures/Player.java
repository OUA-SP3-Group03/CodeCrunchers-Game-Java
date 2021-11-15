package io.codecrunchers.game.entities.creatures;
import io.codecrunchers.core.ASCII;
import io.codecrunchers.facades.App;

import io.codecrunchers.game.entities.creatures.enemies.Enemy;

import io.codecrunchers.game.entities.Entity;

import io.codecrunchers.providers.EntityServiceProvider;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Creature {


    protected final int maxHealth = 100;

    private final App app;
    private boolean jumping = false;
    private boolean attacking = false;
    private int fallSpeed = 0;
    private int jumpSpeed = 30;
    private int moveSpeed = 6;
    private boolean movingRight = true;
    private boolean showPowerUps = false;

    public Player(float x, float y, App app) {
        super(x, y);

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

        if (movingRight)
            this.texture = this.app.texture().animation("playerIdleRight");
        else
            this.texture = this.app.texture().animation("playerIdleLeft");

        if (!showPowerUps) {
            if ((this.app.keyPressed().containsKey((int) 'D') || this.app.keyPressed().containsKey(KeyEvent.VK_RIGHT))
                    && !this.app.getTileAtLocation(((int) (this.x + 46) / 64), (int) (this.y + 32) / 64).solid()) {
                this.x += moveSpeed;
                this.texture = this.app.texture().animation("playerRunRight");
                movingRight = true;
            }

            if ((this.app.keyPressed().containsKey((int) 'A') || this.app.keyPressed().containsKey(KeyEvent.VK_LEFT))
                    && !this.app.getTileAtLocation(((int) (this.x + 12) / 64), (int) (this.y + 32) / 64).solid()
                    && this.x >= this.app.getCamera().getxOffset() - 12) {
                this.x -= moveSpeed;
                this.texture = this.app.texture().animation("playerRunLeft");
                movingRight = false;

            }
        } else {
            this.xVel = 0;

        }


            if ((this.app.keyPressed().containsKey((int) 'D') && this.app.keyPressed().containsKey(ASCII.space)
                    || this.app.keyPressed().containsKey(KeyEvent.VK_RIGHT) && this.app.keyPressed().containsKey(ASCII.space))) {
                showPowerUps = false;
            }


        if (this.app.keyPressed().containsKey(ASCII.space)){
            attack();
        }

        if (xVel > 0) facing = 1;
        if (xVel < 0 ) facing = -1;

            if ((this.app.keyPressed().containsKey((int) 'A') && this.app.keyPressed().containsKey(ASCII.space)
                    || this.app.keyPressed().containsKey(KeyEvent.VK_LEFT) && this.app.keyPressed().containsKey(ASCII.space))) {
                this.moveSpeed = this.moveSpeed + 10;
                showPowerUps = false;
            }
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


        checkCollision();
    }

    @Override
    public void render(Graphics g) {

        if(this.app.showDebug()){
            g.setColor(Color.green);
            g.drawRect((int) ((int)this.x- this.app.getCamera().getxOffset()),(int)this.y,this.width,this.height);
        }

        g.drawImage(this.texture, (int) ((int)this.x - this.app.getCamera().getxOffset()), (int) ((int)this.y - this.app.getCamera().getyOffset()),null);

        if(showPowerUps) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Dialog", Font.BOLD, 40));
            g.drawString("HOLD SPACEBAR AND CHOOSE A POWERUP", 200, 160);

            g.setColor(Color.ORANGE);
            g.fillRect(300,200,200,400);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Dialog", Font.BOLD, 30));
            g.drawString("SPEED", 350, 250);

            g.setFont(new Font("Dialog", Font.BOLD, 20));
            g.drawString("Increase player's", 310, 370);
            g.drawString("movement speed!", 325, 395);
            g.setFont(new Font("Dialog", Font.BOLD, 60));
            g.drawString("<- A", 340, 500);

            g.setColor(Color.ORANGE);
            g.fillRect(700,200,200,400);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Dialog", Font.BOLD, 30));
            g.drawString("DAMAGE", 740, 250);

            g.setFont(new Font("Dialog", Font.BOLD, 20));
            g.drawString("Increase player's", 710, 370);
            g.drawString("attack damage!", 730, 395);
            g.setFont(new Font("Dialog", Font.BOLD, 60));
            g.drawString("D ->", 745, 500);
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
        if(this.y >= this.app.config().interfaceHeight()){
            this.app.playAudioClip("hurt");
            this.setAlive(false);
        }
    }

    @Override
    public void collisionWithPlayer() {
        //DISREGARD ON PLAYER
    }

    public void checkCollision(){


    public int getMaxHealth() {
        return maxHealth;
    }

        Rectangle bounds =  new Rectangle((int)this.x,(int)this.y,this.width,this.height);

        for (Entity entity: ((EntityServiceProvider)this.app.getProvider("entity")).getEntities()) {
            if(entity != this && entity.isAlive()) {
                if (bounds.intersects(entity.getBounds())) {
                    entity.collisionWithPlayer();

                    if(entity.getClass().getName().equals("io.codecrunchers.game.entities.statics.PowerUp")) {
                        showPowerUps = true;
                    }
                }
            }
        }

    }
}