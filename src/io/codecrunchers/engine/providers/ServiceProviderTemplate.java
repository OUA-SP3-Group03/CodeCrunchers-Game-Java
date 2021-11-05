package io.codecrunchers.engine.providers;


import io.codecrunchers.engine.core.Provider;
import io.codecrunchers.engine.facades.App;

import java.awt.*;

public class ServiceProviderTemplate extends Provider {

    //**** SERVICE PROVIDER TEMPLATE ****\\
    //Please use this file as a reference when adding your own service providers to the program
    //in addition to the methods provided here, please feel free to create your own accessor methods that hook into the app facade.
    //when a program or service provider needs access to methods in here go via the app facade and keep it simple!

    //______ REMEMBER TO REGISTER PROVIDER WITH KERNEL ______\\
    // refer to notes in kernel.class for instructions

    //**** BOOT METHOD ****\\
    @Override
    public void boot(App app) {
        //this method is called by the Kernel when the program loads for the first time, you can think of this like your constructor class
        //place any code that you need to run at the start of the program once in here
    }

    //**** PERFORM TICK METHOD ****\\
    @Override
    public boolean performTick() {
        //this method tells the kernel if this service provider needs to be ticked or not, return true for yes or false for no
        return false;
    }

    //**** PERFORM RENDER METHOD ****\\
    @Override
    public boolean performRender() {
        //this method tells the kernel if this service provider needs to render code in its render function, return true for yes or no for false.
        return false;
    }

    //**** RENDER METHOD ****\\
    @Override
    public void render(Graphics g) {
        //place all your canvas rendering code in this method, this is called by the kernel at a time specified by the program max frames per second
        //keep this code as lean as possible to keep performance high
    }

    //**** TICK METHOD ****\\
    @Override
    public void tick() {
        //place all your tick or update code in this method, this is called by the kernel at a time specified by the max ticks per second
        //keep this code as lean as possible to keep performance high
    }

    //**** YOUR METHOD ****\\
    private void newMethod(){
        //add as many private and public methods as you need here
    }

}
