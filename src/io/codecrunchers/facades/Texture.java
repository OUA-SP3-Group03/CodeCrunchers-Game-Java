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
    //Demo method for displaying a "user icon" from test sprite sheet
    public BufferedImage userIcon(){
        return ((AssetServiceProvider)this.kernel.getServiceProvider("asset")).getImages()[6];
    }

}
