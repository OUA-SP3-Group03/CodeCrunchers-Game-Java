package io.codecrunchers.service;

import javax.swing.*;
import java.awt.*;

public class DisplayService {
    
    private final String title;
    private final int width;
    private final int height;

    public JFrame frame;

    public DisplayService(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public Canvas createDisplay(){
        //Sets title of JFrame as it is created.
        this.frame = new JFrame(title);
        //Sets size of said JFrame.
        frame.setSize(width, height);
        //Closes window correctly (stops it from running in the background).
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Prevent user from resizing window.
        frame.setResizable(false);
        //Makes window appear in center of screen.
        frame.setLocationRelativeTo(null);
        //Makes JFrame visible.
        frame.setVisible(true);

        Canvas canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();

        return canvas;
    }

}
