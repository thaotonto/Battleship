package models;

import utils.Utils;

import javax.swing.*;
import java.awt.*;

import static views.PlayerView.*;

/**
 * Created by Inpriron on 5/11/2017.
 */
public class ShipLabel extends JLabel {
    private int row;
    private int column;
    private int x;

    public boolean isVertical() {
        return isVertical;
    }

    private int y;
    private int defaultX;
    private int defaultY;
    private boolean isVertical;
    private int length;
    private ImageIcon imageV;
    private ImageIcon imageH;
    private int lastX;
    private int lastY;

    public int getLastX() {
        return lastX;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    //    public ShipLabel(int row, int column, boolean isVertical, int length, Image image) {
//        this.row = row;
//        this.column = column;
//        this.isVertical = isVertical;
//        this.length = length;
//        this.image = image;
//        x= SQUARE_LENGTH*row;
//        y=SQUARE_LENGTH*column;
//        ImageIcon imageIcon;
//        if (isVertical) {
//            image = image.getScaledInstance(SQUARE_LENGTH, SQUARE_LENGTH * length, Image.SCALE_SMOOTH);
//            imageIcon = new ImageIcon(image);
//            this.setIcon(imageIcon);
//        } else {
//            image = image.getScaledInstance(SQUARE_LENGTH*length, SQUARE_LENGTH, Image.SCALE_SMOOTH);
//            imageIcon = new ImageIcon(image);
//            this.setIcon(imageIcon);
//        }
//    }

    public int getLength() {
        return length;
    }

    public int getDefaultX() {
        return defaultX;
    }

    public int getDefaultY() {
        return defaultY;
    }

    public ShipLabel(int length, int defaultX, int defaultY) {
        row = column = -1;
        isVertical = true;
        this.length = length;
        this.imageV = new ImageIcon("resources/ship" + length + "_v.gif");
        this.imageH = new ImageIcon("resources/ship" + length + "_h.gif");
        x = defaultX;
        y = defaultY;
        this.defaultX = defaultX;
        this.defaultY = defaultY;
        lastX = defaultX;
        lastY = defaultY;
        imageV.setImage(imageV.getImage().getScaledInstance(SQUARE_LENGTH, SQUARE_LENGTH * length, Image.SCALE_DEFAULT));
        this.setIcon(imageV);
    }

    public void setThings(int row, int column) {
        this.row = row;
        this.column = column;
        this.x = column * SQUARE_LENGTH;
        this.y = row * SQUARE_LENGTH;
    }
    public void reset()
    {
        row=-1;
        column=-1;
        x=defaultX;
        y=defaultY;
    }

    public int getXPixel() {
        return x;
    }


    public int getYPixel() {
        return y;
    }

    public void toHorizontal() {

        isVertical = false;
        if (isIntersect(new Rectangle(SQUARE_LENGTH * NUMBER_COLUMNS,
                0,
                COLUMNS_FOR_CHOOSE_SHIP_PANEL * SQUARE_LENGTH,
                SQUARE_LENGTH * NUMBER_ROWS))) {
            isVertical = true;
            return;
        }
        imageH.setImage(imageH.getImage().getScaledInstance(SQUARE_LENGTH * length, SQUARE_LENGTH, Image.SCALE_DEFAULT));
        this.setIcon(imageH);
        setBounds(x, y, SQUARE_LENGTH * length, SQUARE_LENGTH);
    }

    public void toVertical() {
        isVertical = true;
        if (isIntersect(new Rectangle(0,
                SQUARE_LENGTH * NUMBER_ROWS,
                SQUARE_LENGTH * NUMBER_COLUMNS,
                SQUARE_LENGTH))) {
            isVertical = false;
            return;
        }
        this.setIcon(imageV);
        setBounds(x, y, SQUARE_LENGTH, SQUARE_LENGTH * length);
    }

    public boolean isIntersect(Rectangle other) {

        return getRectangle().intersects(other);
    }

    public Rectangle getRectangle() {
        Rectangle rectangle;
        if (isVertical) {
            rectangle = new Rectangle(x, y, SQUARE_LENGTH, SQUARE_LENGTH * length);
        } else {
            rectangle = new Rectangle(x, y, SQUARE_LENGTH * length, SQUARE_LENGTH);
        }
        return rectangle;
    }

    public void setBackToLastLocation() {
        if (lastX == defaultX && lastY == defaultY) {
            row = column = -1;
            x = defaultX;
            y = defaultY;

        }
        x = lastX;
        y = lastY;
        row = x / SQUARE_LENGTH;
        column = y / SQUARE_LENGTH;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
