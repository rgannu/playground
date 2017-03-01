package com.utopian.samples;

import java.util.HashMap;

/**
 * Created by garamasu on 15-Sep-15.
 */
public class TestInteger {
    public static void main(String[] args) {
        Integer i1 = 120;
        Integer i2 = 120;

        if (i1 == i2) {
            System.out.println("i1 and i2 is equal");
        } else {
            System.out.println("i1 and i2 is not equal ");
        }

        HashMap<Integer, String> map = new HashMap<Integer, String>();
        Integer key = new Integer(1);
        map.put(key, "a1");
        map.put(key, "a2");
        System.out.println(map.get(key));

//        map.keySet().stream()
//        for loop => map.size()
//        for (String s : map.keySet()) {
//
//        }

//        for (String s : map.values()) {
//
//        }

//        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
//
//        }


//        Read more: http://javarevisited.blogspot.com/2010/10/what-is-problem-while-using-in.html#ixzz3lmaVqgXz
    }
}
