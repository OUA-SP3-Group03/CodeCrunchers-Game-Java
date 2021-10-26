package io.codecrunchers.entities.creatures.enemies;

import java.awt.*;

public class MeleeEnemy extends Enemy {

    public MeleeEnemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public void die() {

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y,width,height);
    }


}
