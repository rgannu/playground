package com.utopian.samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by garamasu on 20-Aug-15.
 */
public class TestHashmap {
	public static void main(String[] args) {
		Map<String, List<String>> map = new HashMap<>();

		map.getOrDefault("a", new ArrayList<String>()).add("v1");
		System.out.println(map);


        Object i1 = new Integer(10);
        Object i2 = new Integer(20);

        Long param1 = (long) ((Integer) i1).intValue();
        Long param2 = (long) ((Integer) i2).intValue();
        Integer x = function1(param1, param2).intValue();

        Integer x1 = (int) (function2(((Integer) i1).intValue(), ((Integer) i2).intValue()));
        System.out.println(x);

//
//        long l1 = (long) ((Integer) i2).intValue();
//
//        Long normalized = (( -  ((Long)i1).longValue());
//        System.out.println(normalized);

    }

    public static Long function1(Long l1, Long l2) {
        return l1 - l2;
    }

    public static long function2(long l1, long l2) {
        return l1 - l2;
    }
}
