package ui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static java.awt.BorderLayout.*;

/**
 * Created by Inpriron on 5/10/2017.
 */
public class ArrangeShipFrame extends JFrame implements MouseListener, MouseMotionListener {

    private static final int FRAME_WIDTH =600 ;
    private static final int FRAME_HEIGHT =600 ;
    private static final int NUMBER_ROWS =10 ;
    private static final int NUMBER_COLUMNS =10 ;
    private int deltaX;
    private int deltaY;
    private JLayeredPane layeredPane;
    private JPanel gameBoard;
    private JLabel ship;
    public ArrangeShipFrame()
    {

        Dimension frameSize=  new Dimension(FRAME_WIDTH,FRAME_HEIGHT);
        layeredPane= new JLayeredPane();
        layeredPane.setPreferredSize(frameSize);
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        this.setResizable( false );
        this.pack();
        this.setLocationRelativeTo( null );
        this.setVisible(true);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);
        getContentPane().add(layeredPane);
        gameBoard= new JPanel();
        gameBoard.setLayout(new GridLayout(NUMBER_ROWS,NUMBER_COLUMNS));
        gameBoard.setPreferredSize(frameSize);
        gameBoard.setBounds(0,0,FRAME_WIDTH,FRAME_HEIGHT);
        layeredPane.add(gameBoard,JLayeredPane.DEFAULT_LAYER);
        buildGameBoard();
        ImageIcon singleShip= new ImageIcon("resources/ship.png");
        ship= new JLabel(singleShip);
        JPanel temp=(JPanel)gameBoard.getComponent(0);
        temp.add(ship,CENTER);


    }

    private void buildGameBoard() {
        for(int i=0;i<NUMBER_ROWS;i++)
        {
            for(int j=0; j<NUMBER_COLUMNS;j++)
            {
                JPanel square= new JPanel(new BorderLayout());
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
        ship=null;
        Component component= gameBoard.findComponentAt(mouseEvent.getX(),mouseEvent.getY());
        if(component instanceof JPanel) return;
        Point parentLocatePoint= component.getLocation();
        deltaX=parentLocatePoint.x-mouseEvent.getX();
        deltaY=parentLocatePoint.y-mouseEvent.getY();
        ship=(JLabel)component;
        layeredPane.add(ship,JLayeredPane.DRAG_LAYER);
        ship.setLocation(mouseEvent.getX() + deltaX, mouseEvent.getY() + deltaY);
        layeredPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        layeredPane.setCursor(null);
        if (ship==null) return;
        ship.setVisible(false);
        layeredPane.remove(ship);
        ship.setVisible(true);
        int xMax = layeredPane.getWidth() - ship.getWidth();
        int x = Math.min(mouseEvent.getX(), xMax);
        x = Math.max(x, 0);

        int yMax = layeredPane.getHeight() - ship.getHeight();
        int y = Math.min(mouseEvent.getY(), yMax);
        y = Math.max(y, 0);
        System.out.println(x+" "+ y);
        Component component= gameBoard.findComponentAt(x,y);
        Container parent= (Container)component;
        parent.add(ship);
        parent.validate();

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if(ship==null) return;

        int x = mouseEvent.getX() ;
        int xMax = layeredPane.getWidth() - ship.getWidth();

        x = Math.min(x, xMax);
        x = Math.max(x, 0);

        int y = mouseEvent.getY() ;
        int yMax = layeredPane.getHeight() - ship.getHeight();
        y = Math.min(y, yMax);
        y = Math.max(y, 0);

        ship.setLocation(x, y);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}
