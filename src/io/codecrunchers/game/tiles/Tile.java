package io.codecrunchers.game.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile {
    protected BufferedImage texture;
    protected int width = 32;
    protected int height = 32;
public Tile(BufferedImage texture){
    this.texture=texture;
}
    public abstract void render(Graphics g, int x, int y);

   public abstract boolean solid();
}

