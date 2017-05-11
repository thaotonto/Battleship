package models;

import javax.swing.*;
import java.awt.*;

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
        x=60*row;
        y=60*column;
        ImageIcon imageIcon;
        if (isVertical) {
            image = image.getScaledInstance(60, 60 * length, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
            this.setIcon(imageIcon);
        } else {
            image = image.getScaledInstance(60*length, 60, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
            this.setIcon(imageIcon);
        }
        setBounds(x,y,imageIcon.getIconWidth(),imageIcon.getIconHeight());
    }
    public void setRowAndColumn(int row, int column)
    {
        this.row=row;
        this.column=column;
    }
}
