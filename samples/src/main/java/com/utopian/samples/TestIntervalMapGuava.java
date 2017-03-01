package com.utopian.samples;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by garamasu on 2015-12-15.
 */
public class TestIntervalMapGuava {
    @Test
    public void google_guava_range_map_example () {

        RangeMap<Double, String> gradeScale = TreeRangeMap.create();
        gradeScale.put(Range.closed(0D, 60D), "F");
        gradeScale.put(Range.closed(61D, 70D), "D");
        gradeScale.put(Range.closed(71D, 80D), "C");
        gradeScale.put(Range.closed(81D, 90D), "B");
        gradeScale.put(Range.closed(91D, 100D), "A");

        String grade = gradeScale.get(77.5);

        assertThat(grade, org.hamcrest.core.Is.is("C"));
    }

    @Test
    public void testRangeMap() {
        RangeMap<Double, String> interferenceMap = TreeRangeMap.create();

        // x <= 50 => "weak",
        // 50 < x <=70 => "medium",
        // 70 < x <=100 => "ok"
        // x > 100 => "no score"
        interferenceMap.put(Range.atMost(50D), "weak");
        interferenceMap.put(Range.openClosed(50D, 70D), "medium");
        interferenceMap.put(Range.openClosed(70D, 100D), "ok");
        interferenceMap.put(Range.greaterThan(100D), "no score");

        assertThat(interferenceMap.get(-1D), is("weak"));
        assertThat(interferenceMap.get(20D), is("weak"));
        assertThat(interferenceMap.get(50D), is("weak"));
        assertThat(interferenceMap.get(60D), is("medium"));
        assertThat(interferenceMap.get(70D), is("medium"));
        assertThat(interferenceMap.get(71D), is("ok"));
        assertThat(interferenceMap.get(100D), is("ok"));
        assertThat(interferenceMap.get(101D), is("no score"));
    }

    @Test
    public void testInterferenceScoringsMap() {
        RangeMap<Double, String> interferenceScoringsMap = TreeRangeMap.create();

        // strong <= -70 dBm, -70 < medium <= -30 dBm, -30 dBm < weak <= 0 dBm
        interferenceScoringsMap.put(Range.atMost(-70D), "strong");
        interferenceScoringsMap.put(Range.openClosed(-70D, -30D), "medium");
        interferenceScoringsMap.put(Range.openClosed(-30D, 0D), "weak");
        interferenceScoringsMap.put(Range.greaterThan(0D), "no score");

        assertThat(interferenceScoringsMap.get(-75.5D), is("strong"));
        assertThat(interferenceScoringsMap.get(-70D), is("strong"));

        assertThat(interferenceScoringsMap.get(-69.9D), is("medium"));
        assertThat(interferenceScoringsMap.get(-30D), is("medium"));

        assertThat(interferenceScoringsMap.get(-29.5D), is("weak"));
        assertThat(interferenceScoringsMap.get(-10D), is("weak"));
        assertThat(interferenceScoringsMap.get(-1D), is("weak"));
        assertThat(interferenceScoringsMap.get(0D), is("weak"));
        assertThat(interferenceScoringsMap.get(1D), is("no score"));
        assertThat(interferenceScoringsMap.get(5D), is("no score"));

    }
}
