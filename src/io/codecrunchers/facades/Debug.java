package io.codecrunchers.facades;

import io.codecrunchers.core.Kernel;
import io.codecrunchers.providers.EntityServiceProvider;

import java.security.cert.CertificateNotYetValidException;

public class Debug{
    private final Kernel kernel;

    public Debug(Kernel kernel){
        this.kernel = kernel;
    }

    public int providerCount(){
        return this.kernel.getProviderCount();
    }
    public int serviceCount(){
        return this.kernel.getServiceCount();
    }

    public int entityCount(){
        return ((EntityServiceProvider)this.kernel.getServiceProvider("entity")).getEntities().size();
    }

    public void increaseServiceCount(){
        this.kernel.increaseServiceCount();
    }
}
