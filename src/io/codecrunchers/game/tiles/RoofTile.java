package io.codecrunchers.game.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RoofTile extends Tile{
    public RoofTile(BufferedImage texture) {
        super(texture);
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(this.texture,x,y,null);

    }

    @Override
    public boolean solid() {
        return true;
    }
}
