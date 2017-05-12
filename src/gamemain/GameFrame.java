package gamemain;

import ui.MainContainer;


import javax.swing.*;


/**
 * Created by tonto on 5/10/2017.
 */
public class GameFrame extends JFrame {
    private static GameFrame instance;
    private MainContainer mainContainer;

    public GameFrame() {
        instance = this;

        setResizable(false);
        setTitle("Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set contain
        mainContainer = new MainContainer();
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
