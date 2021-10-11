package io.codecrunchers.facades;

import io.codecrunchers.core.Application;
import io.codecrunchers.core.Kernel;
import io.codecrunchers.providers.DisplayServiceProvider;

import java.awt.*;

public class App {

    private final Kernel kernel;

    public App(Kernel kernel){
        this.kernel = kernel;
    }

    //**** GET INSTANCE OF THE APPLICATION ****\\
    public Application self(){
        return this.kernel.getApplication();
    }

    //**** GET INSTANCE OF THE CANVAS ****\\
    public Canvas canvas(){
        return ((DisplayServiceProvider) this.kernel.getServiceProvider("display")).getCanvas();
    }

    //**** RENDER PROVIDERS FACADE ****\\
    public void render(Graphics g){
       this.kernel.render(g);
    }

    //*** TICK PROVIDERS FACADE ****\\
    public void tick(){
        this.kernel.tick();
    }

    //*** GET INTERFACE WIDTH ****\\
    public int interfaceWidth(){
        return this.kernel.getConfig().interfaceWidth;
    }

    //*** GET INTERFACE HEIGHT ****\\
    public int interfaceHeight(){
        return this.kernel.getConfig().interfaceHeight;
    }

    //*** GET INTERFACE TITLE ****\\
    public String interfaceTitle(){
        return this.kernel.getConfig().title;
    }

    //**** GET TARGET FPS & TPS ****\\
    public int getTargetFPS(){
        return this.kernel.getConfig().targetFPS;
    }





}