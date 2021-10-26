package io.codecrunchers.classes.states;

import io.codecrunchers.classes.ASCII;
import io.codecrunchers.facades.App;

import java.awt.*;

public class GameState extends State {

    private App app;

    @Override
    public void boot(App app) {
        this.app = app;
        int[] World;
        String worldRaw="0000002220001111";
        int worldWidth=4;
        int worldHeight=4;
        World=new int[worldRaw.length()];
        for(int i=0;i<worldRaw.length();i++){
            World[i]=worldRaw.charAt(i)-'0';

        }
        this.app.setWorldWidth(worldWidth);
        this.app.setWorldHeight(worldHeight);
        this.app.setWorld(World);
    }

    public void tick() {
        if(this.app.keyPressed().containsKey(ASCII.escape)){
            this.app.keyPressed().remove(ASCII.escape);
            this.app.setCurrentState("pause");
        }
    }
    public void render(Graphics g) {

    }
}
