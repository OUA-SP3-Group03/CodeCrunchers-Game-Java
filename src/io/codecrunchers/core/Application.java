package io.codecrunchers.core;

import io.codecrunchers.facades.App;
import io.codecrunchers.game.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Application {

    private App app;

    public void onBootCompletion(App app) {
        //set this app equal to the app facade
        this.app = app;

        //register the states
        this.app.registerState("login", new LoginState());
        this.app.registerState("game", new GameState());
        this.app.registerState("menu", new MenuState());
        this.app.registerState("pause", new PauseState());
        this.app.registerState("settings", new SettingsState());

        //initialize the states
        this.app.initializeStates();

        //set the default starting state
        this.app.setCurrentState("login");


        //check for prior login
        if(this.app.authCheck()){
            this.app.setCurrentState("menu");
        }else{
            this.app.setCurrentState("login");
        }

        //start the main loop
        this.app.startLoop();

    }

    public void tick(){

        this.app.tick();
    }

    public void render(){
        if(this.app.isBooted()) {
            //create buffered strategy for the app canvas
            BufferStrategy bs = this.app.canvas().getBufferStrategy();
            if (bs == null) {
                this.app.canvas().createBufferStrategy(3);
                return;
            }

            //create the graphics object to draw to that buffered strategy
            Graphics g = bs.getDrawGraphics();

            //clear the screen
            g.clearRect(0, 0, this.app.config().interfaceWidth(), this.app.config().interfaceHeight());



            g.setColor(Color.decode("#22123b"));
            g.fillRect(0, 0, this.app.config().interfaceWidth(), this.app.config().interfaceHeight());


            //render all providers
            this.app.render(g);


            //__ END RENDER

            //finally, show the buffered strategy and dispose of the old graphics
            bs.show();
            g.dispose();
        }
    }

}
