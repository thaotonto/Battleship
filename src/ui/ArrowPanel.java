package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Hoang on 5/12/2017.
 */
public class ArrowPanel extends JPanel {
    private JLabel arrowLabel;
    private boolean flag = false;

    public ArrowPanel() {
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
        arrowLabel = new JLabel(new ImageIcon("resources/arrow_left.png"), JLabel.CENTER);
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
                flipArrow();
            }
        });
        JButton exitBtn = new JButton("EXIT");
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new Insets(0,0,-1,0);
        this.add(exitBtn, gridBagConstraints);
        setVisible(true);
    }

    public void flipArrow() {
        if (!flag) {
            arrowLabel.setIcon(new ImageIcon("resources/arrow_right.png"));
            flag = true;
        } else {
            arrowLabel.setIcon(new ImageIcon("resources/arrow_left.png"));
            flag = false;
        }
    }
}
