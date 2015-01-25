import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Math.*;


//The player class, can be extended later
public class Player implements MouseListener
{
    //For keeping track on what node is currently clicked
    private Node selected;
    //For knowing about the complete list of nodes etc. etc. etc.
    private Field parent;

    //Initialize a new player
    public Player(Field f)
    {
        parent = f;
        selected = null;
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
                    }
                }
            }
        }
        selected = null;
        parent.repaint();
    }    
   
    //For drawing a circle around a selected node
    public void draw(Graphics g)
    {
        if(selected == null)
            return;
        g.setColor(Color.YELLOW);
        Point position = selected.getPosition();
        int r = selected.getR()+2;
        g.fillOval(position.x-r/2, position.y-r/2, r, r);

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

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
    }
}
