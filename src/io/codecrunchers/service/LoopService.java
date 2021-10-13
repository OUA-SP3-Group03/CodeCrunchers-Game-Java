package io.codecrunchers.service;

import io.codecrunchers.providers.LoopServiceProvider;

public class LoopService implements Runnable{

    private final LoopServiceProvider loopServiceProvider;
    private Thread thread;
    private boolean showFPS = false;

    public LoopService(LoopServiceProvider loopServiceProvider){
        this.loopServiceProvider = loopServiceProvider;
    }

    @Override
    public void run() {

        //local vars
        int fps = 60;
        double timePerTick = 1000000000 /fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(this.loopServiceProvider.getRunningStatus()){
            now = System.nanoTime();
            delta+=(now-lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;


            if(delta >= 1){

                this.loopServiceProvider.getApplication().render();
                this.loopServiceProvider.getApplication().tick();

                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                if(this.showFPS) {
                    System.out.println("fps: " + ticks);
                }
                ticks = 0;
                timer = 0;
            }
        }
    this.stop();
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

    public void setShowFPS(boolean value){
        this.showFPS = value;
    }

}
