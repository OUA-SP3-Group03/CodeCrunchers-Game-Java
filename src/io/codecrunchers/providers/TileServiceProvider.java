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
    private int roomStartingY;

    @Override
    public void boot(App app) {
        this.app=app;
        this.roomWidth=0;
        this.roomHeight=0;
        this.roomStartingY = this.app.config().interfaceHeight()-128;

        this.tiles = new ArrayList<>();
        this.tiles.add(new AirTile(this.app.texture().allImages()[20]));
        this.tiles.add(new RoofTile(this.app.texture().allImages()[25]));
        this.tiles.add(new ScaffoldingTile(this.app.texture().allImages()[24]));

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
                    this.tiles.get(this.roomTiles[currentTile]).render(g, (int) ((64*x)+this.app.getCamera().getxOffset()), 720+(int) ((64*y)+this.app.getCamera().getyOffset()));
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

    public Tile getTile(int x, int y){
        System.out.println("x/64: "+x/64+" y/64: "+y/64);
        return null;
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
