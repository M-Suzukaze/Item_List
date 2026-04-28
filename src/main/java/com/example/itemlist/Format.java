package com.example.itemlist;

public class Format {

    public static String correctFormat(String s) {
        s = s.replace(' ', '-').
                replace(",", "").
                replace(":", "").
                replace("(", "").
                replace(")", "").
                replace("'", "").
                toLowerCase();
        return s;
    }
}
