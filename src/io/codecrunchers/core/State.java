package io.codecrunchers.core;

import io.codecrunchers.facades.App;

import java.awt.*;

public abstract class State {

    public abstract void boot(App app);

    public abstract void tick();
    public abstract void render(Graphics g);

}
