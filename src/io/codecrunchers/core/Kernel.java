package io.codecrunchers.core;

import io.codecrunchers.facades.App;
import io.codecrunchers.game.gfx.Camera;
import io.codecrunchers.providers.*;


import java.awt.*;
import java.util.HashMap;

public class Kernel {

    private final HashMap<String, Provider> providers;
    private final Application application;
    private final App app;
    private final Config config;
    private boolean booted = false;
    private final Camera camera;

    //debug
    private boolean showDebug;
    private int serviceCount;

    public Kernel() {
        //create the config
        this.config = new Config();

        //set show debug to false by default
        this.showDebug = false;

        //create the app facade
        this.app = new App(this);

        //create providers hashmap and set it to a new hashmap
        this.providers = new HashMap<>();

        //register all our provider instances within the hashmap
        this.providers.put("display", new DisplayServiceProvider());
        this.providers.put("loop", new LoopServiceProvider());
        this.providers.put("http", new HttpServiceProvider());
        this.providers.put("interface", new InterfaceServiceProvider());
        this.providers.put("asset", new AssetServiceProvider());
        this.providers.put("levelgenerator",new LevelServiceProvider());
        this.providers.put("keyboard", new KeyboardServiceProvider());
        this.providers.put("mouse", new MouseServiceProvider());
        this.providers.put("states", new StatesServiceProvider());
        this.providers.put("tile", new TileServiceProvider());
        this.providers.put("entity", new EntityServiceProvider());
        this.providers.put("audio", new AudioServiceProvider());

        //_________ REGISTER YOUR NEW PROVIDER HERE ___________\\

        //this.provider.put("provider", new YourServiceProvider());

        //______________________________________________________\\
        this.camera = new Camera(this.app, 0, 0);

        //boot all service providers
        this.bootProviders();

        //create our application singleton instance
        this.application = new Application();

        //run the after boot application method
        if(this.booted){
            this.application.onBootCompletion(this.app);
        }else{
            System.out.println("Application failed to boot");
        }

    }

    public void bootProviders(){

        //Boot display first to avoid any boot errors
        DisplayServiceProvider display = (DisplayServiceProvider) this.providers.get("display");
        display.boot(this.app);

        //Boot the Asset Service provider second to avoid errors
        AssetServiceProvider asset = (AssetServiceProvider) this.providers.get("asset");
        asset.boot(this.app);

        //Show Loading Screen
        Graphics g = this.app.canvas().getGraphics();
        g.drawImage(this.app.texture().logo(),0,this.config.interfaceHeight/2-128,null);
        g.setFont(new Font(g.getFont().getName(), Font.BOLD,60));
        g.drawString("Loading",this.config.interfaceWidth/2-128,this.config.interfaceHeight/2+64);

        //run boot method on all service providers
        for(Provider provider : this.providers.values()){
            //display what provider is being booted in console

            //if provider isn't already booted
            if(!provider.isBooted()) {
                //boot provider
                provider.boot(this.app);
            }
        }

        g.dispose();

        this.booted = true;

    }

    public Config getConfig(){
        return this.config;
    }

    public boolean getBootedStatus(){
        return this.booted;
    }

    public Application getApplication(){
        return this.application;
    }

    public Provider getServiceProvider(String provider){
        return this.providers.get(provider);
    }

    public void render(Graphics g){
        for(Provider provider: this.providers.values()){
            if(provider.performRender()){
                provider.render(g);
            }
        }
    }

    public void tick(){
        for (Provider provider: this.providers.values()){
            if(provider.performTick()){
                provider.tick();
            }
        }
    }

    public boolean showDebug(){
        return this.showDebug;
    }

    public void setShowDebug(boolean value){
        this.showDebug = value;
    }

    public int getProviderCount(){
        return this.providers.size();
    }

    public int getServiceCount(){
        return this.serviceCount;
    }

    public void increaseServiceCount(){
        this.serviceCount++;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public boolean isBooted(){
        return this.booted;
    }
}
