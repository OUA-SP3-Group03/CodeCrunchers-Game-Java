package io.codecrunchers.engine.facades;

import io.codecrunchers.engine.core.Kernel;
import io.codecrunchers.engine.providers.AssetServiceProvider;

import java.awt.image.BufferedImage;



public class Texture {
    private Kernel kernel;
    public Texture(Kernel kernel){
    this.kernel = kernel;
    }

    //
    public BufferedImage[] allImages(){

        return ((AssetServiceProvider)this.kernel.getServiceProvider("asset")).getImages();
    }

    public BufferedImage logo(){
        return ((AssetServiceProvider)this.kernel.getServiceProvider("asset")).getLogo();
    }

    public BufferedImage animation(String key){
        return ((AssetServiceProvider)this.kernel.getServiceProvider("asset")).animation(key);
    }
}
