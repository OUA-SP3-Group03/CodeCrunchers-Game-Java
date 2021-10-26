package io.codecrunchers.classes.states;

import io.codecrunchers.classes.gui.InterfaceButton;
import io.codecrunchers.classes.gui.InterfaceCheckBox;
import io.codecrunchers.classes.gui.InterfaceInput;
import io.codecrunchers.facades.App;

import java.awt.*;
import java.io.IOException;

public class LoginState extends State {
    private App app;
    @Override
    public void boot(App app) {
        this.app = app;
        //DEBUGGING INTERFACE OBJECT
        //button one
        this.app.addInterfaceObject(new InterfaceInput()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*3)*this.app.config().textureScale())
                .setY(this.app.config().interfaceHeight()/2 - this.app.config().textureHeight()*this.app.config().textureScale())
                .setWidth(this.app.config().textureWidth()*6)
                .setHeight(this.app.config().textureHeight())
                .setState("login")
                .setTextAlign("left")
                .setText("Email"));

        this.app.addInterfaceObject(new InterfaceInput()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*3)*this.app.config().textureScale())
                .setY((int) (this.app.config().interfaceHeight()/2+ this.app.config().textureHeight()*this.app.config().textureScale()*0.5))
                .setWidth(this.app.config().textureWidth()*6)
                .setHeight(this.app.config().textureHeight())
                .setHiddenInput(true)
                .setState("login")
                .setTextAlign("left")
                .setText("Password"));

        this.app.addInterfaceObject(new InterfaceCheckBox()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2- (this.app.config().textureWidth()*3)*this.app.config().textureScale())
                .setY((this.app.config().interfaceHeight()/2 + this.app.config().textureHeight() *2* this.app.config().textureScale()-14))
                .setWidth(this.app.config().textureWidth()*2)
                .setHeight(this.app.config().textureHeight())
                .setState("login")
                .setShowOnHover(false)
                .setClickEvent(()-> System.out.println("toggled")));


        this.app.addInterfaceObject(new InterfaceButton()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2-this.app.config().textureWidth()*3*this.app.config().textureScale())
                .setY((this.app.config().interfaceHeight()/2 + this.app.config().textureHeight()*3 * this.app.config().textureScale()))
                .setWidth(this.app.config().textureWidth()*3)
                .setHeight(this.app.config().textureHeight())
                .setText("Login")
                .setState("login")
                .setHoverBoxCurve(22)
                .setClickEvent(()-> this.app.setCurrentState("game")));

        //button two
        this.app.addInterfaceObject(new InterfaceButton()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2+4)
                .setY((this.app.config().interfaceHeight()/2 + this.app.config().textureHeight()*3 * this.app.config().textureScale()))
                .setWidth(this.app.config().textureWidth()*3)
                .setText("Register")
                .setState("login")
                .setHeight(this.app.config().textureHeight())
                .setHoverBoxCurve(22)
                .setClickEvent(()-> {
                    try {
                        Desktop.getDesktop().browse(java.net.URI.create("http://codecrunchers.io/register"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        //__ START RENDER
        g.setColor(Color.white);

        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
        g.setFont(newFont);
        g.setColor(Color.black);

        g.drawImage(app.texture().logo(),0,64,null);

        g.drawString("Keep me logged in",this.app.config().interfaceWidth()/2-55,this.app.config().interfaceHeight()/2 + this.app.config().textureHeight() *2* this.app.config().textureScale()+24);



    }
}
