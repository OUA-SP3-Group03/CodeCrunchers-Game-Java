package io.codecrunchers.entities.creatures.enemies;

import io.codecrunchers.entities.creatures.Creature;

public abstract class Enemy extends Creature {

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
    }
}
