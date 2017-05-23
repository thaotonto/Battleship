package ai;

import models.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by THM on 5/11/2017.
 */
public class AIHard extends AI {

    private List<Move> horizontalPriority = new ArrayList<>();
    private List<Move> verticalPriority = new ArrayList<>();
    private List<Move> hitList = new ArrayList<>();

    public AIHard(List<Ship> shipList, int[][] battleMap) {
        super(shipList, battleMap);
    }


    @Override
    public void shot() {
        //first shot random
        Random random = new Random();
        List<Move> priorityList = new ArrayList<>();
        if (lastShot == null) { // first shot random, init priority
            System.out.println("Random first shot");
            lastShot = randomShot();
            if (isHit(lastShot)) {
                hitList.add(lastShot);
                System.out.println("HIT - add " + lastShot.getX() + ", " + lastShot.getY());
                System.out.println("(" + lastShot.getX() + ", " + lastShot.getY() + ")");
                addVerticalPriority(lastShot);
                addHorizontalPriority(lastShot);
            } else {
                System.out.println("MISSED");
                System.out.println("(" + lastShot.getX() + ", " + lastShot.getY() + ")");
            }
        } else {
            if (isDestroy(lastShot)) { // destroy a ship then random, clear priority
                System.out.println("Destroyed ship");
                if (!notFinish(hitList)) {
                    lastShot = randomShot();
                    verticalPriority.clear();
                    horizontalPriority.clear();
                    if (isHit(lastShot)) {
                        hitList.add(lastShot);
                        System.out.println("HIT - add " + lastShot.getX() + ", " + lastShot.getY());
                        System.out.println("(" + lastShot.getX() + ", " + lastShot.getY() + ")");
                        addVerticalPriority(lastShot);
                        addHorizontalPriority(lastShot);
                    }
                } else {
                    Move temp = null;
                    for (Move m : hitList) {
                        if (!isDestroy(m)) {
                            temp = m;
                        }
                    }
                    System.out.println(temp.getX() + ", " + temp.getY() + " is not finish");
                    horizontalPriority.clear();
                    verticalPriority.clear();
                    addVerticalPriority(temp);
                    addHorizontalPriority(temp);
                    priorityList.addAll(horizontalPriority);
                    priorityList.addAll(verticalPriority);
                    Move curShot;
                    curShot = priorityList.get(random.nextInt(priorityList.size()));
                    if (isHorizontalPriority(curShot)) { // if that move is in horizontalPriority
                        System.out.println("Horizontal Priority");
                        if (isHit(curShot)) { // if hit only check horizontalPriority, clear vertical and add new priority
                            verticalPriority.clear();
                            hitList.add(curShot);
                            System.out.println("HIT - add " + curShot.getX() + ", " + curShot.getY());
                            System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                            addHorizontalPriority(curShot);
                        } else {
                            System.out.println("MISSED");
                            System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                        }
                        horizontalPriority.remove(horizontalPriority.indexOf(curShot)); // remove move from priority
                    } else if (isVerticalPriority(curShot)) {
                        System.out.println("Vertical Priority");
                        if (isHit(curShot)) {
                            hitList.add(curShot);
                            horizontalPriority.clear();
                            System.out.println("HIT - add " + curShot.getX() + ", " + curShot.getY());
                            System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                            addVerticalPriority(curShot);
                        } else {
                            System.out.println("MISSED");
                            System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                        }
                        verticalPriority.remove(verticalPriority.indexOf(curShot));
                    }
                    shootMove.add(curShot); // mark move as shoot
                    lastShot = curShot; // update variable

                }
            } else { // if not chose random a move on priorityList
                if (horizontalPriority.isEmpty() && verticalPriority.isEmpty()) {
                    if (notFinish(hitList)) {
                        Move temp = null;
                        for (Move m : hitList) {
                            if (!isDestroy(m)) {
                                temp = m;
                            }
                        }
                        System.out.println(temp.getX() + ", " + temp.getY() + " is not finish");
                        addVerticalPriority(temp);
                        addHorizontalPriority(temp);
                        priorityList.addAll(horizontalPriority);
                        priorityList.addAll(verticalPriority);
                        Move curShot;
                        curShot = priorityList.get(random.nextInt(priorityList.size()));
                        if (isHorizontalPriority(curShot)) { // if that move is in horizontalPriority
                            System.out.println("Horizontal Priority");
                            if (isHit(curShot)) { // if hit only check horizontalPriority, clear vertical and add new priority
                                verticalPriority.clear();
                                hitList.add(curShot);
                                System.out.println("HIT - add " + curShot.getX() + ", " + curShot.getY());
                                System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                                addHorizontalPriority(curShot);
                            } else {
                                System.out.println("MISSED");
                                System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                            }
                            horizontalPriority.remove(horizontalPriority.indexOf(curShot)); // remove move from priority
                        } else if (isVerticalPriority(curShot)) {
                            System.out.println("Vertical Priority");
                            if (isHit(curShot)) {
                                hitList.add(curShot);
                                horizontalPriority.clear();
                                System.out.println("HIT - add " + curShot.getX() + ", " + curShot.getY());
                                System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                                addVerticalPriority(curShot);
                            } else {
                                System.out.println("MISSED");
                                System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                            }
                            verticalPriority.remove(verticalPriority.indexOf(curShot));
                        }
                        shootMove.add(curShot); // mark move as shoot
                        lastShot = curShot; // update variable
                    } else {
                        System.out.println("Random nowhere");
                        lastShot = randomShot();
                        if (isHit(lastShot)) {
                            hitList.add(lastShot);
                            System.out.println("HIT - add " + lastShot.getX() + ", " + lastShot.getY());
                            System.out.println("(" + lastShot.getX() + ", " + lastShot.getY() + ")");
                            addVerticalPriority(lastShot);
                            addHorizontalPriority(lastShot);
                        } else {
                            System.out.println("MISSED");
                            System.out.println("(" + lastShot.getX() + ", " + lastShot.getY() + ")");
                        }
                    }
                } else {
                    System.out.println("Get priority");
                    priorityList.addAll(horizontalPriority);
                    priorityList.addAll(verticalPriority);
                    Move curShot;
                    curShot = priorityList.get(random.nextInt(priorityList.size()));
                    if (isHorizontalPriority(curShot)) { // if that move is in horizontalPriority
                        System.out.println("Horizontal Priority");
                        if (isHit(curShot)) { // if hit only check horizontalPriority, clear vertical and add new priority
                            verticalPriority.clear();
                            hitList.add(curShot);
                            System.out.println("HIT - add " + curShot.getX() + ", " + curShot.getY());
                            System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                            addHorizontalPriority(curShot);
                        } else {
                            System.out.println("MISSED");
                            System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                        }
                        horizontalPriority.remove(horizontalPriority.indexOf(curShot)); // remove move from priority
                    } else if (isVerticalPriority(curShot)) {
                        System.out.println("Vertical Priority");
                        if (isHit(curShot)) {
                            hitList.add(curShot);
                            horizontalPriority.clear();
                            System.out.println("HIT - add " + curShot.getX() + ", " + curShot.getY());
                            System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                            addVerticalPriority(curShot);
                        } else {
                            System.out.println("MISSED");
                            System.out.println("(" + curShot.getX() + ", " + curShot.getY() + ")");
                        }
                        verticalPriority.remove(verticalPriority.indexOf(curShot));
                    }
                    shootMove.add(curShot); // mark move as shoot
                    lastShot = curShot; // update variable
                }
            }
        }
    }


    public boolean isHorizontalPriority(Move move) { // check priority type of move
        for (Move m : horizontalPriority) {
            if (m.equals(move)) {
                return true;
            }
        }
        return false;
    }

    public boolean isVerticalPriority(Move move) {
        for (Move m : verticalPriority) {
            if (m.equals(move)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Move randomShot() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(Move.MAX_COL);
            y = random.nextInt(Move.MAX_ROW);
        } while (isShoot(x, y) || (x + y) % 2 == 0);
        Move move = new Move(x, y);
        shootMove.add(move);
        System.out.println("Shoot: " + x + " " + y);
        return move;
    }

    public void addVerticalPriority(Move move) { // add new priority to the right list
        int x = move.getX();
        int y = move.getY();
        if (!move.isBotEdge() && !move.isTopEdge()) {
            if (!isShoot(x - 1, y)) {
                verticalPriority.add(new Move(x - 1, y));
                System.out.println("Added (" + (x - 1) + ", " + y + ")");
            }

            if (!isShoot(x + 1, y)) {
                verticalPriority.add(new Move(x + 1, y));
                System.out.println("Added (" + (x + 1) + ", " + y + ")");
            }
        } else if (move.isBotEdge()) {
            if (!isShoot(x - 1, y)) {
                verticalPriority.add(new Move(x - 1, y));
                System.out.println("Added (" + (x - 1) + ", " + y + ")");
            }
        } else if (move.isTopEdge()) {
            if (!isShoot(x + 1, y)) {
                verticalPriority.add(new Move(x + 1, y));
                System.out.println("Added (" + (x + 1) + ", " + y + ")");
            }
        }
    }

    public void addHorizontalPriority(Move move) {
        int x = move.getX();
        int y = move.getY();
        if (!move.isLeftEdge() && !move.isRightEdge()) {
            if (!isShoot(x, y + 1)) {
                horizontalPriority.add(new Move(x, y + 1));
                System.out.println("Added (" + (x) + ", " + (y + 1) + ")");
            }
            if (!isShoot(x, y - 1)) {
                horizontalPriority.add(new Move(x, y - 1));
                System.out.println("Added (" + (x) + ", " + (y - 1) + ")");
            }
        } else if (move.isLeftEdge()) {
            if (!isShoot(x, y + 1)) {
                horizontalPriority.add(new Move(x, y + 1));
                System.out.println("Added (" + (x) + ", " + (y + 1) + ")");
            }
        } else if (move.isRightEdge()) {
            if (!isShoot(x, y - 1)) {
                horizontalPriority.add(new Move(x, y - 1));
                System.out.println("Added (" + (x) + ", " + (y - 1) + ")");
            }
        }
    }

    public boolean notFinish(List<Move> hitList) {
        for (Move m : hitList) {
            if (!isDestroy(m)) {
                return true;
            }
        }
        return false;
    }
}
