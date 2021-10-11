package io.codecrunchers.core;

import io.codecrunchers.facades.App;

import java.awt.*;

public abstract class Provider {

    public abstract void boot(App app);
    public abstract boolean performTick();
    public abstract boolean performRender();

    public abstract void render(Graphics g);
    public abstract void tick();
}
