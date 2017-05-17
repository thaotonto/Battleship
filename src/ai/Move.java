package ai;

/**
 * Created by THM on 5/11/2017.
 */
public class Move {
    public static final int MAX_ROW = 10;
    public static final int MAX_COL = 10;
    private int x = 0;
    private int y = 0;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isRightEdge() {
        return y == MAX_COL - 1;
    }

    public boolean isLeftEdge() {
        return y == 0;
    }

    public boolean isTopEdge() {
        return x == 0;
    }

    public boolean isBotEdge() {
        return x == MAX_ROW - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Move){
            if (((Move) o).getX() == this.x && ((Move) o).getY() == this.y) return true;
        }
        return false;
    }
}

