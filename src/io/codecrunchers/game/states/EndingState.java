package io.codecrunchers.game.states;

import io.codecrunchers.facades.App;
import io.codecrunchers.game.gui.InterfaceButton;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EndingState extends State {

    private App app;
    private BufferedImage logo;


    @Override
    public void boot(App app) {
        this.app = app;
        this.logo = this.app.texture().logo();

        this.app.addInterfaceObject(new InterfaceButton()
                .setX((int) (this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*1.5)*this.app.config().textureScale()))
                .setY((this.app.config().interfaceHeight()/2 - this.app.config().textureHeight()*this.app.config().textureScale()+64*5))
                .setAppFacade(this.app)
                .setHeight(this.app.config().textureHeight())
                .setWidth(this.app.config().textureWidth()*3)
                .setText("Continue")
                .setHoverBoxCurve(22)
                .setState("end")
                .setClickEvent(this::backToMenu));

    }


    private void backToMenu(){
        this.app.resetMouseClick();
        //Reset mouse button needed to prevent bug
        this.app.playAudioClip("ui-click");
        this.app.resetAudioClip("ui-click");
        this.app.setCurrentState("menu");
    }

    @Override
    public void tick() {

    }



    @Override
    public void render(Graphics g) {
        g.setFont(new Font(g.getFont().getName(), Font.BOLD,40));
        g.drawString("Congratulations!",(int) (this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*1.5)*this.app.config().textureScale()) - 70,380);
        g.drawString("Time to celebrate!",(int) (this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*1.5)*this.app.config().textureScale()) - 70, 500);


        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(logo, null, 0, 55);
    }
}
