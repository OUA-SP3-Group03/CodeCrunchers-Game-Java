package io.codecrunchers.facades;

import io.codecrunchers.core.Kernel;
import io.codecrunchers.providers.LevelServiceProvider;

public class Level {
    public final Kernel kernel;

    public Level(Kernel kernel){
        this.kernel = kernel;
    }

    public void generate(){
        ((LevelServiceProvider)this.kernel.getServiceProvider("levelgenerator")).generateWorld();
    }


    public int width(){
        return ((LevelServiceProvider)this.kernel.getServiceProvider("levelgenerator")).getWorldWidth();
    }

    public int height(){
        return ((LevelServiceProvider)this.kernel.getServiceProvider("levelgenerator")).getWorldHeight();
    }

    public int[] tiles(){
        return ((LevelServiceProvider)this.kernel.getServiceProvider("levelgenerator")).getWorld();
    }
}
