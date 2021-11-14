package io.codecrunchers.game.states;

import io.codecrunchers.game.gui.InterfaceAlert;
import io.codecrunchers.game.gui.InterfaceButton;
import io.codecrunchers.game.gui.InterfaceInput;
import io.codecrunchers.facades.App;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;

public class LoginState extends State {
    private App app;
    private InterfaceInput emailInput;
    private InterfaceInput passwordInput;
    private InterfaceAlert invalidLoginAlert;


    @Override
    public void boot(App app) {
        this.app = app;

        this.app.addInterfaceObject(this.invalidLoginAlert = (InterfaceAlert) new InterfaceAlert()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*3)*this.app.config().textureScale()+64)
                .setY(this.app.config().interfaceHeight()/2 - this.app.config().textureHeight()*this.app.config().textureScale() - 96)
                .setWidth(this.app.config().textureWidth()*4)
                .setHeight(this.app.config().textureHeight())
                .setText("Placeholder!")
                .setState("login-null")
                .setShowOnHover(false)
                .setTextColor(Color.white));

        //button one
        this.app.addInterfaceObject(this.emailInput = (InterfaceInput) new InterfaceInput()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*3)*this.app.config().textureScale())
                .setY(this.app.config().interfaceHeight()/2 - this.app.config().textureHeight()*this.app.config().textureScale())
                .setWidth(this.app.config().textureWidth()*6)
                .setHeight(this.app.config().textureHeight())
                .setState("login")
                .setTextAlign("left")
                .setText("Email"));

        this.app.addInterfaceObject(this.passwordInput = (InterfaceInput) new InterfaceInput()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2-(this.app.config().textureWidth()*3)*this.app.config().textureScale())
                .setY((int) (this.app.config().interfaceHeight()/2+ this.app.config().textureHeight()*this.app.config().textureScale()*0.5))
                .setWidth(this.app.config().textureWidth()*6)
                .setHeight(this.app.config().textureHeight())
                .setHiddenInput(true)
                .setState("login")
                .setTextAlign("left")
                .setText("Password"));


        this.app.addInterfaceObject(new InterfaceButton()
                .setAppFacade(this.app)
                .setX(this.app.config().interfaceWidth()/2-this.app.config().textureWidth()*3*this.app.config().textureScale())
                .setY((this.app.config().interfaceHeight()/2 + this.app.config().textureHeight()*3 * this.app.config().textureScale()))
                .setWidth(this.app.config().textureWidth()*3)
                .setHeight(this.app.config().textureHeight())
                .setText("Login")
                .setState("login")
                .setHoverBoxCurve(22)
                .setClickEvent(this::login));

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
                .setClickEvent(this::register));
    }

    public void register(){
            this.app.playAudioClip("ui-click");
            this.app.resetAudioClip("ui-click");
            try {
                Desktop.getDesktop().browse(java.net.URI.create(this.app.config().registerUrl()));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void login(){

        if(this.emailInput.getInput().isBlank() || this.passwordInput.getInput().isBlank()){
            this.app.playAudioClip("ui-fail");
            this.app.resetAudioClip("ui-fail");
            this.invalidLoginAlert.setText("Please enter your email and password");
            this.invalidLoginAlert.setState("login");

        }

        if(!this.emailInput.getInput().isBlank() && !this.passwordInput.getInput().isBlank()) {

            if(!this.app.authLogin(this.emailInput.getInput(), this.passwordInput.getInput())){
                this.app.playAudioClip("ui-fail");
                this.app.resetAudioClip("ui-fail");
                this.invalidLoginAlert.setText("Invalid email or password");
                this.invalidLoginAlert.setState("login");

            }else{
                this.app.playAudioClip("ui-click");
                this.app.resetAudioClip("ui-click");
                this.app.setCurrentState("menu");
            }
        }

    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        //__ START RENDER
        g.setColor(Color.white);

        g.drawImage(app.texture().logo(),0,64,null);


    }
}
