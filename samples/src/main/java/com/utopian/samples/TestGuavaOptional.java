package com.utopian.samples;

import com.google.common.base.Optional;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by garamasu on 2015-11-30.
 */
public class TestGuavaOptional {

    @Test
    public void testOptional() {
        MgdDeviceId deviceId1 = new MgdDeviceId(Optional.absent(), Optional.absent(), Optional.absent());
        MgdDeviceId deviceId2 = new MgdDeviceId(Optional.absent(), Optional.absent(), Optional.absent());

        assertThat(deviceId1, is(equalTo(deviceId2)));
    }

}
