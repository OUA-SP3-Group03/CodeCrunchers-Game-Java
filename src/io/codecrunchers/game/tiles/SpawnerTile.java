package io.codecrunchers.game.tiles;

import io.codecrunchers.facades.App;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpawnerTile extends Tile{

    public SpawnerTile(BufferedImage texture) {
        super(texture);
    }

    @Override
    public void render(Graphics g, int x, int y, int tileX, int tileY) {
        g.drawImage(this.texture,x,y,null);
    }

    @Override
    public boolean solid() {
        return false;
    }
}
