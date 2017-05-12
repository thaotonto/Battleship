package views;

import models.PlayerModel;
import models.Ship;
import models.ShipLabel;
import ui.ArrowPanel;
import ui.MainContainer;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Inpriron on 5/11/2017.
 */
public class PlayerView extends JPanel implements MouseMotionListener, MouseListener {


    public static final int NUMBER_ROWS = 10;
    public static final int NUMBER_COLUMNS = 10;
    public static final int SQUARE_LENGTH = 30;
    public static final int COLUMNS_FOR_CHOOSE_SHIP_PANEL = 5;
    private JPanel chooseShipPanel;

    private JLayeredPane layeredPane;
    private JPanel gameBoard;
    private JPanel shipBoard;
    private ArrowPanel arrowPanel;
    private ArrayList<ShipLabel> shipList;
    private ShipLabel currentShip;
    private int deltaX;
    private int deltaY;
    private JButton playBtn;
    private PlayerModel playerModel;
    public PlayerView() {
        setLayout(new BorderLayout());
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(SQUARE_LENGTH * (NUMBER_COLUMNS + COLUMNS_FOR_CHOOSE_SHIP_PANEL), (SQUARE_LENGTH * NUMBER_ROWS)));
        add(layeredPane);
        buildGameBoard();

        buildChooseShipPanel();

        buildShipBoard();

        shipList = new ArrayList<>();

        initShipLabel();

        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);
    }

    private void buildShipBoard() {
        shipBoard = new JPanel();
        shipBoard.setLayout(null);
        shipBoard.setBounds(0, 0, SQUARE_LENGTH * (NUMBER_COLUMNS + COLUMNS_FOR_CHOOSE_SHIP_PANEL), SQUARE_LENGTH * NUMBER_ROWS);
        shipBoard.setOpaque(false);
        layeredPane.add(shipBoard, new Integer(1));
    }

    private void buildChooseShipPanel() {
        chooseShipPanel = new JPanel();
        chooseShipPanel.setLayout(new GridBagLayout());
        chooseShipPanel.setBounds(SQUARE_LENGTH * NUMBER_COLUMNS, 0, COLUMNS_FOR_CHOOSE_SHIP_PANEL * SQUARE_LENGTH, SQUARE_LENGTH * NUMBER_ROWS);
        chooseShipPanel.setBackground(Color.gray);
        layeredPane.add(chooseShipPanel, new Integer(0));
        playBtn = new JButton("PLAY");
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] playerBoard = new int[NUMBER_ROWS][NUMBER_COLUMNS];
                List<Ship> ships = new ArrayList<>();
                Ship ship;
                for (int i = 0; i < shipList.size(); i++) {
                    ShipLabel shipLabel = shipList.get(i);
                    ship = new Ship(shipLabel.getLength());
                    if (shipLabel.isVertical()) {
                        ship.setOrientation(0);
                        for (int j = 0; j < shipLabel.getLength(); j++) {
                            int[] dot = new int[2];
                            dot[0] = shipLabel.getRow() + j;
                            dot[1] = shipLabel.getColumn();
                            playerBoard[dot[0]][dot[1]] = 1;
                            ship.getDotList().add(dot);
                        }
                    } else {
                        ship.setOrientation(0);
                        for (int j = 0; j < shipLabel.getLength(); j++) {
                            int[] dot = new int[2];
                            dot[0] = shipLabel.getRow() ;
                            dot[1] = shipLabel.getColumn()+j;
                            playerBoard[dot[0]][dot[1]] = 1;
                            ship.getDotList().add(dot);
                        }
                    }
                    ships.add(ship);
                }
                playerModel= new PlayerModel(playerBoard,ships);

                layeredPane.remove(chooseShipPanel);
                // TODO: add arrow panel
                arrowPanel = new ArrowPanel();
                arrowPanel.setBounds(SQUARE_LENGTH * NUMBER_COLUMNS, 0, COLUMNS_FOR_CHOOSE_SHIP_PANEL * SQUARE_LENGTH, SQUARE_LENGTH * NUMBER_ROWS);
                layeredPane.add(arrowPanel, new Integer(0));
                layeredPane.removeMouseListener(PlayerView.this);
                layeredPane.removeMouseMotionListener(PlayerView.this);
                MainContainer.getInstance().showPanel(MainContainer.TAG_GAME, true);
            }
        });
        chooseShipPanel.add(playBtn);
        playBtn.setVisible(false);
    }

    private void initShipLabel() {

        createShipLabel(5, (SQUARE_LENGTH * (NUMBER_COLUMNS + 1)), SQUARE_LENGTH / 3);
        createShipLabel(2, (SQUARE_LENGTH * (NUMBER_COLUMNS + 3)), SQUARE_LENGTH / 3);
        createShipLabel(3, (SQUARE_LENGTH * (NUMBER_COLUMNS + 3)), SQUARE_LENGTH / 3 + SQUARE_LENGTH * 2 + SQUARE_LENGTH / 3);
        createShipLabel(3, (SQUARE_LENGTH * (NUMBER_COLUMNS + 1)), SQUARE_LENGTH / 3 + SQUARE_LENGTH * 5 + SQUARE_LENGTH / 3);
        createShipLabel(4, (SQUARE_LENGTH * (NUMBER_COLUMNS + 3)), SQUARE_LENGTH / 3 + SQUARE_LENGTH * 5 + SQUARE_LENGTH / 3);

    }

    private void createShipLabel(int length, int defaultX, int defaultY) {
        ShipLabel shipLabel = new ShipLabel(length, defaultX, defaultY);
        shipBoard.add(shipLabel);
        shipList.add(shipLabel);
        shipLabel.setBounds(defaultX, defaultY, SQUARE_LENGTH, length * SQUARE_LENGTH);
    }

    private void buildGameBoard() {
        gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(NUMBER_ROWS, NUMBER_COLUMNS));
        gameBoard.setBounds(0, 0, SQUARE_LENGTH * NUMBER_ROWS, SQUARE_LENGTH * NUMBER_COLUMNS);
        layeredPane.add(gameBoard, new Integer(0));
        for (int i = 0; i < NUMBER_ROWS; i++) {
            for (int j = 0; j < NUMBER_COLUMNS; j++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setBackground(Color.white);
                gameBoard.add(square);
                square.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        if (SwingUtilities.isRightMouseButton(mouseEvent)) {

            if (mouseEvent.getX() >= 0 && mouseEvent.getX() <= SQUARE_LENGTH * NUMBER_COLUMNS) {
                Component component = layeredPane.findComponentAt(mouseEvent.getX(), mouseEvent.getY());
                if (component instanceof JPanel)
                    return;
                currentShip = (ShipLabel) component;
                if (currentShip.isVertical())
                    currentShip.toHorizontal();
                else
                    currentShip.toVertical();

            }
        }
    }

    //    public void print()
//    {
//        for (int i = 0; i < shipList.size(); i++) {
//            System.out.println(" "+shipList.get(i).getRow());
//            System.out.println(" "+shipList.get(i).getColumn());
//        }
//        System.out.println("_______________________________");
//
//    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (SwingUtilities.isRightMouseButton(mouseEvent)) return;
        Component component = layeredPane.findComponentAt(mouseEvent.getX(), mouseEvent.getY());
        if (component instanceof JPanel) {
            return;
        }

        currentShip = (ShipLabel) component;
        currentShip.setLastX(currentShip.getXPixel());
        currentShip.setLastY(currentShip.getYPixel());
        deltaX = currentShip.getX() - mouseEvent.getX();
        deltaY = currentShip.getY() - mouseEvent.getY();

        currentShip.setLocation(mouseEvent.getX() + deltaX, mouseEvent.getY() + deltaY);
        layeredPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));


    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (SwingUtilities.isRightMouseButton(mouseEvent)) return;
        layeredPane.setCursor(null);
        if (currentShip == null) return;
        if (mouseEvent.getX() > SQUARE_LENGTH * NUMBER_COLUMNS) {
            currentShip.reset();
            currentShip.setLocation(currentShip.getDefaultX(), currentShip.getDefaultY());
            layeredPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            playBtn.setVisible(false);
            currentShip = null;
            return;

        }
        int topLeftX = mouseEvent.getX() + deltaX + SQUARE_LENGTH / 2;
        int topLeftY = mouseEvent.getY() + deltaY + SQUARE_LENGTH / 2;

        currentShip.setThings(topLeftY / SQUARE_LENGTH, topLeftX / SQUARE_LENGTH);
        if (checkIntersect(currentShip)) {
            System.out.println("intersects");
            currentShip.setBackToLastLocation();
        }
        currentShip.setLocation(currentShip.getXPixel(), currentShip.getYPixel());
        currentShip = null;
        playBtn.setVisible(isAllShipDeployed());

    }

    private boolean isAllShipDeployed() {
        for (int i = 0; i < shipList.size(); i++) {
            if (shipList.get(i).getXPixel() == shipList.get(i).getDefaultX()) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    private boolean checkIntersect(ShipLabel curShip) {
        for (int i = 0; i < shipList.size(); i++) {
            if (shipList.get(i) != curShip) {
                if (curShip.isIntersect(shipList.get(i).getRectangle())) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (SwingUtilities.isRightMouseButton(mouseEvent)) return;
        if (currentShip != null) {
            int x = mouseEvent.getX() + deltaX;
            int y = mouseEvent.getY() + deltaY;
            if (currentShip.isVertical()) {
                x = Math.max(0, x);
                x = Math.min(x, layeredPane.getWidth() - SQUARE_LENGTH);
                y = Math.max(0, y);
                y = Math.min(y, layeredPane.getHeight() - SQUARE_LENGTH * currentShip.getLength());
            } else {
                x = Math.max(0, x);
                x = Math.min(x, layeredPane.getWidth() - SQUARE_LENGTH * currentShip.getLength());
                y = Math.max(0, y);
                y = Math.min(y, layeredPane.getHeight() - SQUARE_LENGTH);
            }
            currentShip.setLocation(x, y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PlayerView());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
