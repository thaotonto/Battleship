package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tonto on 5/10/2017.
 */
public class InstructionFrame extends JFrame {
    private int INTRUCTION_WIDTH = 500;
    private int INTRUCTION_HEIGHT = 500;


    public InstructionFrame() throws HeadlessException {
        setSize(INTRUCTION_WIDTH, INTRUCTION_HEIGHT);
        setAlwaysOnTop(true);
        setTitle("INSTRUCTION");
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
