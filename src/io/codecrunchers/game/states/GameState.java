package io.codecrunchers.game.states;

import io.codecrunchers.core.ASCII;
import io.codecrunchers.game.entities.Entity;
import io.codecrunchers.game.entities.creatures.Player;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Random;

public class GameState extends State {

    private App app;
    private Entity player;
    private String backgroundTrack;
    private float timer;
    private DecimalFormat df;
    private String[] music = {"bgm1", "bgm2", "bgm3", "bgm4"};

    @Override
    public void boot(App app) {
        this.app = app;
        this.df = new DecimalFormat("#.#");

    }

    public void tick() {

        //Toggle the Debug screen
        if(this.app.keyPressed().containsKey(ASCII.f3)){
            this.app.setShowDebug(!this.app.showDebug());
            this.app.keyPressed().remove(ASCII.f3);
        }

        //toggle pause menu
        if(this.app.keyPressed().containsKey(ASCII.escape)){
            this.app.setCurrentState("pause");
            this.app.keyPressed().remove(ASCII.escape);
        }

        //center camera on player
        this.app.getCamera().centerOnEntity(player);

        //check for player death
        if(!this.player.isAlive()){
            this.endGame();
        }

        //increase our timer
        this.timer ++;

    }

    public void render(Graphics g) {
        if(!this.app.showDebug()) {
            g.setFont(new Font("Copperplate", Font.BOLD, 40));
            g.setColor(Color.red);
            g.drawString("Time: " + this.df.format(this.timer / 60), 100, 100);
        }else{
            g.setColor(Color.white);
            g.drawString("Code Crunchers Alpha 0.1",64,64);
            g.drawString(this.app.debug().providerCount()+ " Service Providers Loaded",64,80);
            g.drawString(this.app.debug().serviceCount()+ " Services Loaded",64,96);
            g.drawString(this.app.debug().entityCount()+ " Entity Ticking & Rendering",64,110);
            g.drawString("Current FPS: "+this.app.debug().currentFPS()+" | Target: "+this.app.config().targetFPS(),64,126);
            g.drawString("Current TPS: "+this.app.debug().currentTPS()+" | Target: "+this.app.config().targetTPS(),64,142);
            g.drawString(this.app.debug().usedMemory()+"MB of Memory Used",64,158);
            g.drawString("Level Play Time: " + this.df.format(this.timer / 60),64,172);
        }
    }

    public void startGame(){

        this.player = new Player(64, 0, this.app);
        this.app.registerEntity(player);

        //FIX THIS - MUSIC NOT WORKING
        for (String track: this.music) {
            this.app.resetAudioClip(track);
        }

        this.app.resetAudioClip("hurt");
        this.player.setAlive(true);
        this.player.setX(64);
        this.player.setY(0);
        this.app.getCamera().reset();

        this.app.setCurrentState("game");

        this.app.level().generate();
        this.app.setWorldWidth(this.app.level().width());
        this.app.setWorldHeight(this.app.level().height());
        this.app.setWorld(this.app.level().tiles());

        this.backgroundTrack = getRandomMusic();

        //Start BGM
        this.app.playAudioClipLooped(this.backgroundTrack);
    }

    public void endGame(){
        this.app.resetAudioClip(this.backgroundTrack);
        this.app.stopAudioClip(this.backgroundTrack);
        this.timer = 0;
        this.app.resetEntitiesInGame();
        this.app.setCurrentState("menu");
    }


    //Return a random background music track
    public String getRandomMusic() {
        Random rng = new Random();
        int selection;
        selection = rng.nextInt(music.length);
        return music[selection];
    }


}
