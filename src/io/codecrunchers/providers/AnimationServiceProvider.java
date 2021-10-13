package io.codecrunchers.providers;


import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationServiceProvider extends Provider {

    //Cycle speed of the animation
    private float animationSpeed;

    //Index to current selected image
    private int currentImage;

    //Timer variables
    private long animationTimer, elapsedTime;

    //Array of images to hold each different image of an animation
    private BufferedImage[] animationImages;

    //Constructor
    public AnimationServiceProvider(BufferedImage[] animationImages, float animationSpeed) {
        this.animationSpeed = animationSpeed;
        this.animationImages = animationImages;
    }

    //**** BOOT METHOD ****\\
    @Override
    public void boot(App app) {
        //Start at first animation frame
        currentImage = 0;

        //Animation timer starts at 0
        animationTimer = 0;

        //Store time since game started
        elapsedTime = System.currentTimeMillis();
    }

    //**** PERFORM TICK METHOD ****\\
    @Override
    public boolean performTick() {
        return true;
    }

    //**** PERFORM RENDER METHOD ****\\
    @Override
    public boolean performRender() {
        return false;
    }

    //**** RENDER METHOD ****\\
    @Override
    public void render(Graphics g) {}

    //**** TICK METHOD ****\\
    @Override
    public void tick() {
        //Set 'animationTimer' to time of last cycle
        animationTimer += System.currentTimeMillis() - elapsedTime;

        //Set 'elapsedTime' to time of this cycle
        elapsedTime = System.currentTimeMillis();

        //If reached desired animation speed
        if (animationTimer > animationSpeed) {

            //Increment 'currentFrame' to display next animation image
            currentImage++;

            //Reset timer
            animationTimer = 0;

            //If reached end the of our animation images
            if (currentImage > animationImages.length - 1)
                //Return to first image
                currentImage = 0;
        }
    }

    public BufferedImage getCurrentImage() {
        return animationImages[currentImage];
    }

}
