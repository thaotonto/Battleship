package gamemain;

import ui.MainPanel;


import javax.swing.*;


/**
 * Created by tonto on 5/10/2017.
 */
public class GameFrame extends JFrame {
    private static GameFrame instance;
    public static final int GAME_WIDTH = 500;
    public static final int GAME_HEIGHT = 500;
    private MainPanel mainPanel;

    public GameFrame() {
        instance = this;

        setTitle("Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set contain
        mainPanel = new MainPanel();
    }

    public static GameFrame getInstance() {
        return instance;
    }

    public static void setPanel(JPanel panel) {
        instance.setContentPane(panel);
        instance.pack();
        instance.setLocationRelativeTo(null);
        instance.setVisible(true);
    }
}
