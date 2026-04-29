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
        String d;

        try {
            d = desc[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            d = "";
        }

        String p = "";

        try {
            for (int i = 0; i < this.properties.length; i++) {
                String temp = properties[i].toString();
                temp = temp + ", ";
                p = p + temp;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("there is no array");
        }
        try {
            p = p.substring(0, p.length() - 2);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("can't make substring");
        }

        s = this.name + "\n" + "Description: " + d + "\n" +
        "Equipment Category: " + this.equipment_category + "\n" +
        this.cost + " gold coins" + "\n" + "Properties: " + p +
        "\n" + "Weight: " + this.weight + " lbs \n" + "Link: https://www.dnd5eapi.co" + this.url;
        return s;
    }
}
