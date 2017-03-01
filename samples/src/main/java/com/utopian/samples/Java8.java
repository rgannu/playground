package com.utopian.samples;

import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by garamasu on 2015-12-01.
 */
public class Java8 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
//        numbers.sort(Comparator.naturalOrder());
        numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

    }

    @Test
    public void arrayTest() {
        Integer[] channels = {1, 2, 3};
        List<Integer> networksWithChannels = new ArrayList<>();
        networksWithChannels.add(1);
        networksWithChannels.add(3);
        networksWithChannels.add(5);

        networksWithChannels.stream()
                .filter(isInsideBand(channels))
                .forEach(System.out::println);

//        Arrays.asList(channels).stream()
//                .forEach(System.out::println);
    }

    private static Predicate<Integer> isInsideBand(Integer[] channels) {
        return networkWithChannel -> Arrays.asList(channels).contains(networkWithChannel);
    }

    @Test
    public void testDuplicateInList() {
        List<String> homeIds = Arrays.asList("h1", "h1", "h1", "h1", "h1");

        System.out.println("All same ? " + (homeIds.stream().peek(System.out::println).distinct().limit(2).count() == 1));

        System.out.println("All unique ? " + noDupes(new String[]{"Banana", "Apple", "Orange",
                "Banana"}));
        System.out.println("All unique ? " + allUnique(new String[]{"Banana", "Apple", "Orange",
                "Banana"}));
    }

    boolean noDupes(Object[] array) {
        return Arrays.stream(array).allMatch(new HashSet<>()::add);
    }

    static boolean allUnique(String[] strings) {
        HashSet<String> set = new HashSet<>();
        for (String s : strings) {
            if (! set.add(s)) {
                return false;
            }
        }
        return true;
    }
}
