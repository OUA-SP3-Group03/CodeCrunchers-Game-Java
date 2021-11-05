package io.codecrunchers.engine.core;

import io.codecrunchers.engine.facades.App;

import java.awt.*;

public abstract class Provider {

    protected boolean booted = false;

    public abstract void boot(App app);
    public abstract boolean performTick();
    public abstract boolean performRender();

    public abstract void render(Graphics g);
    public abstract void tick();

    public boolean isBooted() {
        return this.booted;
    }
}
