package ui;

import views.PlayerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by Hoang on 5/12/2017.
 */
public class ArrowPanel extends JPanel {
    private JLabel arrowLabel;
    private boolean flag = false;
    private boolean restart;
    public static final int PLAYER_WIN = 0;
    public static final int ENEMY_WIN = 1;
    private MouseMotionListener mouseMotionListener;
    private MouseListener mouseListener;
    private int turn;
    private JLabel textLabel;

    public ArrowPanel(PlayerView playerView, MouseListener mouseListener, MouseMotionListener mouseMotionListener) {
        restart = false;
        turn = 1;
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
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        textLabel = new JLabel("TURN " + turn, JLabel.CENTER);
        textLabel.setFont(new Font("CONSOLAS", Font.BOLD, 16));
        this.add(textLabel, gridBagConstraints);
        arrowLabel = new JLabel(new ImageIcon("resources/arrow_right.png"), JLabel.CENTER);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(arrowLabel, gridBagConstraints);
        JButton instructionBtn = new JButton("INSTRUCTIONS");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(instructionBtn, gridBagConstraints);
        instructionBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainContainer.getInstance().showInstruction();
            }
        });
        JButton restartBtn = new JButton("RESTART");
        restartBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                restart = true;
                playerView.getLayeredPane().remove(playerView.getArrowPanel());
                playerView.getLayeredPane().add(playerView.getChooseShipPanel());
                playerView.getLayeredPane().addMouseListener(mouseListener);
                playerView.getLayeredPane().addMouseMotionListener(mouseMotionListener);
                playerView.removeComponent();
                MainContainer.getInstance().showPanel(MainContainer.TAG_START, false);
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(restartBtn, gridBagConstraints);
        JButton backToMenuBtn = new JButton("MENU");
        backToMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainContainer.getInstance().showPanel(MainContainer.TAG_MENU, true);
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(backToMenuBtn, gridBagConstraints);
        setVisible(true);
    }

    public void flipArrow() {
        if (!flag) {
            arrowLabel.setIcon(new ImageIcon("resources/arrow_left.png"));
            flag = true;
        } else {
            arrowLabel.setIcon(new ImageIcon("resources/arrow_right.png"));
            flag = false;
            turn++;
            textLabel.setText("TURN " + turn);
            repaint();
        }
    }

    public void gameOver(int winner, String playerName, int aiLevel) {
        arrowLabel.setIcon(null);
        arrowLabel.setFont(new Font("CONSOLAS", Font.BOLD, 16));
        switch (winner) {
            case PLAYER_WIN: {
                arrowLabel.setText("YOU WIN!");
                try {
                    FileWriter fileWriter = new FileWriter("resources/history.txt",true);
                    String history = "";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    history += simpleDateFormat.format(System.currentTimeMillis()) + "|";
                    history += playerName + " won against A.I";
                    if (aiLevel == 0) history+="(Easy) ";
                    else history+="(Hard) ";
                    history+="in " + turn + " moves.\n";
                    fileWriter.write(history);
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            break;
            case ENEMY_WIN: {
                arrowLabel.setText("YOU LOSE!");
                try {
                    FileWriter fileWriter = new FileWriter("resources/history.txt",true);
                    String history = "";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    history += simpleDateFormat.format(System.currentTimeMillis()) + "|";
                    history += playerName + " lost against A.I";
                    if (aiLevel == 0) history+="(Easy) ";
                    else history+="(Hard) ";
                    history+="in " + turn + " moves.\n";
                    fileWriter.write(history);
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
        restart = true;
    }

    public int getTurn() {
        return turn;
    }

    public boolean isRestart() {
        return restart;
    }
}
