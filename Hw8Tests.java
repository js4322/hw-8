package hw_8;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Hw8Tests {
    public static void main(String[] args) {
        MyHashMap<String, Integer> deathRate = new MyHashMap<String, Integer>();
        System.out.println("Clear deathRate.size() = " + deathRate.size());
        deathRate.put("coronar deseases", 100);
        deathRate.put("transport incidents", 70);
        deathRate.put("drugs",4);
        deathRate.put("meteor hit",1);
        System.out.println("deathRate.size() = " + deathRate.size());
        System.out.println("deathRate.get(\"transport incidents\") = " + deathRate.get("transport incidents"));
        System.out.println("deathRate.get(\"coronar deseases\") = " + deathRate.get("coronar deseases"));
        System.out.println("deathRate.get(\"drugs\") = " + deathRate.get("drugs"));
        System.out.println("deathRate.get(\"meteor hit\") = " + deathRate.get("meteor hit"));
        System.out.println("deathRate.get(\"not presented key\") = " + deathRate.get("not presented key"));
        deathRate.remove("meteor hit");
        System.out.println("deathRate.remove(\"meteor hit\");");
        System.out.println("deathRate.get(\"meteor hit\") = " + deathRate.get("meteor hit"));
        System.out.println("deathRate.size() = " + deathRate.size());
    }

}