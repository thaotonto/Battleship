package ui;

import models.ShipLabel;
import utils.Utils;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Created by Inpriron on 5/11/2017.
 */
public class ArrangeShipPanel extends JPanel implements MouseMotionListener, MouseListener {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    private static final int NUMBER_ROWS = 10;
    private static final int NUMBER_COLUMNS = 10;
    private JLayeredPane layeredPane;
    private JPanel gameBoard;
    private JPanel shipBoard;
    private ArrayList<ShipLabel> shipList;
    private ShipLabel currentShip;
    private int deltaX;
    private int deltaY;
    public ArrangeShipPanel() {

        Dimension frameSize = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(new BorderLayout());
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(frameSize);
        add(layeredPane);
        gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(NUMBER_ROWS, NUMBER_COLUMNS));
        gameBoard.setPreferredSize(frameSize);
        gameBoard.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        layeredPane.add(gameBoard, new Integer(0));
        buildGameBoard();
        shipBoard = new JPanel();
        shipBoard.setLayout(null);
        shipBoard.setPreferredSize(frameSize);
        shipBoard.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        shipBoard.setOpaque(false);
        layeredPane.add(shipBoard, new Integer(1));
        shipList = new ArrayList<>();
        shipList.add(new ShipLabel(0, 0, true, 5, Utils.loadImageFromRes("ship5_v.gif")));
        shipList.add(new ShipLabel(2, 2, true, 4, Utils.loadImageFromRes("ship4_v.gif")));
        for (int i = 0; i < shipList.size(); ++i) {
            shipBoard.add(shipList.get(i));
        }
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

    }

    private void buildGameBoard() {
        for (int i = 0; i < NUMBER_ROWS; i++) {
            for (int j = 0; j < NUMBER_COLUMNS; j++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setBackground(Color.blue);
                gameBoard.add(square);
                square.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Component component = shipBoard.findComponentAt(mouseEvent.getX(), mouseEvent.getY());
        if (component instanceof JPanel) {
            return;
        }
        currentShip = (ShipLabel) component;
        deltaX= currentShip.getX()-mouseEvent.getX();
        deltaY= currentShip.getY()-mouseEvent.getY();
        currentShip.setLocation(mouseEvent.getX()+deltaX,mouseEvent.getY()+deltaY);
        layeredPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        layeredPane.setCursor(null);
        if (currentShip==null) return;

        int topLeftX= mouseEvent.getX()+deltaX+30;
        int topLeftY= mouseEvent.getY()+ deltaY+30;
        System.out.println(mouseEvent.getX()+" "+ mouseEvent.getY());
        System.out.println(deltaX+" "+ deltaY);
        System.out.println(topLeftX+ " "+topLeftY);
        currentShip.setThings(topLeftX/60,topLeftY/60);
        currentShip.setLocation(currentShip.getXPixel(),currentShip.getYPixel());
        currentShip=null;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (currentShip != null) {
            currentShip.setLocation(mouseEvent.getX()+deltaX,mouseEvent.getY()+deltaY);
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ArrangeShipPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
