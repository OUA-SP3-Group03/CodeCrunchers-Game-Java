package io.codecrunchers.classes.gui;

import io.codecrunchers.classes.ASCII;

import java.awt.*;

public class InterfaceInput extends InterfaceObject{

    private String input = "";
    private String hiddenInput = "";

    @Override
    public void tick() {
        if(this.hovering && this.app.mousePressed() && !this.isPressed){

            this.app.setSelectedInterfaceObject(this);
            //is pressed variable is needed to prevent this firing more than once
        }

        if(this.app.selectedInterfaceObject() == this && !this.app.keyPressed().containsKey(ASCII.backspace)){
            //loop across all the current active keys
            for(Integer key : this.app.keyPressed().keySet()) {

                //get the value of this key
                int x = key;

                //check if the key is active, this is needed to stop spamming between ticks
                if(this.app.keyPressed().get(x)) {

                    //check the key that is being proccsed is not the shift key
                    if(key != ASCII.shift) {
                        this.app.keyPressed().replace(key, false);
                    }

                    //now check if the key is typable to the screen
                    if(this.isTypable(x)) {
                        //next check if it is a letter
                        if(this.isLetter(x)) {

                                if(!this.app.isCapsLocked() && !this.app.keyPressed().containsKey(ASCII.shift)) {
                                    x += 32;
                                }

                        }else {
                            //if holding shift grab alternative key
                            if(this.app.keyPressed().containsKey(ASCII.shift)) {
                                if(ASCII.alternativeKeys.containsKey(x)) {
                                    x = ASCII.alternativeKeys.get(x);
                                }
                            }
                        }

                        //add the value to the text as a character
                        if(this.input.length() < 100) {
                            this.input += (char) x;
                            this.hiddenInput += (char) 42;
                        }

                    }
                }
            }
        }else if(this.app.selectedInterfaceObject() == this && this.app.keyPressed().containsKey(ASCII.backspace)){
            if(this.input.length() > 0) {
                this.app.keyPressed().remove(ASCII.backspace);
                this.input = this.input.substring(0, this.input.length() - 1);
                this.hiddenInput = this.hiddenInput.substring(0, this.hiddenInput.length() - 1);
            }
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
        g.drawImage(this.app.texture().allImages()[3],this.x,this.y,null);
        //draw base image

        int i = 0;
        int textureSize = this.app.config().textureWidth()*this.app.config().textureScale();
        int lastX = this.x+textureSize;


        while(i < this.tileWidth-2) {
            g.drawImage(this.app.texture().allImages()[4], lastX, this.y, null);
            lastX += textureSize;
            i++;
        }


        g.drawImage(this.app.texture().allImages()[5],lastX,this.y,null);




        if(this.app.selectedInterfaceObject() == this){
            g.drawRect(this.x,this.y,this.width,this.height);
        }

        if(this.input.length() > 0) {
            if(this.inputHidden) {
                g.drawString(this.hiddenInput, this.drawTextX, this.drawTextY);
            }else{
                g.drawString(this.input, this.drawTextX, this.drawTextY);

            }
        }else{
            g.drawString(this.text, this.drawTextX, this.drawTextY);

        }

    }


    public boolean isLetter(int key) {

        int alphabetStart = 65;
        int alphabetEnd = 90;

        if(key >= alphabetStart && key <= alphabetEnd) {
            return true;
        }else {
            return false;
        }

    }

    public boolean isTypable(int key) {
        int printableStart = 32;
        int printableEnd = 126;

        if(key >= printableStart && key <= printableEnd) {
            return true;
        }else {

            return false;
        }
    }
}
