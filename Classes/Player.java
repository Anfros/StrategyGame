import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseListener;
import java.lang.Math.*;
import javax.swing.Timer;

//The player class, can be extended later
public class Player extends MouseInputAdapter implements ActionListener 
{
    //For keeping track on what node is currently clicked
    private Node selected, targeted;
    //For knowing about the complete list of nodes etc. etc. etc.
    private Field parent;
    //To keep track of if the mouse is dragging or not
    private boolean drag;
    //To keep track of the start position of a drag
    private Point dragP;
    //To draw a rectangle
    private Rectangle dragRectangle;
    //To keep track of how long the draw time is
    private Timer timer;

    //Initialize a new player
    public Player(Field f)
    {
        timer = new Timer(200, this);
        timer.stop();
        dragP = new Point(0, 0);
        parent = f;
        selected = null;
        drag = false;
        dragRectangle = new Rectangle(0, 0, 0, 0);
    }

    public void mouseClicked(MouseEvent e)
    {
        Point mousePosition = e.getPoint();
        if(selected == null && e.getButton() == MouseEvent.BUTTON1) //We need to select a node
        {
            for(Node n : parent.nodes)
            {
                //This is the point you're looking for?
                Point nodePosition = n.getPosition();
                int radius = n.getR();
                int dx = (int)nodePosition.getX() - (int)mousePosition.getX(),
                    dy = (int)nodePosition.getY() - (int)mousePosition.getY();
                if(Math.sqrt(dx*dx+dy*dy)<radius) //If we are close to a node, select it and finish
                {
                    selected = n;
                    parent.repaint();
                    return;
                }
            }
        }
        else if (selected != null && e.getButton() == MouseEvent.BUTTON3) //We have a node selected, now we see if we have clicked another node and if so, can we move to it in one move
        {
            targeted = null;
            for(Node n : parent.nodes)
            {
                //This is the point you're looking for?
                Point nodePosition = n.getPosition();
                int radius = n.getR();
                int dx = (int)nodePosition.getX() - (int)mousePosition.getX(),
                    dy = (int)nodePosition.getY() - (int)mousePosition.getY();
                if(Math.sqrt(dx*dx+dy*dy)<radius)
                {
                    if(selected.adjacent.contains(n))//We have found a node and it is adjacent to the selected node
                    {
                        parent.addAction(new Move(selected, n));//Move to the node from the selected node
                        targeted = n;
                        timer.start();
                        parent.repaint();
                        return;
                    }
                }
            }
        }
       
        selected = null;
        targeted = null;
        parent.repaint();
    }    
    
    //For stopping the timer
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == timer)
           timer.stop();
        else
            return;
        selected = null;
        targeted = null;
        parent.repaint();
    }

    //For drawing a circle around a selected node
    public void draw(Graphics g)
    {
        
        if(selected == null)
            return;
        g.setColor(Color.ORANGE);
        Point position = selected.getPosition();
        int r = selected.getR()+6;
        g.fillOval(position.x-r/2, position.y-r/2, r, r);
        if(targeted == null)
            return;
        g.setColor(Color.GREEN);
        position = targeted.getPosition();
        r = targeted.getR()+6;
        g.fillOval(position.x-r/2, position.y-r/2, r, r);

    }

    //For drawing the rectangle
    public void draw2(Graphics g)
    {
        if(drag)
        {
            g.setColor(Color.BLACK);
            ((Graphics2D)g).draw(dragRectangle);
        }
    }
    //Todo: fix to make sure clicks are not registered outside the frame
    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    //Same as above
    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    //Stop dragging
    public void mouseReleased(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON3)
            return;
        drag = false;
        parent.repaint();
    }

    //Start dragging
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON3)
            return;
        if(selected != null)
            selected = null;
        drag = true;
        dragP = e.getPoint();
        dragRectangle = new Rectangle(dragP.x, dragP.y, 0, 0);
        editSelect(e);
        parent.repaint();
    }
    
    public void mouseDragged(MouseEvent e)
    {
        editSelect(e);
        parent.repaint();
    }

    public void editSelect(MouseEvent e)
    {
        int xSize = e.getX() - dragP.x,
            ySize = e.getY() - dragP.y;
        
        fixDragRectangle(xSize, ySize);

        if(selected != null)
            return;
        for(Node n : parent.nodes)
        {
            //This is the point you're looking for?
            Point np = n.getPosition();
            if(dragRectangle.contains(np))
            {
                selected = n;
                return;
            }
        } 
    }

    //Make sure drag rect always has the top left as its corner
    private void fixDragRectangle(int xSize, int ySize)
    {
        int x = dragP.x, y = dragP.y;
        x = Math.min(x, x+xSize);
        y = Math.min(y, y+ySize);
        xSize = Math.abs(xSize);
        ySize = Math.abs(ySize);
        dragRectangle = new Rectangle(x, y, xSize, ySize);
    }

}













