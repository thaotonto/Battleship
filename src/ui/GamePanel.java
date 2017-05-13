package ui;

import controllers.EnemyController;
import controllers.GameController;
import controllers.PlayerController;
import views.PlayerView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tonto on 5/10/2017.
 */
public class GamePanel extends JPanel {
    private EnemyController enemyController;

    public GamePanel(PlayerController playerController) {
        setLayout(new BorderLayout());
        add(playerController.getPlayerView(), BorderLayout.LINE_START);
        enemyController = new EnemyController();
        add(enemyController.getEnemyView(), BorderLayout.LINE_END);
        GameController gameController = new GameController(playerController,enemyController);
    }

}
