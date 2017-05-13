package controllers;

import ai.AI;
import ai.AIEasy;
import ai.AIHard;

/**
 * Created by Hoang on 5/13/2017.
 */
public class GameController {
    private PlayerController playerController;
    private EnemyController enemyController;
    private AI ai;
    private Thread thread;

    public GameController(PlayerController playerController, EnemyController enemyController) {
        this.playerController = playerController;
        this.enemyController = enemyController;
        ai = getAI(playerController.getPlayerView().getAiLevelBox().getSelectedIndex());
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (enemyController.getEnemyView().getLastMove()[0] != -1 && enemyController.getEnemyView().getLastMove()[1] != -1) {
                        playerController.getPlayerView().getArrowPanel().flipArrow();
                        ai.shot();
                        try {
                            thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        playerController.getPlayerView().updateBoard(ai.getLastShot().getX(), ai.getLastShot().getY());
                        playerController.getPlayerView().getArrowPanel().flipArrow();
                        enemyController.getEnemyView().setEnabled(true);
                        enemyController.getEnemyView().resetLastMove();
                    }
                }
            }
        });
        thread.start();
    }

    public AI getAI(int aiLevel) {
        AI ai;
        if (aiLevel == 0) {
            System.out.println("Easy Mode");
            ai = new AIEasy(playerController.getPlayerModel().getShipList(), playerController.getPlayerModel().getPlayerBoard());
        } else {
            System.out.println("Hard Mode");
            ai = new AIHard(playerController.getPlayerModel().getShipList(), playerController.getPlayerModel().getPlayerBoard());
        }
        return ai;
    }
}
