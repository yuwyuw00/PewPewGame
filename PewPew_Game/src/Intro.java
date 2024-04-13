import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.swing.*;
import java.awt.*;

public class Intro extends JPanel implements Runnable {
	JFrame window = new JFrame();
	private String imagePath = "src/assets/load.jpg";
	private String audio8bit = "src/assets/RickRoll.wav";
	private int wait = 6000; //As wait
	
	public Intro () {
		
		playAudio();//method PLAY SOUNDS
		
		this.setPreferredSize(new Dimension(GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT));
		window.add(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setIconImage(Toolkit.getDefaultToolkit().getImage("src/assets/elienAndroid.png"));
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
	}
	
	public void playAudio() {
		try {
		File audioPath = new File(audio8bit);
		
		GameManager.stream = AudioSystem.getAudioInputStream(audioPath);
		
		GameManager.clip = AudioSystem.getClip();
		
		GameManager.clip.open(GameManager.stream);
		
		GameManager.clip.start();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		
	}

	public void paint(Graphics g) {
		g.drawImage(new ImageIcon(imagePath).getImage(), 0,0,null);
		
	}

	@Override
	public void run() {
		try {
			Thread.sleep(wait);
			window.dispose();
			//temporarily for  MENU CLASS
			Menu menu = new Menu();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
