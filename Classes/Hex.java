import java.util.LinkedList;
import java.lang.Math;
import java.awt.Graphics;

public class Hex
{
    //A list of all its nodes
    public LinkedList<Node> nodes;
    //Last influence
    private double lastInfl;

    //Construct a node
    public Hex(LinkedList<Node> nds)
    {
        nodes = nds;
        lastInfl = 0;
    }

    //return what team the hex is on
    public int team()
    {
        double influence = 0;
        for(Node n : nodes)
            influence += n.influence();
        if(Math.signum(influence) == Math.signum(lastInfl))
            return (int)Math.signum(influence);
        lastInfl = influence;
        return (int)Math.signum(influence);
    }
}
