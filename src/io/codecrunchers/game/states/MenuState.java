package io.codecrunchers.game.states;

import io.codecrunchers.facades.App;
import io.codecrunchers.game.gui.InterfaceButton;

import java.awt.*;
import java.io.IOException;

public class MenuState extends State {

    private App app;

    @Override
    public void boot(App app) {
        this.app = app;
        this.app.addInterfaceObject(new InterfaceButton()
         .setX(64)
         .setY(180)
        .setAppFacade(this.app)
        .setHeight(this.app.config().textureHeight())
        .setWidth(this.app.config().textureWidth()*3)
        .setText("Play!")
        .setHoverBoxCurve(22)
        .setState("menu")
        .setClickEvent(this::play));

        this.app.addInterfaceObject(new InterfaceButton()
                .setX(64)
                .setY(180+64+16)
                .setAppFacade(this.app)
                .setHeight(this.app.config().textureHeight())
                .setWidth(this.app.config().textureWidth()*3)
                .setText("Settings")
                .setHoverBoxCurve(22)
                .setState("menu")
                .setClickEvent(this::settings));

        this.app.addInterfaceObject(new InterfaceButton()
                .setX(64)
                .setY(180+128+32)
                .setAppFacade(this.app)
                .setHeight(this.app.config().textureHeight())
                .setWidth(this.app.config().textureWidth()*3)
                .setText("Credits")
                .setHoverBoxCurve(22)
                .setState("menu")
                .setClickEvent(this::credits));

        this.app.addInterfaceObject(new InterfaceButton()
                .setX(64)
                .setY(180+128+64+48)
                .setAppFacade(this.app)
                .setHeight(this.app.config().textureHeight())
                .setWidth(this.app.config().textureWidth()*3)
                .setText("Quit Game")
                .setState("menu")
                .setHoverBoxCurve(22)
                .setClickEvent(this::quit));


    }
    private void play(){
        this.app.playAudioClip("ui-click");
        this.app.resetAudioClip("ui-click");
        ((GameState)this.app.getState("game")).startGame();
    }
    private void credits(){
        this.app.playAudioClip("ui-click");
        this.app.resetAudioClip("ui-click");
        try {
            Desktop.getDesktop().browse(java.net.URI.create(this.app.config().creditsUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void settings(){
        this.app.resetMouseClick();
        //Reset mouse button needed to prevent bug
        this.app.playAudioClip("ui-click");
        this.app.resetAudioClip("ui-click");
        this.app.setCurrentState("settings");
    }
    private void quit(){
        this.app.playAudioClip("ui-click");
        Runtime.getRuntime().exit(0);
    }



    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
