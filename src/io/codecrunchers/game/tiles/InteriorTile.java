package io.codecrunchers.game.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InteriorTile extends Tile{
    public InteriorTile(BufferedImage texture) {
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
