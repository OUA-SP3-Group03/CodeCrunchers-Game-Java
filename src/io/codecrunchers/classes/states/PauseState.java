package io.codecrunchers.classes.states;

import io.codecrunchers.classes.gui.InterfaceButton;
import io.codecrunchers.facades.App;

import java.awt.*;

public class PauseState extends State {

    private App app;
    @Override
    public void boot(App app) {
        this.app = app;
        this.app.addInterfaceObject( new InterfaceButton()
                .setAppFacade(this.app)
                .setX((int) (this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*1.5)*this.app.config().textureScale()))
                .setY(this.app.config().interfaceHeight()/2 - this.app.config().textureHeight()*this.app.config().textureScale())
                .setWidth(this.app.config().textureWidth()*3)
                .setHeight(this.app.config().textureHeight())
                .setState("pause")
                .setText("Resume Game")
                .setClickEvent(()->this.app.setCurrentState("game")));

        this.app.addInterfaceObject( new InterfaceButton()
                .setAppFacade(this.app)
                .setX((int) (this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*1.5)*this.app.config().textureScale()))
                .setY((int) (this.app.config().interfaceHeight()/2 - this.app.config().textureHeight()*this.app.config().textureScale()+64*1.5))
                .setWidth(this.app.config().textureWidth()*3)
                .setHeight(this.app.config().textureHeight())
                .setState("pause")
                .setText("Quit Game")
                .setClickEvent(()->this.app.setCurrentState("login")));
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

}
