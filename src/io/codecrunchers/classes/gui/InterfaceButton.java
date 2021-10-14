package io.codecrunchers.classes.gui;


import java.awt.*;

public class InterfaceButton extends InterfaceObject{

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
            System.out.println("Booted Object : "+this);
        }

        //set color
        g.setColor(this.textColor);

        //draw outline
        g.drawRect(this.x, this.y, this.width, this.height);
        //draw string
        g.drawString(this.text,this.drawTextX,this.drawTextY);

    }




}
