package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by tonto on 5/10/2017.
 */
public class InstructionFrame extends JFrame {
    private int INTRUCTION_WIDTH = 500;
    private int INTRUCTION_HEIGHT = 500;
    private JTextArea jTextArea;
    private JScrollPane jScrollPane;
    private JPanel instructionpanel;

    public InstructionFrame() throws HeadlessException {
        setSize(INTRUCTION_WIDTH, INTRUCTION_HEIGHT);
        setAlwaysOnTop(true);
        setTitle("INSTRUCTION");
        setVisible(true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        instructionpanel = new JPanel();
        setUpPanel();
    }

    private void setUpPanel() {
        MainContainer.getInstance().setInstructionOn(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {

               MainContainer.getInstance().setInstructionOn(false);
            }
        });

        instructionpanel.setLayout(new BorderLayout());
        this.add(instructionpanel, BorderLayout.CENTER);
        jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setEditable(false);
        jTextArea.setFont(new Font("CONSOLAS", Font.PLAIN, 15));
        jTextArea.setBackground(Color.WHITE);
        jTextArea.setBorder(BorderFactory.createCompoundBorder(
                jTextArea.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 5)));
        jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setBackground(Color.WHITE);
        jScrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLUE));
        File instruction = new File("resources/instruction.txt");
        try {
            Scanner scanner = new Scanner(instruction);
            while (scanner.hasNextLine()) {
                jTextArea.append("\n");
                jTextArea.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        jTextArea.setCaretPosition(0);

        add(jScrollPane);
    }


}
