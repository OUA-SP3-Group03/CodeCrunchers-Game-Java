package io.codecrunchers.service;

import java.awt.image.BufferedImage;

public class AnimationService {
    //Cycle speed of the animation
    private float animationSpeed;

    //Index to current selected image
    private int currentImage;

    //Timer variables
    private long animationTimer, elapsedTime;

    //Array of images to hold each different image of an animation
    private BufferedImage[] animationImages;

    //Constructor
    public AnimationService(BufferedImage[] animationImages, float animationSpeed) {
        this.animationSpeed = animationSpeed;
        this.animationImages = animationImages;

        //Start at first animation frame
        currentImage = 0;

        //Animation timer starts at 0
        animationTimer = 0;

        //Store time since game started
        elapsedTime = System.currentTimeMillis();
    }

    //Cycle through each image
    //The amount of cycles to pass from one image to the next depends on 'animationSpeed'
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
