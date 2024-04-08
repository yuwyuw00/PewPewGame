import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import actors.*;

public class GamePanel extends JPanel implements ActionListener {
    private Player player;
    private ArrayList<PlayerBullet> bullets;
    private ArrayList<AlienBullet> alienBullets;
    private Alien[][] aliens;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private Timer timer;
    private Image backgroundImage;

    private int alienShootCounter = 0; 
    private static final int ALIEN_SHOOT_FREQUENCY = 45;
    private int lives = 9; 
    private boolean gameOver = false;
    private boolean allAliensRemoved = false;
    private int moveDirection = 1;
    private int moveSpeed = 1; 

    public GamePanel() {
        setPreferredSize(new Dimension(GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT));

        backgroundImage = loadImage("/assets/bgmain.png");

        player = new Player(GameManager.SCREEN_WIDTH / 2 - 25, GameManager.SCREEN_HEIGHT - 130, 60, 60);

        bullets = new ArrayList<>();
        alienBullets = new ArrayList<>();

        int rows = 4;
        int cols = 8;
        int alienWidth = 40;
        int alienHeight = 40;
        int aliensTotalWidth = cols * (alienWidth + 30);
        int initialX = (GameManager.SCREEN_WIDTH - aliensTotalWidth) / 2;
        aliens = new Alien[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = initialX + j * (alienWidth + 30);
                int y = 40 + i * (alienHeight - 160);
                aliens[i][j] = new Alien(x, y, alienWidth, alienHeight, 1); 
            }
        }

        timer = new Timer(5, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    leftPressed = true;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    rightPressed = true;
                } else if (keyCode == KeyEvent.VK_SPACE) {
                    if (!gameOver) {
                        shoot();
                    } else {
                        resetGame();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    leftPressed = false;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    rightPressed = false;
                }
            }
        });
        setFocusable(true);
    }

    private void resetGame() {
        lives = 9;
        gameOver = false;
        allAliensRemoved = false;
        player = new Player(GameManager.SCREEN_WIDTH / 2 - 25, GameManager.SCREEN_HEIGHT - 130, 60, 60);
        bullets.clear();
        alienBullets.clear();
        initializeAliens();
    }

    private void initializeAliens() {
        int rows = 4;
        int cols = 8;
        int alienWidth = 40;
        int alienHeight = 40;
        int aliensTotalWidth = cols * (alienWidth + 30);
        int initialX = (GameManager.SCREEN_WIDTH - aliensTotalWidth) / 2;
        aliens = new Alien[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = initialX + j * (alienWidth + 30);
                int y = 40 + i * (alienHeight - 160);
                aliens[i][j] = new Alien(x, y, alienWidth, alienHeight, 1); 
            }
        }
    }

    private void shoot() {
        int bulletX = player.getX() + player.getWidth() / 2 - 27;
        int bulletY = player.getY();
        bullets.add(new PlayerBullet(bulletX, bulletY));
    }

    private void alienShoot() {
        Random rand = new Random();
        int row = rand.nextInt(aliens.length);
        int col = rand.nextInt(aliens[row].length);
        Alien alien = aliens[row][col];
        int bulletX = alien.getX() + alien.getWidth() / 2 - 14;
        int bulletY = alien.getY() + alien.getHeight();
        alienBullets.add(new AlienBullet(bulletX, bulletY));
    }

    private Image loadImage(String fileName) {
        Image image = null;
        try {
            image = new ImageIcon(fileName).getImage();
        } catch (Exception e) {
            System.err.println("Failed to load image: " + fileName);
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        player.draw(g);
        for (int i = 0; i < aliens.length; i++) {
            for (int j = 0; j < aliens[i].length; j++) {
                aliens[i][j].draw(g);
            }
        }
        for (PlayerBullet bullet : bullets) {
            bullet.draw(g);
        }
        for (AlienBullet bullet : alienBullets) {
            bullet.draw(g);
        }
        // Draw lives counter
        g.setColor(Color.WHITE);
        g.setFont(new Font("Press Start 2P", Font.BOLD, 20));
        g.drawString("Lives: " + lives, 20, 30);
        // Game over message
        if (gameOver) {
            String gameOverText;
            String tryAgainText;
            if (allAliensRemoved && lives > 0) {
                gameOverText = "PP Space Defended";
                tryAgainText = "Press SPACE to try again";
            } else {
                gameOverText = "Game Over";
                tryAgainText = "Press SPACE to try again";
            }
            FontMetrics fontMetrics = g.getFontMetrics(new Font("Press Start 2P", Font.BOLD, 50));
            int gameOverWidth = fontMetrics.stringWidth(gameOverText);
          //  int tryAgainWidth = fontMetrics.stringWidth(tryAgainText);
            g.setFont(new Font("Press Start 2P", Font.BOLD, 50));
            g.drawString(gameOverText, (getWidth() - gameOverWidth) / 2, getHeight() / 2);
            g.setFont(new Font("Press Start 2P", Font.BOLD, 30));
            g.drawString(tryAgainText, (getWidth() - 680) /2, getHeight() / 2+50);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    private void updateGame() {
        if (!gameOver) {
            moveBullets();
            moveAlienBullets();
            movePlayer();
            moveAliens();
            checkPlayerBulletCollisions();
            checkAlienBulletCollisions();
            checkAlienShoot();
            checkPlayerAlienCollision();
            checkAllAliensRemoved(); // Check if all aliens are removed
            checkGameOver();
        }
    }

    private void moveBullets() {
        for (int i = bullets.size() - 1; i >= 0; i--) {
            PlayerBullet bullet = bullets.get(i);
            bullet.move();
            if (!bullet.isActive()) {
                bullets.remove(i);
            }
        }
    }

    private void moveAlienBullets() {
        for (int i = alienBullets.size() - 1; i >= 0; i--) {
            AlienBullet bullet = alienBullets.get(i);
            bullet.move();
            if (!bullet.isActive()) {
                alienBullets.remove(i);
            }
        }
    }

    private void movePlayer() {
        if (leftPressed && player.getX() > 0) {
            player.moveLeft();
        }
        if (rightPressed && player.getX() < getWidth() - player.getWidth()) {
            player.moveRight();
        }
    }

    private void moveAliens() {
        boolean changeDirection = false;
        for (int i = 0; i < aliens.length; i++) {
            for (int j = 0; j < aliens[i].length; j++) {
                aliens[i][j].moveForward();
                aliens[i][j].setX(aliens[i][j].getX() + (moveDirection * moveSpeed));
                // Check if alien edge
                if (aliens[i][j].getX() <= 0 || aliens[i][j].getX() + aliens[i][j].getWidth() >= getWidth()) {
                    changeDirection = true;
                }
            }
        }
        if (changeDirection) {
            // aliens downwards
            moveDirection *= -1;
            for (int i = 0; i < aliens.length; i++) {
                for (int j = 0; j < aliens[i].length; j++) {
                    aliens[i][j].moveForward();
                }
            }
        }
    }

    private void checkPlayerBulletCollisions() {
        for (int i = 0; i < bullets.size(); i++) {
            PlayerBullet bullet = bullets.get(i);
            Rectangle bulletBounds = bullet.getBounds();
            for (int j = 0; j < aliens.length; j++) {
                for (int k = 0; k < aliens[j].length; k++) {
                    Alien alien = aliens[j][k];
                    if (alien.isActive()) {
                        Rectangle alienBounds = alien.getBounds();
                        if (bulletBounds.intersects(alienBounds)) {
                            bullets.remove(i);
                            alien.setActive(false);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void checkAlienBulletCollisions() {
        Rectangle playerBounds = player.getBounds();
        for (int i = 0; i < alienBullets.size(); i++) {
            AlienBullet bullet = alienBullets.get(i);
            Rectangle bulletBounds = bullet.getBounds();
            if (bulletBounds.intersects(playerBounds)) {
                alienBullets.remove(i);
                lives--; 
                break;
            }
        }
    }

    private void checkAlienShoot() {
        alienShootCounter++;
        if (alienShootCounter >= ALIEN_SHOOT_FREQUENCY) {
            alienShoot();
            alienShootCounter = 0;
        }
    }

    private void checkPlayerAlienCollision() {
        Rectangle playerBounds = player.getBounds();
        for (int i = 0; i < aliens.length; i++) {
            for (int j = 0; j < aliens[i].length; j++) {
                Alien alien = aliens[i][j];
                if (alien.isActive()) {
                    Rectangle alienBounds = alien.getBounds();
                    if (playerBounds.intersects(alienBounds)) {
                        gameOver = true;
                        return;
                    }
                }
            }
        }
    }

    private void checkAllAliensRemoved() {
        boolean removed = true;
        for (int i = 0; i < aliens.length; i++) {
            for (int j = 0; j < aliens[i].length; j++) {
                if (aliens[i][j].isActive()) {
                    removed = false;
                    break;
                }
            }
        }
        allAliensRemoved = removed;
    }

    private void checkGameOver() {
        if (lives <= 0 || (allAliensRemoved && lives > 0)) {
            gameOver = true;
        }
    }
}
