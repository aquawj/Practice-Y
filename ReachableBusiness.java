package Yelp;

import java.util.*;

class Business{
    String name;
    Map<Business, Integer> nearbyBusinesses;

    public Business(String name) {
        this.name = name;
        this.nearbyBusinesses = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<Business, Integer> getNearbyBusinesses() {
        return nearbyBusinesses;
    }
}

public class ReachableBusiness {

    public static List<String> find(Business start, int dis){
        List<String> res = new ArrayList<>();
        dfs(start, dis, res);
        return res;
    }

    public static void dfs(Business start, int dis, List<String> res){
        if(dis < 0){
            res.add(start.getName());
            return;
        }
        for(Business next : start.getNearbyBusinesses().keySet()){
            dfs(next, dis - start.getNearbyBusinesses().get(next), res);
        }
    }



}
