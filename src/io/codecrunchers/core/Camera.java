package io.codecrunchers.core;
import io.codecrunchers.classes.entities.Entity;
import io.codecrunchers.facades.App;
public class Camera {

    private float xOffset, yOffset, tempXOffset;
    private App app;

    public Camera(App app, float xOffset, float yOffset){
        this.app = app;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void centerOnEntity(Entity e){

        tempXOffset = e.getX() - (float)this.app.config().interfaceWidth() / 2 + (float)e.getWidth() / 2;

        if(tempXOffset >= xOffset) {
            this.xOffset = e.getX() - (float) this.app.config().interfaceWidth() / 2 + (float) e.getWidth() / 2;
        }

        this.yOffset = e.getY() - (float) this.app.config().interfaceHeight() / 2 + (float) e.getHeight() / 2;

        System.out.println("xOffset: " + xOffset);
        System.out.println("tempOffset: " + tempXOffset);
    }

    public float getxOffset() {
        return xOffset;
    }
    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }
    public float getyOffset() {
        return yOffset;
    }
    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}