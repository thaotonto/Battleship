package ui;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tonto on 5/10/2017.
 */
public class StartPanel extends JPanel implements ActionListener {
    private Image startBackground;
    private int START_WIDTH = 300;
    private int START_HEIGHT = 300;

    private JButton backBtn;
    private JButton playBtn;

    public StartPanel() {
        setLayout(null);
        startBackground = Utils.loadImageFromRes("BG-0.png");
        initComp();
    }

    private void initComp() {
        backBtn = new JButton("BACK");
        backBtn.setBounds(10, 100, 100, 50);
        backBtn.addActionListener(this);
        add(backBtn);

        playBtn = new JButton("PLAY");
        playBtn.addActionListener(this);
        playBtn.setBounds(120, 100, 100, 50);
        add(playBtn);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(START_WIDTH, START_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(startBackground, 0, 0, START_WIDTH, START_HEIGHT, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            MainContainer.getInstance().onBackPressed();
        } else if (e.getSource() == playBtn) {
            MainContainer.getInstance().showPanel(MainContainer.TAG_GAME, true);
        }
    }
}
