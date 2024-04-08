package actors;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle; // Import Rectangle class
import javax.swing.ImageIcon;

public class PlayerBullet {
    private int x;
    private int y;
    private int speed;
    private int width;
    private int height;
    private boolean active;
    private Image image;

    public PlayerBullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 5; 
        this.width = 60;
        this.height = 60;
        this.active = true;
        
        ImageIcon icon = new ImageIcon("/assets/BULLET.png");
        image = icon.getImage();
    }

    public void move() {
        y -= speed; 	
        if (y < 0) {
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
    
   
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
