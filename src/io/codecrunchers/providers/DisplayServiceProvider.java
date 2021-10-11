package io.codecrunchers.providers;

import io.codecrunchers.core.Provider;
import io.codecrunchers.service.DisplayService;

import java.awt.*;

public class DisplayServiceProvider extends Provider {

    private Canvas displayCanvas;

    @Override
    public void boot() {
       //create a new instance of the display service
       DisplayService display = new DisplayService("test", 400, 400);
       //set the canvas from the new display service
       this.displayCanvas = display.createDisplay();

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
