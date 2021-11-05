package io.codecrunchers.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScaffoldingTile extends Tile{
    public ScaffoldingTile(BufferedImage texture) {
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
