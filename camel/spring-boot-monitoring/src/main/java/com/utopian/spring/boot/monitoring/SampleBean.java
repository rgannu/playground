package com.utopian.spring.boot.monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A bean that returns a message when you call the {@link #saySomething()} method.
 * <p/>
 * Uses <tt>@Component("myBean")</tt> to register this bean with the name <tt>myBean</tt>
 * that we use in the Camel route to lookup this bean.
 */
@Component("myBean")
public class SampleBean {

  @Value("${greeting}")
  private String say;

  public String saySomething() {
    getLogger().debug("DEBUG MESSAGE...");
    return say;
  }

  private Logger getLogger() {
    return LoggerFactory.getLogger(this.getClass());
  }
}
