package ui;

import controllers.EnemyController;
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
    private int MENU_WIDTH = 500;
    private int MENU_HEIGHT = 420;
    private Image menuBackground;

    private JButton startBtn;
    private JButton instructionBtn;
    private JButton exitBtn;
    private JButton historyBtn;
    private Dimension dimension= new Dimension(100,30);
    public MenuPanel() {
        instance = this;
        setLayout(new BorderLayout());
        menuBackground = Utils.loadImageFromRes("menu_bg.jpg");
        initComp();
    }

    private void initComp() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JPanel pageEnd = new JPanel(new GridBagLayout());
        pageEnd.setOpaque(false);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        gridBagConstraints.weightx=1;
        gridBagConstraints.anchor=GridBagConstraints.LINE_START;
        gridBagConstraints.insets=new Insets(0,20,20,20);
        startBtn = new JButton("START");
        startBtn.setPreferredSize(dimension);
        startBtn.addActionListener(this);
        pageEnd.add(startBtn, gridBagConstraints);
        gridBagConstraints.anchor=GridBagConstraints.LINE_END;
        instructionBtn = new JButton("HELP");
        instructionBtn.setPreferredSize(dimension);
        instructionBtn.addActionListener(this);
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=0;
        pageEnd.add(instructionBtn, gridBagConstraints);
        gridBagConstraints.anchor=GridBagConstraints.LINE_START;
        historyBtn = new JButton("HISTORY");
        historyBtn.setPreferredSize(dimension);
        historyBtn.addActionListener(this);
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=1;
        pageEnd.add(historyBtn, gridBagConstraints);
        gridBagConstraints.anchor=GridBagConstraints.LINE_END;

        exitBtn = new JButton("EXIT");
        exitBtn.setPreferredSize(dimension);
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=1;
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
        } else if (e.getSource() == historyBtn) {
            MainContainer.getInstance().showPanel(MainContainer.TAG_HISTORY, true);
        }

    }
}
