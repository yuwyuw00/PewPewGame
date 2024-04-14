import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Intro extends JPanel implements Runnable {
    JFrame window = new JFrame();
    private String imagePath = "/assets/load.jpg";
    private String audio8bit = "/assets/RickRoll.wav";
    private int wait = 6000; // As wait

    public Intro() {
        playAudio();

        this.setPreferredSize(new Dimension(GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT));
        window.add(this);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIconImage(new ImageIcon(getClass().getResource("/assets/elienAndroid.png")).getImage());
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
    }

    public void playAudio() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(audio8bit));

            // Get the format of the audio data
            AudioFormat format = audioStream.getFormat();

            // Create a data line to play back the audio data
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);

            // Open the line and start playing the audio data
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); 


        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
        Image image = imageIcon.getImage();
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(wait);
            window.dispose();
            // temporarily for MENU CLASS
            Menu menu = new Menu();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
