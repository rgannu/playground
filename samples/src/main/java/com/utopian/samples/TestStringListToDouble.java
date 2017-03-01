package com.utopian.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.DoubleStream;

/**
 * Created by garamasu on 17-Sep-15.
 */
public class TestStringListToDouble {
    public static void main(String[] args) {
        String str = "1, 2, 12, 5.5, 11";
        List<Double> doubleList = new ArrayList<>();
        Arrays.stream(str.split(","))
                .flatMapToDouble(s -> DoubleStream.of(Double.parseDouble(s.trim())))
                .forEach(doubleList::add);

//        forEach(s -> doubleList.add(Double.valueOf(s.trim())));
        System.out.println(Collections.max(doubleList));
    }
}
