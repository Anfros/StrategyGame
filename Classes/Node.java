//Import swing for JPanel and awt for Graphics
import java.awt.Graphics;
import java.awt.Color;
import java.util.*;

public class Node
{
    //Where the Node is in space
    private Point position;
    //References to the node's neighbors
    private LinkedList<Node> adjacent;
    //References to the creatures currently on the node
    public LinkedList<Creature> creatures;
    //The radius of the node
    private int r;


    //The default constructor
    public Node()
    {
        r = 0;
        position  = new Point();
        adjacent  = new LinkedList<Node>();
        creatures = new LinkedList<Creature>();
    }

    //The constructor to give the node a starting point 
    public Node(Point p)
    {
        r = 0;
        position  = new Point(p);
        adjacent  = new LinkedList<Node>();
        creatures = new LinkedList<Creature>();
    }

    //Add a new neighbor to the adjacency list
    public void addAdjacent(Node adj)
    {
        adjacent.addLast(adj);
    }

    //Add a creature to the node
    public void addCreature(Creature c)
    {
        creatures.add(c);
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
   
    //Draw all the creatures occupying the node and draw all paths to adjacent nodes 
    public void draw(Graphics g)
    {
        //Draw a circle of various size depending on how many creature are on the node
        g.setColor(allegenceColor()); //Find the color of the node
        r = creatures.size()*2; //Grow the circle if the number of creatures on the node is large
        g.fillOval(position.x - r/2- 1, position.y - r/2 - 1, 2+r, 2+r);
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

}
