package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by tonto on 5/21/2017.
 */
public class HistoryPanel extends JPanel {
    private int HISTORY_WIDTH = 460;
    private int HISTORY_HEIGHT = 460;
    private JButton backToMenu;
    private JButton clearHistory;
    private File file;
    private Scanner sc;
    private JTextArea jTextArea;
    private JScrollPane jScrollPane;
    private JPanel holderPanel;
    private boolean flag = false;

    public HistoryPanel() {
        this.setLayout(new BorderLayout());
        holderPanel = new JPanel(new FlowLayout());
        holderPanel.setOpaque(false);
        this.add(holderPanel, BorderLayout.SOUTH);
        file = new File("resources/history.txt");
        backToMenu = new JButton("BACK TO MENU");
        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainContainer.getInstance().showPanel(MainContainer.TAG_MENU, true);
            }
        });
        clearHistory = new JButton("CLEAR HISTORY");
        clearHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter fileWriter = new FileWriter(file, false);
                    fileWriter.write("");
                    jTextArea.setText("No match history found. Why don't you try playing one match now.");
                    flag = false;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        holderPanel.add(backToMenu);
        holderPanel.add(clearHistory);
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setFont(new Font("CONSOLAS", Font.PLAIN, 12));
        jTextArea.setLineWrap(true);
        while (sc.hasNextLine()) {
            flag = true;
            String c = sc.nextLine();
            jTextArea.append(c);
            jTextArea.append("\n\n");
        }
        if (!flag){
            jTextArea.setText("No match history found. Why don't you try playing one match now.");
        }
        setVisible(true);
        jTextArea.setVisible(true);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setCaretPosition(0);
        JLabel textLabel = new JLabel("MATCH HISTORY");
        textLabel.setFont(new Font("CONSOLAS", Font.BOLD, 16));
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setBorder(new EmptyBorder(3,3,3,3));
        jTextArea.setBorder(BorderFactory.createCompoundBorder(
                jTextArea.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 5)));
        jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setBackground(Color.WHITE);
        jScrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        this.add(textLabel, BorderLayout.NORTH);
        this.add(jScrollPane, BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(HISTORY_WIDTH, HISTORY_HEIGHT);
    }
}
