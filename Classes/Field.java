import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import java.awt.event.*;


public class Field extends JPanel implements ActionListener
{
    public LinkedList<Node> nodes;

    public Field(LinkedList<Node> nodes)
    {
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.green);
        this.nodes = nodes;
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
        Node n = nodes.getFirst();
        n.creatures.add(new Creature(n, -1));
        n = nodes.getLast();
        n.creatures.add(new Creature(n, 1));
        repaint();
    }

}
