package io.codecrunchers.classes.gui;


import java.awt.*;

public class InterfaceButton extends InterfaceObject{

    @Override
    public void tick() {

        this.onClick();

    }

    @Override
    public void render(Graphics g) {
        //check if this interface objected has been booted, skip is so
        if(!this.booted){
            //else run the boot method!, only runs once
            this.boot(g);
            System.out.println("Booted Object : "+this);
        }

        g.setColor(this.textColor);
        if(this.showOutline) {
            g.drawRect(this.x, this.y, this.width, this.height);
        }
        g.drawString(this.text,this.drawTextX,this.drawTextY);

    }




}
