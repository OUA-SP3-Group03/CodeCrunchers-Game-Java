package io.codecrunchers.game.entities.creatures.enemies;


import io.codecrunchers.core.ASCII;
import io.codecrunchers.facades.App;
import io.codecrunchers.game.entities.Entity;
import io.codecrunchers.game.entities.creatures.Creature;
import io.codecrunchers.game.entities.creatures.Player;
import io.codecrunchers.game.tiles.Tile;
import io.codecrunchers.facades.App;


import java.awt.*;
import java.util.Random;

public class MeleeEnemy extends Enemy {


    protected final int maxHealth = 100;


    public MeleeEnemy(float x, float y, int width, int height, App app) {
        super(x, y, width, height, app);
        this.texture = this.app.texture().allImages()[26];
        facing = randFacing();
        this.setHealth(100);

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

    }

    @Override
    public void render(Graphics g) {

      if(this.app.showDebug()){
            g.setColor(Color.red);
            g.drawRect((int) ((int)this.x- this.app.getCamera().getxOffset()),(int)this.y,this.width,this.height);
        }
        g.drawImage(this.texture, (int) ((int)this.x- this.app.getCamera().getxOffset()),(int)this.y,null);

        g.drawImage(this.texture, ((int) ((int) this.x - this.app.getCamera().getxOffset())), (int) ((int) this.y - this.app.getCamera().getyOffset()), null);

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());
        g2d.draw(new Rectangle((int) ((int) this.x - this.app.getCamera().getxOffset()), (int) ((int) this.y - this.app.getCamera().getyOffset()) - 10, getMaxHealth(), 10));
        g2d.fillRect((int) ((int) this.x - this.app.getCamera().getxOffset()), (int) ((int) this.y - this.app.getCamera().getyOffset()) - 10, getHealth(), 10);


    }

    private void moveRandom(int facing) {
        setxVel(facing * 1.2f);
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
                Player target = (Player) tempObject;

                if (target.getBounds().intersects(super.range())) {
                    System.out.println("attacked");
                    tempObject.hurt(20);
                    return;
                }
            }
        }
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

    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public void setFacing(int facing) {
        super.setFacing(facing);
    }

    @Override
    public boolean isAlive() {
        return alive;
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
    public void die() {
        this.setAlive(false);
    }

    @Override
    public void collisionWithPlayer() {

    }


}
