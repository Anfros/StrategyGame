import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import java.awt.event.*;


public class Field extends JPanel implements ActionListener
{
    public LinkedList<Node> nodes;
    private JButton turn, move;
    private LinkedList<Action> actions;

    public Field(LinkedList<Node> nodes, JButton t, JButton m)
    {
        this.turn = t;
        this.move = m;
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.green);
        this.nodes = nodes;
        actions = new LinkedList<Action>();
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
                action.execute();
            actions = new LinkedList<Action>();
            for(Node node : nodes)
                node.resolve();
            repaint();
        }
        if(e.getSource() == move)
        {
            actions.add(new Move(nodes.getFirst(), nodes.getLast()));
        }
    }

}
