package io.codecrunchers.facades;

import io.codecrunchers.core.Kernel;
import io.codecrunchers.providers.DisplayServiceProvider;
import io.codecrunchers.providers.KeyboardServiceProvider;
import io.codecrunchers.providers.MouseServiceProvider;

import javax.swing.*;

public class Callback {

    private Kernel kernel;

    public Callback(Kernel kernel) {
        this.kernel = kernel;
    }

    public void setMouseListener(MouseServiceProvider mouse) {
        ((DisplayServiceProvider)this.kernel.getServiceProvider("display")).setMouseListener(mouse);
    }

    public void setKeyListener(KeyboardServiceProvider keys) {
        ((DisplayServiceProvider)this.kernel.getServiceProvider("display")).setKeyListener(keys);
    }

}
