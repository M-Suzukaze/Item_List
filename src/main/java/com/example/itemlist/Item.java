package com.example.itemlist;

import java.util.Arrays;

public class Item {
    public String name;
    public String[] desc;
    public Equipment_Category equipment_category;
    public Cost cost;
    public Properties[] properties;
    public double weight;
    public String url;

    public String toString() {
        String s;
        String d = desc[0];
        String p = Arrays.toString(this.properties);

        s = this.name + "\n" + "Description: " + d + "\n" +
        "Equipment category: " + this.equipment_category + "\n" +
        this.cost + " gold coins" + "\n" + "Properties: " + p +
        "\n" + "Weight: " + this.weight + " lbs \n" + "Link: https://www.dnd5eapi.co" + this.url;
        return s;
    }
}
