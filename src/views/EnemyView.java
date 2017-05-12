package views;

import utils.Utils;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

/**
 * Created by tonto on 5/11/2017.
 */
public class EnemyView extends JPanel implements MouseListener {
    private int WIDTH = 300;
    private int HEIGHT = 300;
    private int[][] board;

    public EnemyView(int[][] board) {
        this.board = board;
        this.setLayout(new GridLayout(board.length, board[0].length));
        buildGameBoard();
        addMouseListener(this);
        setBackground(Color.white);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    private void buildGameBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                JLabel square = new JLabel(new ImageIcon(),JLabel.CENTER);
                square.setOpaque(false);
                square.setBackground(Color.white);
                square.setName(i + " " + j);
                this.add(square);
                square.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int row = e.getY() / PlayerView.SQUARE_LENGTH;
        int column = e.getX() / PlayerView.SQUARE_LENGTH;
        JLabel component = (JLabel) getComponentAt(e.getPoint());
        if (board[row][column] == 1) {
            Image hitIcon = Utils.loadImageFromRes("hit.gif");
            component.setIcon(new ImageIcon(hitIcon));
        } else {
            ImageIcon missIcon = new ImageIcon("resources/miss.gif");
            component.setIcon(missIcon);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
