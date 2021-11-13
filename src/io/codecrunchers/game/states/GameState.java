package io.codecrunchers.game.states;

import io.codecrunchers.core.ASCII;
import io.codecrunchers.game.entities.Entity;
import io.codecrunchers.game.entities.creatures.Player;
import io.codecrunchers.facades.App;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class GameState extends State {

    private App app;
    private Entity player;
    private boolean gameStarted;
    private String backgroundTrack;

    @Override
    public void boot(App app) {
        this.app = app;
        this.gameStarted = false;

        player = new Player(64, 0, 64, 64, this.app);
        this.app.registerEntity(player);

    }

    public void tick() {
        this.app.getCamera().centerOnEntity(player);

        if(!this.player.isAlive()){
            this.endGame();
        }
    }

    public void render(Graphics g) {
    }

    public void startGame(){
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
        this.gameStarted = true;

        //Start BGM
        this.app.playAudioClipLooped(this.backgroundTrack);
    }

    public void endGame(){
        this.gameStarted = false;
        this.app.stopAudioClip(this.backgroundTrack);
        this.app.setCurrentState("login");
    }


    //Return a random background music track
    public String getRandomMusic() {
        String[] music = {"bgm1", "bgm2", "bgm3", "bgm4"};
        Random rng = new Random();
        int selection;

        selection = rng.nextInt(music.length);

        return music[selection];
    }


}
