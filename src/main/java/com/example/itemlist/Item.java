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
        s = this.name + "\n" + Arrays.toString(this.desc) + "\n" +
        "Equipment category: " + this.equipment_category + "\n" +
        this.cost + " gold coins" + "\n" + "properties: " + Arrays.toString(this.properties) +
        "\n" + "Weight: " + this.weight + " lbs \n" + "Link: https://www.dnd5eapi.co" + this.url;
        return s;
    }
}
