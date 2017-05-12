package views;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Created by tonto on 5/11/2017.
 */
public class EnemyView extends JPanel{
    private int WIDTH = 600;
    private int HEIGHT = 600;
    private int[][] board;

    public EnemyView(int[][] board) {
        this.board = board;
        this.setLayout(new GridLayout(board.length, board[0].length));
        buildGameBoard();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    private void buildGameBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setBackground(Color.white);
                this.add(square);
                square.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            }
        }
    }
}
