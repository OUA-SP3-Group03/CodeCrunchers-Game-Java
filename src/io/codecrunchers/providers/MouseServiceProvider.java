package io.codecrunchers.providers;


import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseServiceProvider extends Provider implements MouseListener, MouseMotionListener {

    //Is mouse button pressed
    private boolean mousePressed;


    //Coordinates of mouse cursor
    private int mouseX, mouseY;

    //**** BOOT METHOD ****\\
    @Override
    public void boot(App app) {
        //app.callback().setMouseListener(this);
        app.canvas().addMouseListener(this);
        app.canvas().addMouseMotionListener(this);

        this.booted = true;
    }

    //**** PERFORM TICK METHOD ****\\
    @Override
    public boolean performTick() {
        return false;
    }

    //**** PERFORM RENDER METHOD ****\\
    @Override
    public boolean performRender() {
        return false;
    }

    //**** RENDER METHOD ****\\
    @Override
    public void render(Graphics g) {}

    //**** TICK METHOD ****\\
    @Override
    public void tick() {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            mousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            mousePressed = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //Store coordinates in their variables
        mouseX = e.getX();
        mouseY = e.getY();
    }

    //GETTERS
    public boolean isMousePressed() {
        return mousePressed;
    }

    public void resetMouseClick(){
        this.mousePressed = false;
    }
    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
}
