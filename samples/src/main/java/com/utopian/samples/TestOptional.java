package com.utopian.samples;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by garamasu on 19-Aug-15.
 */
public class TestOptional {

	@Test
	public void testOptional() {
        Optional<Double> avgLinkQuality = Optional.empty();
        Optional<Optional<Double>> avgLinkQuality1 = Optional.of(avgLinkQuality);
    }


}
