import java.awt.*;
import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;


public class Test extends JFrame
{
    private Field f;
    private JButton b, b1;
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
        b1 = new JButton();
        b.setText("Turn");
        b1.setText("Move");
        setTitle("test");
        LinkedList<Node> temp = new LinkedList<Node>();
        temp.addLast(n);
        temp.addLast(n2);
        f = new Field(temp, b, b1);
        b.addActionListener(f);
        b1.addActionListener(f);
        pan.setLayout(new FlowLayout());
        pan.add(b);
        pan.add(b1);
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
