import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private GamePanel gamePanel;

    public Frame() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Frame.class.getResource("/assets/elienAndroid.png")));
    	setResizable(false);
        setTitle("Pew Pew Space Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT));

        // Create game panel
        gamePanel = new GamePanel();
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        pack();
        setLocation(0, 0); 
        setVisible(true);
    }
}
