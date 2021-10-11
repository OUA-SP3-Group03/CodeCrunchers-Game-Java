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
        DisplayServiceProvider display = (DisplayServiceProvider) this.kernel.getServiceProvider("display");
        return display.getCanvas();
    }

    //**** RENDER PROVIDERS FACADE ****\\
    public void render(Graphics g){
       this.kernel.render(g);
    }

    //*** TICK PROVIDERS FACADE ****\\
    public void tick(){
        this.kernel.tick();
    }


}