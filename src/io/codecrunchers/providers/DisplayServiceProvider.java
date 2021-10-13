package io.codecrunchers.providers;

import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;
import io.codecrunchers.service.DisplayService;

import java.awt.*;

public class DisplayServiceProvider extends Provider {

    private Canvas displayCanvas;
    private int targetFPS;

    @Override
    public void boot(App app) {
       //create a new instance of the display service
       DisplayService display = new DisplayService(app.config().interfaceTitle(), app.config().interfaceWidth(), app.config().interfaceHeight());
       //set the canvas from the new display service
       this.displayCanvas = display.createDisplay();
       //set the target FPS and TPS
        this.targetFPS = app.config().targetFPS();

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

    public Canvas getCanvas(){
        return this.displayCanvas;
    }
}
