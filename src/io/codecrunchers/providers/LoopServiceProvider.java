package io.codecrunchers.providers;

import io.codecrunchers.core.Application;
import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;
import io.codecrunchers.service.LoopService;

import java.awt.*;

public class LoopServiceProvider extends Provider {

    private boolean running;
    private Application application;
    private LoopService loopService;

    @Override
    public void boot() {
        this.running = false;
        this.loopService = new LoopService(this);
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

    public void setApplicationCallback(Application application){
        this.application = application;
    }

    public Application getApplication(){
        return this.application;
    }

    public void startLoop(){
        this.loopService.start();
    }

}
