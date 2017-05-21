package ui;

import views.PlayerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Hoang on 5/12/2017.
 */
public class ArrowPanel extends JPanel {
    private JLabel arrowLabel;
    private boolean flag = false;
    public static final int PLAYER_WIN = 0;
    public static final int ENEMY_WIN = 1;
    private MouseMotionListener mouseMotionListener;
    private MouseListener mouseListener;

    public ArrowPanel(PlayerView playerView, MouseListener mouseListener, MouseMotionListener mouseMotionListener) {
        this.mouseListener = mouseListener;
        this.mouseMotionListener = mouseMotionListener;
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridBagLayout);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(5,0,0,0);
        JLabel textLabel = new JLabel("CURRENT TURN", JLabel.CENTER);
        textLabel.setFont(new Font("CONSOLAS", Font.BOLD, 16));
        this.add(textLabel, gridBagConstraints);
        arrowLabel = new JLabel(new ImageIcon("resources/arrow_right.png"), JLabel.CENTER);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        this.add(arrowLabel, gridBagConstraints);
        JButton instructionBtn = new JButton("INSTRUCTIONS");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        this.add(instructionBtn, gridBagConstraints);
        instructionBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainContainer.getInstance().showInstruction();
            }
        });
        JButton exitBtn = new JButton("RESTART");
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playerView.getLayeredPane().remove(playerView.getArrowPanel());
                playerView.getLayeredPane().add(playerView.getChooseShipPanel());
                playerView.getLayeredPane().addMouseListener(mouseListener);
                playerView.getLayeredPane().addMouseMotionListener(mouseMotionListener);
                MainContainer.getInstance().showPanel(MainContainer.TAG_START,false);
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new Insets(0,0,0,0);
        this.add(exitBtn, gridBagConstraints);
        setVisible(true);
    }

    public void flipArrow() {
        if (!flag) {
            arrowLabel.setIcon(new ImageIcon("resources/arrow_left.png"));
            flag = true;
        } else {
            arrowLabel.setIcon(new ImageIcon("resources/arrow_right.png"));
            flag = false;
        }
    }

    public void gameOver(int winner){
        arrowLabel.setIcon(null);
        switch (winner) {
            case PLAYER_WIN:
                arrowLabel.setText("You win!");
                break;
            case ENEMY_WIN:
                arrowLabel.setText("You lose!");
                break;
        }
    }
}
