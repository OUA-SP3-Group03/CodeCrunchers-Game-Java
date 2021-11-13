package io.codecrunchers.game.states;

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

    @Override
    public void boot(App app) {
        this.app = app;
        this.df = new DecimalFormat("#.#");
        player = new Player(64, 0, 64, 64, this.app);
        this.app.registerEntity(player);

    }

    public void tick() {
        this.app.getCamera().centerOnEntity(player);

        if(!this.player.isAlive()){
            this.endGame();
        }

        this.timer ++;

    }

    public void render(Graphics g) {
        g.setFont(new Font("Copperplate",Font.BOLD,40));
        g.setColor(Color.red);
        g.drawString("Time: "+this.df.format(this.timer/60),100,100);
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

        //Start BGM
        this.app.playAudioClipLooped(this.backgroundTrack);
    }

    public void endGame(){
        this.app.stopAudioClip(this.backgroundTrack);
        this.timer = 0;
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
