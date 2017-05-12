package models;

import org.w3c.dom.css.Rect;
import ui.ArrangeShipPanel;
import utils.Utils;

import javax.swing.*;
import java.awt.*;

import static ui.ArrangeShipPanel.*;

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
    private Image imageV;
    private Image imageH;
//    private int lastX;
//    private int lastY;

//    public int getLastX() {
//        return lastX;
//    }
//
//    public void setLastX() {
//        this.lastX = x;
//    }
//
//    public int getLastY() {
//        return lastY;
//    }
//
//    public void setLastY() {
//        this.lastY = y;
//    }
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
        isVertical=true;
        this.length = length;
        this.imageV = Utils.loadImageFromRes("ship" + length + "_v.gif");
        this.imageH = Utils.loadImageFromRes("ship" + length + "_h.gif");
        this.defaultX=defaultX;
        this.defaultY=defaultY;

        ImageIcon imageIcon;
        imageV = imageV.getScaledInstance(SQUARE_LENGTH, SQUARE_LENGTH * length, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(imageV);
        this.setIcon(imageIcon);
    }
    public void setThings(int row, int column)
    {
        this.row=row;
        this.column=column;
        this.x=row*SQUARE_LENGTH;
        this.y=column*SQUARE_LENGTH;
    }


    public int getXPixel() {
        return x;
    }


    public int getYPixel() {
        return y;
    }

    public void toHorizontal()
    {
        isVertical=false;
        imageH = imageH.getScaledInstance(SQUARE_LENGTH*length, SQUARE_LENGTH, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(imageH);
        this.setIcon(imageIcon);
        setBounds(x,y,SQUARE_LENGTH*length,SQUARE_LENGTH);
    }
    public void toVertical()
    {
        isVertical=true;
        ImageIcon imageIcon = new ImageIcon(imageV);
        this.setIcon(imageIcon);
        setBounds(x,y,SQUARE_LENGTH,SQUARE_LENGTH*length);
    }

    public boolean isIntersect(Rectangle other)
    {

        return getRectangle().intersects(other);
    }
    public Rectangle getRectangle()
    {
        Rectangle rectangle;
        if(isVertical)
        {
            rectangle= new Rectangle(x,y,SQUARE_LENGTH,SQUARE_LENGTH*length);
        }
        else
        {
            rectangle= new Rectangle(x,y,SQUARE_LENGTH*length,SQUARE_LENGTH);
        }
        return rectangle;
    }
}
