package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tonto on 5/11/2017.
 */
public class EnemyModel {
    private int[][] enemyBoard;
    private int[] mapSize;
    private List<Ship> shipList;
    private Random random = new Random();

    public EnemyModel(int[][] enemyBoard, int[] mapSize) {
        this.enemyBoard = enemyBoard;
        this.mapSize = mapSize;
        shipList = new ArrayList<>();
        setupBoard();
        showBoard();
    }


    private void setupBoard() {
        genShip(2);
        genShip(3);
        genShip(3);
        genShip(4);
        genShip(5);
    }

    private void genShip(int shipLength) {
        Ship ship = new Ship(shipLength);
        for (int i = 0; i < shipLength; i++) {
            int loopCount = 0;
            int[] dot = new int[2];
            if (i == 0) {
                do {
                    dot[0] = random.nextInt(mapSize[0]);
                    dot[1] = random.nextInt(mapSize[1]);
                } while (enemyBoard[dot[0]][dot[1]] != 0);
                ship.setOrientation(random.nextInt(2));
                ship.getDotList().add(dot);
            } else {
                boolean repeat = false;
                do {
                    loopCount++;
                    if (loopCount > 200) {
                        ship.getDotList().clear();
                        clearMap();
                        break;
                    }
                    int getDot = random.nextInt(ship.getDotList().size());
                    int[] oldDot = ship.getDotList().get(getDot);
                    switch (ship.getOrientation()) {
                        case Ship.VERTICAL:
                            dot[0] = random.nextInt(mapSize[0]);
                            dot[1] = oldDot[1];
                            if (Math.abs(dot[0] - oldDot[0]) != 1) {
                                repeat = true;
                            } else
                                repeat = false;
                            break;
                        case Ship.HORIZONTAL:
                            dot[1] = random.nextInt(mapSize[1]);
                            dot[0] = oldDot[0];
                            if (Math.abs(dot[1] - oldDot[1]) != 1) {
                                repeat = true;
                            } else
                                repeat = false;
                            break;
                    }
                } while (enemyBoard[dot[0]][dot[1]] != 0 || repeat);
                ship.getDotList().add(dot);
            }
            enemyBoard[dot[0]][dot[1]] = 1;
        }
        shipList.add(ship);
    }

    private void clearMap() {
        enemyBoard = new int[mapSize[0]][mapSize[1]];
        setupBoard();
    }

    public void showBoard() {
        System.out.println("________________________________________________________________________________");
        for (int i = 0; i < mapSize[0]; i++) {
            for (int j = 0; j < mapSize[1]; j++) {
                System.out.print(enemyBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("_____________________________________________________________________________");
    }
}
