import java.util.*;

public class Move extends Action<Move>
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

    //If two nodes move along the same line, there will be trouble
    public void solveConflict(Move m)
    {
        if(m.target == this.origin && this.target == m.origin)
        {
            if(this.origin.creatures.size() == 0 || m.origin.creatures.size() == 0) //No conflict to resolve
                return;
            if(this.origin.creatures.getFirst().team() == m.origin.creatures.getFirst().team())
                return;
            if(this.origin.creatures.size() >= m.origin.creatures.size()) //Kill enough creatures to account for the imbalance
            {
                for(int i = 0; i < m.origin.creatures.size(); i++)
                {
                    this.origin.creatures.poll();
                }
                m.origin.creatures = new LinkedList<Creature>();
            }
        }
    }

}
