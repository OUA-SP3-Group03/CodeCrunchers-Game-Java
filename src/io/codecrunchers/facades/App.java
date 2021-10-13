package io.codecrunchers.facades;

import io.codecrunchers.core.Application;
import io.codecrunchers.core.Kernel;
import io.codecrunchers.providers.DisplayServiceProvider;
import io.codecrunchers.providers.LoopServiceProvider;

import java.awt.*;

public class App {

    private final Kernel kernel;
    private final Config config;

    public App(Kernel kernel){
        this.kernel = kernel;
        this.config = new Config(this.kernel);
    }

    //**** GET INSTANCE OF THE APPLICATION ****\\
    public Application self(){
        return this.kernel.getApplication();
    }

    //**** GET CONFIG ****\\
    public Config config(){
        return this.config;
    }

    public boolean booted(){
        return this.kernel.getBootedStatus();
    }

    //**** GET INSTANCE OF THE CANVAS ****\\
    public Canvas canvas(){
        return ((DisplayServiceProvider) this.kernel.getServiceProvider("display")).getCanvas();
    }

    //**** RENDER PROVIDERS FACADE ****\\
    public void render(Graphics g){
       this.kernel.render(g);
    }

    //**** TICK PROVIDERS FACADE ****\\
    public void tick(){
        this.kernel.tick();
    }

    //**** START LOOP CALL ****\\
    public void startLoop(){
        ((LoopServiceProvider)this.kernel.getServiceProvider("loop")).startLoop();
    }
}