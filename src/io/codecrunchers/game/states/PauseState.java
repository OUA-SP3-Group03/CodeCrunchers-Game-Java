package io.codecrunchers.game.states;

import io.codecrunchers.game.gui.InterfaceButton;
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
                .setY((int) (this.app.config().interfaceHeight()/2 - this.app.config().textureHeight()*this.app.config().textureScale()+64*1.5))
                .setWidth(this.app.config().textureWidth()*3)
                .setHeight(this.app.config().textureHeight())
                .setState("pause")
                .setText("Quit Game")
                .setHoverBoxCurve(22)
                .setClickEvent(this::quit));

        this.app.addInterfaceObject( new InterfaceButton()
                .setAppFacade(this.app)
                .setX((int) (this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*1.5)*this.app.config().textureScale()))
                .setY(this.app.config().interfaceHeight()/2 - this.app.config().textureHeight()*this.app.config().textureScale())
                .setWidth(this.app.config().textureWidth()*3)
                .setHeight(this.app.config().textureHeight())
                .setState("pause")
                .setText("Resume Game")
                .setHoverBoxCurve(22)
                .setClickEvent(this::resume));

        //This last interface object is needed to prevent a bug where the displayed "Paused Game" text from the render method
        //turns slightly transparent on hover, this occurs for the last interface object in this boot method.
        this.app.addInterfaceObject(new InterfaceButton()
        .setAppFacade(this.app)
        .setX(-200)
        .setY(0)
        .setWidth(0)
        .setHeight(0)
        .setState("pause"));

    }

    private void quit(){
        this.app.playAudioClip("ui-click");
        this.app.resetAudioClip("ui-click");
        ((GameState)this.app.getState("game")).endGame();
    }

    private void resume(){
        this.app.playAudioClip("ui-click");
        this.app.resetAudioClip("ui-click");
        this.app.setCurrentState("game");
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font(g.getFont().getName(), Font.BOLD,60));
        g.drawString("Game Paused",this.app.config().interfaceWidth()/2-200,128);
    }

}
