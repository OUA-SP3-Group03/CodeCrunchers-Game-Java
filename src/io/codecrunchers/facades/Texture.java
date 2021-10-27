package io.codecrunchers.facades;

import io.codecrunchers.core.Kernel;
import io.codecrunchers.providers.AssetServiceProvider;

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
