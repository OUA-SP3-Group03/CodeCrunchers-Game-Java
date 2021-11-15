package io.codecrunchers.providers;

import io.codecrunchers.game.entities.statics.Spawner;
import io.codecrunchers.game.tiles.*;
import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileServiceProvider extends Provider {

    private ArrayList<Tile> tiles;
    private int roomWidth;
    private int roomHeight;
    private App app;
    private int [] roomTiles;
    private SpawnerTile [] spawnerTiles;

    private final int [] menuTiles = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                                      1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1,
                                      1, 3, 3, 3, 3, 9, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1,
                                      1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1,
                                      2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2,
                                      2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 9, 3, 3, 3, 3, 3, 3, 3, 3, 2,
                                      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                                      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    @Override
    public void boot(App app) {
        this.app=app;
        this.roomWidth=0;
        this.roomHeight=0;
        this.tiles = new ArrayList<Tile>();
        //Tile 0: Air Tile
        this.tiles.add(new AirTile(this.app.texture().allImages()[15]));
        //Tile 1: Blank Brick Tile -> using dynamic texture
        this.tiles.add(new BrickTile(this.app.texture().allImages()[16], this));
        //Tile 2: Blank Interior Wall Tile
        this.tiles.add(new InteriorTile(this.app.texture().allImages()[18]));
        //Tile 3: Interior Wall Door
        this.tiles.add(new InteriorTile(this.app.texture().allImages()[19]));
        //Tile 4: Free Tile
        this.tiles.add(null);
        //Tile 5: Free Tile
        this.tiles.add(null);
        //Tile 6: Free Tile
        this.tiles.add(null);
        //Tile 7: Free Tile
        this.tiles.add(null);
        //Tile 8: Free Tile
        this.tiles.add(null);
        //Tile 9: Spawner Tile
        this.tiles.add(new SpawnerTile(this.app.texture().allImages()[15],app));
        }


    @Override
    public boolean performTick() {
        return false;
    }

    @Override
    public boolean performRender() {
        return true;
    }

    private void analyse(){
        int x = 0;
        int y = 0;
        int currentTile=0;
        while (y < this.roomHeight) {
            while (x < this.roomWidth) {
                if(this.tiles.get(this.roomTiles[currentTile]).getClass().getSimpleName().matches("SpawnerTile"))
                {
                    if(!this.app.checkEntityAtLocation(x*64,y*64)) {
                        this.app.registerEntity(new Spawner(x * 64, y * 64, this.app));
                    }
                }
                currentTile++;
                x++;

            }
            x = 0;
            y++;
        }
    }

    @Override
    public void render(Graphics g) {
        if(this.app.currentState().matches("game")&& this.roomTiles!=null) {
            this.analyse();
            int x = 0;
            int y = 0;
            int currentTile=0;
            while (y < this.roomHeight) {
                while (x < this.roomWidth) {
                        this.tiles.get(this.roomTiles[currentTile]).render(g, (int) ((64 * x) - this.app.getCamera().getxOffset()), (int) ((64 * y) - this.app.getCamera().getyOffset()),x,y);

                    currentTile++;
                    x++;

                }
                x = 0;
                y++;
            }
        }
        else {
            this.app.setWorldWidth(20);
            this.app.setWorldHeight(11);
            this.app.setWorld(menuTiles);
            int x = 0;
            int y = 0;
            int currentTile=0;
            while (y < this.roomHeight) {
                while (x < this.roomWidth) {
                    this.tiles.get(this.menuTiles[currentTile]).render(g, (int) ((64 * x) - this.app.getCamera().getxOffset()), (int) ((64 * y) - this.app.getCamera().getyOffset()),x,y);
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

    public BufferedImage[] getTexture(){
        return this.app.texture().allImages();
    }
}
