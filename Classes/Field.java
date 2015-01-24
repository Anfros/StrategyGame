import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import java.awt.event.*;


public class Field extends JPanel implements ActionListener 
{
    public LinkedList<Node> nodes;
    private JButton turn;
    private LinkedList<Action> actions;
    private Player p1;

    public Field(LinkedList<Node> nodes, JButton t)
    {
        this.turn = t;
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.green);
        this.nodes = nodes;
        actions = new LinkedList<Action>();
        p1 = new Player(this);
        addMouseListener(p1);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(Node node : nodes)
            node.drawLines(g);
        for(Node node : nodes)
            node.draw(g);
    }

    public void addAction(Action a)
    {
        actions.add(a);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == turn)
        {
            Node n = nodes.getFirst();
            n.creatures.add(new Creature(n, -1));
            n.creatures.add(new Creature(n, -1));
            n = nodes.getLast();
            n = nodes.getLast();
            n.creatures.add(new Creature(n, 1));
            for(Action action : actions)
            {
                for(Action action2 : actions)
                    action.solveConflict(action2);
            }
            for(Action action : actions)
                action.execute();
            actions = new LinkedList<Action>();
            for(Node node : nodes)
                node.resolve();
            repaint();
        }
    }

}
