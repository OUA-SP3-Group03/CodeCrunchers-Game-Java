package io.codecrunchers.engine.facades;

import io.codecrunchers.engine.entity.Entity;
import io.codecrunchers.engine.gfx.InterfaceObject;
import io.codecrunchers.engine.core.State;
import io.codecrunchers.Application;
import io.codecrunchers.engine.gfx.Camera;
import io.codecrunchers.engine.core.Kernel;

import io.codecrunchers.engine.core.Provider;
import io.codecrunchers.engine.providers.*;

import java.awt.*;
import java.util.HashMap;

public class App {
    private final Texture texture;
    private final Kernel kernel;
    private final Config config;

    public App(Kernel kernel){
        this.kernel = kernel;
        this.config = new Config(this.kernel);
        this.texture = new Texture(this.kernel);
    }

    public Texture texture (){
        return this.texture;
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

    public Provider getProvider(String name){
        return this.kernel.getServiceProvider(name);
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

    //**** LOGIN HTTP CALL ****\\
    public String authLogin(String email, String password){
        return ((HttpServiceProvider) this.kernel.getServiceProvider("http")).login(email, password);
    }

    //**** CHECK LOGIN HTTP CALL ****\\
    public String authCheck(String token){
        return ((HttpServiceProvider) this.kernel.getServiceProvider("http")).check(token);
    }

    //**** LOGOUT HTTP CALL ****\\
    public String authLogout(String token){
        return ((HttpServiceProvider) this.kernel.getServiceProvider("http")).logout(token);
    }

    //**** ADD GUI OBJECT ****\\
    public void addInterfaceObject(InterfaceObject newObject){
        ((InterfaceServiceProvider) this.kernel.getServiceProvider("interface")).addInterfaceObject(newObject);
    }

    //**** INPUTS ****\\
    public HashMap<Integer, Boolean> keyPressed() {
        return ((KeyboardServiceProvider) this.kernel.getServiceProvider("keyboard")).keyCodes;
    }

    public Boolean mousePressed(){
        return ((MouseServiceProvider) this.kernel.getServiceProvider("mouse")).isMousePressed();
    }

    public int mouseX() {
        return ((MouseServiceProvider) this.kernel.getServiceProvider("mouse")).getMouseX();
    }

    public int mouseY() {
        return ((MouseServiceProvider) this.kernel.getServiceProvider("mouse")).getMouseY();
    }

    public void setSelectedInterfaceObject(InterfaceObject target){
        ((InterfaceServiceProvider)this.kernel.getServiceProvider("interface")).setSelectedObject(target);
    }

    public InterfaceObject selectedInterfaceObject(){
        return   ((InterfaceServiceProvider)this.kernel.getServiceProvider("interface")).getSelectedObject();
    }

    public boolean isCapsLocked(){
        return ((KeyboardServiceProvider) this.kernel.getServiceProvider("keyboard")).isCapsLocked();
    }

    public void registerState(String key, State state){
        ((StatesServiceProvider)this.kernel.getServiceProvider("states")).registerState(key,state);
    }

    public void setCurrentState(String key){
            ((StatesServiceProvider)this.kernel.getServiceProvider("states")).setCurrentState(key);
    }

    public String currentState(){
        return ((StatesServiceProvider)this.kernel.getServiceProvider("states")).getCurrentState();
    }

    public void initializeStates(){
        ((StatesServiceProvider)this.kernel.getServiceProvider("states")).initialize();
    }

    public void setWorldWidth(int width){
        ((TileServiceProvider)this.kernel.getServiceProvider("tile")).setWorldWidth(width);
    }
    public void setWorldHeight(int height){
        ((TileServiceProvider)this.kernel.getServiceProvider("tile")).setWorldHeight(height);
    }
    public void setWorld(int[] world){
        ((TileServiceProvider)this.kernel.getServiceProvider("tile")).setWorld(world);
    }

    public void registerEntity(Entity e){
        ((EntityServiceProvider)this.kernel.getServiceProvider("entity")).registerEntity(e);
    }

    public Camera getCamera() {
        return this.kernel.getCamera();
    }

}