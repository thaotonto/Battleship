package ui;

import gamemain.GameFrame;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tonto on 5/10/2017.
 */
public class MenuPanel extends JPanel implements ActionListener {
    private static MenuPanel instance;
    private int MENU_WIDTH = 650;
    private int MENU_HEIGHT = 650;
    private Image menuBackground;

    private JButton startBtn;
    private JButton instructionBtn;
    private JButton exitBtn;

    public MenuPanel() {
        instance = this;
        setLayout(null);
        menuBackground = Utils.loadImageFromRes("BG-0.png");
        initComp();
    }

    private void initComp() {
        startBtn = new JButton("START");
        startBtn.setBounds(200, 200, 100, 50);
        startBtn.addActionListener(this);
        add(startBtn);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MENU_WIDTH, MENU_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(menuBackground, 0, 0, MENU_WIDTH, MENU_HEIGHT, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            MainPanel.getInstance().showPanel(MainPanel.TAG_START, true);
        }
    }
}
