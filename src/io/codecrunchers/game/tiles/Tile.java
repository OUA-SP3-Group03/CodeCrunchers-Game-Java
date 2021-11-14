package io.codecrunchers.game.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile {
    protected BufferedImage texture;
    public Tile(BufferedImage texture){
    this.texture=texture;
}

    public abstract void render(Graphics g, int x, int y, int tileX, int tileY);

   public abstract boolean solid();
}

