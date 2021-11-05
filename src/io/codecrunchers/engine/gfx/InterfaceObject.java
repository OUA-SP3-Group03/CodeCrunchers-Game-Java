package io.codecrunchers.engine.gfx;

import io.codecrunchers.engine.facades.App;
import io.codecrunchers.gui.ClickEvent;

import java.awt.*;

public abstract class InterfaceObject {

    //Interface Object references variables targeting other objects
    //NEW APP NEEDED TO PREVENT FIST CALL FAILURE
    protected App app = new App(null);
    protected String state = null;
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
    protected Color hoverColor = new Color(0,0,0,0.2F );
    protected int hoverBoxCurve = 0;

    //Interface Object text and alignment variables
    protected String text = "New Button";
    protected String textAlign = "";
    protected int textWidth = 0;
    protected int textHeight = 0;
    protected int drawTextX = 0;
    protected boolean inputHidden = false;

    protected int drawTextY = 0;
    protected int textPadding = 10;
    protected Color textColor = Color.black;

    //Interface Object system variables
    protected Boolean booted= false;

    //texture variables
    protected int tileWidth = 0;
    protected int tileHeight = 0;


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
        g.fillRoundRect(this.x, this.y, this.width, this.height, this.hoverBoxCurve,this.hoverBoxCurve);
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
        this.width = width*this.app.config().textureScale();
        this.bounds = new Rectangle(x,y,this.width, height);
        this.tileWidth = this.width/this.app.config().textureWidth()/this.app.config().textureScale();
        return this;
    }

    public InterfaceObject setHeight(int height){
        this.height = height*this.app.config().textureScale();
        this.bounds = new Rectangle(x,y,this.width, this.height);
        this.tileHeight = this.height/this.app.config().textureWidth()/this.app.config().textureScale();
        return this;
    }

    public InterfaceObject setState(String state){
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

    public InterfaceObject setShowDefaultText(boolean value){
        if(!value){
            this.text = "";
        }
        return this;
    }

    public InterfaceObject setHoverBoxCurve(int value){
        this.hoverBoxCurve = value;
        return this;
    }

    public InterfaceObject setHiddenInput(boolean value){
        this.inputHidden = value;
        return this;
    }

    //**** GETTER METHODS ****\\

    public String getAssignedState() {
        return this.state;
    }

    public boolean getHovering(){
        return this.hovering;
    }

    public boolean getShowOnHover(){
        return this.showOnHover;
    }
}
