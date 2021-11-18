package io.codecrunchers.game.states;

import io.codecrunchers.facades.App;
import io.codecrunchers.game.gui.InterfaceButton;

import java.awt.*;

public class Game_Over extends State {
    private App app;


    @Override
    public void boot(App app) {
        this.app = app;

        this.app.addInterfaceObject(new InterfaceButton()
                .setX((int) (this.app.config().interfaceWidth() / 2 - (this.app.config().textureWidth() * 1.5) * this.app.config().textureScale())/2)
                .setY((this.app.config().interfaceHeight() / 2 - this.app.config().textureHeight() * this.app.config().textureScale() + 64 * 3))
                .setAppFacade(this.app)
                .setHeight(this.app.config().textureHeight())
                .setWidth(this.app.config().textureWidth() * 4)
                .setText("Try Again")
                .setHoverBoxCurve(22)
                .setState("game_over")
                .setClickEvent(this::backToGame));

        this.app.addInterfaceObject(new InterfaceButton()
                .setX((int) (this.app.config().interfaceWidth() / 2 - (this.app.config().textureWidth() * 1.5) * this.app.config().textureScale())+200)
                .setY((this.app.config().interfaceHeight() / 2 - this.app.config().textureHeight() * this.app.config().textureScale() + 64 * 3))
                .setAppFacade(this.app)
                .setHeight(this.app.config().textureHeight())
                .setWidth(this.app.config().textureWidth() * 4)
                .setText("Back to Menu")
                .setHoverBoxCurve(22)
                .setState("game_over")
                .setClickEvent(this::backToMenu));

    }


    private void backToMenu(){
        this.app.resetMouseClick();
        //Reset mouse button needed to prevent bug
        this.app.playAudioClip("ui-click");
        this.app.resetAudioClip("ui-click");
        this.app.setCurrentState("menu");
    }

    private void backToGame(){
        this.app.resetMouseClick();
        //Reset mouse button needed to prevent bug
        this.app.playAudioClip("ui-click");
        this.app.resetAudioClip("ui-click");
        ((GameState)this.app.getState("game")).startGame();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.setFont(new Font(g.getFont().getName(), Font.BOLD, 100));
        g.drawString("GAME OVER!", 320, 175);



    }
}
