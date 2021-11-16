package io.codecrunchers.game.states;

import io.codecrunchers.facades.App;
import io.codecrunchers.game.gui.InterfaceButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuState extends State {

    private App app;
    private BufferedImage logo;

    @Override
    public void boot(App app) {

        this.app = app;
        this.logo = this.app.texture().logo();

        this.app.addInterfaceObject(new InterfaceButton()
                .setX(64)
                .setY(260)
                .setAppFacade(this.app)
                .setHeight(this.app.config().textureHeight())
                .setWidth(this.app.config().textureWidth()*3)
                .setText("Play!")
                .setHoverBoxCurve(22)
                .setState("menu")
                .setClickEvent(this::play));

        this.app.addInterfaceObject(new InterfaceButton()
                .setX(64)
                .setY(340)
                .setAppFacade(this.app)
                .setHeight(this.app.config().textureHeight())
                .setWidth(this.app.config().textureWidth()*3)
                .setText("Settings")
                .setHoverBoxCurve(22)
                .setState("menu")
                .setClickEvent(this::settings));

        this.app.addInterfaceObject(new InterfaceButton()
                .setX(64)
                .setY(420)
                .setAppFacade(this.app)
                .setHeight(this.app.config().textureHeight())
                .setWidth(this.app.config().textureWidth()*3)
                .setText("Credits")
                .setHoverBoxCurve(22)
                .setState("menu")
                .setClickEvent(this::credits));

        this.app.addInterfaceObject(new InterfaceButton()
                .setX(64)
                .setY(500)
                .setAppFacade(this.app)
                .setHeight(this.app.config().textureHeight())
                .setWidth(this.app.config().textureWidth()*3)
                .setText("Quit Game")
                .setState("menu")
                .setHoverBoxCurve(22)
                .setClickEvent(this::quit));

        //BUGS!
        //this last button is needed to be hidden off screen to prevent a strange bug where the rendered text appear to have the hover color applied
        this.app.addInterfaceObject(new InterfaceButton()
                .setX(-200)
                .setY(0)
                .setAppFacade(this.app)
                .setHeight(0)
                .setWidth(0)
                .setText("")
                .setState("menu")
                .setHoverBoxCurve(22)
        );
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
        g.setFont(new Font(g.getFont().getName(), Font.BOLD,40));
        g.drawString("Welcome "+this.app.authUserInfo()[0]+"! ",450,380);
        g.setFont(new Font(g.getFont().getName(), Font.ITALIC,10));
        g.drawString("Code Crunchers Alpha v1",this.app.config().interfaceWidth()/2-50, this.app.config().interfaceHeight()-112);

        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(logo, null, 0, 55);

    }
}