public class Path {

    private String location1, location2;
    private int time;

    public Path(String location1, String location2, int time){
        this.location1 = location1;
        this.location2 = location2;
        this.time = time;
    }

    public String getLocation1() {
        return location1;
    }

    public String getLocation2(){
        return location2;
    }

    public int getTime(){
        return time;
    }
}
