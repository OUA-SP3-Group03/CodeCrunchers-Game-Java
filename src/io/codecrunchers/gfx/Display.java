package io.codecrunchers.gfx;

import java.awt.Canvas;
import java.awt.Dimension;

//imports JFrame.
import javax.swing.JFrame;


public class Display {

    //Creates private JFrame object.
    //Object is made private as classes shouldn't need to access it.
    private JFrame frame;

    //Creates canvas to render stuff to the frame.
    private Canvas canvas;

    //Creates title with String value.
    private String title;

    //Refers to width and height of Frame (Pixels).
    private int width, height;

    //Constructor for Display Class.
    //Takes in Title, Width and Height of Display window.
    public Display(String title, int width, int height) {

        //"this." keyword used in front of variable names since class variables are
        //also named the same as the parameters.
        this.title = title;
        this.width = width;
        this.height = height;

        //Calls createDisplay method to initializes JFrame.
        createDisplay();
    }

    //Method createDisplay initializes JFrames.
    private void createDisplay(){

        //Sets title of JFrame as it is created.
        frame = new JFrame(title);
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

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();

    }

    public Canvas getCanvas() {
        return canvas;

    }

}

