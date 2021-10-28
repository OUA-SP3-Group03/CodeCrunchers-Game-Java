package io.codecrunchers.classes.states;

import io.codecrunchers.classes.ASCII;
import io.codecrunchers.classes.entities.Entity;
import io.codecrunchers.classes.entities.creatures.Player;
import io.codecrunchers.core.Camera;
import io.codecrunchers.facades.App;

import java.awt.*;

public class GameState extends State {

    private App app;
    private Entity player;

    @Override
    public void boot(App app) {
        this.app = app;
        int[] World;
        String worldRaw="00000000000000000022220000000022000000002000000020000000220000001111111111111111";
        int worldWidth=16;
        int worldHeight=5;
        World=new int[worldRaw.length()];
        for(int i=0;i<worldRaw.length();i++){
            World[i]=worldRaw.charAt(i)-'0';
        }
        this.app.setWorldWidth(worldWidth);
        this.app.setWorldHeight(worldHeight);
        this.app.setWorld(World);

        //create the camera

        this.player = new Player(0,0,64,64, this.app);

        this.app.registerEntity(player);

    }

    public void tick() {
        if(this.app.keyPressed().containsKey(ASCII.escape)){
            this.app.keyPressed().remove(ASCII.escape);
            this.app.setCurrentState("pause");
        }

        this.app.getCamera().centerOnEntity(this.player);

    }
    public void render(Graphics g) {

    }
}
