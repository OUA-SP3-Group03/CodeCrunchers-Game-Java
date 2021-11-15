package io.codecrunchers.game.gui;

import java.awt.*;

public class InterfaceAlert extends InterfaceObject{
    @Override
    public void tick() {

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
        g.drawImage(this.app.texture().allImages()[6],this.x,this.y,null);
        //draw base image

        int i = 0;
        int textureSize = this.app.config().textureWidth()*this.app.config().textureScale();
        int lastX = this.x+textureSize;


        while(i < this.tileWidth-2) {
            g.drawImage(this.app.texture().allImages()[7], lastX, this.y, null);
            lastX += textureSize;
            i++;
        }


        g.drawImage(this.app.texture().allImages()[8],lastX,this.y,null);


        //draw string
        g.drawString(this.text,this.drawTextX,this.drawTextY);
    }
}
