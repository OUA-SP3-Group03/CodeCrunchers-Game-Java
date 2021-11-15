package io.codecrunchers.providers;

import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;
import io.codecrunchers.service.AudioService;

import java.awt.*;
import java.util.HashMap;

public class AudioServiceProvider extends Provider {

    HashMap<String, AudioService> clips;

    @Override
    public void boot(App app) {

        this.clips = new HashMap<>();

        //Background Music
        this.clips.put("bgm1", new AudioService("res/Audio/Music1.wav",-25f,app));
        this.clips.put("bgm2", new AudioService("res/Audio/Music2.wav",-25f,app));
        this.clips.put("bgm3", new AudioService("res/Audio/Music3.wav",-25f,app));
        this.clips.put("bgm4", new AudioService("res/Audio/Music4.wav",-25f,app));

        //Player Game sounds
        this.clips.put("jump", new AudioService("res/Audio/Jump.wav",-10f,app));
        this.clips.put("attack", new AudioService("res/Audio/Attack.wav",-10f,app));
        this.clips.put("footstep", new AudioService("res/Audio/Footstep.wav",-10f,app));
        this.clips.put("hurt", new AudioService("res/Audio/Hurt.wav",-10f,app));
        this.clips.put("powerup", new AudioService("res/Audio/SynthChime4.wav",-10f,app));

        //GUI sounds
        this.clips.put("ui-fail", new AudioService("res/Audio/UI_Quirky29.wav",-10f,app));
        this.clips.put("ui-click", new AudioService("res/Audio/UI_Quirky21.wav",-10f,app));


    }

    @Override
    public boolean performTick() {
        return false;
    }

    @Override
    public boolean performRender() {
        return false;
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void tick() {

    }

    public void play(String clip){
        this.clips.get(clip).play();
    }

    public void playLoop(String clip){
        this.clips.get(clip).loop();
    }

    public void reset(String clip){
        this.clips.get(clip).reset();
    }

    public void stop(String clip){
        this.clips.get(clip).stop();
    }
}
