package io.codecrunchers.entities.creatures.enemies;

import io.codecrunchers.entities.creatures.Creature;

import java.awt.*;

public class BossEnemy extends Enemy {

    Creature creature;

    public BossEnemy(float x, float y, int width, int height, int health) {
        super(x, y, width, height);
        creature.setHealth(health);
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
