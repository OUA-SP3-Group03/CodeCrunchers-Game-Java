package io.codecrunchers.providers;


import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;
import io.codecrunchers.service.AnimationService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class AssetServiceProvider extends Provider {

    //**** BOOT METHOD ****\\
    private BufferedImage textureMap;
    private BufferedImage[] images;
    private BufferedImage logo;

    private HashMap<String, AnimationService> animations;

    @Override
    //this method is called by the Kernel when the program loads for the first time, you can think of this like your constructor class
    //place any code that you need to run at the start of the program once in here
    public void boot(App app) {
        //Code Block below is responsible for loading sprite sheet into application
        this.logo = imageLoader(app.config().logoTexturePath());

        this.textureMap = imageLoader(app.config().texturePath());

        this.animations = new HashMap<String, AnimationService>();

        assert textureMap != null;
        int textureMapWidth = textureMap.getWidth();
        int textureMapHeight = textureMap.getHeight();

        int i = 0;
        int rows = 0;
        while (i < textureMapWidth){
            rows ++;
            i += app.config().textureWidth();
        }
        i = 0;
        int columns = 0;
        while(i < textureMapHeight){
            columns ++;
            i += app.config().textureHeight();
        }

        this.images = new BufferedImage[rows * columns];

        //Code block below is responsible for auto-cropping loaded sprite sheet.
        int x = 0;
        int y = 0;
        int currentImage = 0;

        //While loop create sub-images from texture map depending on their position.
        while (y < columns){
            while (x < rows){
                this.images[currentImage] = this.resizeImg(textureMap.getSubimage(x*app.config().textureWidth(),y*app.config().textureHeight(),app.config().textureWidth(),app.config().textureHeight()));
                currentImage++;
                x++;
            }
            x=0;
            y++;
        }

        this.animations.put("playerIdol", new AnimationService(Arrays.copyOfRange(images,40,45), 256, app));

        this.booted=true;
    }

    //**** PERFORM TICK METHOD ****\\
    @Override
    public boolean performTick() {
        //this method tells the kernel if this service provider needs to be ticked or not, return true for yes or false for no
        return true;
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

        for (AnimationService animation : this.animations.values()) {
            animation.tick();
        }

    }

    //**** Image loader method ****\\
    private BufferedImage imageLoader(String path ){
        try{
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage resizeImg(BufferedImage originalImage)
    {
        int scaledWidth = originalImage.getWidth()*2;
        int scaledHeight = originalImage.getHeight()*2;

        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());
        Graphics2D g = scaledImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, 0, 0, originalWidth, originalHeight, null);
        g.dispose();
        return scaledImage;
    }

    //**** Image getter method ****\\
    public BufferedImage[] getImages(){
        return this.images;
    }

    public BufferedImage getLogo(){
        return this.logo;
    }

    public BufferedImage animation(String key) {
        return this.animations.get(key).getCurrentImage();
    }
}
