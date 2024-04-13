package actors;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Player {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loadImage();
    }

    private void loadImage() {
        ImageIcon icon = new ImageIcon("src/assets/spaceship.png");
        image = icon.getImage();
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

    public void moveLeft() {
        x -= 4; // Adjust speed as needed
    }

    public void moveRight() {
        x += 4; // Adjust speed as needed
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
