package com.utopian.spring.boot.monitoring;

import com.utopian.spring.boot.monitoring.SampleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ganesh Ramasubramanian
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApplication.class)
public class SampleApplicationTest {
    @Test
    public void contextLoads() {
    }
}