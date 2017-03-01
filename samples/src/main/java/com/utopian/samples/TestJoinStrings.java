package com.utopian.samples;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by garamasu on 2015-11-25.
 */
public class TestJoinStrings {

    @Test
    public void join_strings_prefix_suffix () {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        String joinedStrings = sj.add("test").add("whatever").toString();
        assertThat(joinedStrings, is("[test,whatever]"));
    }

    @Test
    public void replaceNull() {
        List<String> strings = Arrays.asList("oui1", null, "sn1", null);
        String collect = strings.stream().filter(Objects::nonNull).collect(Collectors.joining("-"));
        assertThat(collect, is(equalTo("oui1-sn1")));
    }

    @Test
    public void join_a_list_of_strings_replacing_null() {

        List<String> cloudGroups = new ArrayList<>();
        cloudGroups.add("Cirrus");
        cloudGroups.add("Alto");
        cloudGroups.add(null);
        cloudGroups.add(null);
        cloudGroups.add("Stratus");
        cloudGroups.add("Vertical Growth");
        cloudGroups.add("Special Clouds");
        cloudGroups.add(null);

        Function<String, String> replaceNull = new Function<String, String>() {
            @Override
            public String apply(String t) {
                if (t == null) {
                    return "[unknown]";
                } else {
                    return t;
                }
            }
        };

        String cloudGroupsJoined = cloudGroups.stream()
                // .map(replaceNull)
                 .map(s -> (s == null ? "[unknown]": s))
                .collect(Collectors.joining(" and "));

        assertThat(cloudGroupsJoined, is("Cirrus and Alto and [unknown] and [unknown] "
                + "and Stratus and Vertical Growth "
                + "and Special Clouds and [unknown]"));
    }
}
