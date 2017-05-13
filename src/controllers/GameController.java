package controllers;

/**
 * Created by Hoang on 5/13/2017.
 */
public class GameController {
    private PlayerController playerController;
    private EnemyController enemyController;
    private Thread thread;

    public GameController(PlayerController playerController, EnemyController enemyController) {
        this.playerController = playerController;
        this.enemyController = enemyController;
    }

    public void run(){

    }
}
