package views;

import utils.Utils;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by tonto on 5/11/2017.
 */
public class EnemyView extends JPanel implements MouseListener {
    private int WIDTH = 300;
    private int HEIGHT = 300;
    private int[][] board;
    private Cursor crosshairCursor;
    private int[] lastMove;
    private ArrayList<Component> guessedList;

    public EnemyView(int[][] board) {
        this.board = board;
        guessedList = new ArrayList<>();
        lastMove = new int[2];
        lastMove[0] = -1;
        lastMove[1] = -1;
        this.setLayout(new GridLayout(board.length, board[0].length));
        buildGameBoard();
        addMouseListener(this);
        setBackground(Color.white);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        toolkit.getBestCursorSize(PlayerView.SQUARE_LENGTH, PlayerView.SQUARE_LENGTH);
        Image image = Utils.loadImageFromRes("crosshair.png");
        Point hotSpot = new Point(PlayerView.SQUARE_LENGTH / 2, PlayerView.SQUARE_LENGTH / 2);
        crosshairCursor = toolkit.createCustomCursor(image, hotSpot, "crosshair");
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    private void buildGameBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                JLabel square = new JLabel(new ImageIcon(), JLabel.CENTER);
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
        if (isEnabled()) {
            int row = e.getY() / PlayerView.SQUARE_LENGTH;
            int column = e.getX() / PlayerView.SQUARE_LENGTH;
            JLabel component = (JLabel) getComponentAt(e.getPoint());
            if (!guessedList.contains(component)) {
                guessedList.add(component);
                if (board[row][column] == 1) {
                    Image hitIcon = Utils.loadImageFromRes("hit.gif");
                    component.setIcon(new ImageIcon(hitIcon));
                } else {
                    ImageIcon missIcon = new ImageIcon("resources/miss.gif");
                    component.setIcon(missIcon);
                }
                lastMove[0] = row;
                lastMove[1] = column;
                setEnabled(false);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(crosshairCursor);
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int[] getLastMove() {
        return lastMove;
    }

    public void resetLastMove() {
        lastMove[0] = -1;
        lastMove[1] = -1;
    }

}
