package io.codecrunchers.entities.creatures.enemies;

import io.codecrunchers.engine.entity.Creature;

public abstract class Enemy extends Creature {

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }
}
