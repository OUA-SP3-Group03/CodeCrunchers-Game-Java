package io.codecrunchers.game.tiles;

import io.codecrunchers.facades.App;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpawnerTile extends Tile{

    private App app;

    public SpawnerTile(BufferedImage texture, App app) {
        super(texture);
        this.app = app;
    }

    @Override
    public void render(Graphics g, int x, int y, int tileX, int tileY) {
        if(this.app.getTileAtLocation(tileX, tileY-1).getClass().getSimpleName().matches("InteriorTile")){
            g.drawImage(this.app.texture().allImages()[19],x,y,null);
        }else {
            g.drawImage(this.texture, x, y, null);
        }
    }

    @Override
    public boolean solid() {
        return false;
    }
}
