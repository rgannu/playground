package com.utopian.camel.spring.boot;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = SampleApplication.class)
public class SpringBootRouterTest extends Assert {

  @Autowired
  CamelContext camelContext;

  @Test
  public void shouldProduceMessages() throws InterruptedException {
    // we expect that a number of messages is automatic done by the Camel
    // route as it uses a timer to trigger
    NotifyBuilder notify = new NotifyBuilder(camelContext).whenDone(4).create();

    assertTrue(notify.matches(10, TimeUnit.SECONDS));
  }

}
