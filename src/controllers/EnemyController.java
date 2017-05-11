package controllers;

import models.EnemyModel;

/**
 * Created by tonto on 5/10/2017.
 */
public class EnemyController {
    private EnemyModel enemyModel;
    private int[] mapSize = {10,10};

    public EnemyController() {
        this.enemyModel = new EnemyModel(new int[10][10],mapSize);
    }
}
