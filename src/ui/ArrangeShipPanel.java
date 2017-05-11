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


    public static final int NUMBER_ROWS = 10;
    public static final int NUMBER_COLUMNS = 10;
    public static final int SQUARE_LENGTH = 30;
    private  JPanel chooseShipPanel;

    private JLayeredPane layeredPane;
    private JPanel gameBoard;
    private JPanel shipBoard;
    private ArrayList<ShipLabel> shipList;
    private ShipLabel currentShip;
    private int deltaX;
    private int deltaY;

    public ArrangeShipPanel() {

        Dimension frameSize = new Dimension(SQUARE_LENGTH * NUMBER_ROWS, SQUARE_LENGTH * NUMBER_COLUMNS);
        setLayout(new BorderLayout());
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(SQUARE_LENGTH * (NUMBER_ROWS+5),(SQUARE_LENGTH*NUMBER_COLUMNS)));
        add(layeredPane);
        gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(NUMBER_ROWS, NUMBER_COLUMNS));

        gameBoard.setBounds(0, 0, (int) frameSize.getWidth(), (int) frameSize.getHeight());
        layeredPane.add(gameBoard, new Integer(0));
        chooseShipPanel= new JPanel();
        chooseShipPanel.setLayout(null);
        chooseShipPanel.setBounds(300,0,5*SQUARE_LENGTH,SQUARE_LENGTH*NUMBER_COLUMNS);
        chooseShipPanel.setBackground(Color.MAGENTA);
        layeredPane.add(chooseShipPanel,new Integer(0));
        buildGameBoard();
        shipBoard = new JPanel();
        shipBoard.setLayout(null);

         shipBoard.setBounds(0, 0, SQUARE_LENGTH * (NUMBER_ROWS+5), SQUARE_LENGTH*NUMBER_COLUMNS);
        shipBoard.setOpaque(false);
        layeredPane.add(shipBoard, new Integer(1));
        shipList = new ArrayList<>();

        ShipLabel shipLabel=  new ShipLabel( 5, Utils.loadImageFromRes("ship5_v.gif"));
        shipBoard.add(shipLabel);
        shipLabel.setBounds(SQUARE_LENGTH*NUMBER_COLUMNS+SQUARE_LENGTH,SQUARE_LENGTH,SQUARE_LENGTH,shipLabel.getLength()*SQUARE_LENGTH);

//        shipLabel=  new ShipLabel( 4, Utils.loadImageFromRes("ship4_v.gif"));
//        chooseShipPanel.add(shipLabel);
//        shipLabel.setBounds(SQUARE_LENGTH*2-SQUARE_LENGTH/3,SQUARE_LENGTH,shipLabel.getWidth(),shipLabel.getHeight());

//        shipLabel=  new ShipLabel( 4, Utils.loadImageFromRes("ship3_v.gif"));
//        chooseShipPanel.add(shipLabel);
//   shipLabel.setBounds(10,SQUARE_LENGTH,shipLabel.getWidth(),shipLabel.getHeight());
//        shipList.add(new ShipLabel( 5, Utils.loadImageFromRes("ship5_v.gif")));
//        shipList.add(new ShipLabel( 4, Utils.loadImageFromRes("ship4_v.gif")));
//        shipList.add(new ShipLabel( 3, Utils.loadImageFromRes("ship3_v.gif")));
//        shipList.add(new ShipLabel( 2, Utils.loadImageFromRes("ship3_v.gif")));
//        shipList.add(new ShipLabel( 2, Utils.loadImageFromRes("ship3_v.gif")));



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
        Component component = layeredPane.findComponentAt(mouseEvent.getX(), mouseEvent.getY());
        if (component instanceof JPanel) {
            return;
        }
        currentShip = (ShipLabel) component;
        deltaX = currentShip.getX() - mouseEvent.getX();
        deltaY = currentShip.getY() - mouseEvent.getY();
        currentShip.setLocation(mouseEvent.getX() + deltaX, mouseEvent.getY() + deltaY);
        layeredPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        layeredPane.setCursor(null);
        if (currentShip == null) return;

        int topLeftX = mouseEvent.getX() + deltaX + SQUARE_LENGTH/2;
        int topLeftY = mouseEvent.getY() + deltaY + SQUARE_LENGTH/2;
        System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
        System.out.println(deltaX + " " + deltaY);
        System.out.println(topLeftX + " " + topLeftY);
        currentShip.setThings(topLeftX / SQUARE_LENGTH, topLeftY / SQUARE_LENGTH);
        currentShip.setLocation(currentShip.getXPixel(), currentShip.getYPixel());
        currentShip = null;
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
            currentShip.setLocation(mouseEvent.getX() + deltaX, mouseEvent.getY() + deltaY);
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
