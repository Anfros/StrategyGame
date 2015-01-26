//Import swing for JPanel and awt for Graphics
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.*;
import javax.swing.*;

public class Node extends JLabel
{
    //Where the Node is in space
    protected Point position;
    //References to the node's neighbors
    public LinkedList<Node> adjacent;
    //References to the creatures currently on the node
    public LinkedList<Creature> creatures;
    //References to the creatures that will be put on the node after all moves are done
    public LinkedList<Creature> creaturesBuffer;
    //The radius of the node
    protected int r;


    //The default constructor
    public Node()
    {
        this.setHorizontalAlignment(JLabel.CENTER);
        r = 0;
        position  = new Point();
        adjacent  = new LinkedList<Node>();
        creatures = new LinkedList<Creature>();
        creaturesBuffer = new LinkedList<Creature>();
    }

    //The constructor to give the node a starting point 
    public Node(Point p)
    {
        this.setHorizontalAlignment(JLabel.CENTER);
        r = 0;
        position  = new Point(p);
        adjacent  = new LinkedList<Node>();
        creatures = new LinkedList<Creature>();
        creaturesBuffer = new LinkedList<Creature>();
    }

    //Add a new neighbor to the adjacency list
    public static void addAdjacent(Node adj, Node adj2)
    {
        adj.adjacent.addLast(adj2);
        adj2.adjacent.addLast(adj);
    }

    //Add a creature to the node
    public void addCreature(Creature c)
    {
        creatures.add(c);
        c.parent = this;
    }

    //Remove a creature from the node
    public void removeCreature(Creature c)
    {
        creatures.remove(c);
    }

    //A method for setting the position
    public void setPosition(Point p)
    {
       this.position = new Point(p);
    }
    
    //Get the radius of the node
    public int getR()
    {
        return r;
    }
     
    //Get the position of the node
    public Point getPosition()
    {
        return position;
    }
     
    //Draw all the creatures occupying the node and draw all paths to adjacent nodes 
    public void draw(Graphics g)
    {
        //Draw a circle of various size depending on how many creature are on the node
        g.setColor(allegenceColor()); //Find the color of the node
        r = creatures.size()*2+20; //Grow the circle if the number of creatures on the node is large
        if(r > 30)
            r = 30;
        g.fillOval(position.x - r/2, position.y - r/2, r, r);
        this.setText(Integer.toString(creatures.size()));
        this.setBounds(position.x - 10, position.y - 10, 20, 20);
        this.setForeground(Color.WHITE);
    }

    public void drawLines(Graphics g)
    {
        g.setColor(Color.black);
        for(Node n : adjacent)
           g.drawLine(position.x, position.y, n.position.x, n.position.y); 
    }
    //Check the color the node is supposed to have
    public Color allegenceColor()
    {
        if(creatures.size() == 0)
            return Color.black;
        switch(creatures.getFirst().team())
        {
            case  0: return Color.black;
            case  1: return Color.blue;
            case -1: return Color.red;
        }       
        return Color.black;
    }

    //Calculate the influence of the node
    public double influence()
    {
        double infl = 0;
        for(Creature c : creatures)
            infl += c.influence()*c.team();
        return infl;
    }

    //Resolve the combat on the node
    public void resolve()
    {
        creaturesBuffer.addAll(creatures);
        if(creaturesBuffer.size() == 0)
            return;
        Creature c = creaturesBuffer.getFirst();
        creatures = new LinkedList<Creature>();
        Iterator<Creature> iter = creaturesBuffer.iterator();
        while(iter.hasNext())
        {
            Creature buffered = iter.next();
            iter.remove();

            //Two creatures from different teams kill each other
            if(c.team() != buffered.team())
            {
                creatures.remove(c);
                if(creatures.size() == 0)
                {
                    if(!iter.hasNext())
                        break;
                    creatures.add(iter.next());
                }
                c = creatures.getLast();
                continue;
            }

            c = buffered;
            creatures.add(c);
        }
        creaturesBuffer = new LinkedList<Creature>();
    }
}








