package io.codecrunchers.providers;

import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;

import java.awt.*;

public class InterfaceServiceProvider extends Provider {

    @Override
    public void boot(App app) {
        //TODO add service provider code
    }

    @Override
    public boolean performTick() {
        return true;
    }

    @Override
    public boolean performRender() {
        return true;
    }

    @Override
    public void render(Graphics g) {
        g.drawString("Hello from the InterfaceServiceProvider",100,200);
    }

    @Override
    public void tick() {
    }
}
