package actors;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Alien {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private boolean active; 
    private BufferedImage image;
    private int initialY; 
    public Alien(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.active = true; 
        this.initialY = y; // Store the initial Y position

        try {
            image = ImageIO.read(new File("src/assets/elienAndroid.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
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
    
    public void moveForward() {
        y += speed;
        if (y > 700) { 	
            y = initialY; 
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void draw(Graphics g) {
        if (image != null && isActive()) { 
            g.drawImage(image, x, y, width, height, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
