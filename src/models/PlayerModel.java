package models;

import views.PlayerView;

import java.util.List;

/**
 * Created by Inpriron on 5/12/2017.
 */
public class PlayerModel {
    private int[][] playerBoard;
    private List<Ship> shipList;

    public PlayerModel(int[][] playerBoard, List<Ship> shipList) {
        this.playerBoard = playerBoard;
        this.shipList = shipList;
    }
}
