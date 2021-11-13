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

        this.clips.put("bgm1", new AudioService("res/Music1.wav",-25f));
        this.clips.put("bgm2", new AudioService("res/Music2.wav",-25f));
        this.clips.put("bgm3", new AudioService("res/Music3.wav",-25f));
        this.clips.put("bgm4", new AudioService("res/Music4.wav",-25f));

        this.clips.put("jump", new AudioService("res/Jump.wav",-10f));
        this.clips.put("attack", new AudioService("res/Attack.wav",-10f));
        this.clips.put("footstep", new AudioService("res/Footstep.wav",-10f));
        this.clips.put("hurt", new AudioService("res/Hurt.wav",-10f));


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
