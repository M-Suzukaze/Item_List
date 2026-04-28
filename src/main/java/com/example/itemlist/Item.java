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
        "\n" + this.weight + " pounds \n" + this.url;
        return s;
    }
}
