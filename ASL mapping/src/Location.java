import java.util.ArrayList;

public class Location {
    private String name;
    private ArrayList<String> nextTo;

    private boolean beenTo;
    private int costToReach;
    private String reachedFrom;

    public Location(String name)
    {
        this.name = name;
        nextTo = new ArrayList<>();
        costToReach = Integer.MAX_VALUE;
    }

    public String getName(){
        return name;
    }

    public void addNextTo(String nextTo){
        this.nextTo.add(nextTo);
    }

    public ArrayList<String> getNextTo(){
        return nextTo;
    }

    public boolean getBeenTo(){
        return beenTo;
    }

    public void setBeenTo(boolean beenTo){
        this.beenTo = beenTo;
    }

    public void setCostToReach(int cost){
        costToReach = cost;
    }

    public int getCostToReach(){
        return costToReach;
    }

    public void setReachedFrom(String reachedFrom){
        this.reachedFrom = reachedFrom;
    }

    public String getReachedFrom(){
        return reachedFrom;
    }
}
