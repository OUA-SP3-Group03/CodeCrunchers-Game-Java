package io.codecrunchers.providers;

import io.codecrunchers.core.Application;
import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;
import io.codecrunchers.service.LoopService;

import java.awt.*;

public class LoopServiceProvider extends Provider {

    private boolean running;
    private App app;
    private LoopService loopService;


    @Override
    public void boot(App app) {
        this.app = app;
        this.running = false;
        this.loopService = new LoopService(this);
        this.loopService.setShowFPS(app.config().showFPS());
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

    public boolean getRunningStatus(){
        return this.running;
    }

    public void setRunningStatus(boolean status){
        this.running = status;
    }

    public Application getApplication(){
        return this.app.self();
    }

    public void startLoop(){
        this.loopService.start();
    }

}
