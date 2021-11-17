package io.codecrunchers.game.states;

import io.codecrunchers.facades.App;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EndingState extends State {

    private App app;
    private BufferedImage logo;


    @Override
    public void boot(App app) {
        this.app = app;
        this.logo = this.app.texture().logo();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font(g.getFont().getName(), Font.BOLD,40));
        g.drawString("Congratulations!",450,380);
        g.drawString("Time to celebrate!",450,380);


        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(logo, null, 0, 55);
    }
}
