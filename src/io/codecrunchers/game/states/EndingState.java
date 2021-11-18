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
                .setX((int) (this.app.config().interfaceWidth() / 2 - (this.app.config().textureWidth() * 1.5) * this.app.config().textureScale()))
                .setY((this.app.config().interfaceHeight() / 2 - this.app.config().textureHeight() * this.app.config().textureScale() + 64 * 5))
                .setAppFacade(this.app)
                .setHeight(this.app.config().textureHeight())
                .setWidth(this.app.config().textureWidth() * 3)
                .setText("Continue")
                .setHoverBoxCurve(22)
                .setState("end")
                .setClickEvent(this::backToMenu));

    }


    private void backToMenu() {
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
        Graphics2D g2 = (Graphics2D) g;


        g.setColor(Color.white);
        g.fillRect(150, 20, this.app.config().interfaceWidth() - 300, this.app.config().interfaceHeight() - 140);

        g.setColor(Color.orange);
        g2.setStroke(new BasicStroke(10));
        g.drawRect(150, 20, this.app.config().interfaceWidth() - 300, this.app.config().interfaceHeight() - 140);

        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getName(), Font.BOLD, 75));
        g.drawString("{Certificate of Graduation}", (int) (this.app.config().interfaceWidth() / 2 - (this.app.config().textureWidth() * 1.5) * this.app.config().textureScale())/2 - 107, 110);


        g.setFont(new Font(g.getFont().getName(), Font.ITALIC, 60));
        g.drawString("Congratulations!", (int) (this.app.config().interfaceWidth() / 2 - (this.app.config().textureWidth() * 1.5) * this.app.config().textureScale())/2 + 165, 210);



        g.setFont(new Font(g.getFont().getName(), Font.ITALIC, 25));
        g.drawString("This certificate is presented to", (int) (this.app.config().interfaceWidth() / 2 - (this.app.config().textureWidth() * 1.5) * this.app.config().textureScale()) - 60, 270);


        g.setFont(new Font(g.getFont().getName(), Font.BOLD, 80));
        g.drawString("[" + this.app.authUserInfo()[0] + "]", (int)(this.app.config().interfaceWidth() / 2 - (this.app.config().textureWidth() * 1.5) * this.app.config().textureScale()) - 108, 360);

        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getName(),Font.PLAIN, 60));
        g.drawString("Score:" + GameState.getScore(), (int)(this.app.config().interfaceWidth() / 2 - (this.app.config().textureWidth() * 1.5) * this.app.config().textureScale()), 440);


        g2.drawImage(logo, (int)(this.app.config().interfaceWidth() / 2 -  (this.app.config().textureWidth() * 1.5) * this.app.config().textureScale()) - 500, this.app.config().interfaceHeight() - 220, this.app.config().interfaceWidth() - 160, logo.getHeight() - 35, null);


    }
}
