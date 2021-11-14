package io.codecrunchers.providers;

import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;
import io.codecrunchers.service.LevelGeneratorService;

import java.awt.*;

public class LevelServiceProvider extends Provider {

    private App app;
    private LevelGeneratorService levelGeneratorService;
    @Override
    public void boot(App app) {
        this.app=app;

        this.levelGeneratorService = new LevelGeneratorService(this, app);

        this.booted = true;
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

    public void generateWorld(){
        this.levelGeneratorService.generateWorld();
    }

    public int[] getWorld(){
        return this.levelGeneratorService.getWorld();
    }

    public int getWorldWidth(){
        return  this.levelGeneratorService.getWorldWidth();
    }

    public int getWorldHeight(){
        return this.levelGeneratorService.getWorldHeight();
    }

    public String world0path(){return this.app.config().world0path(); }

    public int getMaxRooms(){ return this.app.config().maxRooms(); }

}
