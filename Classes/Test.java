import java.awt.*;
import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;


public class Test extends JFrame
{
    private Field f;
    private JButton b;
    private Node n, n2, n3;
    private JPanel pan;

    public Test()
    {
        pan = new JPanel();
        n = new Node(new Point(110, 110));
        n2 = new Node(new Point(500, 500));
        n3 = new Node(new Point(500, 300));
        LinkedList<Node> temp1 = new LinkedList<Node>();
        temp1.add(n);
        temp1.add(n2);
        temp1.add(n3);
        Hex h = new Hex(temp1);
        LinkedList<Hex> hxs = new LinkedList<Hex>();
        hxs.add(h);
        SpawnNode s1 = new SpawnNode(new Point(30, 30), 10, -1);
        SpawnNode s2 = new SpawnNode(new Point(580, 580), 10, 1);
        Node.addAdjacent(n, s1);
        Node.addAdjacent(n2, s2);
        Node.addAdjacent(n2, n);
        Node.addAdjacent(n3, n);
        Node.addAdjacent(n3, n2);
        b = new JButton();
        b.setText("Turn");
        setTitle("test");
        LinkedList<Node> temp = new LinkedList<Node>();
        temp.addLast(n);
        temp.addLast(n2);
        temp.addLast(n3);
        temp.add(s1);
        temp.addLast(s2);
        f = new Field(s1, s2, temp, b, hxs);
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
