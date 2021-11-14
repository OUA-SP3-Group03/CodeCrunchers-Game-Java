package io.codecrunchers.game.tiles;

import io.codecrunchers.providers.TileServiceProvider;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BrickTile extends Tile{
    private final TileServiceProvider tileServiceProvider;

    public BrickTile(BufferedImage texture, TileServiceProvider tileServiceProvider) {
        super(texture);
        this.tileServiceProvider = tileServiceProvider;
    }

    @Override
    public void render(Graphics g, int x, int y, int tileX, int tileY) {
        if(this.tileServiceProvider.getTileAtLocation( (tileX),  (tileY-1)).getClass().getSimpleName().matches("AirTile")){
            g.drawImage(this.tileServiceProvider.getTexture()[25], x,y,null);
        }else if(this.tileServiceProvider.getTileAtLocation( (tileX),  (tileY-1)).getClass().getSimpleName().matches("InteriorTile")){
            g.drawImage(this.tileServiceProvider.getTexture()[25], x,y,null);
        }else if(this.tileServiceProvider.getTileAtLocation( (tileX),  (tileY-1)).getClass().getSimpleName().matches("SpawnerTile")){
        g.drawImage(this.tileServiceProvider.getTexture()[25], x,y,null);
        }else {
            g.drawImage(this.texture, x, y, null);
        }
    }

    @Override
    public boolean solid() {
        return true;
    }
}
