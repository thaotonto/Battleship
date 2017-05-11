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
        genShip(1);
        genShip(2);
        genShip(3);
        genShip(4);
        genShip(5);
    }

    private void genShip(int shipLength) {
        Ship ship = new Ship(shipLength);
        for (int i = 0; i < shipLength; i++) {
            int[] dot = new int[2];
            if (i == 0) {
                do {
                    dot[0] = random.nextInt(mapSize[0]);
                    dot[1] = random.nextInt(mapSize[1]);
                } while (enemyBoard[dot[0]][dot[1]] == 1);
                ship.setOrientation(random.nextInt(2));
                ship.getDotList().add(dot);
            } else {

                boolean repeat = false;
                do {
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
                    System.out.println("dot: " + dot[0] + " " + dot[1]);
                    System.out.println("old dot: " + dot[0] + " " + dot[1]);
                } while (enemyBoard[dot[0]][dot[1]] == 1 || repeat);
                ship.getDotList().add(dot);
            }
            enemyBoard[dot[0]][dot[1]] = 1;
        }
        shipList.add(ship);
    }

    public void showBoard() {
        for (int i = 0; i < mapSize[0]; i++) {
            for (int j = 0; j < mapSize[1]; j++) {
                System.out.print(enemyBoard[i][j] + " ");
            }
            System.out.println();
        }
        showShip();
    }

    private void showShip() {
        for (Ship ship : shipList) {
            for (int[] dot : ship.getDotList()) {
                System.out.println("ship: " + ship.getShipLength() + " " + dot[0] + " " + dot[1]);
            }
        }
    }
}
