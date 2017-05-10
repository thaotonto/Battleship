package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tonto on 5/10/2017.
 */
public class GamePanel extends JPanel {
    private int GAME_WIDTH = 800;
    private int GAME_HEIGHT = 650;

    public GamePanel() {

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GAME_WIDTH, GAME_HEIGHT);
    }
}
