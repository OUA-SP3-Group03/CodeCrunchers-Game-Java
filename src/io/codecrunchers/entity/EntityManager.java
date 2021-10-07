package io.codecrunchers.entity;

import java.awt.*;
import java.util.LinkedList;

public abstract class EntityManager {

    private float x, y;
    protected GameObject id;

    public EntityManager(float x, float y, GameObject id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick(LinkedList<EntityManager> entity);

    public abstract void render(Graphics g);

    //public abstract Rectangle getBounds();


}