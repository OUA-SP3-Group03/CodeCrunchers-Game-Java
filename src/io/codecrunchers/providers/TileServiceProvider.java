package io.codecrunchers.providers;

import io.codecrunchers.game.tiles.AirTile;
import io.codecrunchers.game.tiles.RoofTile;
import io.codecrunchers.game.tiles.ScaffoldingTile;
import io.codecrunchers.game.tiles.Tile;
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
                        this.tiles.get(this.roomTiles[currentTile]).render(g, (int) ((64 * x) - this.app.getCamera().getxOffset()), (int) ((64 * y) - this.app.getCamera().getyOffset()));

                    currentTile++;
                    x++;

                }
                x = 0;
                y++;
            }
        }
    }

    public Tile getTileAtLocation(int targetX, int targetY){

        int currentTile=0;

        Tile selectedTile = this.tiles.get(0);



        int x = 0;
        int y = 0;
        while (y < this.roomHeight) {

                while (x < roomWidth) {
                    if(x == targetX && y == targetY) {
                        if(currentTile < this.roomTiles.length && this.roomTiles.length >1) {
                            selectedTile = this.tiles.get(this.roomTiles[currentTile]);
                        }
                        break;
                    }else{
                        currentTile++;
                    }

                    x++;
                }
                x = 0;
                y++;

        }


        //System.out.println("current tile ="+currentTile);


        return selectedTile;
    }

    public Tile getTile(int index){
        return this.tiles.get(index);
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
