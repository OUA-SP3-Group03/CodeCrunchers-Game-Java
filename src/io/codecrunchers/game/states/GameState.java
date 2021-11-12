package io.codecrunchers.game.states;

import io.codecrunchers.core.ASCII;
import io.codecrunchers.game.entities.Entity;
import io.codecrunchers.game.entities.creatures.Player;
import io.codecrunchers.facades.App;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class GameState extends State {

    private App app;
    Entity player;

    Clip musicClip;

    @Override
    public void boot(App app) {
        this.app = app;

        this.app.level().generate();
        this.app.setWorldWidth(this.app.level().width());
        this.app.setWorldHeight(this.app.level().height());
        this.app.setWorld(this.app.level().tiles());

        player = new Player(64, 0, 64, 64, this.app);
        this.app.registerEntity(player);

        //Load BGM
        try {
            backgroundMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        if(this.app.keyPressed().containsKey(ASCII.escape)){
            this.app.keyPressed().remove(ASCII.escape);
            this.app.level().generate();
            this.app.setWorldWidth(this.app.level().width());
            this.app.setWorldHeight(this.app.level().height());
            this.app.setWorld(this.app.level().tiles());
        }

        this.app.getCamera().centerOnEntity(player);

        //Start BGM
        musicClip.loop(5);
    }

    public void render(Graphics g) {

    }

    public void backgroundMusic() throws Exception {
        AudioInputStream stream = AudioSystem.getAudioInputStream(new File(getRandomMusic()));
        AudioFormat format = stream.getFormat();

        if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
            format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(),
                    format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2,
                    format.getFrameRate(), true); // big endian
            stream = AudioSystem.getAudioInputStream(format, stream);
        }

        DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat(),
                ((int) stream.getFrameLength() * format.getFrameSize()));
        musicClip = (Clip) AudioSystem.getLine(info);


        musicClip.open(stream);

        //Volume control
        FloatControl volume = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-25f);
    }

    //Return a random background music track
    public String getRandomMusic() {
        String[] music = {"res/Music1.wav", "res/Music2.wav", "res/Music3.wav", "res/Music3.wav"};
        Random rng = new Random();
        int selection;

        selection = rng.nextInt(music.length);

        return music[selection];
    }

}
