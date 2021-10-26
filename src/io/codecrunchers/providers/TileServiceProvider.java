package io.codecrunchers.providers;

import io.codecrunchers.classes.tiles.AirTile;
import io.codecrunchers.classes.tiles.RoofTile;
import io.codecrunchers.classes.tiles.ScaffoldingTile;
import io.codecrunchers.classes.tiles.Tile;
import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.util.ArrayList;

public class TileServiceProvider extends Provider {

    private ArrayList<Tile> tiles;
    private int roomWidth;
    private int roomHeight;
    private App app;
    private int [] roomTiles;

    @Override
    public void boot(App app) {
        this.app=app;
        this.roomWidth=0;
        this.roomHeight=0;
        this.tiles = new ArrayList<Tile>();
        this.tiles.add(new AirTile(this.app.texture().allImages()[20]));
        this.tiles.add(new RoofTile(this.app.texture().allImages()[1]));
        this.tiles.add(new ScaffoldingTile(this.app.texture().allImages()[4]));

}


    @Override
    public boolean performTick() {
        return false;
    }

    @Override
    public boolean performRender() {
        return true;
    }

    @Override
    public void render(Graphics g) {
        if(this.app.currentState().matches("game")&& this.roomTiles!=null) {
            int x = 0;
            int y = 0;
            int currentTile=0;
            while (y < this.roomHeight) {
                while (x < this.roomWidth) {
                    this.tiles.get(this.roomTiles[currentTile]).render(g,64*x,64*y);
                    currentTile++;
                    x++;

                }
                x = 0;
                y++;
            }
        }
    }

    @Override
    public void tick() {

    }
    public void setWorldWidth(int width){
        this.roomWidth=width;
    }
    public void setWorldHeight(int height){
        this.roomHeight=height;
    }
    public void setWorld(int[] tiles){
        this.roomTiles=tiles;
    }
}
