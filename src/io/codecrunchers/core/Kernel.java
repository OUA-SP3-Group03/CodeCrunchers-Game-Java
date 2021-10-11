package io.codecrunchers.core;

import io.codecrunchers.facades.App;
import io.codecrunchers.providers.DisplayServiceProvider;
import io.codecrunchers.providers.HttpServiceProvider;
import io.codecrunchers.providers.InterfaceServiceProvider;
import io.codecrunchers.providers.LoopServiceProvider;


import java.awt.*;
import java.util.HashMap;

public class Kernel {

    private final HashMap<String, Provider> providers;
    private final Application application;

    public Kernel() {

        //create providers hashmap and set it to a new hashmap
        this.providers = new HashMap<String, Provider>();

        //register all our provider instances within the hashmap
        this.providers.put("display", new DisplayServiceProvider());
        this.providers.put("loop", new LoopServiceProvider());
        this.providers.put("http", new HttpServiceProvider());
        this.providers.put("interface", new InterfaceServiceProvider());

        this.bootProviders();

        //create the app facade
        App app = new App(this);

        //create our application singleton instance
        this.application = new Application(app);
    }

    public void bootProviders(){
        //run boot method on all service providers
        System.out.println(this.providers.size()+" Service Providers Registered");
        for(Provider provider : this.providers.values()){
            //display what provider is being booted in console
            System.out.println("Booting : "+provider);
            //boot provider
            provider.boot();
        }
    }

    public void loadConfig(){
        //TODO load config
    }

    public Application getApplication(){
        return this.application;
    }

    public Provider getServiceProvider(String provider){
        return this.providers.get(provider);
    }

    public HashMap<String, Provider> getAllServiceProviders(){
        return this.providers;
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
