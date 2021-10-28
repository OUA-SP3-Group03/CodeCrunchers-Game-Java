package io.codecrunchers.classes.gui;


import java.awt.*;

public class InterfaceButtonLarge extends InterfaceObject{

    @Override
    public void tick() {
        if(this.hovering && this.app.mousePressed() && !this.isPressed){

            this.onClick();

            //is pressed variable is needed to prevent this firing more than once
            this.isPressed = true;
        }else{
            //else we set the is pressed variable back to false
            this.isPressed = false;
        }

    }

    @Override
    public void render(Graphics g) {
        //check if this interface objected has been booted, skip is so
        if(!this.booted){
            //else run the boot method!, only runs once
            this.boot(g);
        }

        //set color
        g.setColor(this.textColor);

        //draw outline
        g.drawImage(this.app.texture().allImages()[8],this.x,this.y,null);
        //draw base image

        int i = 0;
        int textureSize = this.app.config().textureWidth()*this.app.config().textureScale();
        int lastX = this.x+textureSize;


        while(i < this.tileWidth-2) {
            g.drawImage(this.app.texture().allImages()[9], lastX, this.y, null);
            lastX += textureSize;
            i++;
        }
        String test ="1,2";

        g.drawImage(this.app.texture().allImages()[10],lastX,this.y,null);


        //draw string
        g.drawString(this.text,this.drawTextX,this.drawTextY);

    }




}
