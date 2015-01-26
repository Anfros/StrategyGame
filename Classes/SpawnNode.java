import java.awt.Point;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Color;

public class SpawnNode extends Node
{
    //Keep track of the hitpoints of the base
    private double maxHp, hp;
    private int team;

    //Simple constructor
    public SpawnNode(Point p, double hp, int team)
    {
        super(p);
        maxHp = this.hp = hp;
        this.team = team;
    }

    //To check status
    public boolean isDead()
    {
        return hp <= 0;
    }

    //Override the resolve to take damage too
    public void resolve()
    {
        super.resolve();
        takeDamage();
    }
    
    //Draw the node
    public void draw(Graphics g)
    {
        super.draw(g);
        //underlay the red bar
        g.setColor(Color.RED);
        g.fillRect(position.x - 15, position.y - 20, 30, 5);
        //Overlay what is left of the green bar
        g.setColor(Color.GREEN);
        g.fillRect(position.x - 15, position.y - 20, (int)(30*(hp/maxHp)), 5);
    }

    //To resolve combat on the node, and subtract hp at the end of it
    public void takeDamage()
    {
        if(creatures.size() == 0)
            return;
        if(creatures.getFirst().team() != team)
        {
            hp -= creatures.size();
            creatures = new LinkedList<Creature>();
        }
    }
}

