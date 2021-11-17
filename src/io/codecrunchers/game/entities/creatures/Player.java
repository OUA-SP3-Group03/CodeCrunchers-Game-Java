package io.codecrunchers.game.entities.creatures;
import io.codecrunchers.core.ASCII;
import io.codecrunchers.facades.App;
import io.codecrunchers.game.entities.Entity;
import io.codecrunchers.game.entities.creatures.enemies.Enemy;
import io.codecrunchers.providers.EntityServiceProvider;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Creature {




    private final App app;
    private boolean jumping = false;
    private boolean attacking = false;
    private int fallSpeed = 0;
    private int jumpSpeed = 30;
    private int moveSpeed = 8;
    private boolean showPowerUps = false;
    private int dmg;
    public Player(float x, float y, App app) {
        super(x, y);
        this.app = app;
        this.texture = this.app.texture().allImages()[26];
        this.health = maxHealth;
        this.facing = 1;
        this.dmg = 10;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    public void gameEnd(){
        if (this.x >= 8000){
            setEnd(true);

        }
    }


    @Override
    public void tick() {
        if (facing == 1)
            this.texture = this.app.texture().animation("playerIdleRight");
        else
            this.texture = this.app.texture().animation("playerIdleLeft");

        if (!showPowerUps) {
            if ((this.app.keyPressed().containsKey((int) 'D') || this.app.keyPressed().containsKey(KeyEvent.VK_RIGHT))
                    && !this.app.getTileAtLocation(((int) (this.x + 46) / 64), (int) (this.y + 32) / 64).solid()) {
                this.x += moveSpeed;
                this.texture = this.app.texture().animation("playerRunRight");
                facing = 1;
            }

            if ((this.app.keyPressed().containsKey((int) 'A') || this.app.keyPressed().containsKey(KeyEvent.VK_LEFT))
                    && !this.app.getTileAtLocation(((int) (this.x + 12) / 64), (int) (this.y + 32) / 64).solid()
                    && this.x >= this.app.getCamera().getxOffset() - 12) {
                this.x -= moveSpeed;
                this.texture = this.app.texture().animation("playerRunLeft");
                facing = -1;
            }
        }
        else {
            if ((this.app.keyPressed().containsKey((int) 'D') && this.app.keyPressed().containsKey(ASCII.space)
                    || this.app.keyPressed().containsKey(KeyEvent.VK_RIGHT) && this.app.keyPressed().containsKey(ASCII.space))) {
                this.dmg += 10;
                showPowerUps = false;
            }

            if ((this.app.keyPressed().containsKey((int) 'A') && this.app.keyPressed().containsKey(ASCII.space)
                    || this.app.keyPressed().containsKey(KeyEvent.VK_LEFT) && this.app.keyPressed().containsKey(ASCII.space))) {
                this.moveSpeed = this.moveSpeed + 4;
                showPowerUps = false;
            }
        }

        if(this.app.keyPressed().containsKey((int)'W') || this.app.keyPressed().containsKey(KeyEvent.VK_UP)) {
            jumping = true;
        }

        if (!showPowerUps) {
            if (this.app.keyPressed().containsKey(ASCII.space)) {
                attack();
            }
        }

        jump();
        fall();

        checkCollision();

        if(this.health <= 0 || this.y >= this.app.config().interfaceHeight()){
            this.die();
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(this.app.showDebug()){
            g.setColor(Color.green);
            g.drawRect((int) ((int)this.x- this.app.getCamera().getxOffset()),(int)this.y,this.width,this.height);
            g.setColor(Color.red);
            g2d.draw(range());
        }
        g.drawImage(this.texture, (int) ((int)this.x - this.app.getCamera().getxOffset()), (int) ((int)this.y - this.app.getCamera().getyOffset()),null);



        g.setColor(Color.red);
        g2d.draw(new Rectangle((int) ((int)this.x - this.app.getCamera().getxOffset()) - 15,(int) ((int)this.y - this.app.getCamera().getyOffset()) - 15,getMaxHealth(),10));
        g2d.fillRect((int) ((int)this.x - this.app.getCamera().getxOffset()) - 15,(int) ((int)this.y - this.app.getCamera().getyOffset()) - 15,getHealth(),10);




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

    @Override
    public void die() {
            this.app.playAudioClip("hurt");
            this.setAlive(false);
    }

    @Override
    public void collisionWithPlayer() {
        //DISREGARD ON PLAYER
    }

    public void checkCollision(){

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

    public void attack() {
        this.app.playAudioClip("attack");
        if (facing == 1) {
            this.texture = this.app.texture().animation("playerAttackRight");
        }
        else {
            this.texture = this.app.texture().animation("playerAttackLeft");
        }


        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }

        attackTimer = 0;
        this.app.resetAudioClip("attack");

        for (int i = 0; i < this.app.getEntity().size(); i++) {
            if (this.app.getEntity().get(i).getClass().getSimpleName().matches("MeleeEnemy")) {
                Creature tempObject = (Creature) this.app.getEntity().get(i);
                if (tempObject.equals(this)) {
                    continue;
                }
                if (tempObject.getBounds().intersects(this  .range())) {
                    Enemy target = (Enemy) tempObject;

                    if (target.getBounds().intersects(this.range())) {
                        tempObject.hurt(dmg);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void hurt(int value) {
        this.health -=value;
    }

    public Rectangle getBounds() {
        return new Rectangle(((int) ((int) this.x - this.app.getCamera().getxOffset())), (int) ((int) this.y - this.app.getCamera().getyOffset()), 64, 64);

    }
}