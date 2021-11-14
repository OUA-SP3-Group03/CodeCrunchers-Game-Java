package io.codecrunchers.service;

import io.codecrunchers.facades.App;

import javax.sound.sampled.*;
import java.io.File;

public class AudioService {

    private Clip clip;

    public AudioService(String path, float volume, App app){
        app.debug().increaseServiceCount();

        try{
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(path));
            AudioFormat format = stream.getFormat();

            if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(),
                        format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2,
                        format.getFrameRate(), true);
                stream = AudioSystem.getAudioInputStream(format, stream);
            }

            DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat(),
                    ((int) stream.getFrameLength() * format.getFrameSize()));

            clip = (Clip) AudioSystem.getLine(info);

            clip.open(stream);

            //Volume control
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void reset(){
        this.clip.setMicrosecondPosition(0);
    }

    public void play(){
        this.clip.start();
    }

    public void loop(){
        this.clip.loop(5);
    }

    public void stop(){
        this.clip.close();
    }
}
