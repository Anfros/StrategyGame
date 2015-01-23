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

    //The default constructor
    public Node()
    {
        position = new Point();
        adjacent = new LinkedList<Node>();
    }

    //The constructor to give the node a starting point 
    public Node(Point p)
    {
        position = new Point(p);
        adjacent = new LinkedList<Node>();
    }

    //Add a new neighbor to the adjacency list
    public void addAdjacent(Node adj)
    {
        adjacent.addLast(adj);
    }

    //Add a creature to the node
    public void addCreature(Creature c)
    {
        creatures.addLast(c);
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
        int r = creatures.size()*2; //Grow the circle if the number of creatures on the node is large
        g.fillOval(position.x, position.y, 2+r, 2+r);

        //Draw lines to all adjacent nodes
        for(Node node : adjacent)
            g.drawLine(position.x, position.y, node.position.x, node.position.y);
    }

    //Check the color the node is supposed to have
    public Color allegenceColor()
    {
        if(creatures.size() == 0)
            return Color.black;
        if(creatures.poll().team = 1)
            return Color.blue;
        return Color.red;
    }

    //Calculate the influence of the node
    public double influence()
    {
        double infl = 0;
        for(Creature c : creatures)
            infl += c.influence();
        return infl;
    }

}
