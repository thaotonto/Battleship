package ui;

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
        setLayout(new BorderLayout());
        menuBackground = Utils.loadImageFromRes("BG-0.png");
        initComp();
    }

    private void initComp() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10, 20, 100, 20);

        JPanel pageEnd = new JPanel(new GridBagLayout());
        pageEnd.setOpaque(false);


        startBtn = new JButton("START");
        startBtn.addActionListener(this);
        pageEnd.add(startBtn, gridBagConstraints);

        instructionBtn = new JButton("INSTRUCTION");
        instructionBtn.addActionListener(this);
        pageEnd.add(instructionBtn, gridBagConstraints);

        exitBtn = new JButton("EXIT");
        exitBtn.addActionListener(this);
        pageEnd.add(exitBtn, gridBagConstraints);

        add(pageEnd, BorderLayout.PAGE_END);
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
            MainContainer.getInstance().showPanel(MainContainer.TAG_START, true);
        } else if (e.getSource() == exitBtn) {
            System.exit(1);
        } else if (e.getSource() == instructionBtn) {
            MainContainer.getInstance().showPanel(MainContainer.TAG_INSTRUCTION, true);
        }

    }
}
