package Lesson06_Building_Zomato.managers;

import java.util.ArrayList;
import java.util.List;

import Lesson06_Building_Zomato.models.Restraunt;

public class RestrauntManager {
    private List<Restraunt> restraunts = new ArrayList<>();
    private static RestrauntManager instance = null;

    private RestrauntManager() {
        // private constructor
    }

    public RestrauntManager getInstance() {
        if (instance == null) {
            instance = new RestrauntManager();
        }
        return instance;
    }

    public void addRestraunt(Restraunt restraunt) {
        restraunts.add(restraunt);
    }

    public List<Restraunt> searchByLocation(String location) {
        List<Restraunt> result = new ArrayList<>();
        for (Restraunt restraunt : result) {
            if (restraunt.getLocation().equalsIgnoreCase(location))
                result.add(restraunt);
        }
        return result;
    }
}
