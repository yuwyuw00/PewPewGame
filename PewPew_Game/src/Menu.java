import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {
    private static JFrame frmPewPewMenu;

    private static void setupStartButton(Container pane) {
        JButton button = new JButton();
        button.setBounds(292, 443, 400, 100);

        button.setPreferredSize(new Dimension(GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT / 5));

        button.setIcon(new ImageIcon("/assets/start.jpg"));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmPewPewMenu.dispose(); 
               
                GameFrame gameFrame = new GameFrame();
                
                gameFrame.setVisible(true); 
            }
        });


        frmPewPewMenu.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("By Brandon, Natalie, and Regine");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Press Start 2P", Font.PLAIN, 11));
        lblNewLabel.setBounds(-63, 632, 500, 16);
        frmPewPewMenu.getContentPane().add(lblNewLabel);

        pane.add(button);
    }
    private static void setupBottomButtn(Container pane) {
        JButton instruction = new JButton();
        instruction.setBounds(295, 551, 400, 70);

        instruction.setPreferredSize(new Dimension(GameManager.SCREEN_WIDTH / 2, GameManager.SCREEN_HEIGHT / 5));

        instruction.setIcon(new ImageIcon("/assets/instruction.jpg"));
        instruction.setBorderPainted(false);
        instruction.setContentAreaFilled(false);
        instruction.setFocusPainted(false);
        instruction.setOpaque(false);

        instruction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frmPewPewMenu, "Instructions", true);
                dialog.getContentPane().setBackground(new Color(0, 0, 20));
                dialog.setSize(740, 550);
                dialog.setLocationRelativeTo(frmPewPewMenu);

                JLabel label = new JLabel("<html><div style='text-align: center;'>Welcome to Pew Pew Space Game!<br>"
                        + "Left and Right arrow keys to move your spaceship.<br>"
                        + "Space to shoot enemies and defend your base!<br>"
                        + "You have 9 lives to survive the alien androids.<br>"
                        + "And when those disgusting aliens take-over your bound...<br>"
                        + "IT IS GAME OVER!<br>"
                        + "So, Good luck Captain!</div></html>");
                label.setForeground(Color.WHITE);
                Font font = new Font("Press Start 2P", Font.PLAIN, 15);
                label.setFont(font);
                dialog.getContentPane().add(label, BorderLayout.CENTER);

                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });

                dialog.setVisible(true);
            }
        });
        pane.add(instruction);
    }
    private static void setupCenterImg(Container pane) {
        JLabel label = new JLabel();
        label.setForeground(new Color(255, 255, 255));
        label.setBounds(-9, 0, 1000, 700);
        label.setPreferredSize(new Dimension(GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT));
        label.setIcon(new ImageIcon("/assets/menu.jpg"));
        pane.add(label);
    }

    private static void addComponents(Container pane) {
        setupStartButton(pane);
        setupBottomButtn(pane);
        setupCenterImg(pane);
    }

    private static void createFrame() {
        frmPewPewMenu = new JFrame("MENU");
        frmPewPewMenu.setTitle("PEW PEW MENU");
        frmPewPewMenu.setIconImage(Toolkit.getDefaultToolkit().getImage("/assets/elienAndroid.png"));
        frmPewPewMenu.setResizable(false);
        frmPewPewMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponents(frmPewPewMenu.getContentPane());
        
            
        frmPewPewMenu.setPreferredSize(new Dimension(GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT));
        frmPewPewMenu.pack();
        frmPewPewMenu.setVisible(true);
    }

    public Menu() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createFrame();
            }
        });
    }
}