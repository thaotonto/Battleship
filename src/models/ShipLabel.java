package models;

import ui.ArrangeShipPanel;

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
    private int y;
    private boolean isVertical;
    private int length;
    private Image image;

    public ShipLabel(int row, int column, boolean isVertical, int length, Image image) {
        this.row = row;
        this.column = column;
        this.isVertical = isVertical;
        this.length = length;
        this.image = image;
        x= SQUARE_LENGTH*row;
        y=SQUARE_LENGTH*column;
        ImageIcon imageIcon;
        if (isVertical) {
            image = image.getScaledInstance(SQUARE_LENGTH, SQUARE_LENGTH * length, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
            this.setIcon(imageIcon);
        } else {
            image = image.getScaledInstance(SQUARE_LENGTH*length, SQUARE_LENGTH, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
            this.setIcon(imageIcon);
        }
    }

    public int getLength() {
        return length;
    }

    public ShipLabel(int length, Image image) {

        this.length = length;
        this.image = image;

        ImageIcon imageIcon;
        image = image.getScaledInstance(SQUARE_LENGTH, SQUARE_LENGTH * length, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
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
}
