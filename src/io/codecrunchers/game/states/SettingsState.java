package io.codecrunchers.game.states;

import io.codecrunchers.facades.App;
import io.codecrunchers.game.gui.InterfaceButton;

import java.awt.*;

public class SettingsState extends State {

    private App app;

    @Override
    public void boot(App app) {
        this.app = app;

        this.app.addInterfaceObject(new InterfaceButton()
                .setAppFacade(this.app)
                .setState("settings")
                .setX(this.app.config().interfaceWidth()/2-128)
                .setY(this.app.config().interfaceHeight()-128)
                .setWidth(this.app.config().textureWidth()*3)
                .setHeight(this.app.config().textureHeight())
                .setHoverBoxCurve(22)
                .setText("Save and Exit")
                .setClickEvent(this::save));

        //This last interface object is needed to prevent a bug where the displayed "Paused Game" text from the render method
        //turns slightly transparent on hover, this occurs for the last interface object in this boot method.
        this.app.addInterfaceObject(new InterfaceButton()
                .setAppFacade(this.app)
                .setX(-200)
                .setY(0)
                .setWidth(0)
                .setHeight(0)
                .setState("settings"));
    }

    public void save(){
        this.app.resetMouseClick();
        this.app.playAudioClip("ui-click");
        this.app.resetAudioClip("ui-click");
        this.app.setCurrentState("menu");
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawString("Coming Soon!",this.app.config().interfaceWidth()/2-70,400);

        g.setFont(new Font(g.getFont().getName(), Font.BOLD,60));
        g.drawString("Settings",this.app.config().interfaceWidth()/2-150,310);

    }

}
