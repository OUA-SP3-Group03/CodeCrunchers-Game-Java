package io.codecrunchers.providers;

import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;
import io.codecrunchers.service.LevelGeneratorService;

import java.awt.*;

public class LevelGeneratorServiceProvider extends Provider {

    private LevelGeneratorService levelGenerator;

    @Override
    public void boot(App app) {


        this.levelGenerator = new LevelGeneratorService();


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

    public void generateWorld(){
        this.levelGenerator.generateWorld();
    }

    public void writeWorldOnFile(){
        this.levelGenerator.writeWorldOnFile();
    }

    public void chooseWorld(){
        this.levelGenerator.chooseWorld();
    }

}
