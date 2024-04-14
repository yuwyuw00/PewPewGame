package actors;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle; // Import Rectangle class
import javax.swing.ImageIcon;

public class AlienBullet {
    private int x;
    private int y;
    private int speed;
    private int width;
    private int height;
    private boolean active;
    private Image image;

    public AlienBullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 5; 
        this.width = 55; 
        this.height = 55; 
        this.active = true;
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/BULLETa.png"));
        image = icon.getImage();

    }

    public void move() {
        y += speed; 
        if (y > 700) {
            active = false; 
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null); 
    }

    public boolean isActive() {
        return active;
    }

   
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
