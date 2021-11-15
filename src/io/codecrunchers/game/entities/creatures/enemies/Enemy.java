package io.codecrunchers.game.entities.creatures.enemies;

import io.codecrunchers.facades.App;
import io.codecrunchers.game.entities.creatures.Creature;

import java.awt.*;

public abstract class Enemy extends Creature {

    protected int facing;

    public Enemy(float x, float y, int width, int height, App app) {
        super(x, y, width, height, app);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.texture, (int) ((int) this.x - this.app.getCamera().getxOffset()), (int) ((int) this.y - this.app.getCamera().getyOffset()), null);
    }


    public void setFacing(int facing) {
        this.facing = facing;
    }
}
