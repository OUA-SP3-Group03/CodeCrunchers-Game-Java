package io.codecrunchers.engine.core;

import io.codecrunchers.engine.facades.App;

import java.awt.*;

public abstract class State {

    public abstract void boot(App app);

    public abstract void tick();
    public abstract void render(Graphics g);

}
