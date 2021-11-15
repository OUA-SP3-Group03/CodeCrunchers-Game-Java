package io.codecrunchers.game.entities.creatures.enemies;

import io.codecrunchers.facades.App;
import io.codecrunchers.game.entities.creatures.Creature;
import io.codecrunchers.game.entities.creatures.Player;

import java.awt.*;
import java.util.Random;

public class MeleeEnemy extends Enemy {

    private final App app;

    public MeleeEnemy(float x, float y, App app) {
        super(x, y);
        this.app = app;
        this.texture = this.app.texture().allImages()[27];
        facing = randFacing();
        this.health = 50;
        this.rangeWidth = 0;
    }

    @Override
    public void tick() {
        this.x += xVel;
        this.y += yVel;
        fall();
        moveRandom(facing);
        preventFalling();
        collision();

        if (xVel > 0) facing = 1;
        if (xVel < 0) facing = -1;
        attack();

        if(this.health <= 0){
            this.die();
        }
    }

    @Override
    public void render(Graphics g) {
        if (this.app.showDebug()) {
            g.setColor(Color.red);
            g.drawRect((int) ((int) this.x - this.app.getCamera().getxOffset()), (int) this.y, this.width, this.height);
        }
        g.drawImage(this.texture, (int) ((int) this.x - this.app.getCamera().getxOffset()), (int) this.y, null);

        g.setColor(Color.red);
        ((Graphics2D) g).draw(new Rectangle((int) ((int)this.x - this.app.getCamera().getxOffset()),(int) ((int)this.y - this.app.getCamera().getyOffset()) - 10,getMaxHealth(),10));
        ((Graphics2D) g).fillRect((int) ((int)this.x - this.app.getCamera().getxOffset()),(int) ((int)this.y - this.app.getCamera().getyOffset()) - 10,getHealth(),10);
    }


    @Override
    public void die() {
        this.setAlive(false);
    }

    @Override
    public void collisionWithPlayer() {
    }

    private int randFacing() {
        Random rand = new Random();
        int max = 2;
        int randNum = rand.nextInt(max);

        if (randNum == 1) {
            return 1;
        } else {

            return -1;
        }
    }

    private void moveRandom(int facing) {
        setxVel(facing * 1.2f);
    }

    public void preventFalling() {
        if (this.app.getTileAtLocation(((int) ((this.x) / 64)), ((int) (this.y + height) / 64)).solid()) {
            if (!this.app.getTileAtLocation(((int) ((this.x + width) / 64)), ((int) (this.y + height) / 64)).solid()) {

                xVel = -3;
            }
            if (!this.app.getTileAtLocation(((int) ((this.x - width) / 64)), ((int) (this.y + height) / 64)).solid()) {

                xVel = 3;
            }
        }
    }

    public void collision() {
        if (this.app.getTileAtLocation(((int) (this.x + width) / 64), (int) this.y / 64).solid()) {
            this.xVel = -3;

        }
        if (this.app.getTileAtLocation(((int) (this.x - width / 6) / 64), (int) this.y / 64).solid()) {
            this.xVel = 3;
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
            if(this.app.getEntity().get(i).getClass().getSimpleName().matches("Player")) {
                Creature tempObject = (Creature) this.app.getEntity().get(i);
                if (tempObject.equals(this)) {
                    continue;
                }
                if (tempObject.getBounds().intersects(this.range())) {
                    Player target = (Player) tempObject;

                    if (target.getBounds().intersects(this.range())) {
                        System.out.println("attacked");
                        tempObject.hurt(20);
                        return;
                    }
                }
            }
        }
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

    @Override
    public void hurt(int value) {
        this.health -= value;
    }
}
