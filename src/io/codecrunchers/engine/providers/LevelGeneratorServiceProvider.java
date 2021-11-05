package io.codecrunchers.engine.providers;

import io.codecrunchers.engine.core.Provider;
import io.codecrunchers.engine.facades.App;
import io.codecrunchers.engine.service.LevelGeneratorService;

import java.awt.*;

public class LevelGeneratorServiceProvider extends Provider {

    private LevelGeneratorService levelGenerator;
    private App app;
    @Override
    public void boot(App app) {
        this.app=app;

        this.levelGenerator = new LevelGeneratorService(this);


        this.booted = true;

    }

    @Override
    public boolean performTick() {
        return false;
    }

    @Override
    public boolean performRender() {
        return false;
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void tick() {

    }

    public String world0path(){return this.app.config().world0path(); }

    public int getMaxRooms(){ return this.app.config().maxRooms(); }

}
