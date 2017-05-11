package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonto on 5/11/2017.
 */
public class Ship {
    private int shipLength;
    private int orientation;
    private List<int[]> dotList;

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;


    public Ship(int shipLength) {
        this.shipLength = shipLength;
        dotList = new ArrayList<>();
    }

    public List<int[]> getDotList() {
        return dotList;
    }

    public int getShipLength() {
        return shipLength;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getOrientation() {
        return orientation;
    }
}
