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
        return x == MAX_COL - 1;
    }

    public boolean isLeftEdge() {
        return x == 0;
    }

    public boolean isTopEdge() {
        return y == 0;
    }

    public boolean isBotEdge() {
        return y == MAX_ROW - 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Move){
            if (((Move) obj).getX() == this.x && ((Move) obj).getY() == this.y) return true;
        }
        return false;
    }
}

