package models;

import views.PlayerView;

import java.util.List;

/**
 * Created by Inpriron on 5/12/2017.
 */
public class PlayerModel {
    private int[][] playerBoard;
    private int[][] checkEndBoard;
    private String name;
    private List<Ship> shipList;
    private boolean end;

    public PlayerModel(int[][] playerBoard, List<Ship> shipList, String name) {
        this.name = name;
        this.playerBoard = playerBoard;
        this.checkEndBoard = new int[PlayerView.NUMBER_ROWS][PlayerView.NUMBER_COLUMNS];
        for (int i = 0; i < playerBoard.length; i++) {
            checkEndBoard[i] = new int[playerBoard[i].length];
            for (int j = 0; j < playerBoard[i].length; j++) {
                checkEndBoard[i][j] = playerBoard[i][j];
            }
        }
        this.shipList = shipList;
    }

    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public void getHit(int row, int col) {
        checkEndBoard[row][col] = 0;
        if (checkEnd()) {
            this.end = true;
        }
    }

    private boolean checkEnd() {
        for (int i = 0; i < checkEndBoard.length; i++) {
            for (int j = 0; j < checkEndBoard[i].length; j++) {
                if (checkEndBoard[i][j] == 1) return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public boolean isEnd() {
        return end;
    }
}
