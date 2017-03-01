package com.utopian.samples;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by garamasu on 2016-10-02.
 */
public class TestVolatile {
    volatile String[] strArray = new String[] {"a", "b"};

    public static void main(String[] args) throws IOException {
        TestVolatile obj = new TestVolatile();
        /*Stream<String> lines =
                Files.lines(
                        Paths.get("files", "movies-mpaa.txt"),
                        Charset.forName("windows-1252")
                );*/


        Arrays.stream(obj.strArray).forEach(System.out::println);
//    movies.stream().Collectors.(s -> s.)



    }
}
