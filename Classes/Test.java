import java.awt.*;
import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;


public class Test extends JFrame
{
    private Field f;
    private JButton b;
    private Node n, n2;
    private JPanel pan;

    public Test()
    {
        pan = new JPanel();
        n = new Node(new Point(100, 100));
        n2 = new Node(new Point(500, 500));
        SpawnNode s1 = new SpawnNode(new Point(20, 20), 10, -1);
        SpawnNode s2 = new SpawnNode(new Point(580, 580), 10, 1);
        s1.addAdjacent(n);
        s2.addAdjacent(n2);
        n.addAdjacent(n2);
        n.addAdjacent(s1);
        n2.addAdjacent(n);
        n2.addAdjacent(s2);
        b = new JButton();
        b.setText("Turn");
        setTitle("test");
        LinkedList<Node> temp = new LinkedList<Node>();
        temp.addLast(n);
        temp.addLast(n2);
        temp.add(s1);
        temp.addLast(s2);
        f = new Field(s1, s2, temp, b);
        b.addActionListener(f);
        pan.setLayout(new FlowLayout());
        pan.add(b);
        add(f, BorderLayout.CENTER);
        add(pan, BorderLayout.EAST);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] argv)
    {
        Test t = new Test();
    }
}
