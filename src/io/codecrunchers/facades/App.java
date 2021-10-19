package io.codecrunchers.facades;

import io.codecrunchers.core.Application;
import io.codecrunchers.core.Kernel;
import io.codecrunchers.providers.AssetServiceProvider;
import io.codecrunchers.providers.DisplayServiceProvider;

import java.awt.*;
import java.awt.image.BufferedImage;

public class App {

    private final Kernel kernel;

    private Texture texture;

    public App(Kernel kernel){
        this.kernel = kernel;
    }

    public void textureCallback(Texture texture){
        this.texture = texture;
    }
    public Texture texture (){
        return this.texture;
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
    public int targetFPS(){
        return this.kernel.getConfig().targetFPS;
    }

    //
    public int textureWidth(){
        return this.kernel.getConfig().textureWidth;
    }
    //
    public int textureHeight(){
        return this.kernel.getConfig().textureHeight;
    }
    //
    public int textureMapWidth(){
        return this.kernel.getConfig().textureMapWidth;
    }
    //
    public int textureMapHeight(){
        return this.kernel.getConfig().textureMapHeight;
    }
    //
    public String texturePath(){
        return this.kernel.getConfig().texturePath;
    }

    public BufferedImage[] getImages(){
        AssetServiceProvider provider = ((AssetServiceProvider)this.kernel.getServiceProvider("assets"));
        if(provider!=null){
        return provider.getImages();
        }
        else{
            return null;
        }
    }

}