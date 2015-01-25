import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Iterator;
import java.awt.event.*;


public class Field extends JPanel implements ActionListener 
{
    //Keep track of all nodes, the players to listen for and when the turn ends
    public LinkedList<Node> nodes;
    private JButton turn;
    private LinkedList<Action> actions;
    private Player p1;
    private SpawnNode spawn1, spawn2;

    //Copy all relevant data and make a new area on the screen to display the playing field
    public Field(SpawnNode s1, SpawnNode s2, LinkedList<Node> nodes, JButton t)
    {
        this.turn = t;
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.green);
        this.nodes = nodes;
        actions = new LinkedList<Action>();
        p1 = new Player(this);
        addMouseListener(p1);
        spawn1 = s1;
        spawn2 = s2;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //Draw all lines between nodes
        for(Node node : nodes)
            node.drawLines(g);
        //Draw all nodes
        for(Node node : nodes)
            node.draw(g);
    }

    //Push an action to the list of actions to be performed
    public void addAction(Action a)
    {
        actions.add(a);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == turn)
        {
            //Spawn creatures on the spawn nodes
            spawn1.creatures.add(new Creature(spawn1, -1));
            spawn1.creatures.add(new Creature(spawn1, -1));
            spawn2.creatures.add(new Creature(spawn2, 1));
            
            //Resolve conflicts between actions and
            //Remove duplicate actions
            Iterator<Action> act1 = actions.iterator();
            while(act1.hasNext())
            {
                Action action = act1.next();
                Iterator<Action> act2 = actions.iterator();
                while(act2.hasNext())
                {
                    Action action2 = act2.next();
                    if(action2.isDuplicate(action))
                    {
                        actions.remove(action2);
                        continue;
                    }
                    action.solveConflict(action2);
                }
            }
            //Execute all actions
            for(Action action : actions)
                action.execute();
            actions = new LinkedList<Action>();
            for(Node node : nodes)
                node.resolve();
            repaint();
        }
    }

}
