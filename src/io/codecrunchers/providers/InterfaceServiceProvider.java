package io.codecrunchers.providers;

import io.codecrunchers.classes.gui.InterfaceObject;
import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.util.ArrayList;

public class InterfaceServiceProvider extends Provider {

    private ArrayList <InterfaceObject> interfaceObjects;
    private App app;

    @Override
    public void boot(App app) {
        this.app = app;
        this.interfaceObjects = new ArrayList<InterfaceObject>();
    }

    @Override
    public boolean performTick() {
        return true;
    }

    @Override
    public boolean performRender() {
        return true;
    }

    @Override
    public void render(Graphics g) {
        for(InterfaceObject object : this.interfaceObjects){
            if(this.app.state() != object.getAssignedState()){
                object.render(g);
            }
        }
    }

    @Override
    public void tick() {
        for(InterfaceObject object : this.interfaceObjects){
            if(this.app.state() != object.getAssignedState()){
                object.tick();
            }
        }
    }

    public void addInterfaceObject(InterfaceObject interfaceObject){
        this.interfaceObjects.add(interfaceObject);
    }


}
