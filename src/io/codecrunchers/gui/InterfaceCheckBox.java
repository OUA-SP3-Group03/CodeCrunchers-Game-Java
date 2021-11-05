package io.codecrunchers.gui;

import io.codecrunchers.engine.gfx.InterfaceObject;

import java.awt.*;

public class InterfaceCheckBox extends InterfaceObject {


    private boolean toggled = false;

    @Override
    public void tick() {
        if(this.hovering && this.app.mousePressed() && !this.isPressed){

            this.toggled = !this.toggled;

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

        if(!this.toggled) {
            g.drawImage(this.app.texture().allImages()[14],this.x,this.y,null);
            g.drawImage(this.app.texture().allImages()[15],this.x+this.app.config().textureWidth()*this.app.config().textureScale(),this.y,null);
            g.drawImage(this.app.texture().allImages()[13],this.x+15,this.y,null);
        }else{
            g.drawImage(this.app.texture().allImages()[6],this.x,this.y,null);
            g.drawImage(this.app.texture().allImages()[7],this.x+this.app.config().textureWidth()*this.app.config().textureScale(),this.y,null);
            g.drawImage(this.app.texture().allImages()[13],this.x+this.app.config().textureWidth()*this.app.config().textureScale()-15,this.y,null);

        }


    }
}
