package ui;

import controllers.EnemyController;
import views.PlayerView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tonto on 5/10/2017.
 */
public class GamePanel extends JPanel {
    private EnemyController enemyController;

    public GamePanel(PlayerView playerView) {
        setLayout(new BorderLayout());
        add(playerView, BorderLayout.LINE_START);
        enemyController = new EnemyController();
        add(enemyController.getEnemyView(), BorderLayout.LINE_END);
    }

}
