package com.utopian.camel.spring.boot;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.stereotype.Component;

@Component
public class SpringBootRouter extends RouteBuilder {

//  @Autowired
//  private SampleBean sampleBean;

  @Autowired
  private HealthEndpoint health;

  @Override
  public void configure() {
    from("timer:trigger?period={{timer.period}}")
            .transform(method("myBean", "saySomething"))
//            .bean(sampleBean, "saySomething")
            .to("log:out");

    from("timer:status?period={{health.period}}")
            .bean(health, "invoke")
            .log("Health is ${body}");

  }
}
