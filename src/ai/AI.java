package ai;

import models.Ship;
import views.PlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by THM on 5/11/2017.
 */
public abstract class AI {
    protected int[][] battleMap = new int[PlayerView.NUMBER_COLUMNS][PlayerView.NUMBER_ROWS];
    protected Move lastShot = null;
    protected List<Move> shootMove = new ArrayList<>();
    protected List<List<Move>> shipList;

    public AI(List<Ship> shipList, int[][] battleMap) { // pass a map
        this.battleMap = battleMap;
        this.shipList = new ArrayList<>();
        for (Ship el: shipList){
            int x = el.getDotList().get(0)[0];
            int y = el.getDotList().get(0)[1];
            List<Move> ship = createShip(x,y,el.getShipLength(),el.getOrientation());
            this.shipList.add(ship);
        }
    }

    public int[][] getBattleMap() {
        return battleMap;
    }

    public abstract void shot(); // start a move

    public Move randomShot() {  // random a move
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(Move.MAX_COL);
            y = random.nextInt(Move.MAX_ROW);
        } while (isShoot(x, y));
        Move move = new Move(x, y);
        shootMove.add(move);
        return move;
    }

    public boolean isShoot(int x, int y) { // check if a move already shoot
        for (Move c : shootMove) {
            if (c.getY() == y && c.getX() == x) {
                return true;
            }
        }
        return false;
    }

    public boolean isDestroy(Move move) { // check if a move destroy the whole ship
        if (isHit(move)) {
            for(List<Move> ship : shipList){
                if (ship.contains(move)) return isShrink(ship);
            }
        }
        return false;
    }

    public boolean isShrink(List<Move> ship) {
        for (Move m : ship) {
            if (!shootMove.contains(m))
                return false;
        }
        return true;
    }

    public boolean isHit(Move move) { // check if a move hit a part of a ship
        return (battleMap[move.getX()][move.getY()] == 1);
    }

    public List<Move> createShip(int x, int y, int length, int vector) { // vector = 0: vertical, vector = 1: horizontal
        List<Move> result = new ArrayList<>();
        if (vector == 1) {
            for (int i = x; i < x + length; i++) {
                result.add(new Move(i, y));
            }
        } else if (vector == 0) {
            for (int i = y; i < y + length; i++) {
                result.add(new Move(x, i));
            }
        }
        return result;
    }
}
