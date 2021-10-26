package io.codecrunchers.core;

import io.codecrunchers.classes.gui.InterfaceButton;
import io.codecrunchers.classes.states.GameState;
import io.codecrunchers.classes.states.SettingsState;
import io.codecrunchers.classes.gui.InterfaceCheckBox;
import io.codecrunchers.classes.gui.InterfaceInput;
import io.codecrunchers.classes.states.State;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Application {

    private App app;

    public void onBootCompletion(App app) {
        //set this app equal to the app facade
        this.app = app;
        //start the main loop
        this.app.startLoop();

        //DEBUGGING INTERFACE OBJECT
        //button one
        this.app.addInterfaceObject(new InterfaceInput()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*3)*this.app.config().textureScale())
                .setY(this.app.config().interfaceHeight()/2 - this.app.config().textureHeight()*this.app.config().textureScale())
                .setWidth(this.app.config().textureWidth()*6)
                .setHeight(this.app.config().textureHeight())
                .setTextAlign("left")
                .setText("Email"));

        this.app.addInterfaceObject(new InterfaceInput()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*3)*this.app.config().textureScale())
                .setY((int) (this.app.config().interfaceHeight()/2+ this.app.config().textureHeight()*this.app.config().textureScale()*0.5))
                .setWidth(this.app.config().textureWidth()*6)
                .setHeight(this.app.config().textureHeight())
                .setHiddenInput(true)
                .setTextAlign("left")
                .setText("Password"));

        this.app.addInterfaceObject(new InterfaceCheckBox()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2- (this.app.config().textureWidth()*3)*this.app.config().textureScale())
                .setY((this.app.config().interfaceHeight()/2 + this.app.config().textureHeight() *2* this.app.config().textureScale()-14))
                .setWidth(this.app.config().textureWidth()*2)
                .setHeight(this.app.config().textureHeight())
                .setShowOnHover(false)
                .setClickEvent(()-> System.out.println("toggled")));


        this.app.addInterfaceObject(new InterfaceButton()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2-this.app.config().textureWidth()*3*this.app.config().textureScale())
                .setY((this.app.config().interfaceHeight()/2 + this.app.config().textureHeight()*3 * this.app.config().textureScale()))
                .setWidth(this.app.config().textureWidth()*3)
                .setHeight(this.app.config().textureHeight())
                .setState(new State())
                .setText("Login")
                .setHoverBoxCurve(22)
                .setClickEvent(()-> System.out.println("Clicked Login")));

        //button two
        this.app.addInterfaceObject(new InterfaceButton()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2+4)
                .setY((this.app.config().interfaceHeight()/2 + this.app.config().textureHeight()*3 * this.app.config().textureScale()))
                .setWidth(this.app.config().textureWidth()*3)
                .setText("Register")
                .setHeight(this.app.config().textureHeight())
                .setState(new State())
                .setHoverBoxCurve(22)
                .setClickEvent(()-> {
                    try {
                        Desktop.getDesktop().browse(java.net.URI.create("http://codecrunchers.io/register"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
    }

    public void tick(){

        this.app.tick();
    }

    public void render(){
        //create buffered strategy for the app canvas
        BufferStrategy bs = this.app.canvas().getBufferStrategy();
            if(bs == null){
                this.app.canvas().createBufferStrategy(3);
                return;
            }

       //create the graphics object to draw to that buffered strategy
       Graphics g = bs.getDrawGraphics();

       g.clearRect(0,0,this.app.config().interfaceWidth(),this.app.config().interfaceHeight());

       //__ START RENDER
        g.setColor(Color.white);
        g.fillRect(0,0,this.app.config().interfaceWidth(),this.app.config().interfaceHeight());

            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
            g.setFont(newFont);
            g.setColor(Color.black);

            g.drawImage(app.texture().logo(),0,64,null);

        g.drawString("Keep me logged in",this.app.config().interfaceWidth()/2-55,this.app.config().interfaceHeight()/2 + this.app.config().textureHeight() *2* this.app.config().textureScale()+24);
           //render providers
           this.app.render(g);

       //__ END RENDER

       //finally, show the buffered strategy and dispose of the old graphics
       bs.show();
       g.dispose();
    }

}
