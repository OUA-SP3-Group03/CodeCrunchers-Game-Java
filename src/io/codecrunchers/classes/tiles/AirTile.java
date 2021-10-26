package io.codecrunchers.classes.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AirTile extends Tile {
    public AirTile(BufferedImage texture) {
        super(texture);
    }

    @Override
    public void render(Graphics g, int x, int y) {

    }

    @Override
    public boolean solid() {
        return false;
    }
}
