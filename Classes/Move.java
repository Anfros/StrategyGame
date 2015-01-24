import java.util.*;

public class Move extends Action
{
    //The nodes to move between
    public Node origin, target;

    //Create a new Move
    public Move(Node origin, Node target)
    {
        this.origin = origin;
        this.target = target;
    }

    //Move all creatures from origin to target
    @Override
    public void execute()
    {
        for(Creature c : origin.creatures)
        {
            c.parent = target;
            target.creaturesBuffer.add(c);
        }
        origin.creatures = new LinkedList<Creature>();
    }

}
