package io.codecrunchers.core;

import java.awt.*;

public abstract class Provider {

    public abstract void boot();
    public abstract boolean performTick();
    public abstract boolean performRender();

    public abstract void render(Graphics g);
    public abstract void tick();
}
