import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static ArrayList<Location> locations;

    private static ArrayList<Path> paths;

    public static void readFile(String file){
        try{
            List<String> strings = Files.readAllLines(Paths.get(file));
            for (int i = 1; i < strings.size(); i++){
                String[] chunks = strings.get(i).split(",\\s*");
                if(getLocation(chunks[0]) == null)
                    locations.add(new Location(chunks[0]));
                if(getLocation(chunks[1]) == null)
                    locations.add(new Location(chunks[1]));
                getLocation(chunks[0]).addNextTo(chunks[1]);
                getLocation(chunks[1]).addNextTo(chunks[0]);
                paths.add(new Path(chunks[0], chunks[1], (int) Double.parseDouble(chunks[2])));
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        locations = new ArrayList<>();
        paths = new ArrayList<>();
        readFile("Data.txt");
        System.out.println(dijkstra("Cs Room", "Nurse"));
    }

    public static Location getLocation(String name){
        for(int i = 0; i<locations.size(); i++){
            if(locations.get(i).getName().equals(name))
                return locations.get(i);
        }
        return null;
    }

    public static int getCost(String location1, String location2){
        for(int i = 0; i<paths.size(); i++){
            if((paths.get(i).getLocation1().equals(location1) || paths.get(i).getLocation1().equals(location2))&&(paths.get(i).getLocation2().equals(location1) || paths.get(i).getLocation2().equals(location2))){
                return paths.get(i).getTime();
            }
        }
        return Integer.MAX_VALUE;
    }

    public static ArrayList<String> dijkstra(String start, String end){
        getLocation(start).setCostToReach(0);
        ArrayList<String> queueKey = new ArrayList<>();
        queueKey.add(start);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(getLocation(start).getCostToReach());
        while(!queue.isEmpty()){
            int cost = queue.remove();
            String node = "";
            Location nodeLocation = null;
            for(int i = 0; i<queueKey.size(); i++){
                if(getLocation(queueKey.get(i)).getCostToReach() == cost)
                    node = queueKey.get(i);
                    nodeLocation = getLocation(node);
            }
            getLocation(node).setBeenTo(true);
            for(int i = 0; i<nodeLocation.getNextTo().size(); i++){
                Location nextToNodeLoaction = getLocation(nodeLocation.getNextTo().get(i));
                if(!nextToNodeLoaction.getBeenTo()) {
                    if (nextToNodeLoaction.getCostToReach() > getCost(node, nodeLocation.getNextTo().get(i))) {
                        if(queueKey.contains(nodeLocation.getNextTo().get(i))){
                            queue.remove(nextToNodeLoaction.getCostToReach());
                            queueKey.remove(nodeLocation.getNextTo().get(i));
                        }
                        nextToNodeLoaction.setCostToReach(cost + getCost(node, nodeLocation.getNextTo().get(i)));
                        nextToNodeLoaction.setReachedFrom(node);
                        queue.add(cost + getCost(node, nodeLocation.getNextTo().get(i)));
                        queueKey.add(nodeLocation.getNextTo().get(i));
                    }
                }
            }
        }
        Location endLocation = getLocation(end);
        if(!endLocation.getBeenTo())
            return null;
        Deque<String> stack = new ArrayDeque<>();
        Location currentLocation = endLocation;
        stack.push(end);
        while(currentLocation.getReachedFrom() != start) {
            stack.push(currentLocation.getReachedFrom());
            currentLocation = getLocation(currentLocation.getReachedFrom());
        }
        stack.push(start);
        return new ArrayList<>(stack);
    }
}