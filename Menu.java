package Yelp;

import java.util.*;
import java.util.List;
class Meal{
    String name;
    List<String> ingredients;
    public Meal(String name){
        this.name = name;
        ingredients = new ArrayList<>();
    }
}

public class Menu {
    public int uniqueMeal(List<Meal> meals){
        Set<List<String>> set = new HashSet<>();
        for (Meal m : meals){
            Collections.sort(m.ingredients);
            set.add(new ArrayList<>(m.ingredients));
        }
        return set.size();
    }
}
