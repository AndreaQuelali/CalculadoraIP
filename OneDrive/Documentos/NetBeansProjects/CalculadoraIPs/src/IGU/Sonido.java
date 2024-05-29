package IGU;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class Sonido {
    Clip clip;
    
    public void reproducirSonido(String son) {
    try {
        URL url = getClass().getResource("/sonidos/"+son);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        e.printStackTrace();
    }
    }
    public void pararSonido() {
        if (clip.isRunning() && clip != null) {
            clip.stop();
        }
    }
}

