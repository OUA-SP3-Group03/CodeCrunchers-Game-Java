package io.codecrunchers.service;

import io.codecrunchers.facades.App;
import io.codecrunchers.providers.LoopServiceProvider;

public class LoopService implements Runnable{

    private final LoopServiceProvider loopServiceProvider;
    private Thread thread;
    private int realFPS;
    private int realTPS;
    private int usedMemory;
    private int mb = 1024*1024;

    public LoopService(LoopServiceProvider loopServiceProvider, App app){
        app.debug().increaseServiceCount();
        this.loopServiceProvider = loopServiceProvider;
    }

    @Override
    public void run() {

        //set FPS and TPS target
        int fpsTarget = this.loopServiceProvider.getFPSTarget();
        int tpsTarget = this.loopServiceProvider.getTPSTarget();

        //calculate the required time per tick and time per render
        double timePerRender = 1000000000 /fpsTarget;
        double timePerTick = 1000000000 /tpsTarget;

        //time calculation variables
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        //real FPS and TPS
        int fpsReal = 0;
        int tpsReal = 0;

        //double delta time for render and ticks
        double renderDelta = 0;
        double ticksDelta = 0;

        while(this.loopServiceProvider.getRunningStatus()){

            now = System.nanoTime();

            ticksDelta+=(now-lastTime) / timePerTick;
            renderDelta+=(now-lastTime) / timePerRender;

            timer += now - lastTime;

            lastTime = now;

            //process the ticks
            if(ticksDelta >= 1){
                this.loopServiceProvider.getApplication().tick();
                tpsReal++;
                ticksDelta--;
            }

            //process the render
            if(renderDelta >= 1){
                this.loopServiceProvider.getApplication().render();
                fpsReal++;
                renderDelta--;
            }

            if(timer >= 1000000000){
                    this.realFPS = fpsReal;
                    this.realTPS = tpsReal;
                    this.usedMemory = (int) (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/this.mb;
                tpsReal=0;
                fpsReal=0;
                timer = 0;
            }
        }
        this.stop();
    }

    public int getCurrentFPS(){
        return  this.realFPS;
    }

    public int getCurrentTPS(){
        return this.realTPS;
    }

    public int getUsedMemory(){
        return this.usedMemory;
    }

    public synchronized void start(){
        if(this.loopServiceProvider.getRunningStatus()){
            return;
        }else{
            this.loopServiceProvider.setRunningStatus(true);
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    public synchronized void stop(){
        if(!this.loopServiceProvider.getRunningStatus()){
            return;
        }
        else{
            try{
                this.thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}