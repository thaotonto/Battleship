package models;

import views.PlayerView;

import java.util.List;

/**
 * Created by Inpriron on 5/12/2017.
 */
public class PlayerModel {
    private int[][] playerBoard;
    private List<Ship> shipList;
    private boolean end;

    public PlayerModel(int[][] playerBoard, List<Ship> shipList) {
        this.playerBoard = playerBoard;
        this.shipList = shipList;
    }

    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public void getHit(int row, int col) {
        playerBoard[row][col] = 0;
        if (checkEnd()) {
            this.end = true;
        }
    }

    private boolean checkEnd() {
        for (int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard[i].length; j++) {
                if (playerBoard[i][j] == 1) return false;
            }
        }
        return true;
    }

    public boolean isEnd() {
        return end;
    }
}
