package io.codecrunchers.game.entities.creatures.enemies;

import io.codecrunchers.facades.App;
import io.codecrunchers.game.entities.creatures.Creature;

import java.awt.*;

public class BossEnemy extends Enemy {

    Creature creature;

    public BossEnemy(float x, float y, App app) {
        super(x, y);
        creature.setHealth(health);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }


    @Override
    public void die() {

    }

    @Override
    public void collisionWithPlayer() {

    }
}
