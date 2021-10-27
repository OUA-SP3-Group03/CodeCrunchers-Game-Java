package io.codecrunchers.core;

import io.codecrunchers.facades.App;
import io.codecrunchers.providers.*;


import java.awt.*;
import java.util.HashMap;

public class Kernel {

    private final HashMap<String, Provider> providers;
    private final Application application;
    private final App app;
    private final Config config;
    private boolean booted = false;

    public Kernel() {
        //create the config
        this.config = new Config();

        //create the app facade
        this.app = new App(this);

        //create providers hashmap and set it to a new hashmap
        this.providers = new HashMap<String, Provider>();

        //register all our provider instances within the hashmap
        this.providers.put("display", new DisplayServiceProvider());
        this.providers.put("loop", new LoopServiceProvider());
        this.providers.put("http", new HttpServiceProvider());
        this.providers.put("interface", new InterfaceServiceProvider());
        this.providers.put("asset", new AssetServiceProvider());
        this.providers.put("levelgenerator",new LevelGeneratorServiceProvider());
        this.providers.put("keyboard", new KeyboardServiceProvider());
        this.providers.put("mouse", new MouseServiceProvider());
        this.providers.put("states", new StatesServiceProvider());
        this.providers.put("tile", new TileServiceProvider());
        this.providers.put("entity", new EntityServiceProvider());

        //_________ REGISTER YOUR NEW PROVIDER HERE ___________\\

        //this.provider.put("provider", new YourServiceProvider());

        //______________________________________________________\\

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
        //Display how many service providers are registered
        System.out.println(this.providers.size()+" Service Providers Registered");

        //Boot display first to avoid any boot errors
        this.providers.get("display").boot(this.app);
        this.providers.get("asset").boot(this.app);

        //run boot method on all service providers
        for(Provider provider : this.providers.values()){
            //display what provider is being booted in console
            System.out.println("Booting : "+provider);
            //if provider isn't already booted
            if(!provider.isBooted()) {
                //boot provider
                provider.boot(this.app);
            }
        }

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



}
