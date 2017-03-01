package com.utopian.samples;

import com.google.common.base.Objects;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;

/**
 * Created by garamasu on 2015-12-04.
 */
public class TestHashmapHamcrest {

    @SuppressWarnings("unchecked")
    @Test
    public void mapTest() {
        Map<String, List<MyItem>> map = new HashMap<String, List<MyItem>>();
        map.put("one", asList(new MyItem("1"), new MyItem("one")));
        map.put("two", asList(new MyItem("2")));

        assertThat(map.get("one"), hasItems(is(new MyItem("one")), is(new MyItem("1"))));
        assertThat(map.get("two"), hasItems(is(new MyItem("2"))));

//        assertThat(map, hasEntry(is("one"),
//                containsInAnyOrder(hasItems(is(new MyItem("one"))),
//                        hasItems(is(new MyItem("1")))));

//        assertThat(map, hasEntry(is("one"),
//                containsInAnyOrder(hasProperty("name", is(new MyItem("one"))),
//                        hasProperty("name", is(new MyItem("1"))))));

//        assertThat(map, hasListEntry(is("one"), containsInAnyOrder("one", "1")));
    }

    @SuppressWarnings("unchecked")
    public static org.hamcrest.Matcher<Map<String, Object>> hasListEntry(org.hamcrest.Matcher<String> keyMatcher, org.hamcrest.Matcher<Iterable<?>> valueMatcher) {
        Matcher mapMatcher = org.hamcrest.collection.IsMapContaining.<String, List<?>>hasEntry(keyMatcher, valueMatcher);
        return mapMatcher;
    }

    class MyItem {
        String item;

        public MyItem(String item) {
            this.item = item;
        }

        public String getItem() {
            return item;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyItem myItem = (MyItem) o;
            return Objects.equal(item, myItem.item);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(item);
        }

        @Override
        public String toString() {
            return "MyItem{" +
                    "item='" + item + '\'' +
                    '}';
        }
    }
}
