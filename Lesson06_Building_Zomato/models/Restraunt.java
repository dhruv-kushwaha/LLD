package Lesson06_Building_Zomato.models;

import java.util.ArrayList;
import java.util.List;

public class Restraunt {
    private static int nextRestrauntId = 0;
    private int restrauntId;
    private String name;
    private String location;
    private List<MenuItem> menu = new ArrayList<>();

    public Restraunt(String name, String location) {
        this.name = name;
        this.location = location;
        restrauntId = ++nextRestrauntId;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String loc) {
        location = loc;
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }
}
