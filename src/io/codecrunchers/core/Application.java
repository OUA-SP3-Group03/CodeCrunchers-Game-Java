package io.codecrunchers.core;

import io.codecrunchers.classes.gui.InterfaceButton;
import io.codecrunchers.classes.states.GameState;
import io.codecrunchers.classes.states.SettingsState;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Application {

    private App app;

    public void onBootCompletion(App app) {
        //set this app equal to the app facade
        this.app = app;
        //start the main loop
        this.app.startLoop();

        //DEBUGGING INTERFACE OBJECT

        //button one
        this.app.addInterfaceObject(new InterfaceButton()
                .setX(this.app.config().interfaceWidth()/2-200/2)
                .setY(this.app.config().interfaceHeight()/2-50/2)
                .setWidth(200)
                .setHeight(50)
                .setText("Play")
                .setState(new GameState())
                .setTextColor(Color.black)
                .showOutline(true));

        //button two
        this.app.addInterfaceObject(new InterfaceButton()
                .setX(this.app.config().interfaceWidth()/2-200/2)
                .setY(this.app.config().interfaceHeight()/2-50/2+75)
                .setWidth(200)
                .setText("Settings")
                .setHeight(50)
                .setState(new SettingsState())
                .setTextColor(Color.blue)
                .showOutline(true));

        //button three
        this.app.addInterfaceObject(new InterfaceButton()
                .setX(this.app.config().interfaceWidth()/2-200/2)
                .setY(this.app.config().interfaceHeight()/2-50/2+150)
                .setWidth(200)
                .setHeight(50)
                .setText("Quit")
                //TODO does a new state need to be called to quit the game?
                //.setState(new State())
                .setTextColor(Color.red)
                .showOutline(true)
        );
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

        //Test
        g.drawImage(this.app.getImages()[0],16,16,null);

           //PLACEHOLDER TESTING
           g.drawString("Hello World!",20,20);
           g.setColor(Color.red);
           g.drawRect(50,50,100,100);
           //render providers
           this.app.render(g);


       //__ END RENDER

       //finally, show the buffered strategy and dispose of the old graphics
       bs.show();
       g.dispose();
    }

}
