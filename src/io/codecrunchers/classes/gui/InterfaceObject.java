package io.codecrunchers.classes.gui;

import io.codecrunchers.classes.states.State;
import io.codecrunchers.facades.App;

import java.awt.*;

public abstract class InterfaceObject {

    //Interface Object references variables targeting other objects
    //NEW APP NEEDED TO PREVENT FIST CALL FAILURE
    protected App app = new App(null);
    protected State state = null;
    protected ClickEvent clickListener = null;
    protected boolean isPressed = false;

    //Interface object size and bounds
    protected Rectangle bounds;
    protected int x = 0;
    protected int y = 0;
    protected int width = 0;
    protected int height = 0;
    protected Boolean showOutline = false;

    //Interface object hovering status
    protected Boolean hovering = false;
    protected Boolean showOnHover = true;
    protected Color hoverColor = new Color(0,0,0,0.5F );

    //Interface Object text and alignment variables
    protected String text = "New Button";
    protected String textAlign = "";
    protected int textWidth = 0;
    protected int textHeight = 0;
    protected int drawTextX = 0;

    protected int drawTextY = 0;
    protected int textPadding = 10;
    protected Color textColor = Color.black;

    //Interface Object system variables
    protected Boolean booted= false;


    //Abstract methods
    public abstract void tick();
    public abstract void render(Graphics g);


    //**** BOOT METHOD ****\\
    //called once when the object is first loaded
    protected void boot(Graphics g) {

        //set the text width and height
        this.textWidth = g.getFontMetrics().stringWidth(this.text);
        this.textHeight = g.getFontMetrics().getHeight();

        //check for text alignment for this object
        if(this.textAlign.contentEquals("left")){
            //align left
            this.drawTextX = (this.x+this.textPadding);
            this.drawTextY = (this.y+this.height/2)+(textHeight/4);
        }else if(this.textAlign.contentEquals("right")){
            //align right
            this.drawTextX = (this.x+this.width-this.textPadding-this.textWidth);
            this.drawTextY = (this.y+this.height/2)+(textHeight/4);
        }else{
            //Default is align center
            this.drawTextX = (this.x+this.width/2)-(textWidth/2);
            this.drawTextY = (this.y+this.height/2)+(textHeight/4);
        }

        //set booted to true so this method is not called again
        this.booted = true;
    }

    //check hovering
    public void checkHover(){
        this.hovering = this.bounds.contains(this.app.mouseX(), this.app.mouseY());
    }

    public void drawOnHover(Graphics g){
        g.setColor(this.hoverColor);
        g.fillRect(this.x, this.y, this.width, this.height);
    }


    //**** ON CLICK METHOD ****\\
    protected void onClick(){
        if(this.clickListener != null) {
            this.clickListener.onClick();
        }
    }

    //**** SETTER METHODS ****\\
    //the following methods are used when creating the button

    public InterfaceObject setX(int x){
        this.x = x;
        this.bounds = new Rectangle(x,y,width, height);
        return this;
    }

    public InterfaceObject setY(int y){
        this.y = y;
        this.bounds = new Rectangle(x,y,width, height);
        return this;
    }

    public InterfaceObject setWidth(int width){
        this.width = width;
        this.bounds = new Rectangle(x,y,width, height);
        return this;
    }

    public InterfaceObject setHeight(int height){
        this.height = height;
        this.bounds = new Rectangle(x,y,width, height);
        return this;
    }

    public InterfaceObject setState(State state){
        this.state = state;
        return this;
    }

    public InterfaceObject setAppFacade(App app){
        this.app = app;
        return this;
    }

    public InterfaceObject setText(String text){
        this.text = text;
        return this;
    }

    public InterfaceObject setTextAlign(String align){
        this.textAlign = align;
        return this;
    }

    public InterfaceObject setTextColor(Color color){
        this.textColor = color;
        return this;
    }

    public InterfaceObject setTextPadding(int padding){
        this.textPadding = padding;
        return this;
    }

    public InterfaceObject setClickEvent(ClickEvent clickListener){
        this.clickListener = clickListener;
        return this;
    }

    public InterfaceObject setShowOnHover(boolean value){
        this.showOnHover = value;
        return this;
    }

    public InterfaceObject setHoverColor(Color value){
        this.hoverColor = value;
        return this;
    }

    //**** GETTER METHODS ****\\

    public State getAssignedState() {
        return this.state;
    }

    public boolean getHovering(){
        return this.hovering;
    }

    public boolean getShowOnHover(){
        return this.showOnHover;
    }
}
