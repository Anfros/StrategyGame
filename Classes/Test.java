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
        n.addAdjacent(n2);
        n2.addAdjacent(n);
        b = new JButton();
        b.setText("Turn");
        setTitle("test");
        LinkedList<Node> temp = new LinkedList<Node>();
        temp.addLast(n);
        temp.addLast(n2);
        f = new Field(temp, b);
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
