package io.codecrunchers.providers;

import io.codecrunchers.game.gui.InterfaceButton;
import io.codecrunchers.game.gui.InterfaceObject;
import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.util.ArrayList;

public class InterfaceServiceProvider extends Provider {

    private ArrayList <InterfaceObject> interfaceObjects;
    private App app;
    private InterfaceObject selectedObject = new InterfaceButton();

    @Override
    public void boot(App app) {
        this.app = app;
        this.interfaceObjects = new ArrayList<>();
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
            if(object.getAssignedState().matches(this.app.currentState())) {
                if (object.getHovering() && object.getShowOnHover()) {
                    object.render(g);
                    object.drawOnHover(g);
                } else {
                    object.render(g);
                }
            }
        }
    }

    @Override
    public void tick() {
        for(InterfaceObject object : this.interfaceObjects){
            if(object.getAssignedState().matches(this.app.currentState())) {

                object.checkHover();
                object.tick();
            }
        }
    }

    public void addInterfaceObject(InterfaceObject interfaceObject){
        this.interfaceObjects.add(interfaceObject);
    }

    public void setSelectedObject(InterfaceObject target){
        this.selectedObject = target;
    }

    public InterfaceObject getSelectedObject(){
        return this.selectedObject;
    }

}
