public class Creature
{
    //Default team is no team
    //[-1 enemy] [0 neutral] [1 friendly] 
    private int myTeam = 0;
    //Default influence is 1
    private double infl = 1;
    //The creature has a parent node
    private Node parent; 
    
    //Fairly obvious constructor
    public Creature(Node parent, int team)
    {
        this.myTeam = team;
        this.parent = parent;
    }

    //Calculate the influence (other types of creature may have complex influence functions)
    public double influence()
    {
        return infl;
    }
    
    //Return the team allegence
    public int team()
    {
        return myTeam;
    }

}
