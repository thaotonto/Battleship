package controllers;

import models.EnemyModel;
import views.EnemyView;

/**
 * Created by tonto on 5/10/2017.
 */
public class EnemyController {
    private EnemyModel enemyModel;
    private EnemyView enemyView;
    private int[] mapSize = {10,10};

    public EnemyController() {
        this.enemyModel = new EnemyModel(new int[10][10],mapSize);
        this.enemyView = new EnemyView(enemyModel.getEnemyBoard());
    }

    public EnemyView getEnemyView() {
        return enemyView;
    }

    public EnemyModel getEnemyModel() {
        return enemyModel;
    }
}
