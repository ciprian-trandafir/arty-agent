package classes;

import interfaces.AdvancedMediaPlayer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class WavPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        //do nothing
    }

    @Override
    public void playMp4(String fileName) {
        //do nothing
    }

    @Override
    public void playWav(String fileName) {
        System.out.println("Playing wav file. Name: "+ fileName);
        new Thread(() -> {
			try {
				InputStream audioSrc = new FileInputStream(System.getProperty("user.dir") + "\\src\\sounds\\" + fileName + ".wav");
				InputStream bufferedIn = new BufferedInputStream(audioSrc);
				Clip clip = AudioSystem.getClip();
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
				clip.open(audioStream);
				clip.start();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}).start();
    }
}
