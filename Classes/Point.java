public class Point
{
    //This class holds points in the x-y plane
    public int x, y;
    public Point()
    {
        x = y = 0;
    }

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(Point p)
    {
        this.x = p.x;
        this.y = p.y;
    }

}
